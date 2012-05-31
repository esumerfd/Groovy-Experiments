package org.bitbashers.groovy.experiments.metaclass.category

class MetaCategoryTest extends GroovyTestCase {
  
  void test_using_scoped_use() {
    
    use(FooMix) {            
      assertEquals "mixed in", new Foo().mixedIn()
    }
  }
}

class Foo {
}

class FooMix {
  static mixedIn(Foo) {
    "mixed in"
  }
}
