/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.ci.ch02;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;
import com.sisllc.mathformulas.ci.lib.LinkedListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q22NthToLastLinkedListNode {

    private static final Logger log = LoggerFactory.getLogger(Q22NthToLastLinkedListNode.class);

    public static int nthToLastR1(LinkedListNode head, int n) {
        if (n == 0 || head == null) {
            return 0;
        }
        int k = nthToLastR1(head.next, n) + 1;
        if (k == n) {
            System.out.println(n + "th to last node is " + head.data);
        }
        return k;
    }

    public static LinkedListNode nthToLastR2(LinkedListNode head, int n, Q22IntWrapper i) {
        if (head == null) {
            return null;
        }
        LinkedListNode node = nthToLastR2(head.next, n, i);
        i.value = i.value + 1;
        if (i.value == n) {
            return head;
        }
        return node;
    }

    public static Q22Result nthToLastR3Helper(LinkedListNode head, int k) {
        if (head == null) {
            return new Q22Result(null, 0);
        }
        Q22Result res = nthToLastR3Helper(head.next, k);
        if (res.node == null) {
            res.count++;
            if (res.count == k) {
                res.node = head;
            }
        }
        return res;
    }

    public static LinkedListNode nthToLastR3(LinkedListNode head, int k) {
        Q22Result res = nthToLastR3Helper(head, k);
        if (res != null) {
            return res.node;
        }
        return null;
    }

    public static LinkedListNode nthToLast(LinkedListNode head, int n) {
        LinkedListNode p1 = head;
        LinkedListNode p2 = head;

        if (n <= 0) {
            return null;
        }

        // Move p2 n nodes into the list.  Keep n1 in the same position.
        for (int i = 0; i < n - 1; i++) {
            if (p2 == null) {
                return null; // Error: list is too small.
            }
            p2 = p2.next;
        }
        if (p2 == null) { // Another error check.
            return null;
        }

        // Move them at the same pace.  When p2 hits the end,
        // p1 will be at the right element.
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public static void main(String[] args) {
        LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
        System.out.println(head.printForward());
        int nth = 3;
        Q22IntWrapper wr = new Q22IntWrapper();
        LinkedListNode n = nthToLastR3(head, nth);
        nthToLastR1(head, nth);
        if (n != null) {
            System.out.println(nth + "th to last node is " + n.data);
        } else {
            System.out.println("Null.  n is out of bounds.");
        }
    }

}
