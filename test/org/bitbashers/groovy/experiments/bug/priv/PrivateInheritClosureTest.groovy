package org.bitbashers.groovy.experiments.bug.priv

class PrivateInheritClosure {

  static nonPrivateStatic = "nps"
  private static privateStatic = "ps"

  void processNonPrivate( ){
    println nonPrivateStatic
    [1].each {
      println "in closuer for processNonPrivate"
      println nonPrivateStatic
    }
  }

  void processPrivate( ){
    println privateStatic
    [1].each {
      println "in closure for processPrivate"
      println privateStatic
    }
  }
}

class PrivateInheritClosureTest extends groovy.util.GroovyTestCase{

  void testProcessNonPrivate(){
    def e = new PrivateInheritClosure(){
          // Dynamic Extension of Example
        }
    e.processNonPrivate()
  }

  void testProcessPrivateWithoutDynamicExtension(){
    def e = new PrivateInheritClosure()
    e.processPrivate()
  }

  void testProcessPrivateWithDynamicExtension(){
    def e = new PrivateInheritClosure(){
          // Dynamic Extension of Example
        }
    e.processPrivate()
  }
}
