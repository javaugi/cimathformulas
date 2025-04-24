/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.text.diff.*;
//import com.github.difflib.*;
//import com.github.difflib.algorithm.DiffException;

/**
 *
 * @author javaugi
 */
public class StringDiff {
    //Finding and Printing the Difference Between Two Strings in Java
    // Here are several approaches to compare two strings and show their differences:

    public static void main(String[] args) {
    }

    public static void printStringDifferences(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());

        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);

        StringBuilder sb = new StringBuilder();        
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                String formatted = String.format("Position %d: '%c' vs '%c'\n", i, str1.charAt(i), str2.charAt(i));
                System.out.printf(formatted);
                sb.append(formatted);
            }
        }

        // Handle extra characters in longer string
        if (str1.length() > str2.length()) {
            String formatted = String.format("String 1 has extra characters: '%s'\n", str1.substring(minLength));
            System.out.printf(formatted);
            sb.append(formatted);
        } else if (str2.length() > str1.length()) {
            String formatted = String.format("String 2 has extra characters: '%s'\n", str2.substring(minLength));
            System.out.printf(formatted);
            sb.append(formatted);
        }
        System.out.println("printStringDifferences  Differences:" + sb.toString());
    }

    public static void printDiffApacheCommon(String str1, String str2) {
        StringsComparator comparator = new StringsComparator(str1, str2);
        EditScript<Character> script = comparator.getScript();

        script.visit(new CommandVisitor<Character>() {
            @Override
            public void visitInsertCommand(Character c) {
                System.out.println("Insert: " + c);
            }

            @Override
            public void visitDeleteCommand(Character c) {
                System.out.println("Delete: " + c);
            }

            @Override
            public void visitKeepCommand(Character c) {
                // Characters that match
            }
        });
        System.out.println("printDiffApacheCommon Differences:" + script.getModifications());
        
    }

    public static void printWordDifferences(String str1, String str2) {
        String[] words1 = str1.split("\\s+");
        String[] words2 = str2.split("\\s+");

        int maxLength = Math.max(words1.length, words2.length);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            String word1 = i < words1.length ? words1[i] : "";
            String word2 = i < words2.length ? words2[i] : "";

            if (!word1.equals(word2)) {                
                String formatted = String.format("Word %d: '%s' vs '%s'\n", i, word1, word2);
                System.out.printf(formatted);
                sb.append(formatted);
            }
        }
        System.out.println("printStringDifferences  Differences:" + sb.toString());
    }

    /*
    public static void printDiffWithLibrary(String str1, String str2) {
        try {
            List<AbstractDelta<String>> deltas = DiffUtils.diff(
                    Arrays.asList(str1.split("")),
                    Arrays.asList(str2.split(""))
            ).getDeltas();

            for (AbstractDelta<String> delta : deltas) {
                System.out.println(delta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // */
}
