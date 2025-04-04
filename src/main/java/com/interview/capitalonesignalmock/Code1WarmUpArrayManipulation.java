/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

/**
 *
 * @author javaugi
 */
public class Code1WarmUpArrayManipulation {

    /*
        Problem 1: Warm-Up (Easy) – Array Manipulation
        Task: Given an array of integers, return the second-largest distinct value. If none, return -1.

        Example:
        Input: [5, 5, 4, 2, 3] → Output: 4
        Input: [1, 1, 1] → Output: -1    
    
    
    Key Points:
        Single-pass O(N) time, O(1) space.
        Handles duplicates and edge cases (all identical values).
    // */

    public static void main(String[] args) {
        Code1WarmUpArrayManipulation main = new Code1WarmUpArrayManipulation();
        int[] intArr = {1, 2, 3, 4, 5};
        int result = main.secondLargest(intArr);
        System.out.println("1 The result is: " + result + " from input: " + intArr);
        
        int[] intArr2 = {5, 5, 4, 2, 3};
        result = main.secondLargest(intArr2);
        System.out.println("2 The result is: " + result + " from input: " + intArr2);
        
        int[] intArr3 = {1, 1, 1};
        result = main.secondLargest(intArr3);
        System.out.println("3 The result is: " + result + " from input: " + intArr3);
    }

    public int secondLargest(int[] nums) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num != first) {
                second = num;
            }
        }
        return second != Integer.MIN_VALUE ? second : -1;
    }
}
