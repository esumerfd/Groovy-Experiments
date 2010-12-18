package org.bitbashers.groovy.experiments.propertyMissing


import groovy.util.GroovyTestCase;

class MissingPropertyTest extends GroovyTestCase {
    
    void test_missing_property_intercepted_with_local_propertyMissing_method() {
        
        assertEquals "missed property", new PropertyMissingFoo().some_property
    }
}

class PropertyMissingFoo {
    def propertyMissing(name) {
        "missed property"
    }
}
