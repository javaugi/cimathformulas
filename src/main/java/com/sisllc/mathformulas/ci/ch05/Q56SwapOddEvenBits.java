package com.sisllc.mathformulas.ci.ch05;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;

public class Q56SwapOddEvenBits {

    public static int swapOddEvenBits(int x) {
        return (((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1));
    }

    public static void main(String[] args) {
        int a = 103217;
        System.out.println(a + ": " + AssortedMethods.toFullBinaryString(a));
        int b = swapOddEvenBits(a);
        System.out.println(b + ": " + AssortedMethods.toFullBinaryString(b));
    }

}
