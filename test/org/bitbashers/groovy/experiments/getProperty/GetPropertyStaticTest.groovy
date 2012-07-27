package org.bitbashers.groovy.experiments.getProperty

class GetPropertyStaticTest extends GroovyTestCase {
  
  void test_intercepting_property_access_using_get_property() {
    
    //FooStatic.value = 123
    assertEquals 123, FooStatic.value
  }
}

class FooStatic {
  static internal_value

  static {
    FooStatic.metaClass.'static'.getProperty = { String name ->
      FooStatic."internal_${name}"
    }

    FooStatic.metaClass.'static'.setProperty = { String name, value ->
      println ">>> HERE"
      FooStatic."internal_${name}" = value
    }
  }
}
