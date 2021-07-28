/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yalemisew
 */
class Product implements Comparable<Object> {

    private int productID;
    private String productName;
    private String productDescription;
    private float productPrice;
    private String productType;

    // constructor
    public Product(int productID, String productName, String productDescription, float productPrice, String productType){
        setProductID(productID);
        setProductName(productName);
        setProductDescription(productDescription);
        setProductPrice(productPrice);
        setProductType(productType);
    }

    public int getProductID() {
        return productID;
    }

    private void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    private void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    private void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    private void setProductPrice(float productPrice) {
        this.productPrice = productPrice;        
    }

    // PART 2 - QUESTION 2
    public void setProductPriceRange(float productPrice) {

        // check if the input is accurate or not
        // check if the price is between a range (between 1$ and 9999$)
        if (productPrice >= 1 && productPrice <= 9999) {
            this.productPrice = productPrice;
        // alert the user if the price entered is out of the range    
        } else {
            System.out.println("Product price is not correct, please update the value.");
        }
        
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductType(String productType) {

        // PART 2 - QUESTION 1
        // check if the input is accurate or not
        // throw a custom exception defined in the ProductTypeException class if product type is not correct 
        // Product type must be Hardware, Software or Service
        try {          
            if (productType.equalsIgnoreCase("Hardware") || productType.equalsIgnoreCase("Software") || productType.equalsIgnoreCase("Service")) {              
                this.productType = productType;
            } else {
                // throw the exception if the product type is not correct
                // customed message is defined here
                throw new ProductTypeException("Product type can only be 'Hardware', 'Software' or 'Service'. Please correct the product Type.");
            }
            
        } catch (ProductTypeException ex) {
            System.out.println(ex.toString());
        }
        
    }

    public String getProductType() {
        return productType;
    }

    // so the product objects can be compared when sorting/searching
    // NOTE: this will only allow comparisons based on the productName
    @Override
    public int compareTo(Object obj) {
        Product prod = (Product) obj;
        return productName.compareTo(prod.getProductName());
    }

    // return a String containing the product details
    @Override
    public String toString() {
        return productID + " " + productName + " " + productDescription + " " + productPrice + " " + productType;
    }

    
    // PART1: Sorting and searching

    // Question 1 - bubbleSort

    // that methods accepts an array of Object, which then will be used for the products in the main method
    static void bubbleSortProduct(Object[] arr) {
        int n = arr.length;
        Product temp = null;
        for (int i = 0; i < n; i++){
            for (int j = 1; j < (n - i); j++){
                // check if the following product name should be placed first depending on the alphabetical order
                // cast the array element as a Product to be able to use the customized compareTo()
                if (((Product) arr[j]).compareTo(arr[j-1]) < 0){ // use the overriden compareTo method in order to be able to pass Product objects.
                    temp = (Product) arr[j-1];
                    arr[j-1] = arr[j]; // swap the elements
                    arr[j] = temp;
                }
            }
        }
        
    }

    // Question 2 - quickSort

    // that methods accepts an array of Object, which then will be used for the products in the main method
    static void quickSortProduct(Object[] arr, int start, int end) {
        
        if (start < end) {
            int partitionIndex = partition(arr, start, end); // partition() is defined below
            quickSortProduct(arr, start, partitionIndex - 1);
            quickSortProduct(arr, partitionIndex + 1, end);
        }
    }

    // quickSortProduct() uses partition()
    static int partition(Object[] arr, int start, int end) {
        Product temp;
        Product pivot = (Product) arr[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {	
            // check if the productName is in alphabetic order less than the pivot value
            if (((Product) arr[i]).compareTo(pivot) < 0) { // cast the array element as a Product to be able to use the customized compareTo()
                temp = (Product) arr[i]; 
                arr[i] = arr[partitionIndex]; // swap the array elements 
                arr[partitionIndex] = temp;
                
                partitionIndex++;
            }
        }
        temp = (Product) arr[partitionIndex];
        arr[partitionIndex] = arr[end];
        arr[end] = temp;
        return partitionIndex;
    }
    

    // Question 4 - Binary Search
    // search if an element (productID) exists in the array of Product
    // returns the index if the product is found and -1 if it's not found
    static int binarySearchProduct(Product arr[], int l, int r, int searchKey) {

        if (r >= l) {
            int mid = l + (r - l) / 2;
            // check if the productID is equal to the value that we are searching for
            if (arr[mid].productID == searchKey) {
                return mid; // returns the index if the product is found
            }
            // continue searching for the searchKey
            if (arr[mid].productID > searchKey) {
                return binarySearchProduct(arr, l, mid - 1, searchKey);
            }
            return binarySearchProduct(arr, mid + 1, r, searchKey);
        }
        return -1; // searchKey was not found in the array
    }    
}
