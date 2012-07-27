package org.bitbashers.groovy.experiments.invokeMethod;

class InvokeMethodTest extends GroovyTestCase {
  
  void test_invoke_method() {
    assertEquals "intercepted someMethod [123]", new Foo().someMethod(123)
  }
  
  void test_delegating_call() {
    assertEquals 3, new Bar().add(1, 2)
  }
}

class Foo {
  def invokeMethod(String name, args) {
    "intercepted ${name} ${args}"
  }
}

class Bar {
  def barDelegate = new BarDelegate()
  def invokeMethod(String name, args) {
    barDelegate."${name}"(*args)
  }
}

class BarDelegate {
  def add(number1, number2) {
    number1 + number2
  }
}
