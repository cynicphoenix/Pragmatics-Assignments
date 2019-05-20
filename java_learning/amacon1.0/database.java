import java.util.*;

public class database{
    public ArrayList<category> categoryList;
    public database(){
        categoryList = new ArrayList<category>();
    }

    public void printDatabase() {
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

    public product searchProduct(String productName) { //Search product
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
        if(flag == 0)
            return null;
        else
            return categoryList.get(i).subCategoryList.get(j).productList.get(k);
    }

    public void pathFinder(product existingProduct) {
    	int i = 0, j = 0, k = 0;
    	int flag = 0;
    	for(i = 0; i < categoryList.size(); i++){
            for(j = 0; j < categoryList.get(i).subCategoryList.size(); j++){
                for(k = 0; k < categoryList.get(i).subCategoryList.get(j).productList.size(); k++){
                    if(categoryList.get(i).subCategoryList.get(j).productList.get(k).productName.equals(existingProduct.productName)){
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
    	if(flag == 1)
    		System.out.println("Path : "+categoryList.get(i).categoryName+">"+categoryList.get(i).subCategoryList.get(j).subCategoryName+">"+existingProduct.productName);
    	
    }
    
    public void insertCategory(String categoryName) { // Insert category
        category new_category = searchCategory(categoryName);
        if(new_category != null){
            System.out.println("Exception : Category already exists!");
            return;
        }
        category addNewCategory = new category(categoryName);
        categoryList.add(addNewCategory);
    }

    public void insertSubCategory(String categoryName, String subCategoryName) { // Insert subCategory
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
                System.out.println("Exception : Subcategory already exists!");
                return;
            } // Insert subCategory
            subCategory addNewSubCategory = new subCategory(subCategoryName);
            search_category.subCategoryList.add(addNewSubCategory);
        }
    }

    public void insertProduct(String categoryName, String subCategoryName, String productName) { // Insert product
        category search_category = searchCategory(categoryName);
        Scanner sc = new Scanner(System.in);
        if(search_category == null){ // Insert category>subCategory>product
            category addNewCategory = new category(categoryName);
            categoryList.add(addNewCategory);
            subCategory addNewSubCategory = new subCategory(subCategoryName);
            addNewCategory.subCategoryList.add(addNewSubCategory);
            System.out.print("Enter Price of product : ");
            int price = sc.nextInt();
            System.out.print("Enter Stocks of product : ");
            int stocks = sc.nextInt();
            product addNewProduct = new product(productName, price, stocks);
            addNewSubCategory.productList.add(addNewProduct);
        }
        else{
            subCategory search_sub_category = searchSubCategory(subCategoryName);
            if(search_sub_category == null){ // Insert subCategory>product
                subCategory addNewSubCategory = new subCategory(subCategoryName);
                search_category.subCategoryList.add(addNewSubCategory);
                System.out.print("Enter Price of product : ");
                int price = sc.nextInt();
                System.out.print("Enter Stocks of product : ");
                int stocks = sc.nextInt();
                product addNewProduct = new product(productName, price, stocks);
                addNewSubCategory.productList.add(addNewProduct);
            }
            else{
                product search_product = searchProduct(productName);
                if(search_product != null){ // Product already exist
                    System.out.println("Exception : Product already exists!");
                    
                    return;
                }// Insert Product
                System.out.print("Enter Price of product : "); 
                int price = sc.nextInt();
                System.out.print("Enter Stocks of product : ");
                int stocks = sc.nextInt();
                product addNewProduct = new product(productName, price, stocks);
                search_sub_category.productList.add(addNewProduct);
            }
        }
        
    }

    public void insert(String path, String productName){
        String categoryName, subCategoryName;
        int first_path_sign = path.indexOf(">");
        if(productName != "\0") { // Insert Product
            categoryName = path.substring(0, first_path_sign);   
            subCategoryName = path.substring(first_path_sign + 1, path.length());
            insertProduct(categoryName, subCategoryName, productName);
            return;
        }
        if(first_path_sign == -1){ // Insert Category
            categoryName = path;
            insertCategory(categoryName);
        }
        else{ //Insert subCategory
            categoryName = path.substring(0, first_path_sign);
            subCategoryName = path.substring(first_path_sign + 1, path.length());
            insertSubCategory(categoryName, subCategoryName);  
        }
    }

    public void delete(String path) { // delete product or subCategory or category
        String categoryName, subCategoryName, productName;
        int first_path_sign = path.indexOf(">");

        // Delete Category
        if(first_path_sign == -1){ 
            categoryName = path;
            category search_category = searchCategory(categoryName);
            if(search_category == null){ // Category does not exist
                System.out.println("Exception : Category does not exists!");
                return;
            }
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
                System.out.println("Exception : Category does not exists!");
                return;
            }
            subCategory search_sub_category = searchSubCategory(subCategoryName);
            if(search_sub_category == null){ // subCategory does not exist
                System.out.println("Exception : SubCategory does not exists!");
                return;
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
            System.out.println("Exception : Category does not exists!");
            return;
        }
        subCategory search_sub_category = searchSubCategory(subCategoryName);
        if(search_sub_category == null){ // subCategory does not exist
            System.out.println("Exception : SubCategory does not exists!");
            return;
        }
        product search_product = searchProduct(productName);
        if(search_product == null){ // Product already exist
            System.out.println("Exception : Product does not exists!");
            return;
        }
        search_sub_category.productList.remove(search_product);
        System.out.println("Product removed");
    }


    public void modify(String productName) {  //modify product information : Administrator
        product product_exist = searchProduct(productName);    
        if(product_exist == null){
            System.out.println("Exception : Product does not exists!");
            return;
        }
        Scanner sc = new Scanner(System.in); 
        System.out.println("Press 1 : To modify Price");
        System.out.println("Press 2 : To modify Stocks");
        int choice = sc.nextInt();
        if(choice != 1 && choice != 2){
            System.out.println("Exception : Incorrect Choice!");         
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

    public int sale(String product, int quantity, int remainingFunds) { //sale of product : Customer
        product product_exist = searchProduct(product);

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
}