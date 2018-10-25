package com.sisllc.mathformulas.impl;

/**
 * Euclidean Algorithm The Euclidean algorithm is a way to find the greatest
 * common divisor of two positive integers.
 *
 * @author david
 *
 */
public class EuclideanAlgorithm {
    // find greatest common divisor

    public static int gcd(int a, int b) {
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println("Common Divisors:");
        System.out.println("   5  7 : " + gcd(5, 7));
        System.out.println("  99  6 : " + gcd(99, 6));
        System.out.println(" 100 10 : " + gcd(100, 10));
        System.out.println(" 990 77 : " + gcd(990, 77));
    }
}
