package org.bitbashers.groovy.experiments.invokeMethod;

class InvokeMethodTest extends GroovyTestCase {
    
    void test_invoke_method() {
        
        assertEquals "intercepted someMethod [123]", new Foo().someMethod(123)
    }
}

class Foo {
    def invokeMethod(String name, args) {
        "intercepted ${name} ${args}"
    }
}