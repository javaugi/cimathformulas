package com.sisllc.mathformulas.ci.ch02;

import java.util.Objects;
import java.util.Random;

public class Q27PalindromeC {

    public static void main(String[] args) {
        String[] words = {"12321", "Anna", "Madam", "Are we not drawn onward to new era",
            "A man, a plan, a cat, a ham, a yak, a yam, a hat, a canal-Panama!"};
        for (String word : words) {
            //word = word.replaceAll("[^a-zA-Z]", "").toLowerCase().replaceAll(" ", "");
            word = word.replaceAll("\\W", "").toLowerCase();
            System.out.println(word + ": " + isPalindrome2(word));
            System.out.println(word + ": " + isPalindrome(word.toCharArray()));
            System.out.println(word + ": " + isPalindrome(word.toCharArray(), 0, word.length() - 1));

        }

        Q27PalindromeC palindrome = new Q27PalindromeC();
        palindrome.runExample();
    }

    void runExample() {
        int num = new Random().nextInt();
        long reversed = reverse(num);
        System.out.println("The number " + num + " is a palindrome ?" + (num == reversed));
        System.out.println("The number " + num + " is a palindrome ?" + isPalindrome(num));
    }

    /*
    This seemed to work too, but did you consider the possibility that the reversed number
    might overflow? If it overflows, the behavior is language specific (For Java the number
    wraps around on overflow, but in C/C++ its behavior is undefined). Yuck.
    Of course, we could avoid overflow by storing and returning a type that has larger size
    than int (ie, long long). However, do note that this is language specific, and the larger
    type might not always be available on all languages.
     */
    public long reverse(int num) {
        assert num >= 0; // for non-negative integers only.
        int rev = 0;
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
        }
        return rev;
    }

    /*
    We could construct a better and more generic solution. One pointer is that, we must start
    comparing the digits somewhere. And you know there could only be two ways, either
    expand from the middle or compare from the two ends.
    It turns out that comparing from the two ends is easier. First, compare the first and last
    digit. If they are not the same, it must not be a palindrome. If they are the same, chop off
    one digit from both ends and continue until you have no digits left, which you conclude
    that it must be a palindrome.
    35Now, getting and chopping the last digit is easy. However, getting and chopping the first
    digit in a generic way requires some thought. I will leave this to you as an exercise.
    Please think your solution out before you peek on the solution below.
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            int l = x / div;
            int r = x % 10;
            if (l != r) {
                return false;
            }
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public static boolean isPalindrome(char[] chars) {
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalindrome(char[] chars, int i, int j) {
        if (i >= j) {
            return true;
        }
        if (chars[i] != chars[j]) {
            return false;
        }
        i++;
        j--;

        return isPalindrome(chars, i, j);
    }

    public static boolean isPalindrome(String word) {
        word = word.replaceAll("[^a-zA-Z]", "").toLowerCase().replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        System.out.println("word=\n ***** " + word + "\n reversed word =\n ***** " + sb.toString());
        return Objects.equals(word, sb.toString());
    }

    public static boolean isPalindrome2(String word) {
        word = word.replaceAll("\\W", "").toLowerCase();
        //String reversed = reverseRecursive(word.toCharArray(), 0, word.length() - 1);
        String reversed = reverse(word.toCharArray(), 0, word.length() - 1);
        System.out.println(" word=          " + word + "\n reversed word=   " + reversed + "\n isPalindrome=    " + Objects.equals(word, reversed));
        return Objects.equals(word, reversed);
    }

    public static String reverseRecursive(char[] chars, int i, int j) {
        if (i >= j) {
            return new String(chars);
        }
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
        i++;
        j--;

        return reverseRecursive(chars, i, j);
    }

    public static String reverse(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }

        return new String(chars);
    }
}
