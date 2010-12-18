package org.bitbashers.groovy.experiments.metaclass.methods

class MetaClassAssignmentTest extends GroovyTestCase {
    
    void test_adding_new_method() {
        Foo.metaClass.newMethod = { "new method" }
        
        assertEquals "new method", new Foo().newMethod()
    }
    
    void test_replacing_existing_method() {
        Foo.metaClass.existingMethod = { "replaced existing" }
        
        assertEquals "replaced existing", new Foo().existingMethod()
    }
    
    void test_override_method() {
        Foo.metaClass.baseMethod =	{ "replaced based method" }
        
        assertEquals "replaced based method", new Foo().baseMethod()
    }
    
    void test_cross_test_polution_may_be_an_issue() {
        
        assertEquals "new method", new Foo().newMethod()
    }
}

class FooBase {
    def baseMethod() {
        "Original base method"
    }
}
class Foo extends FooBase {
    def existingMethod() {
        "Original Method"
    }
}