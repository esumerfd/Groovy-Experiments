package org.bitbashers.groovy.experiments.immutable;

import groovy.util.GroovyTestCase;

class CanonicalTest extends GroovyTestCase {
  
  void test_immutability() {
    def thingy = new CanonicalThingy(name: "Fred")
    
    assertEquals "Fred", thingy.name
  }
  //  
  //  void test_equality() {
  //    assertEquals new CanonicalThingy(name: "Fred"), new CanonicalThingy(name: "Fred")
  //    
  //    assert new CanonicalThingy(name: "Fred") != new CanonicalThingy(name: "NOT FRED")
  //  }
  //  
  //  void test_hashCode() {
  //    
  //    assertEquals new CanonicalThingy(name: "Fred").hashCode(), new CanonicalThingy(name: "Fred").hashCode()
  //    
  //    assert new CanonicalThingy(name: "Fred").hashCode() != new CanonicalThingy(name: "NOT FRED").hashCode()
  //  }
  //  
  //  void test_can_not_change() {    
  //    
  //    shouldFail ReadOnlyPropertyException, {
  //      def thingy = new CanonicalThingy(name: "Fred")
  //      thingy.name = "Someone else"
  //    }
  //  }
  //  
  //  void test_to_string() {
  //    
  //    assertEquals "org.bitbashers.groovy.experiments.immutable.CanonicalThingy(Fred)",
  //        new CanonicalThingy(name: "Fred").toString()
  //  }
  //  
  //  void test_two_property_constructor_relying_on_arg_sequence() {   
  //    assertEquals new CanonicalTwoProps(name:"a",age:1), new CanonicalTwoProps(name:"a",age:1)
  //  }
  //  
  //  void test_two_property_constructor_using_hash_constructor() {   
  //    assertEquals new CanonicalTwoProps(name:"a", age:1), new CanonicalTwoProps(age:1, name:"a")
  //  }
  //  
  //  void test_two_props_with_defaults_must_use_empty_hash_as_constructor() {
  //    def canonical = new CanonicalTwoPropsWithDefaults([:])
  //    
  //    assertEquals "Default Jamie", canonical.name
  //    assertEquals 10, canonical.age
  //  }
}
//
//@Canonical
class CanonicalThingy { 
  String name
}
//
//@Canonical
//class CanonicalTwoProps {
//  String name
//  Integer age
//}
//
//@Canonical
//class CanonicalTwoPropsWithDefaults {
//  String name = "Default Jamie"
//  Integer age = 10
//}
