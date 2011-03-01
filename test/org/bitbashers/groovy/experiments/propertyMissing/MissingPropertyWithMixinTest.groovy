package org.bitbashers.groovy.experiments.propertyMissing

import groovy.util.GroovyTestCase;

class MissingPropertyWithMixinTest extends GroovyTestCase {
  
  void test_missing_property_intercepted_with_mixin_annotation() {
    
    assertEquals "missed property", new MissingPropertyWithMixinTestFoo().some_property
  }
}

@Mixin(MissingPropertyWithMixinTestMixin)
class MissingPropertyWithMixinTestFoo {
}

class MissingPropertyWithMixinTestMixin {
  def propertyMissing(name) {
    "missed property"
  }
}
