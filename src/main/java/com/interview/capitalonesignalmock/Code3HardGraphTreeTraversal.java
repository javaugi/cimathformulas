/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

/**
 *
 * @author javaugi
 */
public class Code3HardGraphTreeTraversal {

    /*
Problem 3: Hard – Graph/Tree Traversal
Task: Given a binary tree, find the maximum path sum, where a path is any sequence of nodes connected by edges.

Example:
Input:

Copy
    1  
   / \  
  2   3  
Output: 6 (Path: 2 → 1 → 3)

Solution (Post-Order DFS):

Key Points:

Recursive DFS with O(N) time.

Tracks local (branch) and global (path) maxima.    
    
In the context of "binary tree Post-Order DFS", DFS stands for Depth-First Search, a method for traversing a tree 
    or graph that explores as far as possible along each branch before backtracking. 
Here's a more detailed explanation:
Depth-First Search (DFS):
    Depth-first search (DFS) is an algorithm for traversing or searching tree or graph data structures. The algorithm 
    starts at the root node (selecting some arbitrary node as the root node in the case of a graph) and explores as 
    far as possible along each branch before backtracking.
    DFS is an algorithm used to systematically explore a tree or graph by going as deep as possible
    down one path before exploring other paths. 
Binary Tree:
    A binary tree is a data structure where each node has at most two children, referred to as the left and right child. 
Post-Order:
    In the context of DFS, "Post-Order" refers to a specific order in which nodes are visited during the traversal. 
    In post-order traversal, the left and right subtrees are visited before the root node. 
How it works:
    Start at the root node. 
    Explore the left subtree recursively (go as deep as possible). 
    Explore the right subtree recursively (go as deep as possible). 
    Visit (process) the current node (root). 
Example:
    Imagine a simple binary tree: A (root) has children B (left) and C (right). B has a child D (left).
    In post-order DFS, you would visit: D, then B, then C, then A. 
Other DFS Traversal Types:
    There are other DFS traversal methods, including Pre-Order and In-Order, each with a different order of visiting nodes. 
    Pre-Order: Visit the root, then the left subtree, then the right subtree. 
    In-Order: Visit the left subtree, then the root, then the right subtree
    
     */
    int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Code3HardGraphTreeTraversal main = new Code3HardGraphTreeTraversal();
        main.run();        
    }
    
    private void run() {
        TreeNode root = createBinaryTree();
        System.out.println("\n Test 1: The Maxsum of the binaryTree created: " + maxPathSum(root));
        

        int[] values = {5, 3, 7, 2, 4, 6, 8}; // Example values to insert.
        root = null;
        for (int val : values) {
            root = insert(root, val);
        }

        System.out.println("Inorder traversal of the BST:");
        inorderTraversal(root);        
        System.out.println("\n Test 2: The Maxsum of the binaryTree created: " + maxPathSum(root));
    }

    private TreeNode createBinaryTree() {
        // Create the root node (1).
        TreeNode root = new TreeNode(1);

        // Create the left child node (2).
        TreeNode leftChild = new TreeNode(2);

        // Create the right child node (3).
        TreeNode rightChild = new TreeNode(3);

        // Connect the nodes to form the tree.
        root.left = leftChild;
        root.right = rightChild;

        // Optionally, print the tree using a traversal method (e.g., inorder).
        System.out.println("Inorder traversal:");
        inorderTraversal(root);
        return root;
    }
    
    public TreeNode insert(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val);
        }

        return root; // Return the (possibly modified) root.
    }    
    

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        maxSum = Math.max(maxSum, left + right + node.val);
        int rtnValue = Math.max(left, right) + node.val;
        System.out.print("\nPost-Order Depth-First Search rtnValue: " + rtnValue);
        return rtnValue;
    }
    
    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }    

    public class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            this.val = x;
            this.left = null;
            this.right = null;
        }
    }
}
