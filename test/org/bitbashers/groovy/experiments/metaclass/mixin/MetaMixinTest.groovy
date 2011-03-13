package org.bitbashers.groovy.experiments.metaclass.mixin

import groovy.util.GroovyTestCase;

class MetaMixinTest extends GroovyTestCase {
  
  void test_mixin() {
    
    assertEquals "mixed in", new Foo().mixedIn()
    assertEquals "a mixed property", new Foo().mixedProperty
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
}
