package org.bitbashers.groovy.experiments.performance

import java.util.Date;

class SwitchIfTest extends PerfFixture {
    
    def random
    
    void setUp() {
        random = new Random(new Date().time)
    }
    
    void test_control() {
        timeIt "control", {
            def a = random.nextInt(2)
            callToPreventJITOptimization()
        }
    }
    
    void test_2_else_if() {
        timeIt "random 2 if else", {
            def a = random.nextInt(2)
            if (a == 0) {
                callToPreventJITOptimization()
            }
            else {
                callToPreventJITOptimization()
            }
        }
    }	
    
    void test_four_else_if() {
        timeIt "random 4 if else", {
            def a = random.nextInt(4)
            if (a == 0) {
                callToPreventJITOptimization()
            }
            else if (a == 1) {
                callToPreventJITOptimization()
            }
            else if (a == 2) {
                callToPreventJITOptimization()
            }
            else {
                callToPreventJITOptimization()
            }
        }
    }	
    
    void test_six_else_if() {
        
        timeIt "random 6 if else", {
            def a = random.nextInt(6)
            if (a == 0) {
                callToPreventJITOptimization()
            }
            else if (a == 1) {
                callToPreventJITOptimization()
            }
            else if (a == 2) {
                callToPreventJITOptimization()
            }
            else if (a == 3) {
                callToPreventJITOptimization()
            }
            else if (a == 4) {
                callToPreventJITOptimization()
            }
            else {
                callToPreventJITOptimization()
            }
        }
    }
    
    void test_10_else_if() {
        
        timeIt "random 10 if else", {
            def a = random.nextInt(10)
            if (a == 0) {
                callToPreventJITOptimization()
            }
            else if (a == 1) {
                callToPreventJITOptimization()
            }
            else if (a == 2) {
                callToPreventJITOptimization()
            }
            else if (a == 3) {
                callToPreventJITOptimization()
            }
            else if (a == 4) {
                callToPreventJITOptimization()
            }
            else if (a == 5) {
                callToPreventJITOptimization()
            }
            else if (a == 6) {
                callToPreventJITOptimization()
            }
            else if (a == 7) {
                callToPreventJITOptimization()
            }
            else if (a == 8) {
                callToPreventJITOptimization()
            }
            else {
                callToPreventJITOptimization()
            }
        }
    }
    
    void test_two_switch() {
        
        timeIt "random 2 switch", {
            def a = random.nextInt(2)
            switch (a) {
                case(0): 
                    callToPreventJITOptimization()
                    break;
                default: 
                    callToPreventJITOptimization()
            }
        }
    }
    
    void test_four_switch() {
        
        timeIt "random 4 switch", {
            def a = random.nextInt(3)
            switch (a) {
                case(0): 
                    callToPreventJITOptimization()
                    break;
                case(1): 
                    callToPreventJITOptimization()
                    break;
                case(2): 
                    callToPreventJITOptimization()
                    break;
                default: 
                    callToPreventJITOptimization()
            }
        }
    }
    
    void test_six_switch() {
        
        timeIt "random 6 switch", {
            def a = random.nextInt(6)
            switch (a) {
                case(0):  
                    callToPreventJITOptimization()
                    break;
                case(1):  
                    callToPreventJITOptimization()
                    break;
                case(2): 
                    callToPreventJITOptimization()
                    break;
                case(3): 
                    callToPreventJITOptimization()
                    break;
                case(4):  
                    callToPreventJITOptimization()
                    break;
                case(5):  
                    callToPreventJITOptimization()
                    break;
                default:  
                    callToPreventJITOptimization()
            }
        }
    }
    
    void test_ten_switch() {
        
        timeIt "random 10 switch", {
            def a = random.nextInt(10)
            switch (a) {
                case(0):  
                    callToPreventJITOptimization()
                    break;
                case(1):  
                    callToPreventJITOptimization()
                    break;
                case(2):  
                    callToPreventJITOptimization()
                    break;
                case(3):  
                    callToPreventJITOptimization()
                    break;
                case(4):  
                    callToPreventJITOptimization()
                    break;
                case(5):  
                    callToPreventJITOptimization()
                    break;
                case(6):  
                    callToPreventJITOptimization()
                    break;
                case(7):  
                    callToPreventJITOptimization()
                    break;
                case(8):  
                    callToPreventJITOptimization()
                    break;
                case(9):  
                    callToPreventJITOptimization()
                    break;
                default:  
                    callToPreventJITOptimization()
            }
        }
    }
    
    def callToPreventJITOptimization() {
    }
}

