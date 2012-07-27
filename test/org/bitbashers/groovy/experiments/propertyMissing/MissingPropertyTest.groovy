package org.bitbashers.groovy.experiments.propertyMissing

import groovy.util.GroovyTestCase;

class MissingPropertyTest extends GroovyTestCase {
  
  void test_get_missing_property_intercepted_with_local_propertyMissing_method() {
    assertEquals "missed property ''", new PropertyMissingFoo().some_property
  }

  void test_set_missing_property_intercepted_with_local_propertyMissing_method() {
    def foo = new PropertyMissingFoo()
    foo.some_property = "VALUE"
    assertEquals "missed property 'VALUE'", foo.some_property
  }
}

class PropertyMissingFoo {
  def internalProperty = ""

  def propertyMissing(name) {
    "missed property '${internalProperty}'"
  }
  def propertyMissing(name, value) {
    internalProperty = value
  }
}
