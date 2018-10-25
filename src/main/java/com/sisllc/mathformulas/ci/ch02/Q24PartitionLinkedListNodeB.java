/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.sisllc.mathformulas.ci.ch02;

import com.sisllc.mathformulas.ci.lib.LinkedListNode;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q24PartitionLinkedListNodeB {

    public static LinkedListNode partition(LinkedListNode node, int x) {
        LinkedListNode beforeStart = null;
        LinkedListNode afterStart = null;

        /* Partition list */
        while (node != null) {
            LinkedListNode next = node.next;
            if (node.data < x) {
                /* Insert node into start of before list */
                node.next = beforeStart;
                beforeStart = node;
            } else {
                /* Insert node into front of after list */
                node.next = afterStart;
                afterStart = node;
            }
            node = next;
        }

        /* Merge before list and after list */
        if (beforeStart == null) {
            return afterStart;
        }

        LinkedListNode head = beforeStart;
        while (beforeStart.next != null) {
            beforeStart = beforeStart.next;
        }
        beforeStart.next = afterStart;
        return head;
    }

    public static void main(String[] args) {
        int length = 20;
        LinkedListNode[] nodes = new LinkedListNode[length];
        for (int i = 0; i < length; i++) {
            nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
        }

        for (int i = 0; i < length; i++) {
            if (i < length - 1) {
                nodes[i].setNext(nodes[i + 1]);
            }
            if (i > 0) {
                nodes[i].setPrevious(nodes[i - 1]);
            }
        }

        LinkedListNode head = nodes[0];
        System.out.println(head.printForward());

        LinkedListNode h = partition(head, 7);
        System.out.println(h.printForward());
    }
}
