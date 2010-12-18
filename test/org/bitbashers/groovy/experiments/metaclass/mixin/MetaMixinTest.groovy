package org.bitbashers.groovy.experiments.metaclass.mixin
;

import groovy.util.GroovyTestCase;

class MetaMixinTest extends GroovyTestCase {
	
	void test_mixin() {
		
		assertEquals "mixed in", new Foo().mixedIn()
	}
}

@Mixin(FooMix)
class Foo {
}

class FooMix {
	static mixedIn() {
		"mixed in"
	}
}
