package org.bitbashers.groovy.experiments;

import static org.junit.Assert.*

import org.junit.Test

class OperatorsTest {

  @Test
  void should_override_plus_operator() {
    assert new Operators() + 1 == 11
  }
  
  @Test
  void should_override_minus_operator() {
    assert new Operators() - 1 == 9
  }

  @Test
  void should_index_a_class() {
    assert new Operators()[3] == 30
  }
 
  class Operators {
    def plus(otherNumber) {
      10 + otherNumber
    }
    
    def minus(otherNumber) {
      10 - otherNumber
    }

    def getAt(index) {
      10 * index
    }
  }

}
