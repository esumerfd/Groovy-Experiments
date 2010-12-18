package org.bitbashers.groovy.experiments.getProperty

class GetPropertyTest extends GroovyTestCase {
    
    void test_intercepting_property_access_using_get_property() {
        
        def foo = new Foo()
        foo.some_property = 123
        assertEquals 123, foo.some_property
    }
}

class Foo {
    def value
    
    // Note use of String type is required
    def getProperty(String name) {
        value
    }
    
    // Note use of void return type and String type is required
    void setProperty(String name, value) {
        this.value = value
    }
}
