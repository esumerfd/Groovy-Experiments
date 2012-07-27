package org.bitbashers.groovy.experiments.metaclass.mixin

import groovy.util.GroovyTestCase

class MetaMixinTest extends GroovyTestCase {
  
  void test_mixin() {
    
    assertEquals "mixed in", new Foo().mixedIn()
    assertEquals "a mixed property", new Foo().mixedProperty
  }
  
  void test_mixin_has_reference_to_this() {
    
    def foo = new Foo()

    assertEquals foo.class, foo.thisIs().class    
    assertTrue foo.thisIs().class.isAssignableFrom(Foo)
    
    assertFalse foo.thisIs() instanceof Foo
  }
}

@Mixin(FooMix)
class Foo {
}

class FooMix {
  def mixedProperty = "a mixed property"
  def mixedIn() {
    "mixed in"
  }
  def thisIs() {
    this
  }
}
