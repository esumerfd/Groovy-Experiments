package org.bitbashers.groovy.experiments.http.builder

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*

class HTTPBuilderTest extends GroovyTestCase {
    
    def serverThread
    def still_running = true
    
    def response = "Hello World"
    
    void setUp() {
        serverThread = Thread.start()  {
            def server = new ServerSocket(5000)
            server.setSoTimeout(1000)
            try {
                while(still_running) {
                    server.accept() { socket ->
                        socket.withStreams { input, output ->
                            
                            output.withWriter { writer ->
                                writer << "HTTP/1.1 200 OK\n"
                                writer << "Content-Type: text/html\n\n"
                                writer << "${response}"
                            }
                        }
                    }
                }
            }
            catch (anything) {
                // Just finish the thread.
            }
        }
    }
    
    void tearDown() {
        still_running = false
        serverThread.join()
    }
    
    void test_google_search() {
        
        def http = new HTTPBuilder("http://localhost:5000")
        def html = http.get( path : '/search', query : [q:'Groovy'] ) { resp, reader ->
            
            assert resp.success
            assertEquals "Hello World", reader.text()
        }
    }
    
    void test_xml_request_response() {
        
        response = "<a>ABC<b>123</b><c>456</c></a>"
        
        def http = new HTTPBuilder("http://localhost:5000")
        def resp = http.request( POST, XML) {
            body = {
                request: { requestId: "1234"
                }
            }
            
            response.success = { resp, xml ->
                println "Status: ${resp.statusLine}"
                println "content type: ${resp.headers.'Content-Type'}"
                println ">>>${xml.b.text}<<<"
            }
            
            response.failure = { resp -> println "ERROR: >>>${resp}<<<" }
        }
    }
    
    void DISABLE_test_twitter_user_timeline() {
        def http = new HTTPBuilder( 'http://twitter.com/statuses/' )
        
        http.get( path: 'user_timeline.xml', query:[id:'twitter'] ) { resp, xml ->
            xml.status.each { println "${it.created_at}: ${it.text.text()}" }
        }
    }
    
    void DISABLE_test_twitter_user_timeline_without_closure() {
        def twitter = new RESTClient( 'https://twitter.com/statuses/' )
        
        def resp = twitter.get( path : 'user_timeline.xml', query:[id:'twitter'], contentType : TEXT,
                headers : [Accept : 'application/xml'] )
        
        println resp.data.text       // print the XML
    }
}