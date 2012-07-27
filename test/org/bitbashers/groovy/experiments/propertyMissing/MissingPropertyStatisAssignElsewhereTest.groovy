package org.bitbashers.groovy.experiments.propertyMissing


import groovy.util.GroovyTestCase;

class MissingPropertyStatisAssignElsewhereTest extends GroovyTestCase {
  
  void test_missing_property_from_static_from_another_class() {
    
    /*
     * MUST REFERENCE CLASS FOR THE STATIC ASSIGNMENT TO TAKE PLACE
     */
    MissingPropertyStatisAssignElsewhereAssigner
    
    assertEquals "missed property", new MissingPropertyStatisAssignElsewhereFoo().some_property
  }
}

class MissingPropertyStatisAssignElsewhereFoo {
}

class MissingPropertyStatisAssignElsewhereAssigner {
  static {
    MissingPropertyStatisAssignElsewhereFoo.metaClass.propertyMissing = { name -> "missed property" }
  }
}
