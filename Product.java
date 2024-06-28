public class Product
{
    private String name, productID, category;
    private float price;
    // First constructor of the Product class with parameters
    public Product(String name, String productID, String category, float price)
    {
        this.name = name;
        this.productID = productID;
        this.category = category;
        this.price = price;
    }
    //Second constructor of the Product class whithout parameters
    public Product()
    {

    }
    public String getName() 
    {
        
        return this.name; // returns the name value
    }
    public void setName(String name)
    {
        this.name = name; // sets the name value
    }
    public String getProductID()
    {
        return this.productID; // returns the productID value
    }
    public void setProductID(String productID)
    {
        this.productID = productID; //sets the productID value
    }
    public String getCategory()
    {
        return this.category; //returns the category value
    }
    public void setCategory(String category)
    {
        this.category = category; //sets the category value
    }
    public float getPrice()
    {
        return this.price; // returns the price value
    }
    public void setPrice(float price)
    {
        this.price = price; // sets the price value
    }
}
