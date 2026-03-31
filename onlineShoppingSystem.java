package project;

import java.io.*;
import java.util.*;

public class onlineShoppingSystem {

    static Scanner sc = new Scanner(System.in);

    static String productFile = "C:\\Users\\DMOUNIKA\\OneDrive\\array1.txt";
    static String transactionFile = "C:\\Users\\DMOUNIKA\\OneDrive\\array2.txt";


/* ADD NEW PRODUCT TO FILE */
static void addProductToFile(int id,String name,String category,
                             double price,int stock,double discount){

    try{

        File f = new File(productFile);
        boolean exists = f.exists();

        FileWriter fw = new FileWriter(f,true);

        if(!exists){
            fw.write("ID\tName\tCategory\tPrice\tStock\tDiscount\n");
        }

        fw.write(id+"\t"+name+"\t"+category+"\t"+price+"\t"+stock+"\t"+discount+"\n");

        fw.close();

    }catch(Exception e){
        System.out.println("Error saving product");
    }
}


/* LOAD PRODUCTS FROM FILE */
static int loadProducts(int[] id,String[] name,String[] category,
                        double[] price,int[] stock,double[] discount){

    int n=0;

    try{

        File f=new File(productFile);

        if(!f.exists())
            return 0;

        Scanner fs=new Scanner(f);

        // FIX: safe header skip
        if(fs.hasNextLine())
            fs.nextLine();

        // FIX: prevent overflow
        while(fs.hasNextLine() && n < 100){

            String line=fs.nextLine();
            String data[]=line.split("\t");

            // FIX: avoid crash if bad line
            if(data.length < 6) continue;

            id[n]=Integer.parseInt(data[0]);
            name[n]=data[1];
            category[n]=data[2];
            price[n]=Double.parseDouble(data[3]);
            stock[n]=Integer.parseInt(data[4]);
            discount[n]=Double.parseDouble(data[5]);

            n++;
        }

        fs.close();

    }catch(Exception e){
        System.out.println("Error loading products");
    }

    return n;
}


/* SAVE PRODUCTS AFTER STOCK CHANGE */
static void saveProducts(int n,int[] id,String[] name,String[] category,
                         double[] price,int[] stock,double[] discount){

    try{

        FileWriter fw=new FileWriter(productFile);

        fw.write("ID\tName\tCategory\tPrice\tStock\tDiscount\n");

        for(int i=0;i<n;i++){

            fw.write(id[i]+"\t"+name[i]+"\t"+category[i]+"\t"+
                     price[i]+"\t"+stock[i]+"\t"+discount[i]+"\n");
        }

        fw.close();

    }catch(Exception e){
        System.out.println("Error saving products");
    }
}


/* DISPLAY PRODUCTS */
static void displayProducts(int n,int[] id,String[] name,String[] category,
                            double[] price,int[] stock,double[] discount){

    System.out.println("\nID\tName\tCategory\tPrice\tStock\tDiscount");

    for(int i=0;i<n;i++){

        System.out.println(id[i]+"\t"+name[i]+"\t"+category[i]+"\t"+
                           price[i]+"\t"+stock[i]+"\t"+discount[i]);
    }
}


/* ADD TO CART */
static int addToCart(int n,int[] id,int[] stock,int[] cartId,int cartCount){

    System.out.print("Enter Product ID: ");
    int pid=sc.nextInt();

    for(int i=0;i<n;i++){

        if(id[i]==pid){

            if(stock[i]>0){

                cartId[cartCount]=pid;
                stock[i]--;

                System.out.println("Added to cart");
                return cartCount+1;

            }else{
                System.out.println("Out of stock");
                return cartCount;
            }
        }
    }

    System.out.println("Invalid ID");
    return cartCount;
}


/* SAVE TRANSACTION */
static void saveTransaction(String product,double amount){

    try{

        File f=new File(transactionFile);
        boolean exists=f.exists();

        FileWriter fw=new FileWriter(f,true);

        if(!exists){
            fw.write("Product\tAmount\n");
        }

        fw.write(product+"\t"+amount+"\n");

        fw.close();

    }catch(Exception e){
        System.out.println("Error saving transaction");
    }
}


/* VIEW TRANSACTIONS */
static void viewTransactions(){

    try{

        File f=new File(transactionFile);

        if(!f.exists()){
            System.out.println("No transactions yet");
            return;
        }

        Scanner fs=new Scanner(f);

        while(fs.hasNextLine()){
            System.out.println(fs.nextLine());
        }

        fs.close();

    }catch(Exception e){
        System.out.println("Error reading transactions");
    }
}


/* CHECKOUT */
static int checkout(int cartCount,int[] cartId,
                     int n,int[] id,String[] name,
                     double[] price,double[] discount){

    double total=0;

    for(int c=0;c<cartCount;c++){

        for(int i=0;i<n;i++){

            if(cartId[c]==id[i]){

                double finalPrice=
                        price[i]-(price[i]*discount[i]/100);

                total+=finalPrice;

                System.out.println(name[i]+" = ₹"+finalPrice);

                saveTransaction(name[i],finalPrice);
            }
        }
    }

    System.out.println("Total Bill = ₹"+total);

    return 0; // FIX: clear cart after checkout
}



/* MAIN */
public static void main(String[] args){

    int[] id=new int[100];
    String[] name=new String[100];
    String[] category=new String[100];
    double[] price=new double[100];
    int[] stock=new int[100];
    double[] discount=new double[100];

    int[] cartId=new int[100];
    int cartCount=0;

    int n=loadProducts(id,name,category,price,stock,discount);

    int choice;

    do{

        System.out.println("\nONLINE SHOP MENU");
        System.out.println("1 Add Product");
        System.out.println("2 View Products");
        System.out.println("3 Add To Cart");
        System.out.println("4 Checkout");
        System.out.println("5 View Transactions");
        System.out.println("6 Exit");

        choice=sc.nextInt();

        switch(choice){

            case 1:

                if(n >= 100){
                    System.out.println("Storage full");
                    break;
                }

                System.out.print("ID: ");
                id[n]=sc.nextInt();
                sc.nextLine();

                System.out.print("Name: ");
                name[n]=sc.nextLine();

                System.out.print("Category: ");
                category[n]=sc.nextLine();

                System.out.print("Price: ");
                price[n]=sc.nextDouble();

                System.out.print("Stock: ");
                stock[n]=sc.nextInt();

                System.out.print("Discount: ");
                discount[n]=sc.nextDouble();

                addProductToFile(id[n],name[n],category[n],price[n],stock[n],discount[n]);
                n++;

                break;

            case 2:
                displayProducts(n,id,name,category,price,stock,discount);
                break;

            case 3:
                cartCount=addToCart(n,id,stock,cartId,cartCount);
                break;

            case 4:
                cartCount=checkout(cartCount,cartId,n,id,name,price,discount);
                saveProducts(n,id,name,category,price,stock,discount);
                break;

            case 5:
                viewTransactions();
                break;

            case 6:
                System.out.println("Thank you");
                break;

            default:
                System.out.println("Invalid choice");
        }

    }while(choice!=6);

}
}