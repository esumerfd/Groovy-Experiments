package org.bitbashers.groovy.experiments.performance

class TypedArgumentsTest extends PerfFixture {
  
  void test_overloaded_methods() {
    def overload = new Overload()
    
    def i = 1        
    timeIt "overloaded method with types", {overload.go(i)}
  }
  
  void test_overload_alternative() {
    def instance = new OverloadAlternative()
    
    timeIt "overloaded alternative", { instance.go( {} )  }
  }
  
  void test_no_overloads() {
    def noOverload = new NoOverload()
    
    def i = 1
    timeIt "no overloaded methods no types", { noOverload.go(i) }
  }
  
  void test_no_overloads_but_with_a_type() {
    def instance = new NoOverloadWithAType()
    
    def i = 1
    timeIt "no overloaded methods with a type", { instance.go(i) }
  }
  
  void test_closure_no_types() {
    timeIt "closure no types", { { fred -> } }
  }
  
  void test_closure_types() {
    timeIt "closure with types", {  { String fred -> } }
  }
}

class Overload {
  def go(String s) {
  }
  def go(Integer i) {
  }
  def go(Date d) {
  }
}

class NoOverloadWithAType {
  def go(Integer i) {
  }
}

class NoOverload {
  def go(x) {
  }
}

class OverloadAlternative {
  def go(x) { x()
  }
}
