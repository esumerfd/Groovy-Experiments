package org.bitbashers.groovy.experiments.invokeMethod;

class InvokeMethodTest extends GroovyTestCase {
    
    void test_invoke_method() {
        
        assertEquals "intercepted", new Foo().someMethod()
    }
}

class Foo {
    def someMethod() {
        "intercepted"
    }
}