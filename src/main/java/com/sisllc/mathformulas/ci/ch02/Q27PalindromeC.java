package com.sisllc.mathformulas.ci.ch02;

import java.util.Objects;

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
