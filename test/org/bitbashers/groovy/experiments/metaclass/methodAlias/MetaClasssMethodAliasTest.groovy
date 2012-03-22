package org.bitbashers.groovy.experiments.metaclass.methodAlias

import org.codehaus.groovy.runtime.MethodClosure

class MetaClasssMethodAliasTest extends GroovyTestCase {
  
  void setUp() {
    Foo.metaClass = null
  }

  void tearDown() {
    Foo.metaClass = null
  }

  void test_replacing_method_and_delegating_to_original_version_using_java_method() {

    def existingMethod = Foo.class.getMethod("existingMethod")
    Foo.metaClass.originalExistingMethod = { existingMethod.invoke(delegate) }

    Foo.metaClass.existingMethod = { ">>> ${delegate.originalExistingMethod()} <<<" }

    assertEquals ">>> Original Method <<<", new Foo().existingMethod()
  }

  void test_replacing_method_and_delegating_to_original_version_using_groovy_ampersand() {

    Foo.metaClass.originalExistingMethod = new Foo().&existingMethod

    Foo.metaClass.existingMethod = { ">>> ${delegate.originalExistingMethod()} <<<" }

    assertEquals ">>> Original Method <<<", new Foo().existingMethod()
  }

  void test_replacing_method_and_delegating_to_original_version_using_groovy_getmethod() {

    Foo.metaClass.originalExistingMethod = new Foo().metaClass.getMetaMethod('existingMethod')

    Foo.metaClass.existingMethod = { ">>> ${delegate.originalExistingMethod.invoke(delegate)} <<<" }

    assertEquals ">>> Original Method <<<", new Foo().existingMethod()
  }
  
  void test_should_prove_that_meta_method_is_not_the_same_instance() {
    assert !new Foo().&existingMethod.is(new Foo().&existingMethod)
  }
  
  void test_meta_class_changes_are_specific_to_an_instance() {
    def foo = new Foo()
    foo.metaClass.existingMethod = { "New Method" }
    
    assert foo.existingMethod() == "New Method"
    assert new Foo().existingMethod() == "Original Method"
  }
}

class Foo {
  def existingMethod() {
    "Original Method"
  }
}
