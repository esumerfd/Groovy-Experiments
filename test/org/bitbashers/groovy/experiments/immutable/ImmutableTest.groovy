package org.bitbashers.groovy.experiments.immutable;

import groovy.util.GroovyTestCase;

class ImmutableTest extends GroovyTestCase {
  
  void test_immutability() {
    def thingy = new ImmutableThingy("Fred")
    
    assertEquals "Fred", thingy.name
  }
  
  void test_equality() {
    assertEquals new ImmutableThingy("Fred"), new ImmutableThingy("Fred")
    
    assert new ImmutableThingy("Fred") != new ImmutableThingy("NOT FRED")
  }
  
  void test_hashCode() {
    
    assertEquals new ImmutableThingy("Fred").hashCode(), new ImmutableThingy("Fred").hashCode()
    
    assert new ImmutableThingy("Fred").hashCode() != new ImmutableThingy("NOT FRED").hashCode()
  }
  
  void test_can_not_change() {    
    
    shouldFail ReadOnlyPropertyException, {
      def thingy = new ImmutableThingy("Fred")
      thingy.name = "Someone else"
    }
  }
  
  void test_to_string() {
    
    assertEquals "org.bitbashers.groovy.experiments.immutable.ImmutableThingy(Fred)",
        new ImmutableThingy("Fred").toString()
  }
  
  void test_two_property_constructor_relying_on_arg_sequence() {   
    assertEquals new ImmutableTwoProps("a",1), new ImmutableTwoProps("a",1)
  }
  
  void test_two_property_constructor_using_hash_constructor() {   
    assertEquals new ImmutableTwoProps(name:"a", age:1), new ImmutableTwoProps(age:1, name:"a")
  }
  
  void test_two_props_with_defaults_must_use_empty_hash_as_constructor() {
    def immutable = new ImmutableTwoPropsWithDefaults([:])
    
    assertEquals "Default Jamie", immutable.name
    assertEquals 10, immutable.age
  }
}

@Immutable
class ImmutableThingy { 
  String name
}

@Immutable
class ImmutableTwoProps {
  String name
  Integer age
}

@Immutable
class ImmutableTwoPropsWithDefaults {
  String name = "Default Jamie"
  Integer age = 10
}

// COMPILE FAILURE
// ImmutableTest.groovy: 64: Explicit constructors not allowed for @Immutable class: ImmutableWithConstructor
//
//@Immutable
//class ImmutableWithConstructor {
//  String name
//  Integer age
//  ImmutableWithConstructor(name, age = 10) {
//    this.name = name
//    this.age = age
//  }
//}

