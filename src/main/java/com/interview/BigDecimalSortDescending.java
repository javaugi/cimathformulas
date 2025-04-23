/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author javaugi
 */
public class BigDecimalSortDescending {

    public static void main(String[] args) {
        // Input data
        String[] input = {
            "-100", "50", "0", "56.6", "90",
            "0.12", ".12", "02.34", "000.000"
        };

        System.out.println("Original " + Arrays.toString(input));
        System.out.println("Solution 1");
        sortPrint1(input);
        System.out.println("Solution 2");
        sortPrint2(input);
    }
    
    private static void sortPrint2(String[] input) {
        Arrays.stream(input)
              .sorted((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)))
              .forEach(System.out::println);
    }

    private static void sortPrint1(String[] input) {
        // Convert to BigDecimal and sort in descending order
        List<BigDecimal> decimals = new ArrayList<>();
        for (String num : input) {
            decimals.add(new BigDecimal(num));
        }

        // Sort in descending order
        Collections.sort(decimals, Collections.reverseOrder());

        // Print the results (preserving original string representation)
        // To match exact input format, we need to track original strings
        List<String> originalNumbers = Arrays.asList(input);
        List<String> sortedNumbers = new ArrayList<>(originalNumbers);

        // Custom comparator to sort based on BigDecimal value
        sortedNumbers.sort((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)));

        // Print the sorted numbers
        for (String num : sortedNumbers) {
            System.out.println(num);
        }
    }
}
 
/*
Explanation
Convert Strings to BigDecimal

Ensures correct numerical sorting (avoids lexicographic issues with String sorting).

Sort in Descending Order

Collections.sort(decimals, Collections.reverseOrder()) sorts BigDecimal values in descending order.

Preserve Original String Format

Since BigDecimal parsing removes leading zeros (e.g., 02.34 → 2.34), we instead:

Sort the original strings based on their BigDecimal values.

Use a custom comparator:

java
sortedNumbers.sort((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)));
Print the Result

Maintains the exact input format (e.g., 02.34 instead of 2.34).

Alternative (Using Java Streams)
java
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.*;

public class BigDecimalSortDescending {
    public static void main(String[] args) {
        String[] input = { "-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000" };
        
        Arrays.stream(input)
              .sorted((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)))
              .forEach(System.out::println);
    }
}
Key Takeaways
BigDecimal ensures correct numerical sorting (handles decimals, negatives, and leading zeros).

Custom comparator sorts strings while preserving formatting.

Descending order is achieved using Collections.reverseOrder() or (b.compareTo(a)).

This approach guarantees accurate numerical sorting while keeping the original string representations intact.
*/

