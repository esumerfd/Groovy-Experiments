package org.bitbashers.groovy.experiments.metaclass.delegate;

import groovy.util.GroovyTestCase;

class CustomConstructorTest extends GroovyTestCase {
  
  void test_replace_constructor() {
    
    def customType = new CustomConstructor()

    assert customType != null
    assert (customtype instanceof CustomConstructor)
  }
}

class CustomConstructorMetaClass extends DelegatingMetaClass {
  CustomConstructorMetaClass(MetaClass meta) {
    super(meta)
  }

  public Object invokeConstructor(Object[] arguments) {
    println "arguments: ${arguments}"
    return delegate.invokeConstructor(arguments);
  }
}

class CustomConstructor {
  CustomConstructor() {
    println "Original Construtor"
  }
}
