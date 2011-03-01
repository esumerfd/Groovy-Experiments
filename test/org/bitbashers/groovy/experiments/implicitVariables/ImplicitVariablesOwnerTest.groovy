package org.bitbashers.groovy.experiments.implicitVariables;

class ImplicitVariablesOwnerTest extends GroovyTestCase {
  
  void test_owner_same_as_parent_this() {
    
    def testInstance = this
    
    def closure = {
      assertEquals testInstance, this
      assertEquals this, owner
    }
    
    closure()
  }
  
  void test_delegate_defaults_to_parent_this() {
    
    def testInstance = this
    
    def closure = {
      assertEquals testInstance, this
      assertEquals this, delegate
    }
    
    closure()
  }
  
  void test_delegate_reassigned_to_affect_closure_this() {
    
    def alternateContext = "some object"
    
    def closure = {
      delegate = alternateContext
      
      assertEquals "SOME OBJECT", toUpperCase()
    }
    
    closure()
  }
}
