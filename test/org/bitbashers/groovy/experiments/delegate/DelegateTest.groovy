package org.bitbashers.groovy.experiments.delegate;

class DelegateTest extends GroovyTestCase {
    
    void test_delegate() {
        def foo = new FooProxy()
        
        assertEquals "default instance", foo.info()
    }
    
    void test_dynamic_delegation() {
        def foo = new FooProxy()
        foo.x = new Foo("dynamic foo instance")
        
        assertEquals "dynamic foo instance", foo.info()
    }
    
    void test_assigning_a_new_delegate_at_construction_time() {
        def foo = new FooProxy( new Foo("assigned in constructor") )
        
        assertEquals "assigned in constructor", foo.info()
    }
}

class Foo {
    def text
    Foo(text) {
        this.text = text
    }
    def info() {
        text
    }
}

class FooProxy {
    @Delegate Foo x = new Foo("default instance")
    
    FooProxy(newX) {
        x = newX
    }
}