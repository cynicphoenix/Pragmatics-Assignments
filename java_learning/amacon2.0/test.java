import org.junit.Test;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import java.io.*;
import java.util.*;


public class test {

    @Test
    public void test1() throws IOException {
        System.out.println("----------------------------------------------------");
        System.out.println("Running Test 1 : Checking product at same path exception!");
        database amaconTest = new database();
        String productName = "oneplus";
        String categoryName = "electronics";
        String subCategoryName = "smartphone";
        int stocks = 10;
        int price = 30000;
        product productTest = new product(productName, price, stocks);
        subCategory subCategoryTest = new subCategory(subCategoryName);
        category categoryTest = new category(categoryName);
        subCategoryTest.productList.add(productTest);
        categoryTest.subCategoryList.add(subCategoryTest);
        amaconTest.categoryList.add(categoryTest);
        customer testCustomer = new customer("amit", 1000, 1);
        product productInCart = new product("onepus", 1, 30000);
        testCustomer.cart.add(productInCart);
        try {
            amaconTest.insert("electronics>smartphone", "oneplus");
        } catch (productAtSamePathException | searchException ex) {
            System.err.println(ex);
        }
        System.out.println("End of Test 1");
        System.out.println("----------------------------------------------------");
    }

    @Test
    public void test2() throws IOException {
        System.out.println("Running Test 2 : Checking delete exception!");
        database amaconTest = new database();
        String productName = "oneplus";
        String categoryName = "electronics";
        String subCategoryName = "smartphone";
        int stocks = 10;
        int price = 30000;
        product productTest = new product(productName, price, stocks);
        subCategory subCategoryTest = new subCategory(subCategoryName);
        category categoryTest = new category(categoryName);
        subCategoryTest.productList.add(productTest);
        categoryTest.subCategoryList.add(subCategoryTest);
        amaconTest.categoryList.add(categoryTest);
        customer testCustomer = new customer("amit", 1000, 1);
        product productInCart = new product("onepus", 1, 30000);
        testCustomer.cart.add(productInCart);
        try {
            amaconTest.delete("electronics>smartphone>apple");
        } catch (deleteException | searchException ex) {
            System.err.println(ex);
        }
        System.out.println("End of Test 2");
        System.out.println("----------------------------------------------------");
    }
    
    @Test
    public void test3() throws IOException {
        System.out.println("Running Test 3 : Checking search exception!");
        database amaconTest = new database();
        String productName = "oneplus";
        String categoryName = "electronics";
        String subCategoryName = "smartphone";
        int stocks = 10;
        int price = 30000;
        product productTest = new product(productName, price, stocks);
        subCategory subCategoryTest = new subCategory(subCategoryName);
        category categoryTest = new category(categoryName);
        subCategoryTest.productList.add(productTest);
        categoryTest.subCategoryList.add(subCategoryTest);
        amaconTest.categoryList.add(categoryTest);
        customer testCustomer = new customer("amit", 1000, 1);
        product productInCart = new product("onepus", 1, 30000);
        testCustomer.cart.add(productInCart);
        try {
            amaconTest.searchProduct("nokia", 1);
        } catch (searchException ex) {
            System.err.println(ex);
        }
        System.out.println("End of Test 3");
        System.out.println("----------------------------------------------------");
    }

    @Test
    public void test4() throws IOException {
        System.out.println("Running Test 4 : Checking out of stock exception!");
        database amaconTest = new database();
        String productName = "oneplus";
        String categoryName = "electronics";
        String subCategoryName = "smartphone";
        int stocks = 0;
        int price = 30000;
        product productTest = new product(productName, price, stocks);
        subCategory subCategoryTest = new subCategory(subCategoryName);
        category categoryTest = new category(categoryName);
        subCategoryTest.productList.add(productTest);
        categoryTest.subCategoryList.add(subCategoryTest);
        amaconTest.categoryList.add(categoryTest);
        customer testCustomer = new customer("amit", 1000, 0);
        amaconTest.customerList.add(testCustomer);
        try {
            amaconTest.customerList.get(0).addProductToCart(amaconTest, "oneplus", 1);
        } catch (outOfStockException | searchException ex) {
            System.err.println(ex);
        }
        System.out.println("End of Test 4");
        System.out.println("----------------------------------------------------");
    }

    @Test
    public void test5() throws IOException {
        System.out.println("Running Test 5 : Checking insufficient funds exception!");
        database amaconTest = new database();
        String productName = "oneplus";
        String categoryName = "electronics";
        String subCategoryName = "smartphone";
        int stocks = 1;
        int price = 30000;
        product productTest = new product(productName, price, stocks);
        subCategory subCategoryTest = new subCategory(subCategoryName);
        category categoryTest = new category(categoryName);
        subCategoryTest.productList.add(productTest);
        categoryTest.subCategoryList.add(subCategoryTest);
        amaconTest.categoryList.add(categoryTest);
        customer testCustomer = new customer("amit", 1000, 0);
        product productInCart = new product("oneplus", 30000, 1);
        testCustomer.cart.add(productInCart);
        amaconTest.customerList.add(testCustomer);
        try {
            testCustomer.checkOutCart(amaconTest);
        } catch (insufficientFundException | searchException ex) {
            System.err.println(ex);
        }
        System.out.println("End of Test 5");
        System.out.println("----------------------------------------------------");
    }

    @Test
    public void test6() throws IOException {
        System.out.println("Running Test 6 : Checking serialization/deserialization exception!");
        database amaconTest = new database();
        String productName = "oneplus";
        String categoryName = "electronics";
        String subCategoryName = "smartphone";
        int stocks = 10;
        int price = 30000;
        product productTest = new product(productName, price, stocks);
        subCategory subCategoryTest = new subCategory(subCategoryName);
        category categoryTest = new category(categoryName);
        subCategoryTest.productList.add(productTest);
        categoryTest.subCategoryList.add(subCategoryTest);
        amaconTest.categoryList.add(categoryTest);
        customer testCustomer = new customer("amit", 1000, 1);
        product productInCart = new product("oneplus", 30000, 1);
        testCustomer.cart.add(productInCart);
        amaconTest.customerList.add(testCustomer);
        amaconTest.addCustomerToFile();
        amaconTest.addDatabaseToFile();
        database amaconTest2 = new database();
        try{
        amaconTest2.addFileToDatabase();
        amaconTest2.addFileToCustomer();
        System.out.println("Database : ");
        amaconTest2.printDatabase();
        System.out.println("Customer : ");
        amaconTest2.printCustomerDatabase();
        } catch (searchException | productAtSamePathException ex){
            System.out.println(ex);
        }
        System.out.println("End of Test 6");
        System.out.println("----------------------------------------------------");
    }
}