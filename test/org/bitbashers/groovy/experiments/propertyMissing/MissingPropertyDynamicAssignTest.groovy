package org.bitbashers.groovy.experiments.propertyMissing


import groovy.util.GroovyTestCase;

class PropertyMissingDynamicAssign extends GroovyTestCase {
	
	void test_missing_property_intercepted_with_dynamic_assignment_of_propertyMissing() {
		
		PropertyMissingDynamicAssignFoo.metaClass.propertyMissing = { name -> "missed property" }
		
		assertEquals "missed property", new PropertyMissingDynamicAssignFoo().some_property
	}
}

class PropertyMissingDynamicAssignFoo {
}
