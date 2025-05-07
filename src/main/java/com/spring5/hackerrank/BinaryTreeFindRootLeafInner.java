/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.hackerrank;

import com.spring5.MyApplication;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class BinaryTreeFindRootLeafInner implements CommandLineRunner{
    private static final Logger log = LoggerFactory.getLogger(BinaryTreeFindRootLeafInner.class);

    public static void main(String[] args) {
        //testDataPopulation();        
        printQueryResults(args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("BinaryTreeFindRootLeafInner with args {}", Arrays.toString(args)); 
    }
    
    private static void printQueryResults(String[] args) throws BeansException {
        // Start the Spring application and get the application context
        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
        log.info("BinaryTreeFindRootLeafInner started with context {}", context);

        // Application logic can be placed here, after the context is initialized
        BSTNativeQueryService myService = context.getBean(BSTNativeQueryService.class);
        log.info("StationNativeQueryService {}", myService);
        List<String> results = myService.doQuery(getQuery());
        log.info("StationNativeQueryService results {}", results);
        results.stream().forEach(System.out::println);

        // Close the application context when done
        context.close();
    }
    
    private static String getQuery() {
        String q = " select b.N, "
            + "         case when b.P is null then 'Root' "
            + "             when b.N in (select distinct b0.P from BST b0 where b0.P is not null) then 'Inner' "
            + "             else 'Leaf' "
            + "         end as p  "
            + "     from BST b "
            + "     order by b.N ";

        return q;
    }   
}

/*
You are given a table, BST, containing two columns: N and P, where N represents the value of a node in Binary Tree, and P is the parent of N.
Column  Type
N       Integer
P       Integer

Write a query to find the node type of Binary Tree ordered by the value of the node. Output one of the following for each node:

Root: If node is root node.
Leaf: If node is leaf node.
Inner: If node is neither root nor leaf node.

Sample Input
N       P
1       2
3       2
6       8
9       8
2       5
8       5
5       null

Sample Output

1 Leaf
2 Inner
3 Leaf
5 Root
6 Leaf
8 Inner
9 Leaf
*/