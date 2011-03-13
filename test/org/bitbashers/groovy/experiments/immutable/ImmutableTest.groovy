package org.bitbashers.groovy.experiments.immutable;

import groovy.util.GroovyTestCase;

class ImmutableTest extends GroovyTestCase {
  
  void test_immutability() {
    def thingy = new ImmutableThingy(name: "Fred")
    
    assertEquals "Fred", thingy.name
  }
  
  void test_can_not_change() {    
    
    shouldFail ReadOnlyPropertyException, {
      def thingy = new ImmutableThingy(name: "Fred")
      thingy.name = "Someone else"
    }
  }
  
  void test_two_props_with_defaults_must_use_empty_hash_as_constructor() {
    def immutable = new ImmutableTwoPropsWithDefaults([:])
    
    assertEquals "Default Jamie", immutable.name
    assertEquals 10, immutable.age
  }
}

@groovy.transform.Immutable
class ImmutableThingy { 
  String name
}

@groovy.transform.Immutable
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

