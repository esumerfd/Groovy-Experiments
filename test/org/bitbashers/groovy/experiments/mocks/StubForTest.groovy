package org.bitbashers.groovy.experiments.mocks;

import groovy.mock.interceptor.*

class StubForTest extends GroovyTestCase {
  
  void test_stub_single_method_no_different_from_mock() {
    def mock = new StubFor(StubForTestsClass)
    mock.demand.amethod { "from stub" }
    mock.use {
      assertEquals "from stub", new StubForTestsClass().amethod()
    }
  }
  
  /*
   * While we can call stubbed methods many times we still have to specify
   * a cardinality higher than our expected call count.
   */
  void test_stub_single_method_but_call_it_many_times() {
    def mock = new StubFor(StubForTestsClass)
    mock.demand.amethod(9999) { "from stub" }
    mock.use {
      assertEquals "from stub", new StubForTestsClass().amethod()
      assertEquals "from stub", new StubForTestsClass().amethod()
      assertEquals "from stub", new StubForTestsClass().amethod()
      assertEquals "from stub", new StubForTestsClass().amethod()
      assertEquals "from stub", new StubForTestsClass().amethod()
      assertEquals "from stub", new StubForTestsClass().amethod()
    }
  }
  
  void test_stub_single_method_dont_call_it_and_expect_no_errors() {
    def mock = new StubFor(StubForTestsClass)
    mock.demand.amethod { "not called" }
    mock.use { new StubForTestsClass() }
  }
}

class StubForTestsClass {
  void amethod() {
    println "original method"
  }
}