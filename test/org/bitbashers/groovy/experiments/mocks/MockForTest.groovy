package org.bitbashers.groovy.experiments.mocks;

import junit.framework.AssertionFailedError;
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

  void test_create_a_spy_by_mocking_one_method_and_ignoring_others() {
    def mock = new MockFor(MockForTestsClass)
    mock.ignore.amethod()
    mock.demand.amethodwithparameters { a -> "from mock with parameters ${a}" }
    
    mock.use {
      assertEquals "original method", new MockForTestsClass().amethod()
      assertEquals "from mock with parameters 123",
          new MockForTestsClass().amethodwithparameters(123)
    }
  }

  void test_create_a_spy_wtih_a_wildcard_ignore_pattern() {
    def mock = new MockFor(MockForTestsClass)
    mock.ignore(~/.*method/)
    mock.demand.amethodwithparameters { a -> "from mock with parameters ${a}" }
    
    mock.use {
      assertEquals "original method", new MockForTestsClass().amethod()
      assertEquals "original static method", MockForTestsClass.astaticmethod()
      assertEquals "from mock with parameters 123",
          new MockForTestsClass().amethodwithparameters(123)
    }
  }

  /*
   * This demonstrates that you can not do an "ignore everything except" pattern without
   * specifying all the methods to ignore specifically.
   */
  void test_create_a_spy_noting_that_ignore_override_demand() {
    def mock = new MockFor(MockForTestsClass)
    mock.ignore(~/.*/)
    mock.demand.amethodwithparameters { a -> "from mock with parameters ${a}" }

    // Assertion claims that amethodwithparameters was never called.
    shouldFail AssertionFailedError, {

      mock.use {
        new MockForTestsClass().amethodwithparameters(123)
      }
    }
  }

  void test_mock_single_method_using_demand_with_and_use_as_category() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.with {
      amethod() { "from mock"}
    }

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
   * The bug is that you can not mock the "find" method unless you pass in the
   * cardinality as well.
   */
  void test_mocking_a_method_called_find_is_a_special_case() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.find(1) { "cardinality 1" }
    mock.use {
      assertEquals "cardinality 1", MockForTestsClass.find()
    }
  }

  /*
   * This illustrates a down side of MockFor. Despite the fact that the MockFor constructor
   * is passed the type that it is replacing it does no verification that the demands actually
   * match the signatures of the methods in the real implementation.
   */
  void test_methods_do_not_have_to_exist_on_a_class_for_them_to_be_mocked() {

    def mock = new MockFor(Object)
    mock.demand.aMethodThatDoesNotExist { "SEE IT WORKS ANYWAY" }

    mock.use {
      assert "SEE IT WORKS ANYWAY" == new Object().aMethodThatDoesNotExist()
    }
  }

  /*
   * This illustrates a down side of MockFor. Despite the fact that the MockFor constructor
   * is passed the type that it is replacing it does no verification that the demands actually
   * match the signatures of the methods in the real implementation.
   */
  void test_demand_the_wrong_number_of_arguments_is_allowed() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.amethodwithparameters { a, extraArg -> "blindly called, changes in implementation ignored" }

    mock.use {
      assertEquals "blindly called, changes in implementation ignored",
          new MockForTestsClass().amethodwithparameters(123, 123)
    }
  }

  void test_mock_single_method_and_use_proxy() {
    def mock = new MockFor(MockForTestsClass)
    mock.demand.amethod { "from mock with proxy"}

    def proxy = mock.proxyInstance()
    assertEquals "from mock with proxy", proxy.amethod()
    mock.verify proxy
  }

  void test_create_proxy_instance_with_constructor_arguments() {
    def mock = new MockFor(MockForTestClassWithConstructorArgs)
    mock.demand.amethod { "from mock with proxy"}

    def proxy = mock.proxyInstance(["value1", "value2"]as Object[])
    assertEquals "from mock with proxy", proxy.amethod()
    mock.verify proxy
  }

  void test_mocking_a_constructor_call() {
    def mock = new MockFor(MockForTestsClass, true)
    mock.demand.with {
      MockForTestsClass() {  throw new Exception("YES IT WORKED") }
    }

    mock.use {
      shouldFail Exception, { new MockForTestsClass() }
    }
  }

  /*
   * Constructor mocking requires that an instance of a class is returned
   * and that instance can be cast to the mocked type.
   */
  void test_mock_a_constructor_that_gets_used() {
    def mock = new MockFor(MockForTestsClass, true)
    mock.demand.with {
      MockForTestsClass() { new Object() as GroovyObject }
    }

    mock.use {  new MockForTestsClass() }
  }

  /*
   * Constructor mocking requires that an instance of a class is returned
   * and that instance can be cast to the mocked type. This can be done
   * using a map but the map must be constructed outside the demand for the 
   * constructor of a recursive call will ensue.
   */
  void test_mock_a_constructor_that_gets_used_with_a_hash_map() {
    def myProxy = [amethod: { println "a proxy method" } ] as MockForTestsClass
    
    def mock = new MockFor(MockForTestsClass, true)
    mock.demand.MockForTestsClass() { myProxy }
    mock.ignore.amethod()

    mock.use { 
      new MockForTestsClass().amethod()
    }
  }

  /*
   * If you return the wrong type from this mocked constructor you will get
   * an error like this which is a little hard to work out.
   * 
   *    java.lang.ClassCastException: java.lang.String cannot be cast to groovy.lang.GroovyObject
   */
  void test_mock_a_constructor_that_returns_the_wrong_type() {
    def mock = new MockFor(MockForTestsClass, true)
    mock.demand.with {
      MockForTestsClass() { "THIS IS THE WRONG TYPE BEING RETURNED" }
    }

    shouldFail ClassCastException, {
      mock.use {  new MockForTestsClass() }
    }
  }

  /*
   * To demand more interactions with the object beyond the 
   * constructor the mocked constructor most return the proxy
   * instance instead.
   * 
   * NOTE: MockFor constructs an instance of the real class when
   * creating the proxy instance. This will require that the
   * required parameters are passed in to the proxyInstance
   * call for forwarding to the real constructor.
   */
  void test_mock_a_constructor_that_returns_a_proxy_instance() {
    def mock = new MockFor(MockForTestsClass, true)

    def theProxyInstance = mock.proxyInstance()

    mock.demand.with {
      MockForTestsClass() { theProxyInstance }
      amethod() { }
    }

    mock.use {
      def testClass= new MockForTestsClass()
      testClass.amethod()
    }
  }
}

class MockForTestsClass {

  def amethod() {
    "original method"
  }

  static def astaticmethod() {
    "original static method"
  }

  def amethodwithparameters(message) {
    "original method with parameters"
  }
}

class MockForTestClassWithConstructorArgs extends MockForTestsClass {
  def arg1
  def arg2
  MockForTestClassWithConstructorArgs(arg1, arg2) {
    this.arg1 = arg1
    this.arg2 = arg2
  }
}