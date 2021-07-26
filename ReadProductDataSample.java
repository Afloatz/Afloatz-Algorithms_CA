
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class ReadProductDataSample {

    public static void main(String[] args) throws Exception {
        //parsing and reading the CSV file data into the product (object) array
        // provide the path here...
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        //Don't forget to change the file to the location on your computer below.
        Scanner sc = new Scanner(new File(dir + "/Product_Data.csv"));
        Product[] products = new Product[10000];

        // this will just print the header in CSV file
        System.out.println(sc.nextLine());

        int i = 0;
        String st = "";
        while (sc.hasNextLine()) //returns a boolean value
        {

            st = sc.nextLine();
            st = st.replace("\"", "");
            String[] data = st.split(",");
            System.out.println(st);

            products[i] = new Product(Integer.parseInt(data[0]), data[1], data[2], Float.parseFloat(data[3]), data[4]);
            i++;
        }
        sc.close();  //closes the scanner

        // We can print product details due to overridden toString method in Product class
        // System.out.println("==================");
        // System.out.println(products[0]);
        // System.out.println(products[1]);
        // System.out.println("==================");
        // we can compare products based on their name due to overridden CompareTo method in Product class
        // System.out.println(products[0] == products[1]);

        
        // QUESTION 1 - BUBBLESORT
        Product[] arr = Arrays.copyOf(products, products.length); // copy of the array in order to keep the unsorted original one
        // Object[] arr = Arrays.copyOf(products, 10);
        // Object[] arr = Arrays.copyOf(products, 100);
        // Object[] arr = Arrays.copyOf(products, 1000);
        long startTime1 = System.nanoTime();
        Product.bubbleSortProduct(arr);
        long elapsedTime1 = System.nanoTime() - startTime1;

        // System.out.println("Sorted values with bubbleSort: ");
        // for (int j = 0; j < arr.length; j++) {
        //     System.out.println(arr[j]);
        // } 

        System.out.println("======================================");

        // QUESTION 2 - QUICKSORT
        Product[] arr2 = Arrays.copyOf(products, products.length);
        // Object[] arr2 = Arrays.copyOf(products, 10);
        // Object[] arr2 = Arrays.copyOf(products, 100);
        // Object[] arr2 = Arrays.copyOf(products, 1000);
        long startTime2 = System.nanoTime();
        Product.quickSortProduct(arr2, 0, 9999);  
        // Product.quickSortProduct(arr2, 0, 9); 
        // Product.quickSortProduct(arr2, 0, 99); 
        // Product.quickSortProduct(arr2, 0, 999); 
        long elapsedTime2 = System.nanoTime() - startTime2;     

        // System.out.println("Sorted values with quickSort: ");
        // for (int j = 0; j < arr2.length; j++) {
        //     System.out.println(arr2[j]);
        // } 

        System.out.println("======================================");


        // QUESTION 3 - ELAPSED TIMES
        System.out.println("Elapsed Time of bubbleSort: " + elapsedTime1 + " Nanosecond");
        System.out.println("Elapsed Time of quickSort: " + elapsedTime2 + " Nanosecond");
        System.out.println("======================================");
        
        // QUESTION 4 - BINARY SEARCH

        int key = 4000;
        int foundAt = Product.binarySearchProduct(products, 0, products.length-1, key);
        System.out.println("Product ID: " + products[foundAt].getProductID() + "\nProduct name: " + products[foundAt].getProductName() + "\nDescription: " + products[foundAt].getProductDescription() + "\nPrice: " + products[foundAt].getProductPrice() + "\nType: " + products[foundAt].getProductType() );


    }

}
