import java.util.*;

public class category{
    public String categoryName;
    public ArrayList<subCategory> subCategoryList;
    
    public category(String name){
        categoryName = name;
        subCategoryList = new ArrayList<subCategory>();
    }

}

