/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.stringmanipulation;

import static com.sisllc.mathformulas.stringmanipulation.CapitalizeFirstLetter.sentence;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ReverseWordsOfSentence {

    private static final Logger log = LoggerFactory.getLogger(ReverseWordsOfSentence.class);

    public static void main(String[] args) {
        String[] words = sentence.split(" ");
        System.out.println(reverseWords(words));
        //reverse(words);

        words = sentence.split(" ");
        reverseRecursive(words, 0, words.length - 1);
        System.out.println(Arrays.toString(words).replaceAll(", ", " ").replace('[', ' ').replace(']', ' '));

        words = sentence.split(" ");
        reverseSwap(words, 0, words.length - 1);
        System.out.println(Arrays.toString(words).replaceAll(", ", " ").replace('[', ' ').replace(']', ' '));
        /*
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word + " ");
        }
        System.out.println(sb.toString());
        // */
    }

    public static void reverse(String[] words) {
        if (words == null || words.length <= 0) {
            return;
        }
        reverseRecursive(words, 0, words.length - 1);
        //swap(words, 0, words.length - 1);
    }

    public static void reverseSwap(String[] words, int i, int j) {
        while (i < j) {
            String tmp = words[i];
            words[i] = words[j];
            words[j] = tmp;
            i++;
            j--;
        }
    }

    public static void reverseRecursive(String[] words, int i, int j) {
        if (i >= j) {
            return;
        }
        String tmp = words[i];
        words[i] = words[j];
        words[j] = tmp;
        i++;
        j--;
        reverseRecursive(words, i, j);
    }

    public static String reverseWords(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }

        // split to words by space
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; --i) {
            if (!arr[i].equals("")) {
                sb.append(arr[i]).append(" ");
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}
