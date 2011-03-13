package org.bitbashers.groovy.experiments.toString

import groovy.transform.ToString 
import groovy.util.GroovyTestCase;

class ToStringTest extends GroovyTestCase {
  
  void test_to_string_default() {
    def foo = new Foo(name: "Fred", age: 21)
    assertEquals "org.bitbashers.groovy.experiments.toString.Foo(Fred, 21)", foo.toString()
  }
  
  void test_to_string_with_names_and_fields() {
    def foo = new Foo(name: "Fred", age: 21)
    assertEquals "org.bitbashers.groovy.experiments.toString.Foo(Fred, 21)", foo.toString()
  }
}

@ToString
class Foo {
  def name
  def age
}

class Jim {
  def parentsName
}

@ToString(includeFields = true, includeNames = true, includeSuper = true)
class Bar extends Jim {
  def name
  def age
}