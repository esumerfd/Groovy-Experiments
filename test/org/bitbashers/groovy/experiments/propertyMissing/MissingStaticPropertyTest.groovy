package org.bitbashers.groovy.experiments.propertyMissing

import groovy.util.GroovyTestCase;

class MissingStaticPropertyTest extends GroovyTestCase {
  
  void test_get_missing_static_property_intercepted_with_local_propertyMissing_method() {
    assertEquals "missed property 'unset'", StaticPropertyMissingFoo.some_property
  }

  void DOES_NOT_WORK_test_set_missing_static_property_intercepted_with_local_propertyMissing_method() {
    StaticPropertyMissingFoo.some_property
    assertEquals "missed property 'fred'", StaticPropertyMissingFoo.some_property
  }
}

class StaticPropertyMissingFoo {
  static internalValue = "unset"
  static {
    StaticPropertyMissingFoo.metaClass.static.propertyMissing << { String name ->
      "missed property '${internalValue}'"
    }
  }
}
