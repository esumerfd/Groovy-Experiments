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
    def getProperty(String name) {
        value
    }
    void setProperty(String name, value) {
        this.value = value
    }
}
