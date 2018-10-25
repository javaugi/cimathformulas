package com.sisllc.mathformulas.impl;

/**
 * List Of Divisors A divisor is an integer that can be multiplied by some other
 * integer to produce n. The following java program list all of the divisors of
 * the numbers 1 to the given number.
 *
 * @author david
 *
 */
public class Divisors {

    public static void main(String[] args) {
        int N = 15; // Integer.parseInt(args[0]);

        for (int i = 1; i <= N; i++) {
            System.out.print(i + ": ");
            for (int j = 1; j <= N; j++) {
                if (i % j == 0) {
                    System.out.print(j + " ");
                }
            }
            System.out.println("");
        }
    }
}
