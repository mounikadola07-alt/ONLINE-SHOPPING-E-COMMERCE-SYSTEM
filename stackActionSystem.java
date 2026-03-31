package project;

import java.io.*;
import java.util.*;

public class stackActionSystem {

    Scanner sc = new Scanner(System.in);

    static String actionFile = "C:\\Users\\ DMOUNIKA\\OneDrive\\stack.txt";

    class Node {
        int id;
        String type;
        String item;
        Node next;
    }

    Node top = null;
    int actionCounter = 1;

    /* Push operation */
    void push(String type, String item) {

        Node CNode = new Node();
        CNode.id = actionCounter++;
        CNode.type = type;
        CNode.item = item;
        CNode.next = top;

        top = CNode;

        saveAction(CNode, "Performed");

        System.out.println("Action stored in stack.");
    }

    /* Save action to file */
    void saveAction(Node node, String status) {

        try {

            File f = new File(actionFile);
            boolean exists = f.exists();

            FileWriter fw = new FileWriter(f, true);

            if (!exists) {

                fw.write(String.format("%-10s %-15s %-20s %-15s\n",
                        "ACTION_ID", "ACTION_TYPE", "ITEM_NAME", "STATUS"));

                fw.write("--------------------------------------------------------------\n");
            }

            fw.write(String.format("%-10d %-15s %-20s %-15s\n",
                    node.id, node.type, node.item, status));

            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Pop operation (Undo) */
    void pop() {

        if (top == null) {
            System.out.println("Nothing to undo");
            return;
        }

        System.out.println("Undo action: " + top.type + " " + top.item);

        saveAction(top, "Undone");

        top = top.next;
    }

    /* Display stack */
    void display() {

        if (top == null) {
            System.out.println("Stack empty");
            return;
        }

        Node temp = top;

        System.out.println("\nSTACK ACTIONS");
        System.out.printf("%-10s %-15s %-20s\n", "ID", "TYPE", "ITEM");
        System.out.println("---------------------------------------------");

        while (temp != null) {

            System.out.printf("%-10d %-15s %-20s\n",
                    temp.id, temp.type, temp.item);

            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        // FIXED HERE
        stackActionSystem stack = new stackActionSystem();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n CART STACK MENU ");
            System.out.println("1 Add Item Action");
            System.out.println("2 Remove Item Action");
            System.out.println("3 Undo Last Action");
            System.out.println("4 View Stack");
            System.out.println("5 Exit");

            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter item added: ");
                    String add = sc.nextLine();

                    stack.push("Added", add);

                    break;

                case 2:

                    System.out.print("Enter item removed: ");
                    String remove = sc.nextLine();

                    stack.push("Removed", remove);

                    break;

                case 3:

                    stack.pop();

                    break;

                case 4:

                    stack.display();

                    break;

                case 5:

                    System.out.println("Exiting...");
                    break;

                default:

                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}