package org.bitbashers.groovy.experiments.metaclass.delegate;

import groovy.util.GroovyTestCase;

class CustomConstructorTest extends GroovyTestCase {
  
  void test_constructor_baseline() {
    
    def customType = new CustomConstructor()

    assert (customType instanceof CustomConstructor)
    
    assert customType.message == "Original Construtor"
  }

  void test_replace_constructor() {
    CustomConstructor.metaClass = new CustomConstructorMetaClass(CustomConstructor.metaClass)

    def customType = new CustomConstructor()

    assert (customType instanceof CustomConstructor)

    assert customType.message == "Custom Constructor"
  }
}

class CustomConstructorMetaClass extends DelegatingMetaClass {
  CustomConstructorMetaClass(MetaClass meta) {
    super(meta)
  }

  public Object invokeConstructor(Object[] arguments) {
    def instance = delegate.invokeConstructor(arguments);
    instance.message = "Custom Constructor"
    instance
  }
}

class CustomConstructor {
  def message
  CustomConstructor() {
    message = "Original Construtor"
  }
}
