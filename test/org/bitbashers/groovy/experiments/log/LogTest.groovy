package org.bitbashers.groovy.experiments.log;

import groovy.util.GroovyTestCase;
import groovy.util.logging.Log
import groovy.util.logging.Commons;

class LogTest extends GroovyTestCase {
  
  void test_is_a_java_util_logger() {
    def logit = new LogForJavaUtil()
    
    assertEquals java.util.logging.Logger, logit.log.class
    logit.log.info("Log annotation must exist")
  }
  
  void test_is_a_commons_logger() {
    def logit = new LogForCommonsLogger()
    
    assertEquals org.apache.commons.logging.impl.Jdk14Logger, logit.log.class
    logit.log.info("commons logger must exist")
  }
}

@Log
class LogForJavaUtil {
}

@Commons
class LogForCommonsLogger {
}
