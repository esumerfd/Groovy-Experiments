package org.bitbashers.groovy.experiments.getProperty

class GetPropertyTest extends GroovyTestCase {
  
  void test_intercepting_property_access_using_get_property() {
    
    def foo = new Foo()
    foo.value = 123
    assertEquals 123, foo.value
  }
}

class Foo {
  private internal_value
  
  // Note use of String type is required
  // Note use of @ to limit scope
  def getProperty(String name) {
    this.@"internal_${name}"
  }
  
  // Note use of void return type and String type is required
  // Note use of @ to limit scope
  void setProperty(String name, value) {
    this.@"internal_${name}" = value
  }
}
