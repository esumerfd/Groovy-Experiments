package org.bitbashers.groovy.experiments.invokeMethod;

import static org.junit.Assert.*
import org.junit.*
import groovy.mock.interceptor.*

class InvokeMethodStaticTest {

  @Test
  void should_invoke_static_method_on_meta_class() {
    assert "intercepted static someMethod [123]" == FooStatic.someMethod(123)
  }

  @Test
  void should_invoke_static_method() {
    assert "intercepted static someMethod [123]" == FooStaticMethod.someMethod(123)
  }
}

class FooStatic {
  static {
    FooStatic.metaClass.'static'.invokeMethod = { String name, args ->
      "intercepted static ${name} ${args}"
    }
  }
}

class FooStaticMethod {
  def invokeStaticMethod(String name, args) {
    "intercepted static ${name} ${args}"
  }
}
