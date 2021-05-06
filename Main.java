package com.company;

/*  Student: Adam Coakley
    Student Code: C00240782
    DSA Assessment -
    Due Date: 22nd February 2021
 */

import java.util.ArrayList;
import java.util.Scanner;

class BinarySearchTree {
    class Node {
        String keyword;
        ArrayList<String> URL = new ArrayList<>();
        Node left;
        Node right;

        public Node(String keyword) {
            this.keyword = keyword;
            right = null;
            left = null;
        }
    }

    Node root;
    BinarySearchTree(){
        root = null;
    }

    //Add to binary search tree.
    public void insert(String keyword) {
        root = insertRec(root, keyword);
    }

    Node insertRec(Node root, String keyword) {
        // If the tree is empty..
        if (root == null) {
            root = new Node(keyword);
            return root;
        }

        // Otherwise, recur down the tree
        if (keyword.compareTo(root.keyword) < 0)
            root.left = insertRec(root.left, keyword);
        else if (keyword.compareTo(root.keyword) > 0)
            root.right = insertRec(root.right, keyword);

        return root;
    }

    //Print inorder (alphabetically)
    void printOrder(Node node) {
        if (node == null)
            return;

        // Traverse left
        printOrder(node.left);
        // Traverse root
        System.out.print(node.keyword + " ");
        // Traverse right
        printOrder(node.right);
    }

    //Check if node exists
    public boolean ifNodeExists(Node node, String keyword) {
        if (node == null)
            return false;

        if (node.keyword.equals(keyword))
            return true;

        // then recur on left subtree
        boolean left = ifNodeExists(node.left, keyword);
        // node found, no need to look further
        if(left) return true;

        // node is not found in left,
        // so recur on right subtree
        boolean right = ifNodeExists(node.right, keyword);
        return right;
    }


    public void put(Node node, String keyword, String URL){
        if(!ifNodeExists(node, keyword)){
            insert(keyword);
        }
        if (node == null)
            return;
        // Traverse left
        put(node.left, keyword, URL);

        //check if current nodes keyword = keyword, if it does, add the URL to the list of URLs
        if(keyword.equals(node.keyword)){
            node.URL.add(URL);
        }
        // Traverse right
        put(node.right, keyword, URL);
    }

    public void get(Node node, String keyword){
        if (node == null)
            return;

        // Traverse left
        get(node.left, keyword);

        //check if current nodes keyword = keyword, if it does, print the list of URLs
        if(keyword.equals(node.keyword)){
            System.out.print("The list of URLs for " + keyword + ": ");
            for(int i = 0; i < node.URL.size(); i++){
                System.out.print(node.URL.get(i) + " ");
            }
        }
        // Traverse right
        get(node.right, keyword);
    }

    public void count(Node node, String keyword) {
        if(node == null)
            return;
        count(node.left, keyword);

        if(keyword.equals(node.keyword)) {
            System.out.println("The number of URLs referencing the " + keyword + " keyword is: " + node.URL.size());
        }
        count(node.right, keyword);
    }

    public void delete(Node node, String keyword, String URL){
        if (node == null)
            return;
        delete(node.left, keyword, URL);
        if(keyword.equals(node.keyword)) {
            node.URL.remove(URL);
        }
        delete(node.right, keyword, URL);
    }

    //return the number of leaf nodes
    int getLeafCount(Node node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }
    public void clearURLs(Node node, String keyword){
        if (node == null)
            return;

        // Traverse left
        clearURLs(node.left, keyword);

        //check if current nodes keyword = keyword, if it does, print the list of URLs
        if(keyword.equals(node.keyword)){
            node.URL.clear();
        }
        // Traverse right
        clearURLs(node.right, keyword);
    }

    public void updateKeyword(Node node, String oldKeyword, String newKeyword){
        if (node == null)
            return;

        // Traverse left
        updateKeyword(node.left, oldKeyword, newKeyword);

        //check if current nodes keyword = keyword, if it does, print the list of URLs
        if(oldKeyword.equals(node.keyword)){
            node.keyword = newKeyword;
        }
        // Traverse right
        updateKeyword(node.right, oldKeyword, newKeyword);
    }

    public void printMenu(){
        System.out.println("Search Engine Optimisation (SEO)");
        System.out.println("-----------------------------------");
        System.out.println("1. Add a keyword to the BST and/or a link to a URL.");
        System.out.println("2. Return the list of URLs which use a given keyword.");
        System.out.println("3. Print the keywords in order.");
        System.out.println("4. Delete a URLs link with a keyword.");
        System.out.println("5. Return the number of URLs which reference a given keyword.");
        System.out.println("6. Clear the list of URLs linked with a given keyword.");
        System.out.println("7. Update a keyword.");
        System.out.println("-1 to exit.");
        System.out.print("Enter a number: ");
    }
}


public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert("Jumbo");
        tree.insert("Extra");
        tree.insert("Above");
        tree.insert("Heavy");
        tree.insert("Short");
        tree.insert("Light");
        tree.insert("Tough");

        //To speed up my tests
        tree.put(tree.root, "Vital", "URL1");
        tree.put(tree.root, "Above", "URL1");
        tree.put(tree.root, "Above", "URL2");
        tree.put(tree.root, "Above", "URL3");
        tree.put(tree.root, "Above", "URL4");
        tree.put(tree.root, "Above", "URL5");
        tree.put(tree.root, "Above", "URL6");

        //MENU
        Scanner sc = new Scanner(System.in);
        tree.printMenu();
        boolean menu = true;
        while(menu){
            int choice = sc.nextInt();
            if(choice == 1){
                sc.nextLine();
                System.out.println("Enter a keyword: ");
                String keyword = sc.nextLine();
                System.out.println("Enter a URL:");
                String URL = sc.nextLine();
                tree.put(tree.root, keyword, URL);
                System.out.println("Success! 0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == 2){
                sc.nextLine();
                System.out.println("Enter a keyword: ");
                String keyword = sc.nextLine();
                tree.get(tree.root, keyword);
                System.out.println("");
                System.out.println("0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == 3){
                tree.printOrder(tree.root);
                System.out.println("");
                System.out.println("0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == 4){
                sc.nextLine();
                System.out.println("Enter a keyword: ");
                String keyword = sc.nextLine();
                System.out.println("Enter a URL:");
                String URL = sc.nextLine();
                tree.delete(tree.root, keyword, URL);
                System.out.println("Deleted! 0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == 5){
                sc.nextLine();
                System.out.println("Enter a keyword: ");
                String keyword = sc.nextLine();
                tree.count(tree.root, keyword);
                System.out.println("0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == 6){
                sc.nextLine();
                System.out.println("Enter a keyword: ");
                String keyword = sc.nextLine();
                tree.clearURLs(tree.root, keyword);
                System.out.println("0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == 7){
                sc.nextLine();
                System.out.println("Enter a keyword to change: ");
                String oldKeyword = sc.nextLine();
                System.out.println("Enter a new keyword ");
                String newKeyword = sc.nextLine();
                tree.updateKeyword(tree.root, oldKeyword, newKeyword);
                System.out.println("0 for menu. -1 to exit.");
                choice = sc.nextInt();
                if(choice == 0){
                    tree.printMenu();
                } else if(choice == -1){
                    menu = false;
                }
            }
            if(choice == -1){
               menu = false;
            }
        }
    }
}
