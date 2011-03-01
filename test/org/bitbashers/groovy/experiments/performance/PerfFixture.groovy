package org.bitbashers.groovy.experiments.performance

import java.text.NumberFormat;

class PerfFixture extends GroovyTestCase {
  
  def timeIt(message, closure) {
    println "${message} ${ measure closure }"
  }
  
  def measure(closure) {
    
    100.times { closure() }
    
    def start = System.nanoTime()
    
    for (def loop = 10000; loop > 0; loop--) {
      closure()
    }
    
    def time = System.nanoTime() - start
    "${NumberFormat.getInstance( ).format(time)} ns"
  }
}

