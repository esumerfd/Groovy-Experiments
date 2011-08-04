package org.bitbashers.groovy.experiments.performance

import java.util.Date;

class ConfigSlurperPropertiesTest extends PerfFixture {
  
  def propertyFile
  def groovyFile
  
  void setUp() {
    def thisPath = "org/bitbashers/groovy/experiments/performance"
    def loader = Thread.currentThread().contextClassLoader
    propertyFile = new File(loader.getResource("${thisPath}/ConfigSlurperProperties.properties.txt").path)
    groovyFile = new File(loader.getResource("${thisPath}/ConfigSlurperProperties.groovy.txt").path)
    
    WARMUP = 10
    ITERATIONS = 100
  }

  void test_properties_file() {
    def input = new FileInputStream(propertyFile)
    timeIt "properties", {
      Properties props = new Properties()
      props.load(input)
    }
  }
  
  void test_slurpler_class() {
    timeIt "slurper from class", {
      def slurper = new ConfigSlurper()
      slurper.parse(ConfigSlurperProperties)
    }
  }

  void test_slurpler_from_string() {
    def text = propertyFile.text
    timeIt "slurper from string", {
      def slurper = new ConfigSlurper()
      slurper.parse(text)
    }
  }

  void test_slurpler_from_properties() {
    def url = propertyFile.toURL()
    timeIt "slurper from properties", {
      def slurper = new ConfigSlurper()
      slurper.parse(url)
    }
  }

  void test_slurpler_file() {
    def url = groovyFile.toURL()
    timeIt "slurper from file", {
      def slurper = new ConfigSlurper()
      slurper.parse(url)
    }
  }
}

