import java.util.*; 
import java.io.FileReader; 
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.File;

public class database{
    public ArrayList<category> categoryList;
    public ArrayList<customer> customerList;
    
    public database(){
        categoryList = new ArrayList<category>();
        customerList = new ArrayList<customer>();
    }

    public void printDatabase() { // Prints Database
    	int i = 0, j = 0, k = 0;
    	System.out.println("------------------------------------------------------------");
    	for(i = 0; i < categoryList.size(); i++){
    		System.out.println("------------------------------------------------------------");
    		System.out.println("Category\t: "+categoryList.get(i).categoryName);
        	System.out.println("------------------------------------------------------------");
            for(j = 0; j < categoryList.get(i).subCategoryList.size(); j++){
        		System.out.println("Subcategory\t: "+categoryList.get(i).subCategoryList.get(j).subCategoryName);
            	System.out.println("------------------------------------------------------------");
                for(k = 0; k < categoryList.get(i).subCategoryList.get(j).productList.size(); k++){
                	System.out.println("Product\t\t: "+categoryList.get(i).subCategoryList.get(j).productList.get(k).productName);
                	System.out.println("Price\t\t: "+categoryList.get(i).subCategoryList.get(j).productList.get(k).price);
                	System.out.println("Stocks\t\t: "+categoryList.get(i).subCategoryList.get(j).productList.get(k).stocks);
                	System.out.println("------------------------------------------------------------");
                }
            }
    	}
    }
    
    public void printCustomerDatabase() {
    	for(int i = 0; i < customerList.size(); i++) {
    		System.out.println("------------------------------------------------------------");
    		System.out.println("Username :\t"+customerList.get(i).username);
    		System.out.println("Funds    :\t"+customerList.get(i).funds);
    		System.out.println("Items    :\t"+customerList.get(i).items);
    		System.out.println("------------------------------------------------------------");
    		System.out.println("Product\t\tQuantity");
    		for(int j = 0; j < customerList.get(i).cart.size(); j++) {
    			System.out.println(customerList.get(i).cart.get(j).productName+"\t\t"+customerList.get(i).cart.get(j).stocks);
    		}
    	}
    	System.out.println("------------------------------------------------------------");
    }
    
    public customer searchCustomer(String username){ // Search existing customer
        int flag = 0, i = 0;
        for(i = 0; i < customerList.size(); i++)
            if(customerList.get(i).username.equals(username)){
                flag = 1;
                break;
            }
        if(flag == 0)
            return null;
        return customerList.get(i);
    }
    
    public category searchCategory(String categoryName) { //Search category
        int flag = 0; //To check whether category exist or not
        int i;
        for(i = 0; i < categoryList.size(); i++){
            if(categoryList.get(i).categoryName.equals(categoryName)){
                flag = 1;
                break;
            }
        }
        if(flag == 0)
            return null;
        else
            return categoryList.get(i);
    }

    public subCategory searchSubCategory(String subCategoryName) { //Search subCategory
    	int i = 0, j = 0;
        int flag = 0; //To check whether subCataegory exist or not
        for(i = 0; i < categoryList.size(); i++){
            for(j = 0; j < categoryList.get(i).subCategoryList.size(); j++){
                if(categoryList.get(i).subCategoryList.get(j).subCategoryName.equals(subCategoryName)){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)
                break;
        }
        if(flag == 0)
            return null;
        else
            return categoryList.get(i).subCategoryList.get(j);
    }

    public product searchProduct(String productName, int check) throws searchException{ //Search product
    	int i = 0, j = 0, k = 0;
        int flag = 0; //To check whether product exist or not
        for(i = 0; i < categoryList.size(); i++){
            for(j = 0; j < categoryList.get(i).subCategoryList.size(); j++){
                for(k = 0; k < categoryList.get(i).subCategoryList.get(j).productList.size(); k++){
                    if(categoryList.get(i).subCategoryList.get(j).productList.get(k).productName.equals(productName)){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1)
                    break;
            }
            if(flag == 1)
                break;
        }
        if(flag == 0 && check == 1)
            throw new searchException("Product is not present!");
        if(flag == 0)
            return null;
        else {
        	if(check == 1) {
        		System.out.println("Path    : "+categoryList.get(i).categoryName+">"+categoryList.get(i).subCategoryList.get(j).subCategoryName);
        		System.out.println("Product : "+productName);
            }
        }
        return categoryList.get(i).subCategoryList.get(j).productList.get(k);
    }

    public void insertCategory(String categoryName, int check) throws productAtSamePathException{ // Insert category
        category new_category = searchCategory(categoryName);
        if(new_category != null){
        	if(check == 0) 
        		throw new productAtSamePathException("Category already present!");
            return;
        }
        category addNewCategory = new category(categoryName);
        categoryList.add(addNewCategory);
    }

    public void insertSubCategory(String categoryName, String subCategoryName, int check) throws productAtSamePathException{ // Insert subCategory
        category search_category = searchCategory(categoryName);
        if(search_category == null){ // Insert category>subCategory
            category addNewCategory = new category(categoryName);
            categoryList.add(addNewCategory);
            subCategory addNewSubCategory = new subCategory(subCategoryName);
            addNewCategory.subCategoryList.add(addNewSubCategory);
        }
        else{ 
            subCategory search_sub_category = searchSubCategory(subCategoryName);
            if(search_sub_category != null) { // subCategory already exist
            	if(check == 0)
            		throw new productAtSamePathException("Subcategory already present!");
                return;
            } // Insert subCategory
            subCategory addNewSubCategory = new subCategory(subCategoryName);
            search_category.subCategoryList.add(addNewSubCategory);
        }
    }

    public void insertProduct(String categoryName, String subCategoryName, String productName, int check, int price, int stocks) throws productAtSamePathException, searchException {
        category search_category = searchCategory(categoryName);
        Scanner sc = new Scanner(System.in);
        if(search_category == null){ // Insert category>subCategory>product
            category addNewCategory = new category(categoryName);
            categoryList.add(addNewCategory);
            subCategory addNewSubCategory = new subCategory(subCategoryName);
            addNewCategory.subCategoryList.add(addNewSubCategory);
            if(check == 0) {
            	System.out.print("Enter Price of product : ");
            	price = sc.nextInt();
            	System.out.print("Enter Stocks of product : ");
            	stocks = sc.nextInt();
            }
            product addNewProduct = new product(productName, price, stocks);
            addNewSubCategory.productList.add(addNewProduct);
        }
        else{
            subCategory search_sub_category = searchSubCategory(subCategoryName);
            if(search_sub_category == null){ // Insert subCategory>product
                subCategory addNewSubCategory = new subCategory(subCategoryName);
                search_category.subCategoryList.add(addNewSubCategory);
                if(check == 0) {
                	System.out.print("Enter Price of product : ");
                	price = sc.nextInt();
                	System.out.print("Enter Stocks of product : ");
                	stocks = sc.nextInt();
                };
                product addNewProduct = new product(productName, price, stocks);
                addNewSubCategory.productList.add(addNewProduct);
            }
            else{
                product search_product = searchProduct(productName, 0);
                if(search_product != null){ // Product already exist
                	if(check == 0)
                		throw new productAtSamePathException("Product already present!"); 
                    return;
                }// Insert Product
                if(check == 0) {
                	System.out.print("Enter Price of product : ");
                	price = sc.nextInt();
                	System.out.print("Enter Stocks of product : ");
                	stocks = sc.nextInt();
                }
                product addNewProduct = new product(productName, price, stocks);
                search_sub_category.productList.add(addNewProduct);
            }
        }
        
    }
    
    public void insertFromFile(String line) throws searchException, productAtSamePathException{ // Insert Database from file 
    	String categoryName, subCategoryName, productName;
    	int stocks, price;
    	int first_path_sign = line.indexOf(">");
    	if(first_path_sign == -1) { // Add Category 
    		categoryName = line;
    		insertCategory(line, 1);
    		return;
    	}
    	categoryName = line.substring(0, first_path_sign);
        int second_path_sign = line.indexOf(">", first_path_sign + 1);
        if(second_path_sign == -1) { // Add subCategory
        	subCategoryName = line.substring(first_path_sign + 1, line.length());
        	insertSubCategory(categoryName, subCategoryName, 1);
        	return;
        }
        int third_path_sign = line.indexOf(">", second_path_sign + 1);
        int fourth_path_sign = line.indexOf(">", third_path_sign + 1);
        subCategoryName = line.substring(first_path_sign + 1, second_path_sign);
        productName = line.substring(second_path_sign + 1, third_path_sign);
        price = Integer.parseInt(line.substring(third_path_sign + 1, fourth_path_sign));
        stocks = Integer.parseInt(line.substring(fourth_path_sign + 1, line.length()));
    	insertProduct(categoryName, subCategoryName, productName, 1, price, stocks);
    }

    public void insert(String path, String productName) throws productAtSamePathException, searchException {
        String categoryName, subCategoryName;
        int first_path_sign = path.indexOf(">");
        if(productName != "\0") { // Insert Product
            categoryName = path.substring(0, first_path_sign);   
            subCategoryName = path.substring(first_path_sign + 1, path.length());
            try{
                insertProduct(categoryName, subCategoryName, productName, 0, 0, 0);
            } catch (productAtSamePathException e){
                System.out.println(e);
                return;
            }
            return;
        }
        if(first_path_sign == -1){ // Insert Category
            categoryName = path;
            try {
                insertCategory(categoryName, 0);
            } catch (productAtSamePathException e) {
                System.out.println(e);
                return;
            }
        }
        else{ //Insert subCategory
            categoryName = path.substring(0, first_path_sign);
            subCategoryName = path.substring(first_path_sign + 1, path.length());
            try {
                insertSubCategory(categoryName, subCategoryName, 0);
            } catch (productAtSamePathException e) {
                System.out.println(e);
                return;
            } 
        }
    }

    public void delete(String path) throws deleteException, searchException{ // delete product or subCategory or category
        String categoryName, subCategoryName, productName;
        int first_path_sign = path.indexOf(">");

        // Delete Category
        if(first_path_sign == -1){ 
            categoryName = path;
            category search_category = searchCategory(categoryName);
            if(search_category == null) // Category does not exist
                throw new deleteException("Category is not present!");
            categoryList.remove(search_category);
            System.out.println("Category removed!");
            return;
        }

        // Delete subCategory
        categoryName = path.substring(0, first_path_sign);
        int second_path_sign = path.indexOf(">", first_path_sign + 1);
        if(second_path_sign == -1){
            subCategoryName = path.substring(first_path_sign + 1, path.length());
            category search_category = searchCategory(categoryName);
            if(search_category == null){ // Category does not exist
                throw new deleteException("Category is not present!");
            }
            subCategory search_sub_category = searchSubCategory(subCategoryName);
            if(search_sub_category == null){ // subCategory does not exist
                throw new deleteException("Subcategory is not present!");
            }
            search_category.subCategoryList.remove(search_sub_category);
            System.out.println("SubCategory removed!");
            return;
        }

        // Delete product
        subCategoryName = path.substring(first_path_sign + 1, second_path_sign);
        productName = path.substring(second_path_sign + 1, path.length());
        category search_category = searchCategory(categoryName);
        if(search_category == null){ // Category does not exist
            throw new deleteException("Category is not present!");
        }
        subCategory search_sub_category = searchSubCategory(subCategoryName);
        if(search_sub_category == null){ // subCategory does not exist
            throw new deleteException("Subcategory is not present!");
        }
        product search_product = searchProduct(productName, 0);
        if(search_product == null){ // Product already exist
            throw new deleteException("Product is not present!");
        }
        search_sub_category.productList.remove(search_product);
        System.out.println("Product removed!");
    }


    public void modify(String productName) throws searchException {  //modify product information : Administrator
        product product_exist = searchProduct(productName, 0);    
        if(product_exist == null){
            throw new searchException("Product is not present!");
        }
        Scanner sc = new Scanner(System.in); 
        System.out.println("Press 1 : To modify Price");
        System.out.println("Press 2 : To modify Stocks");
        int choice = sc.nextInt();
        if(choice != 1 && choice != 2){
            System.out.println("Incorrect Choice!");         
            return;
        }
        if(choice == 1){
            System.out.print("New Price : ");
            int newPrice = sc.nextInt();
            product_exist.price = newPrice;
        }
        else if(choice == 2){
            System.out.print("New Stock : ");
            int newStock = sc.nextInt();
            product_exist.stocks = newStock;
        }
        
    }

    public int sale(String product, int quantity, int remainingFunds) throws searchException{ //sale of product : Customer
        product product_exist = searchProduct(product, 0);

        if(product_exist == null) {
            System.out.println("Exception : Product does not exists!");
            return -1;
        }
        if(product_exist.stocks < quantity){
            System.out.println("Exception : That much stock does not exist!");
            return -1;
        }
        int total_amount = quantity * product_exist.price;
        if(remainingFunds < total_amount){
            System.out.println("Exception : Insufficient Balance!");
            return -1;
        }
        product_exist.stocks -= quantity;
        remainingFunds -= total_amount;
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Product : "+product);
        System.out.println("Total Cost : "+product_exist.price+" * "+quantity);
        System.out.println("Product succesfully bought!");
        System.out.println("-----------------------------------------------------------------");
        return total_amount;
    }
    
    public void addFileToDatabase() throws IOException, searchException, productAtSamePathException {
    	String line;
    	BufferedReader fileReader = new BufferedReader(new FileReader("database.txt"));
    	while ((line = fileReader.readLine()) != null) {
    	       insertFromFile(line);
    	}
    }
    
    public void addFileToCustomer() throws IOException {
    	String line;
    	BufferedReader fileReader = new BufferedReader(new FileReader("customer.txt"));
    	while ((line = fileReader.readLine()) != null) {
            String username = line;
 	        int funds = Integer.parseInt(fileReader.readLine());
 	        int items = Integer.parseInt(fileReader.readLine());
 	        customer newCustomer = new customer(username, funds, items);
 	        for(int i = 0; i < items; i++) {
 	    	    String product = fileReader.readLine();
                int quantity = Integer.parseInt(fileReader.readLine());
 	    	    product addNewProduct = new product(product, 0, quantity);
                newCustomer.cart.add(addNewProduct); 
            }
            customerList.add(newCustomer); 
        }	
    }
    
    public void addDatabaseToFile() throws IOException{
        FileWriter fileWriter = new FileWriter("database.txt");;
    	for(int i = 0; i < categoryList.size(); i++){
    		if(categoryList.get(i).subCategoryList.isEmpty())
    			fileWriter.write(categoryList.get(i).categoryName+"\n");
            for(int j = 0; j < categoryList.get(i).subCategoryList.size(); j++){
            	if(categoryList.get(i).subCategoryList.get(j).productList.isEmpty())
        			fileWriter.write(categoryList.get(i).categoryName+">"+categoryList.get(i).subCategoryList.get(j).subCategoryName+"\n");
                for(int k = 0; k < categoryList.get(i).subCategoryList.get(j).productList.size(); k++){
                	fileWriter.write(categoryList.get(i).categoryName+">"+categoryList.get(i).subCategoryList.get(j).subCategoryName+">"+categoryList.get(i).subCategoryList.get(j).productList.get(k).productName);
                	fileWriter.write(">"+categoryList.get(i).subCategoryList.get(j).productList.get(k).price);
                    fileWriter.write(">"+categoryList.get(i).subCategoryList.get(j).productList.get(k).stocks+"\n");
                }
            }
        }
        fileWriter.close();
    }
    
    public void addCustomerToFile() throws IOException{
    	FileWriter fileWriter = new FileWriter("customer.txt");
    	for(int i = 0; i < customerList.size(); i++) {
    		fileWriter.write(customerList.get(i).username+"\n");
    		fileWriter.write(customerList.get(i).funds+"\n");
    		fileWriter.write(customerList.get(i).items+"\n");
    		for(int j = 0; j < customerList.get(i).cart.size(); j++) {
    			fileWriter.write(customerList.get(i).cart.get(j).productName+"\n");
    			fileWriter.write(customerList.get(i).cart.get(j).stocks+"\n");
    		}
    	}
        fileWriter.close();
    }
}