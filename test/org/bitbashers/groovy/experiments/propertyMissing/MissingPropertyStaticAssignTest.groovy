package org.bitbashers.groovy.experiments.propertyMissing

import groovy.util.GroovyTestCase;

class MissingPropertyStaticAssignTest extends GroovyTestCase {
    
    void test_missing_property_from_meta() {
        
        assertEquals "missed property", new PropertyMissingStatisAssignFoo().some_property
    }
}

class PropertyMissingStatisAssignFoo {
    static {
        PropertyMissingStatisAssignFoo.metaClass.propertyMissing = { name -> "missed property" }
    }
}
