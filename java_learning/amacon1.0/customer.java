import java.util.*;
import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 

public class customer {
    public int funds = 0;
    public ArrayList <product> cart;
    
    public customer(){
    	cart = new ArrayList<product>();
    	funds = 0;
    }

    public void addFunds(int funds_field){
        funds += funds_field;
    }
    
    public void printCart() {
    	System.out.println("------------------------------------------------------------");
    	System.out.println("Available Funds : "+funds);
    	System.out.println("------------------------------------------------------------");
        System.out.println("Cart details : ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Product\t\tQuantity");
        for(int i = 0; i < cart.size(); i++)
            System.out.println(cart.get(i).productName+"\t\t"+cart.get(i).stocks);
        System.out.println("------------------------------------------------------------");
    }

    public product searchCart(String productName) {
    	int i = 0;
    	for(i = 0; i < cart.size(); i++)
    		if(cart.get(i).productName.equals(productName))
    			return cart.get(i);
    	return null;
    }
    
    public void addProductToCart(database amaconDatabase) throws IOException{
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Product : ");
        String productName = reader.readLine();
        product search_product = amaconDatabase.searchProduct(productName);
        if(search_product == null){
            System.out.println("Exception : Product does not exists!");      
            return;
        }
        product addNewProduct;
        addNewProduct = searchCart(productName);
        if(addNewProduct != null) {
        	 System.out.println("Product already exists in Cart!");
        	 System.out.println("Press 1 : To change Quantity of Product");
             System.out.println("Press ANYKEY : To Move back to MENU");
             int choice = sc.nextInt();
             if(choice == 1){
                 System.out.print("Enter new Quantity : ");
                 int quantity = sc.nextInt();
                 addNewProduct.stocks = quantity;
             }
             return;
        }
        	
        System.out.print("Enter Quantity : ");
        int quantity = sc.nextInt();
        addNewProduct = new product(search_product.productName, search_product.price, quantity);
        cart.add(addNewProduct);
    }

    public int checkOutCart(database amaconDatabase) { //Cart checkout portal
    	if(cart.isEmpty()) {
    		System.out.println("Cart is Empty!\nBuy Something first!");
    		return 0;
    	}
        int cart_total = 0;
        Scanner sc =  new Scanner(System.in); 
        for(int i = 0; i < cart.size(); i++) {
            product product_exist = amaconDatabase.searchProduct(cart.get(i).productName);
            if(product_exist == null) {
                System.out.println("Exception : "+cart.get(i).productName+" does not exists!");
                System.out.println("Press 1      : To remove Product from Cart");
                System.out.println("Press ANYKEY : To EXIT Cart");
                int choice = sc.nextInt();
                if(choice == 1)
                    cart.remove(i);
                
                return 0;
            }
            if(product_exist.stocks < cart.get(i).stocks){
                System.out.println("Exception : Insufficient "+product_exist.productName+" stocks!");
                System.out.println("Available stocks : "+product_exist.stocks);
                System.out.println("Press 1 : To remove Product from Cart");
                System.out.println("Press 2 : To change Quantity of Product");
                System.out.println("Press ANYKEY : To EXIT Cart");
                int choice = sc.nextInt();
                if(choice == 1)
                    cart.remove(i);
                else if(choice == 2){
                    System.out.print("Enter new Quantity : ");
                    int quantity = sc.nextInt();
                    cart.get(i).stocks = quantity;
                }
                
                return 0;
            }
            cart_total += product_exist.price * cart.get(i).stocks;
        }
        if(cart_total > funds){
            System.out.println("Insufficient Funds!");
            
            return 0;
        }
        funds -= cart_total;
        System.out.println("------------------------------------------------------------");
        System.out.println("Cart details : ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Product\t\tPrice\t\tQuantity");
        while(true) {
            if(cart.size() == 0)
                break;
            product product_exist = amaconDatabase.searchProduct(cart.get(0).productName);
            System.out.println(cart.get(0).productName+"\t\t"+product_exist.price+"\t\t"+cart.get(0).stocks);
            product_exist.stocks -= cart.get(0).stocks;
            cart.remove(0);
        }
        System.out.println("------------------------------------------------------------");
        System.out.println("Payable Amount  : "+cart_total);
        System.out.println("Available funds : "+funds);
        
        return cart_total;
    }
}