
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
            // System.out.println(st);

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

        // Please comment out part of the code that you want to use.
        
        // PART 1
        // QUESTION 1 - BUBBLE SORT
        // Sort the products array by productName using bubble sort algorithm
        Product[] arr = Arrays.copyOf(products, products.length); // copy of the array in order to keep the unsorted original one
        // Below are different sizes of the products array (10,100, 1000) for the running time calculation:
        // Product[] arr = Arrays.copyOf(products, 10);
        // Product[] arr = Arrays.copyOf(products, 100);
        // Product[] arr = Arrays.copyOf(products, 1000);
        long startTime1 = System.nanoTime();
        Product.bubbleSortProduct(arr);
        long elapsedTime1 = System.nanoTime() - startTime1; // calculate the time bubbleSortProduct() took to run

        // Display the sorted array using Bubble Sort
        System.out.println("======================================");
        System.out.println("Sorted values with bubbleSort: ");
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
        } 

        System.out.println("======================================");

        // QUESTION 2 - QUICKSORT
        // Sort the products array by productName using quick sort algorithm
        Product[] arr2 = Arrays.copyOf(products, products.length);
        // Below are different sizes of the products array (10,100, 1000) for the running time calculation:
        // Product[] arr2 = Arrays.copyOf(products, 10);
        // Product[] arr2 = Arrays.copyOf(products, 100);
        // Product[] arr2 = Arrays.copyOf(products, 1000);
        long startTime2 = System.nanoTime();
        Product.quickSortProduct(arr2, 0, 9999);  
        // Product.quickSortProduct(arr2, 0, 9); 
        // Product.quickSortProduct(arr2, 0, 99); 
        // Product.quickSortProduct(arr2, 0, 999); 
        long elapsedTime2 = System.nanoTime() - startTime2; // calculate the time quickSortProduct() took to run

        // // Display the sorted array using Quick Sort
        // System.out.println("Sorted values with quickSort: ");
        // for (int j = 0; j < arr2.length; j++) {
        //     System.out.println(arr2[j]);
        // } 

        System.out.println("======================================");


        // QUESTION 3 - ELAPSED TIMES
        // Display the running times of the bubble sort and quick sort algorithms
        System.out.println("Elapsed Time of bubbleSort: " + elapsedTime1 + " Nanosecond");
        System.out.println("Elapsed Time of quickSort: " + elapsedTime2 + " Nanosecond");
        System.out.println("======================================");
        
        // QUESTION 4 - BINARY SEARCH

        // Look for a specific productID inside the products array
        int key = 4000; // productID to search for
        int foundAt = Product.binarySearchProduct(products, 0, products.length-1, key); // the input data must be a sorted array (products is sorted by productID)
        if (foundAt == -1) {
            // display a message if product is not found
            System.out.println("The product is not in the list.");
        } else {
            // display the corresponding product description if found
            System.out.println("Product is found. Product information:");
            System.out.println("Product ID: " + products[foundAt].getProductID() +
            "\nProduct name: " + products[foundAt].getProductName() +
            "\nDescription: " + products[foundAt].getProductDescription() + 
            "\nPrice: " + products[foundAt].getProductPrice() + 
            "\nType: " + products[foundAt].getProductType() );
        }

        // PART 2
        // QUESTION 1 
        // products[1].setProductType("work"); // test to see if setting the product type to a wrong value throw an exception


        // QUESTION 2
        // products[1].setProductPriceRange(0.9f); // test to see if setting the product price to a value that is too low display an alert message
        // products[1].setProductPriceRange(10000f); // value that is too high
    }

}
