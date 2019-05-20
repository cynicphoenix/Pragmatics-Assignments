import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader;
import java.util.Scanner; 

public class main {
    public static void main(String args[]) throws IOException, searchException, productAtSamePathException, outOfStockException {
        database amaconDatabase = new database();
        int revenue = 0;
        amaconDatabase.addFileToDatabase();
        amaconDatabase.addFileToCustomer();
    	Scanner sc = new Scanner(System.in);
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
        while(true){
        	System.out.println("------------------------------------------------------------");
            System.out.println("Press 1 : For Administrator");
            System.out.println("Press 2 : For Customer");
            System.out.println("Press 0 : To Exit");
            int choice = sc.nextInt();
        	System.out.println("------------------------------------------------------------");
            switch(choice){
                case 1 :
                    while(true) { //Administrator Menu
                    	System.out.println("------------------------------------------------------------");
                        System.out.println("Press 1 : To Insert Product");
                        System.out.println("Press 2 : To Delete Product");
                        System.out.println("Press 3 : Search Product");
                        System.out.println("Press 4 : Modify Product");
                        System.out.println("Press 5 : Display Database");
                        System.out.println("Press 6 : Display Customers Information");
                        System.out.println("Press 0 : To Exit as Administrator");
                        int ad_choice = sc.nextInt();
                        if(ad_choice == 1){ // Insert category/product
                            System.out.println("Press 1      : To enter Product");
                            System.out.println("Press ANYKEY : To enter Category/Subcategory");
                            int in_choice =  sc.nextInt();
                            System.out.print("Enter path : ");
                            String path = reader.readLine();
                            String product = "\0";
                            if(in_choice == 1){
                                System.out.print("Enter product : ");
                                product = reader.readLine();
                            }
                            amaconDatabase.insert(path, product);
                        }
                        else if(ad_choice == 2){ // Delete category/product
                            System.out.print("Enter path : ");
                            String path = reader.readLine();
                            try{
                                amaconDatabase.delete(path);
                            }
                            catch(deleteException e) {
                                System.out.println(e);
                            }
                        }
                        else if(ad_choice == 3){ // Search category/product
                            System.out.print("Enter product : ");
                            String product = reader.readLine();
                            System.out.println("------------------------------------------------------------");
                            try{
                                product search_product = amaconDatabase.searchProduct(product, 1);
                                System.out.println("Price  : "+search_product.price);
                                System.out.println("Stocks : "+search_product.stocks);
                                System.out.println("------------------------------------------------------------");
                            }
                            catch (searchException e) {
                                System.out.println(e);
                            }
                        }
                        else if(ad_choice == 4){ // Modify category/product
                            System.out.print("Enter product : ");
                            String product = reader.readLine();
                            try{
                                amaconDatabase.modify(product);
                            }
                            catch(searchException e){
                                System.out.println(e);
                            }
                        }
                        else if(ad_choice == 5)
                            amaconDatabase.printDatabase();
                        else if(ad_choice == 6)
                            amaconDatabase.printCustomerDatabase();
                        else if(ad_choice == 0) // Exit as administrator
                            break;
                        else
                            System.out.println("Enter correct choice!");
                    }
                break;
                case 2:// customer Menu
                    System.out.print("Enter Username : ");
                    String username = reader.readLine();
                    customer newCustomer = amaconDatabase.searchCustomer(username);
                    if(newCustomer == null){
                        System.out.println("Hey! New User");
                        newCustomer = new customer(username, 0, 0);
                        amaconDatabase.customerList.add(newCustomer);
                    }
                    else
                        System.out.println("Welcome Back "+newCustomer.username);

                    while (true){
                    	System.out.println("------------------------------------------------------------");
                        System.out.println("Press 1 : To Add Funds");
                        System.out.println("Press 2 : To Add Product to Cart");
                        System.out.println("Press 3 : Checkout Cart");
                        System.out.println("Press 4 : Print Cart");
                        System.out.println("Press 0 : To Exit as Customer");
                        int ad_choice = sc.nextInt();
                        if(ad_choice == 1){ // Add funds
                            System.out.print("Enter Funds : ");
                            int funds = sc.nextInt();
                            newCustomer.addFunds(funds);
                        }
                        else if(ad_choice == 2) {// Add product to cart
                            System.out.print("Enter Product : ");
                            String productName = reader.readLine();
                            System.out.println("Enter Quantity : ");
                            int quantity = sc.nextInt();
                            newCustomer.addProductToCart(amaconDatabase, productName, quantity);
                        }
                        else if(ad_choice == 3) // Checkout cart
                        try{
                            revenue += newCustomer.checkOutCart(amaconDatabase);
                        }
                        catch (insufficientFundException e){
                            System.out.println(e);
                        }
                        else if(ad_choice == 4)
                        	newCustomer.printCart();
                        else if(ad_choice == 0) // Exit as customer
                            break;
                        else
                        System.out.println("Enter correct choice!");    
                    }
                break;
                case 0:
                break;
                default:
                    System.out.println("Enter correct choice!");      
            }
            if(choice == 0){
            	System.out.println("------------------------------------------------------------");
                System.out.println("Exiting Portal!");
                break;
            }
        }
        System.out.println("Total Revenue of Amacon : Rs. "+revenue);
        System.out.println("------------------------------------------------------------");
        amaconDatabase.addCustomerToFile();
        amaconDatabase.addDatabaseToFile();
    }
}