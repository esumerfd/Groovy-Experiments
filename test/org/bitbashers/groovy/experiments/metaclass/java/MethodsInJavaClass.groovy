package org.bitbashers.groovy.experiments.metaclass.java

class MethodsInJavaClassTest extends GroovyTestCase {
  
  void test_add_groovy_method_to_java_class() {
      Date.metaClass.formatGmtMillis = {
      }

      println new Date().formatGmtMillis()
  }
}
