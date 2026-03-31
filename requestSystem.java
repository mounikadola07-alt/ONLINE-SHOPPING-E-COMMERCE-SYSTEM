package project;

import java.io.*;
import java.util.*;

public class requestSystem {

    Scanner sc = new Scanner(System.in);

    static String requestFile = "C:\\Users\\DMOUNIKA\\OneDrive\\Q-1.txt";
    static String processedFile = "C:\\Users\\DMOUNIKA\\OneDrive\\Q-2.txt";
    class Node {
        int id;
        String type;
        Node next;
    }

    Node front = null;
    Node rear = null;


    /* Add request to queue */
    void addRequest() {

        Node CNode = new Node();

        System.out.print("Enter Request ID: ");
        CNode.id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Request Type (Order / Delivery / Complaint): ");
        CNode.type = sc.nextLine();

        CNode.next = null;

        if (front == null)
            front = rear = CNode;
        else {
            rear.next = CNode;
            rear = CNode;
        }

        saveRequest(CNode);

        System.out.println("Request added successfully.");
    }


    /* Save request to file */
    void saveRequest(Node node) {

        try {

            File f = new File(requestFile);
            boolean exists = f.exists();

            FileWriter fw = new FileWriter(f, true);

            if (!exists) {
                fw.write(String.format("%-10s %-20s\n", "REQUEST_ID", "REQUEST_TYPE"));
                fw.write("------------------------------------------------\n");
            }

            fw.write(String.format("%-10d %-20s\n", node.id, node.type));

            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* Save processed request */
    void saveProcessed(Node node) {

        try {

            File f = new File(processedFile);
            boolean exists = f.exists();

            FileWriter fw = new FileWriter(f, true);

            if (!exists) {
                fw.write(String.format("%-10s %-20s\n", "REQUEST_ID", "REQUEST_TYPE"));
                fw.write("------------------------------------------------\n");
            }

            fw.write(String.format("%-10d %-20s\n", node.id, node.type));

            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* Process request */
    void processRequest() {

        if (front == null) {
            System.out.println("No requests in queue.");
            return;
        }

        System.out.println("\nProcessing Request:");
        System.out.printf("%-10d %-20s\n", front.id, front.type);

        saveProcessed(front);

        front = front.next;

        if (front == null)
            rear = null;
    }


    /* View next request */
    void peekRequest() {

        if (front == null) {
            System.out.println("Queue empty.");
            return;
        }

        System.out.println("\nNext Request:");
        System.out.printf("%-10d %-20s\n", front.id, front.type);
    }


    /* Display queue */
    void displayQueue() {

        if (front == null) {
            System.out.println("Queue empty.");
            return;
        }

        Node temp = front;

        System.out.println("\nPENDING REQUESTS");
        System.out.printf("%-10s %-20s\n","REQUEST_ID","REQUEST_TYPE");
        System.out.println("--------------------------------------------");

        while (temp != null) {

            System.out.printf("%-10d %-20s\n", temp.id, temp.type);

            temp = temp.next;
        }
    }


    /* Count requests */
    void countRequests() {

        int count = 0;

        Node temp = front;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total pending requests: " + count);
    }


    /* View request file */
    void viewRequestFile() {

        try {

            File f = new File(requestFile);

            if (!f.exists()) {
                System.out.println("No request records.");
                return;
            }

            Scanner fs = new Scanner(f);

            System.out.println("\nREQUEST LOG");

            while (fs.hasNextLine())
                System.out.println(fs.nextLine());

            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /* View processed requests */
    void viewProcessedFile() {

        try {

            File f = new File(processedFile);

            if (!f.exists()) {
                System.out.println("No processed records.");
                return;
            }

            Scanner fs = new Scanner(f);

            System.out.println("\nPROCESSED REQUESTS");

            while (fs.hasNextLine())
                System.out.println(fs.nextLine());

            fs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        // FIXED HERE
        requestSystem q = new requestSystem();

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\nQUEUE MENU");
            System.out.println("1 Add Request");
            System.out.println("2 Process Request");
            System.out.println("3 View Pending Requests");
            System.out.println("4 View Next Request");
            System.out.println("5 Count Requests");
            System.out.println("6 View Request File");
            System.out.println("7 View Processed File");
            System.out.println("8 Exit");

            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    q.addRequest();
                    break;

                case 2:
                    q.processRequest();
                    break;

                case 3:
                    q.displayQueue();
                    break;

                case 4:
                    q.peekRequest();
                    break;

                case 5:
                    q.countRequests();
                    break;

                case 6:
                    q.viewRequestFile();
                    break;

                case 7:
                    q.viewProcessedFile();
                    break;

                case 8:
                    System.out.println("System closed.");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 8);

        sc.close();
    }
}