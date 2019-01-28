/*
 * Copyright (C) 2019 Strategic Information Systems, LLC.
 *
 */
package com.interview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class LambdaExpression {

    private static final Logger log = LoggerFactory.getLogger(LambdaExpression.class);

    interface NumericTest {

        boolean computeTest(int n);
    }

    interface MyGreeting {

        String processName(String str);
    }

    interface MyString {

        String myStringFunction(String str);
    }

    public static String reverseStr(MyString reverse, String str) {
        return reverse.myStringFunction(str);
    }

    interface MyGeneric<T> {

        T compute(T t);
    }

    public static void main(String args[]) {
        NumericTest isEven = (n) -> (n % 2) == 0;
        NumericTest isNegative = (n) -> (n < 0);
        // Output: false
        System.out.println(isEven.computeTest(5));
        // Output: true
        System.out.println(isNegative.computeTest(-5));

        MyGreeting morningGreeting = (str) -> "Good Morning " + str + "!";
        MyGreeting eveningGreeting = (str) -> "Good Evening " + str + "!";
        // Output: Good Morning Luis!
        System.out.println(morningGreeting.processName("Luis"));
        // Output: Good Evening Jessica!
        System.out.println(eveningGreeting.processName("Jessica"));

        MyString reverseStr = (str) -> {
            String result = "";

            for (int i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }

            return result;
        };
        // Output: omeD adbmaL
        System.out.println(reverseStr.myStringFunction("Lambda Demo"));

        MyGeneric<String> reverse = (str) -> {
            String result = "";

            for (int i = str.length() - 1; i >= 0; i--) {
                result += str.charAt(i);
            }

            return result;
        };

        // Integer version of MyGeneric
        MyGeneric<Integer> factorial = (Integer n) -> {
            int result = 1;

            for (int i = 1; i <= n; i++) {
                result = i * result;
            }

            return result;
        };

        // Output: omeD adbmaL
        System.out.println(reverse.compute("Lambda Demo"));
        // Output: 120
        System.out.println(factorial.compute(5));
    }
}
