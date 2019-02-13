package com.fibonacci;

public class FibonacciFB {

    public static int max = 100; // Make this as big as you want! (Though you'll exceed the bounds of a long around 46)
    public static int[] fib = new int[max];

    public static int fibonacci(int i) {
        if (i <= 1) {
            return i;
        }
        return fibonacci(i - 1) + fibonacci(i - 2);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int trials = 10; // Run code multiple times to compute average time.
        double[] times = new double[max]; // Store times

        for (int j = 0; j < trials; j++) { // Run this 10 times to compute
            for (int i = 0; i < max; i++) {
                fib = new int[max];
                long start = System.currentTimeMillis();
                fibonacci(i);
                long end = System.currentTimeMillis();
                long time = end - start;
                times[i] += time;
            }
        }

        for (int j = 0; j < max; j++) {
            System.out.println(j + ": " + times[j] / trials + "ms");
        }
    }

}
