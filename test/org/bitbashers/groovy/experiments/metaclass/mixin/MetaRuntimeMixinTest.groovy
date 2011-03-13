package org.bitbashers.groovy.experiments.metaclass.mixin

import groovy.util.GroovyTestCase;

class MetaRuntimeMixinTest extends GroovyTestCase {
  
  void test_mixin() {
    
    Bar.mixin BarMix
    
    assertEquals "mixed in", new Bar().mixedIn()
  }
  
  /*
   * MUST RUN BOTH TESTS FOR THIS TO WORK
   */
  void test_mixin_persistent_accross_jvm() {
    
    assertEquals "mixed in", new Bar().mixedIn()
  }
}

class Bar {
}

class BarMix {
  def mixedIn() {
    "mixed in"
  }
}
