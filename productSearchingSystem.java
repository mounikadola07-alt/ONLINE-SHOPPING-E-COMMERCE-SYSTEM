package project;
import java.util.Scanner;

public class productSearchingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        int[] id = new int[n];
        String[] name = new String[n];
        double[] price = new double[n];

        for(int i=0;i<n;i++){

            System.out.println("\nProduct "+(i+1));

            System.out.print("ID: ");
            id[i] = sc.nextInt();
            sc.nextLine(); // clear buffer

            System.out.print("Name: ");
            name[i] = sc.nextLine();

            System.out.print("Price: ");
            price[i] = sc.nextDouble();
        }

        System.out.println("\n1 Search by ID");
        System.out.println("2 Search by Name");
        System.out.println("3 Search by Price Range");

        int choice = sc.nextInt();
        sc.nextLine(); // clear buffer

        if(choice==1){

            System.out.print("Enter ID: ");
            int key = sc.nextInt();

            boolean found=false;

            for(int i=0;i<n;i++){
                if(id[i]==key){
                    System.out.println("Product Found: "+name[i]+" ₹"+price[i]);
                    found=true;
                }
            }

            if(!found)
                System.out.println("Product not found");
        }

        else if(choice==2){

            System.out.print("Enter Name: ");
            String key = sc.nextLine();

            boolean found=false;

            for(int i=0;i<n;i++){
                if(name[i].equalsIgnoreCase(key)){
                    System.out.println("Product Found: "+id[i]+" ₹"+price[i]);
                    found=true;
                }
            }

            if(!found)
                System.out.println("Product not found");
        }

        else if(choice==3){

            System.out.print("Enter Minimum Price: ");
            double min = sc.nextDouble();

            System.out.print("Enter Maximum Price: ");
            double max = sc.nextDouble();

            System.out.println("\nProducts in Range:");

            boolean found = false;

            for(int i=0;i<n;i++){

                if(price[i]>=min && price[i]<=max){
                    System.out.println(name[i]+" ₹"+price[i]);
                    found = true;
                }
            }

            if(!found)
                System.out.println("No products in this range");
        }

        else{
            System.out.println("Invalid choice");
        }

        sc.close();
    }
}