public class product{
    public String productName;
    public int price;
    public int stocks;

    public product(String name, int price_field, int stocks_field) {
        productName = name;
        price = price_field;
        stocks = stocks_field;
    }

    public int checkStocks() throws outOfStockException{
        if(stocks > 0)
            return stocks;
        else 
            throw new outOfStockException("Product is out of stock!");
    }
}

