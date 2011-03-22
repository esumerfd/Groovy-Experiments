package org.bitbashers.groovy.experiments.closures;

import groovy.util.GroovyTestCase;

class ClosureTest extends GroovyTestCase {
  
  void test_using_closure_as_iterator() {
    
    assertEquals "ABCDEF", apply { toUpperCase() }.join()
  }
  
  private apply(closure) {
    
    ("a".."f").collect { letter ->
      
      closure.delegate = letter
      closure()
    }
  }
}
