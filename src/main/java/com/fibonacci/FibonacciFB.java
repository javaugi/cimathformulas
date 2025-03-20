package com.fibonacci;

import java.math.BigInteger;

public class FibonacciFB {

    public static int max = 100; // Make this as big as you want! (Though you'll exceed the bounds of a long around 46)
    public static int[] fib = new int[max];

    public static int fibonacci(int i) {
        //if (i < 0) {
        //    throw new IllegalArgumentException("Input must be non-negative");
        //}
        
        if (i <= 1) {
            return i;
        }
        return fibonacci(i - 1) + fibonacci(i - 2);
    }
    /*
    Here are the main strategies to prevent overflow in this scenario:

    1. Using BigInteger: The java.math.BigInteger class in Java is designed to handle 
    arbitrarily large integers. It does not have a fixed size limit like primitive 
    integer types. This is the most common and straightforward solution for this problem.
    2. Modulo Operation (if the goal is the Fibonacci number modulo some value): 
    If the requirement is not the exact Fibonacci number but its value modulo some number
    (e.g., for competitive programming problems), then you can apply the modulo operation 
    at each step of the calculation. This will keep the numbers within a manageable range.
    3. Approximation using Binet's Formula (for very large n, if exact value isn't critical):
    Binet's formula provides a closed-form expression for the nth Fibonacci number involving 
    the golden ratio. For very large n, this can be used to get an approximation, 
    but it involves floating-point numbers, which might have their own precision issues and
    won't give the exact integer result. This is usually not the primary approach when exact 
    large integers are needed.
    Given the user's question about preventing overflow for very large n, the most appropriate and 
    general solution is using BigInteger. I will provide a Java example demonstrating this. 
    I will also briefly mention the modulo operation approach as it's relevant in certain contexts.    
    // */
    
    public static BigInteger fibonacci2(int i) {
        //if (i < 0) {
        //    throw new IllegalArgumentException("Input must be non-negative");
        //}
        if (i <= 1) {
            return BigInteger.valueOf(i);
        }
        return BigInteger.valueOf(fibonacci(i - 1) + fibonacci(i - 2));
    }    

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        FibonacciFB f = new FibonacciFB();
        f.run1();
        //f.run2();
    }
    
    private void run1() {
        int trials = 10; // Run code multiple times to compute average time.
        double[] times = new double[max]; // Store times

        for (int j = 1; j < trials; j++) { // Run this 10 times to compute
            for (int i = 1; i < max; i++) {
                fib = new int[max];
                long start = System.currentTimeMillis();
                fibonacci(i);
                long end = System.currentTimeMillis();
                long time = end - start;
                times[i] += time;
            }
        }

        for (int j = 1; j < max; j++) {
            System.out.println(j + ": " + times[j] / trials + "ms");
        }
    }
    
    private void run2() {
        int trials = 12; // Run code multiple times to compute average time.
        double[] times = new double[max]; // Store times

        for (int j = 1; j < trials; j++) { // Run this 10 times to compute
            for (int i = 1; i < max; i++) {
                fib = new int[max];
                long start = System.currentTimeMillis();
                fibonacci2(i);
                long end = System.currentTimeMillis();
                long time = end - start;
                times[i] += time;
            }
        }

        for (int j = 1; j < max; j++) {
            System.out.println(j + ": " + times[j] / trials + "ms");
        }
    }    

}
