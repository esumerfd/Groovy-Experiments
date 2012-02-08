package org.bitbashers.groovy.experiments.mocks;

import static org.junit.Assert.*
import groovy.mock.interceptor.MockFor

import org.junit.Test

class A {}
class B {}

class MockForListTest {

  static {
    List.metaClass.asMock = { closure, index = 0 ->
      if (index < size()) {
        getAt(index).use {
          asMock(closure, ++index)
        }
      }
      else {
        closure()
      }    
    }
  }
  
  @Test
  void should_mock_using_an_entry_in_a_list() {
    def aMock = new MockFor(A)
    aMock.demand.toString { "a mock" }
    
    [aMock].asMock {
      assert "a mock" == new A().toString()
    }
  }

  @Test
  void should_mock_using_two_entries_in_a_list() {
    def aMock = new MockFor(A)
    aMock.demand.toString { "a mock" }

    def bMock = new MockFor(B)
    bMock.demand.toString { "b mock" }

    [aMock, bMock].asMock {
      assert "a mock" == new A().toString()
      assert "b mock" == new B().toString()
    }
  }
}

