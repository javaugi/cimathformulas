package com.sisllc.mathformulas.impl;

import junit.framework.Assert;
import org.junit.Test;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class FibonacciRecursiveTest {

    @Test
    public void whenFib0Then0() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(0)=" + fib.fib(0));
        Assert.assertTrue(0 == fib.fib(0));
    }

    @Test
    public void whenFib1Then1() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(1)=" + fib.fib(1));
        Assert.assertTrue(1 == fib.fib(1));
    }

    @Test
    public void whenFib2Then1() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(2)=" + fib.fib(2));
        Assert.assertTrue(1 == fib.fib(2));
    }

    @Test
    public void whenFib3Then2() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(3)=" + fib.fib(3));
        Assert.assertTrue(2 == fib.fib(3));
    }

    @Test
    public void whenFib4Then3() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(4)=" + fib.fib(4));
        Assert.assertTrue(3 == fib.fib(4));
    }

    @Test
    public void whenFib5Then5() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(5)=" + fib.fib(5));
        Assert.assertTrue(5 == fib.fib(5));
    }

    @Test
    public void whenFib6Then8() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(6)=" + fib.fib(6));
        Assert.assertTrue(8 == fib.fib(6));
    }

    @Test
    public void whenFib7Then13() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("fib(7)=" + fib.fib(7));
        Assert.assertTrue(13 == fib.fib(7));
    }

    @Test
    public void whenPrintFib7Then0Through13() {
        FibonacciRecursive fib = new FibonacciRecursive();
        System.out.println("printFib(7)=" + fib.printFib(7));
        Assert.assertTrue("0, 1, 1, 2, 3, 5, 8, 13,".equals(fib.printFib(7)));
    }
}
