package org.bitbashers.groovy.experiments.metaclass.methods

class DynamicMethodsTest extends GroovyTestCase {
    
    void test_call_defined_method() {
        def foo = new DynamicMethodsFoo()
        assertEquals "method a", foo.method_a()
        assertEquals "method b", foo.method_b()
        assertEquals "method c", foo.method_c()
    }
}

class DynamicMethodsFoo {
    def static names = ["a", "b", "c"]
    
    static {
        names.each { name ->
            DynamicMethodsFoo.metaClass."method_${name}" = { "method ${name}" }
        }
    }
}
