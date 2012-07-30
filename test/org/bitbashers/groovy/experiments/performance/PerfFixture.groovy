package org.bitbashers.groovy.experiments.performance

import java.text.NumberFormat;

class PerfFixture extends GroovyTestCase {
  
  static WARMUP = 100
  static ITERATIONS = 10000

  def timeIt(message, closure) {
    println("${message} ${ measure(closure) }")
  }
  
  def measure(closure) {
    
    WARMUP.times { closure() }
    
    def start = System.nanoTime()
    
    for (def loop = ITERATIONS; loop > 0; loop--) {
      closure()
    }
    
    def time = System.nanoTime() - start
    "${NumberFormat.getInstance( ).format(time)} ns"
  }
}

