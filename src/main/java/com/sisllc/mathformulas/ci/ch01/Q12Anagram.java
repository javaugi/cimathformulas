/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.ci.ch01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q12Anagram {

    private static final Logger log = LoggerFactory.getLogger(Q12Anagram.class);

    public static String sort(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    public static boolean permutation(String s, String t) {
        return sort(s).equals(sort(t));
    }

    public static boolean anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] letters = new int[128];
        int num_unique_chars = 0;
        int num_completed_t = 0;
        char[] s_array = s.toCharArray();
        for (char c : s_array) { // count number of each char in s.
            if (letters[c] == 0) {
                ++num_unique_chars;
            }
            ++letters[c];
        }
        for (int i = 0; i < t.length(); ++i) {
            int c = (int) t.charAt(i);
            if (letters[c] == 0) { // Found more of char c in t than in s.
                return false;
            }
            --letters[c];
            if (letters[c] == 0) {
                ++num_completed_t;
                if (num_completed_t == num_unique_chars) {
                    // itÕs a match if t has been processed completely
                    return true;
                    //return i == t.length() - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = permutation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
            System.out.println(anagram(word1, word2));
        }
    }
}
