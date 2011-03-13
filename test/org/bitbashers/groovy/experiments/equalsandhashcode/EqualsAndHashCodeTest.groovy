package org.bitbashers.groovy.experiments.equalsandhashcode

import groovy.transform.EqualsAndHashCode;
import groovy.util.GroovyTestCase;

class EqualsAndHashCodeTest extends GroovyTestCase {
  
  void test_equality() {
    def thingFred1 = new EqualsAndHashCodeThingy(name: "Fred")
    def thingFred2 = new EqualsAndHashCodeThingy(name: "Fred")
    
    def thingNotFred = new EqualsAndHashCodeThingy(name: "NOT FRED")
    
    assertEquals thingFred1, thingFred2
    
    assertEquals false, thingFred1.equals(thingNotFred)
  }
  
  void test_hashCode() {
    
    assertEquals new EqualsAndHashCodeThingy(name: "Fred").hashCode(), new EqualsAndHashCodeThingy(name: "Fred").hashCode()
    
    assert new EqualsAndHashCodeThingy(name: "Fred").hashCode() != new EqualsAndHashCodeThingy(name: "NOT FRED").hashCode()
  }
}

//@groovy.transform.EqualsAndHashCode
@EqualsAndHashCode
class EqualsAndHashCodeThingy { 
  String name
}
