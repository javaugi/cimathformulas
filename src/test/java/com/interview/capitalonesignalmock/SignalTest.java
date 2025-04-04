/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author javaugi
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SignalTest {
    
    @Test
    public void testArrayManipulation() {
        Code1WarmUpArrayManipulation main = new Code1WarmUpArrayManipulation();
        int[] intArr = {1, 2, 3, 4, 5};
        int result = main.secondLargest(intArr);
        System.out.println("testArrayManipulation Test 1 The result is: " + result + " from input: " + intArr);
        assertTrue((result == 4), "The result should be 4 from {1, 2, 3, 4, 5}");
        
        int[] intArr2 = {5, 5, 4, 2, 3};
        result = main.secondLargest(intArr2);
        System.out.println("testArrayManipulation Test 2 The result is: " + result + " from input: " + intArr2);
        assertTrue((result == 4), "The result should be 4 from {5, 5, 4, 2, 3}");
        
        int[] intArr3 = {1, 1, 1};
        result = main.secondLargest(intArr3);
        System.out.println("testArrayManipulation Test 3 The result is: " + result + " from input: " + intArr3);        
        assertTrue((result == -1), "The result should be -1 from {1, 1, 1}");
    }
    
    @Test
    public void testStringManipulation() {
        Code2MediumStringProcessing main = new Code2MediumStringProcessing();
        String input = "ccaabbb";
        int result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("testStringManipulation Test 1 The result is: " + result + " from input: " + input);
        assertTrue((result == 5), "The result should be 5 from ccaabbb");

        input = "dkfkflfhf";
        result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("testStringManipulation Test 2 The result is: " + result + " from input: " + input);
        assertTrue((result == 4), "The result should be 4 from dkfkflfhf");

        input = "cfdfehgf";
        result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("testStringManipulation Test 3 The result is: " + result + " from input: " + input);
        assertTrue((result == 3), "The result should be 3 from cfdfehgf");
    }    
    
    @Test
    public void testTreeTraversalMaxLength() {
        
    }
}
