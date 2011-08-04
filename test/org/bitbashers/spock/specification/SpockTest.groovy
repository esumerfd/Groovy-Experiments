package org.bitbashers.spock.specification
import static org.junit.Assert.*;
import spock.lang.Shared;

class SpockTest extends spock.lang.Specification {

  def someInstanceVariable = "INSTANCE VAR"
  @Shared someSharedVariable = "SHARED VAR"

  def "length of Spock's and his friends' names"() {
    expect:
    name.size() == length

    where:
    name     | length
    "Spock"  | 5
    "Kirk"   | 4
    "Scotty" | 6
  }

  def "reference a local variable"() {
    expect:
    someString == "123"

    where:
    someString = "123"
  }
  
  def "reference instance variable - first reference"() {
    setup:
    someInstanceVariable = "instance variable in first reference" 
    expect:
    someInstanceVariable == "instance variable in first reference"
  }
  
  def "reference instance variable - second reference"() {
    expect:
    someInstanceVariable != "instance variable in first reference"
  }
  
  def "reference a shared variable - first reference"() {
    setup:
    someSharedVariable = "shared set in first reference"

    expect:
    someSharedVariable == "shared set in first reference"
  }
  
  def "reference a shared variable - second reference"() {
    expect:
    someSharedVariable == "shared set in first reference"
  }
  
  def "prove that one can be added to two"() {
    setup:
    def result = 0
        
    when:
    result = 1 + 2
    
    then:
    result == 3
    
    then:
    result - 1 == 2
  }
  
  def "an exception can be thrown"() {
    when:
    throw new Exception("OOPS")
    
    then:
    thrown(Exception)
    notThrown(NullPointerException)
  }
  
  def "computing the maximum of two numbers"() {
    expect:
    Math.max(a, b) == c
  
    where:
    a << [5, 3]
    b << [1, 9]
    c << [5, 9]
  }
}
