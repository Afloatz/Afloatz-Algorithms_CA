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
    public Product(int productID, String productName, String productDescription, float productPrice, String productType) {
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

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductType(String productType) throws ProductTypeException {

        // put the try-catch here
        if (productType.equalsIgnoreCase("Hardware") || productType.equalsIgnoreCase("Software") || productType.equalsIgnoreCase("Service")) {
            this.productType = productType;
        } else {        
            throw new ProductTypeException("Product type can only be 'Hardware', 'Software' or 'Service'");
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
    // I used the bubbleSort method of the lecture and modified it to accept an array of Object instead of int
    static void bubbleSortProduct(Object[] arr) {
        int n = arr.length;
        Product temp = null;
        for (int i = 0; i < n; i++){
            for (int j = 1; j < (n - i); j++){
                // cast the array element as a Product to be able to use the customized compareTo()
                if (((Product) arr[j]).compareTo(arr[j-1]) < 0){ // use the overriden compareTo method in order to be able to pass Product objects.
                    temp = (Product) arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        
    }

    // Question 2 - quickSortProduct

    // that methods accepts an array of Object, which then will be used for the products in the main method
    // I used the quickSort method of the lecture and modified it to accept an array of Object instead of int
    static void quickSortProduct(Object[] arr, int start, int end) {
        
        if (start < end) {
            int partitionIndex = partition(arr, start, end);
            quickSortProduct(arr, start, partitionIndex - 1);
            quickSortProduct(arr, partitionIndex + 1, end);
        }
    }

    static int partition(Object[] arr, int start, int end) {
        Product temp;
        Product pivot = (Product) arr[end];
        int partitionIndex = start;
        for (int i = start; i < end; i++) {	
            if (((Product) arr[i]).compareTo(pivot) < 0) { // cast the array element as a Product to be able to use the customized compareTo()
                temp = (Product) arr[i];
                arr[i] = arr[partitionIndex];
                arr[partitionIndex] = temp;
                
                partitionIndex++;
            }
        }
        temp = (Product) arr[partitionIndex];
        arr[partitionIndex] = arr[end];
        arr[end] = temp;
        return partitionIndex;
    }
    

    // Question 3 - binarySearchProduct
    // I used the binarySearch method of the lecture and modified it to accept an array of Product instead of int
    static int binarySearchProduct(Product arr[], int l, int r, int searchKey) {

        if (r >= l) {
            int mid = l + (r - l) / 2;
            // check if the productID is equal to the value that we are searching for
            if (arr[mid].productID == searchKey) {
                return mid;
            }
            if (arr[mid].productID > searchKey) {
                return binarySearchProduct(arr, l, mid - 1, searchKey);
            }
            return binarySearchProduct(arr, mid + 1, r, searchKey);
        }
        return -1;
    }


    // PART 2
    // setProductType (handle the exception in that method)
    // check if the input is accurate or not

    
}
