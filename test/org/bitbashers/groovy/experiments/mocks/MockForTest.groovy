package org.bitbashers.groovy.experiments.mocks;

import grails.test.*
import groovy.mock.interceptor.*

class MockForTests extends GroovyTestCase {
  void test_mock_single_method_and_use_as_category() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.amethod { "from mock"}
    mock.use {
      assertEquals "from mock", new MockForTestsClass().amethod()
    }
  }
  
  void test_mock_single_static_method_and_use_as_category() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.astaticmethod { "from static mock"}
    mock.use {
      assertEquals "from static mock", MockForTestsClass.astaticmethod()
    }
  }
  
  void test_mock_single_method_and_use_proxy() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.amethod { "from mock with proxy"}
    
    def proxy = mock.proxyInstance()
    assertEquals "from mock with proxy", proxy.amethod()
    mock.verify proxy
  }
  
  void test_stub_single_method() {
    def mock = new StubFor(MockForTestsClass)
    mock.demand.amethod { "from stub" }
    mock.use {
      assertEquals "from stub", new MockForTestsClass().amethod()
    }
  }
  
  void test_stub_single_method_and_expect_no_errors() {
    def mock = new StubFor(MockForTestsClass)
    mock.demand.amethod { "not called" }
    mock.use { new MockForTestsClass() }
  }
  
  void test_mock_method_with_parameters() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.amethodwithparameters { a -> "from mock with parameters ${a}" }
    
    mock.use {
      assertEquals "from mock with parameters 123",
          new MockForTestsClass().amethodwithparameters(123)
    }
  }
  
  
  /*
   * This is a special case problem related to a bug. This proves the work around.
   */
  void test_mocking_a_method_called_find_is_a_special_case() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.find(1) { "cardinality 1" }
    mock.use {
      assertEquals "cardinality 1", MockForTestsClass.find()
    }
  }
}

class MockForTestsClass {
  void amethod() {
    println "original method"
  }
  
  static void astaticmethod() {
    println "original static method"
  }
  
  void amethodwithparameters(message) {
    println "original method with parameters"
  }
}