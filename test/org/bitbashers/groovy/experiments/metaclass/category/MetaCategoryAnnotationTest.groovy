package org.bitbashers.groovy.experiments.metaclass.category

class MetaCategoryAnnotationTest extends GroovyTestCase {
  
  void test_using_scoped_use() {
    
    use(BarMix) {    
      assertEquals "mixed in", new Bar().mixedIn()
    }
  }
  
  void test_method_not_included_if_no_use() {
    
    assertEquals "original method", new Bar().mixedIn()
  }
}

class Bar {
  def mixedIn() {
    "original method"
  }
}

@Category(Bar)
class BarMix {
  def mixedIn() {
    "mixed in"
  }
}
