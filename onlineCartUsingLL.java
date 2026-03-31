package project;

import java.io.*;
import java.util.*;

public class onlineCartUsingLL {

    Scanner sc = new Scanner(System.in);

    static String productFile = "C:\\Users\\DMOUNIKA\\OneDrive\\LL-1.txt";
    static String browsingFile = "C:\\Users\\DMOUNIKA\\OneDrive\\LL-2.txt";
    static String orderFile = "C:\\Users\\DMOUNIKA\\OneDrive\\LL-3.txt";

    class Node {
        int id;
        String name;
        double price;
        Node next;
    }

    Node head = null;
    Node browseHead = null;
    Node orderHead = null;

    void addProductDatabase() {
        try {
            File f = new File(productFile);
            boolean exists = f.exists();
            FileWriter fw = new FileWriter(f, true);

            if (!exists) {
                fw.write(String.format("%-10s %-20s %-10s\n", "ID", "NAME", "PRICE"));
                fw.write("---------------------------------------------\n");
            }

            System.out.print("Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Product Name: ");
            String name = sc.nextLine();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            fw.write(String.format("%-10d %-20s %-10.2f\n", id, name, price));
            fw.close();

            System.out.println("Product stored successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void viewProducts() {
        try {
            File f = new File(productFile);

            if (!f.exists()) {
                System.out.println("No products found");
                return;
            }

            Scanner fs = new Scanner(f);
            System.out.println("\nProduct List");

            while (fs.hasNextLine()) {
                System.out.println(fs.nextLine());
            }

            fs.close();

        } catch (Exception e) {
            System.out.println("Error reading products");
        }
    }

    void browseProduct() {
        Node newNode = new Node();

        System.out.print("Product ID viewed: ");
        newNode.id = sc.nextInt();
        sc.nextLine();

        System.out.print("Product Name: ");
        newNode.name = sc.nextLine();

        System.out.print("Price: ");
        newNode.price = sc.nextDouble();

        newNode.next = browseHead;
        browseHead = newNode;

        try {
            FileWriter fw = new FileWriter(browsingFile, true);
            fw.write(newNode.id + " " + newNode.name + " " + newNode.price + "\n");
            fw.close();

        } catch (Exception e) {
            System.out.println("Error saving browsing history");
        }

        System.out.println("Product viewed");
    }

    void addItem() {
        Node newNode = new Node();

        System.out.print("Enter Product ID: ");
        newNode.id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Name: ");
        newNode.name = sc.nextLine();

        System.out.print("Enter Price: ");
        newNode.price = sc.nextDouble();

        newNode.next = null;

        if (head == null)
            head = newNode;
        else {
            Node cnode = head;
            while (cnode.next != null)
                cnode = cnode.next;
            cnode.next = newNode;
        }

        System.out.println("Added to cart");
    }

    void removeItem() {
        System.out.print("Enter product ID to remove: ");
        int id = sc.nextInt();

        if (head == null) {
            System.out.println("Cart empty");
            return;
        }

        if (head.id == id) {
            head = head.next;
            System.out.println("Item removed");
            return;
        }

        Node cnode = head;

        while (cnode.next != null && cnode.next.id != id)
            cnode = cnode.next;

        if (cnode.next != null) {
            cnode.next = cnode.next.next;
            System.out.println("Item removed");
        } else
            System.out.println("Item not found");
    }

    void displayCart() {
        if (head == null) {
            System.out.println("Cart empty");
            return;
        }

        Node cnode = head;
        System.out.println("\nCart Items");

        while (cnode != null) {
            System.out.println(cnode.id + " | " + cnode.name + " | ₹" + cnode.price);
            cnode = cnode.next;
        }
    }

    void countItems() {
        int count = 0;
        Node cnode = head;

        while (cnode != null) {
            count++;
            cnode = cnode.next;
        }

        System.out.println("Items in cart: " + count);
    }

    void expensiveItem() {
        if (head == null) {
            System.out.println("Cart empty");
            return;
        }

        Node cnode = head;
        Node maxNode = head;

        while (cnode != null) {
            if (cnode.price > maxNode.price)
                maxNode = cnode;
            cnode = cnode.next;
        }

        System.out.println("Most expensive item: " + maxNode.name + " ₹" + maxNode.price);
    }

    void saveOrder(Node node) {
        try {
            FileWriter fw = new FileWriter(orderFile, true);
            fw.write(node.id + " " + node.name + " " + node.price + "\n");
            fw.close();

        } catch (Exception e) {
            System.out.println("Error saving order");
        }
    }

    void viewOrders() {
        try {
            File f = new File(orderFile);

            if (!f.exists()) {
                System.out.println("No orders yet");
                return;
            }

            Scanner fs = new Scanner(f);
            System.out.println("\nOrder History");

            while (fs.hasNextLine())
                System.out.println(fs.nextLine());

            fs.close();

        } catch (Exception e) {
            System.out.println("Error reading orders");
        }
    }

    void checkout() {
        Node cnode = head;
        double total = 0;

        System.out.println("\nBILL");

        while (cnode != null) {
            System.out.println(cnode.name + " ₹" + cnode.price);
            total += cnode.price;
            saveOrder(cnode);
            cnode = cnode.next;
        }

        System.out.print("Coupon (SAVE10/SAVE20/NONE): ");
        String code = sc.next();

        if (code.equalsIgnoreCase("SAVE10"))
            total *= 0.9;
        else if (code.equalsIgnoreCase("SAVE20"))
            total *= 0.8;

        System.out.println("Total Bill = ₹" + total);

        head = null;
    }

    public static void main(String[] args) {

        onlineCartUsingLL shop = new onlineCartUsingLL(); 

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nSHOP MENU");
            System.out.println("1 Add Product Database");
            System.out.println("2 View Products");
            System.out.println("3 Browse Product");
            System.out.println("4 Add Item to Cart");
            System.out.println("5 Remove Item");
            System.out.println("6 View Cart");
            System.out.println("7 Count Items");
            System.out.println("8 Most Expensive Item");
            System.out.println("9 Checkout");
            System.out.println("10 View Orders");
            System.out.println("11 Exit");

            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: shop.addProductDatabase(); break;
                case 2: shop.viewProducts(); break;
                case 3: shop.browseProduct(); break;
                case 4: shop.addItem(); break;
                case 5: shop.removeItem(); break;
                case 6: shop.displayCart(); break;
                case 7: shop.countItems(); break;
                case 8: shop.expensiveItem(); break;
                case 9: shop.checkout(); break;
                case 10: shop.viewOrders(); break;
                case 11: System.out.println("Thank you"); break;
                default: System.out.println("Invalid choice");
            }

        } while (choice != 11);
    }
}