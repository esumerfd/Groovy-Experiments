package org.bitbashers.groovy.experiments.metaclass.methods

import org.junit.After
import org.junit.Before
import org.junit.Test

class MetaClassInstanceReferenceTest {
  
  @Before
  void before() {
    List.metaClass = null
  }
  
  @After
  void after() {
    List.metaClass = null
  }
  
  @Test
  void should_reference_an_instance_variable() {
    
    List.metaClass.newMethod = { size() }
    
    assert 0 == [].newMethod()
  }
  
  @Test
  void should_call_overridden_methods() {
    List.metaClass.getIndex = { index -> getAt(index) }
    
    assert 1 == [1, 2, 3].getIndex(0)
    assert 2 == [1, 2, 3].getIndex(1)
    assert 3 == [1, 2, 3].getIndex(2)
  }


}

