package project;
import java.util.Scanner;

public class productSorting {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        String[] name = new String[n];
        double[] price = new double[n];
        double[] rating = new double[n];

        for(int i=0;i<n;i++){

            sc.nextLine();

            System.out.print("\nProduct Name: ");
            name[i] = sc.nextLine();

            System.out.print("Price: ");
            price[i] = sc.nextDouble();

            System.out.print("Rating: ");
            rating[i] = sc.nextDouble();
        }

        System.out.println("\n===== SORT MENU =====");
        System.out.println("1 Sort by Price (Low to High)");
        System.out.println("2 Sort by Price (High to Low)");
        System.out.println("3 Sort by Rating");
        System.out.println("4 Sort by Name (Alphabetical)");
        System.out.println("5 Show Top 3 Cheapest Products");
        System.out.println("6 Show Top 3 Highest Rated Products");

        int choice = sc.nextInt();

        switch(choice){

            case 1:
                sortPriceAscending(name,price,rating,n);
                break;

            case 2:
                sortPriceDescending(name,price,rating,n);
                break;

            case 3:
                sortRating(name,price,rating,n);
                break;

            case 4:
                sortName(name,price,rating,n);
                break;

            case 5:
                sortPriceAscending(name,price,rating,n);
                System.out.println("\nTop 3 Cheapest Products:");
                for(int i=0;i<Math.min(3,n);i++)
                    System.out.println(name[i]+" ₹"+price[i]+" Rating:"+rating[i]);
                break;

            case 6:
                sortRating(name,price,rating,n);
                System.out.println("\nTop 3 Highest Rated Products:");
                for(int i=0;i<Math.min(3,n);i++)
                    System.out.println(name[i]+" ₹"+price[i]+" Rating:"+rating[i]);
                break;

            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }

    /* Sort Price Ascending */
    static void sortPriceAscending(String name[], double price[], double rating[], int n){

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){

                if(price[j] > price[j+1]){

                    // swap price
                    double tp = price[j];
                    price[j] = price[j+1];
                    price[j+1] = tp;

                    // swap name
                    String tn = name[j];
                    name[j] = name[j+1];
                    name[j+1] = tn;

                    // swap rating
                    double tr = rating[j];
                    rating[j] = rating[j+1];
                    rating[j+1] = tr;
                }
            }
        }

        System.out.println("\nProducts Sorted by Price (Low -> High):");

        for(int i=0;i<n;i++)
            System.out.println(name[i]+" ₹"+price[i]+" Rating:"+rating[i]);
    }

    /* Sort Price Descending */
    static void sortPriceDescending(String name[], double price[], double rating[], int n){

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){

                if(price[j] < price[j+1]){

                    double tp = price[j];
                    price[j] = price[j+1];
                    price[j+1] = tp;

                    String tn = name[j];
                    name[j] = name[j+1];
                    name[j+1] = tn;

                    double tr = rating[j];
                    rating[j] = rating[j+1];
                    rating[j+1] = tr;
                }
            }
        }

        System.out.println("\nProducts Sorted by Price (High -> Low):");

        for(int i=0;i<n;i++)
            System.out.println(name[i]+" ₹"+price[i]+" Rating:"+rating[i]);
    }

    /* Sort by Rating */
    static void sortRating(String name[], double price[], double rating[], int n){

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){

                if(rating[j] < rating[j+1]){

                    double tr = rating[j];
                    rating[j] = rating[j+1];
                    rating[j+1] = tr;

                    String tn = name[j];
                    name[j] = name[j+1];
                    name[j+1] = tn;

                    double tp = price[j];
                    price[j] = price[j+1];
                    price[j+1] = tp;
                }
            }
        }

        System.out.println("\nProducts Sorted by Rating:");

        for(int i=0;i<n;i++)
            System.out.println(name[i]+" ₹"+price[i]+" Rating:"+rating[i]);
    }

    /* Sort by Name */
    static void sortName(String name[], double price[], double rating[], int n){

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){

                if(name[j].compareToIgnoreCase(name[j+1]) > 0){

                    String tn = name[j];
                    name[j] = name[j+1];
                    name[j+1] = tn;

                    double tp = price[j];
                    price[j] = price[j+1];
                    price[j+1] = tp;

                    double tr = rating[j];
                    rating[j] = rating[j+1];
                    rating[j+1] = tr;
                }
            }
        }

        System.out.println("\nProducts Sorted Alphabetically:");

        for(int i=0;i<n;i++)
            System.out.println(name[i]+" ₹"+price[i]+" Rating:"+rating[i]);
    }
}