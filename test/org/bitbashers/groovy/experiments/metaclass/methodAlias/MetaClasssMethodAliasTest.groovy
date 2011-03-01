package org.bitbashers.groovy.experiments.metaclass.methodAlias

import org.codehaus.groovy.runtime.MethodClosure 

class MetaClasssMethodAliasTest extends GroovyTestCase {
  
  void test_replacing_method_and_delegating_to_original_version_using_java_method() {
    
    def existingMethod = Foo1.class.getMethod("existingMethod")
    Foo1.metaClass.originalExistingMethod = { existingMethod.invoke(delegate) }
    
    Foo1.metaClass.existingMethod = { ">>> ${delegate.originalExistingMethod()} <<<" }
    
    assertEquals ">>> Original Method <<<", new Foo1().existingMethod()
  }
  
  void test_replacing_method_and_delegating_to_original_version_using_groovy_ampersand() {
    
    Foo2.metaClass.originalExistingMethod = new Foo2().&existingMethod        
    
    Foo2.metaClass.existingMethod = { ">>> ${delegate.originalExistingMethod()} <<<" }
    
    assertEquals ">>> Original Method <<<", new Foo2().existingMethod()
  }
  
  void test_replacing_method_and_delegate_to_original_using_method_closure() {
    
    shouldFail IllegalArgumentException, {
      
      Foo3.metaClass.originalExistingMethod = new MethodClosure(Foo3, "existingMethod")
      
      Foo3.metaClass.existingMethod = { ">>> ${delegate.originalExistingMethod()} <<<" }
      
      assertEquals ">>> Original Method <<<", new Foo3().existingMethod()
    }
  }
}

class Foo1 {
  def existingMethod() {
    "Original Method"
  }
}
class Foo2 {
  def existingMethod() {
    "Original Method"
  }
}
class Foo3 {
  def existingMethod() {
    "Original Method"
  }
}
