import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

public class Product_actions
{
    private Product [] product_list;
    private int count, index;
    private Product [] second_product_list;
    private static final int SIZE = 1000; // Initial size of the arrays

    public Product_actions()
    {
        product_list = new Product[SIZE];
        second_product_list = new Product[SIZE];
        count = 0;
    }

    /**
     * addProduct: add products in the product_list array but are not
     * directly saved inside the file
     */
    public void addProduct()
    {
        System.out.println("\nDo not forget to save the changes ! \n");
        product_list[count] = new Product();
        System.out.println("Enter the product information : ");
        Scanner input = new Scanner(System.in);
        System.out.print("name : ");
        String name = input.nextLine();
        System.out.print("\ncategory : ");
        String category = input.nextLine();
        System.out.print("\nprice : ");
        float price = input.nextFloat();
        System.out.println("\n"); 
        product_list[count].setName(name);
        product_list[count].setCategory(category);
        product_list[count].setPrice(price);

        UUID uniqueID = UUID.randomUUID(); // Create a unique ID for each product added
        product_list[count].setProductID(uniqueID.toString());
        count++;

    }

    /**
     * save_product_to_file: saves the products added
     */
    public void save_product_to_file()
    {
        Create_file file = new Create_file(); // object of the Create_file class
        file.file_creation();   // file_creation call

        try
        {
            FileWriter myWriter = new FileWriter("file_product.txt", true);
            for (int i = 0; i < count; i++)
            {
                if (product_list[i] != null) // check whether there is a product inside the array
                {
                    myWriter.write("Name: " + product_list[i].getName() + "\n");
                    myWriter.write("ProductID: " + product_list[i].getProductID() + "\n");
                    myWriter.write("Category: " + product_list[i].getCategory() + "\n");
                    myWriter.write("Price: " + product_list[i].getPrice() + " RM" + "\n");
                    myWriter.write("\n");
                }
                else
                {
                    return; // if no products nothing is written inside the file
                }
                
            }
            myWriter.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        for (int i = 0; i < product_list.length; i++)
        {
            product_list[i] = null; /*After every saving the array is freed to avoid getting the same product saved more
                                     than once inside the file */
        }
        count = 0; // For saving an eventual product that will be added to the first index of product_list
        System.out.println("\nSaved successefully!\n");
    }

    /**
     * display_all_products: displays all the products contained inside the file
     */
    public void display_all_products()
    {
        try
        {
            File myobj = new File("file_product.txt");
            Scanner myReader = new Scanner(myobj);
            while (myReader.hasNextLine()) // checks for an existing line
            {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * load_product_from_file: by parsing, it loads the file's content to second_product array of
     * product objects
     */
    public void load_product_from_file()
    {
        try
        {
            File myobj = new File("file_product.txt");
            Scanner myReader = new Scanner(myobj);
            index = 0;
            int flag = 0; // to track the file content
            while (myReader.hasNextLine())
            {
                if (flag == 0) // means we are in a new object features
                {
                    second_product_list[index] = new Product(); // creation of a new object at the current index
                }
                String data = myReader.nextLine();
                String[] parts = data.split("[: ]+"); // each line of the file is parsed using colon or spaces
                if (parts.length >= 2)
                {
                    if (parts[0].equals("Name"))
                    {
                        second_product_list[index].setName(parts[1]);
                    }
                    else if (parts[0].equals("ProductID"))
                    {
                        second_product_list[index].setProductID(parts[1]);
                    }
                    else if (parts[0].equals("Category"))
                    {
                        second_product_list[index].setCategory(parts[1]);
                    }
                    else if (parts[0].equals("Price"))
                    {
                        try {
                            float float_value = Float.parseFloat(parts[1].trim()); // the price value is parsed from string to float
                            second_product_list[index].setPrice(float_value);
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing float value: " + parts[1]);
                        }
                    }
                    flag++;
                    if (flag == 4) // means we have collected all the 4 features of the product object
                    {
                        index++; // to store the next object to the next index
                        flag = 0;
                    }

                }
                else
                {
                    flag = 0;
                }
         
            }
            System.out.println("\n Products loaded successefully\n");
            myReader.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * remove_product: remove a product by providint it's category and ID
     * This action requires the procuct to be loaded first by using load_product_from_file method
     */
    public void remove_product()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\n Make sure products have been loaded from file before removing\n");
        System.out.print("Enter the category of the product: ");
        String category = input.nextLine();
        System.out.println("\nHere are the products ID in this category, copy the ID you need and paste it in the next prompt\n");
        boolean categoryExists = false; // tracks the existing of the category inside our database file
        for (int i = 0; i < index; i++)
        {
            if (second_product_list[i].getCategory().equalsIgnoreCase(category)) // checks the category case insensitively
            {
                categoryExists = true;
                System.out.println("Product ID: " + second_product_list[i].getProductID());
            }
        }
        System.out.print("\n");
        if (!categoryExists) // when given category not found
        {
            System.out.println("Category does not exist in the database Or products not loaded from the database");
            return;
        }
        System.out.print("ID: ");
        String id = input.nextLine().trim(); // ask for the ID
        Product [] copy_second_list = new Product[second_product_list.length]; /*new array of product object which will contain
                                                                                 all the products except the one removed */
        int second_index = 0;
        for (int i = 0; i < index; i++)
        {
            if (second_product_list[i].getProductID().equals(id)) // looks for the product to remove
            {
                continue; // skips the product to be removed
            }
            copy_second_list[second_index] = new Product();
            copy_second_list[second_index].setName(second_product_list[i].getName());
            copy_second_list[second_index].setCategory(second_product_list[i].getCategory());
            copy_second_list[second_index].setProductID(second_product_list[i].getProductID());
            copy_second_list[second_index].setPrice(second_product_list[i].getPrice());
            second_index++;
        }
        second_product_list = copy_second_list; //second_product_list now contains all products withou the one removed
        index = second_index; // indicates the number of products currently present inside the array
        save_updates(); // call of save_updates method to overwrite the file with the new content of the array.
        System.out.println("\nProduct removed successfully.");
    }

    /**
     * search_product: looks for a product by providing its ID
     * It requires the products to be loaded first
     * For this one the loading will be done automatically without asking the user to do so.
     */
    public void search_produt()
    {
        Scanner input = new Scanner(System.in);
        load_product_from_file(); //load_product_from_file method call
        System.out.println("\nHere are the available IDs. Take the one you are looking for :\n");
        for (int i = 0; i < index; i++)
        {
            System.out.println(second_product_list[i].getProductID()); // outputs all products'ID
        }
        System.out.print("\nEnter the ID you want to search : ");
        String id = input.nextLine();
        boolean product_exists = false; // Tracks whether the given ID exists or not
        for (int i = 0; i < index; i++)
        {
            if (second_product_list[i].getProductID().equals(id))
            {
                System.out.println("\nHere is your product! \n");
                System.out.println("Name: " + second_product_list[i].getName());
                System.out.println("ProductID: " + second_product_list[i].getProductID());
                System.out.println("Category: " + second_product_list[i].getCategory());
                System.out.println("Price: " + second_product_list[i].getPrice());
                System.out.println("\n");
                product_exists = true;
                return;
            }
        }
        if (!product_exists) // when the ID does not exist
        {
            System.out.println("\n Wrong ID !\n");
        }
    }

    /**
     * update_product: updates a specified product by giving its ID
     * It requires the products to be loaded first
     * For this one the loading will be done automatically without asking the user to do so.
     */
    public void update_product()
    {
        Scanner input = new Scanner(System.in);
        load_product_from_file(); //load_product_from_file method call
        System.out.println("\nHere are the available IDs. Take the one you want to update :\n");
        for (int i = 0; i < index; i++)
        {
            System.out.println(second_product_list[i].getProductID()); //outputs all IDs
        }
        System.out.print("\nEnter the ID: ");
        String id = input.nextLine();
        boolean product_exists = false; // checks the existance of the product
        int i;
        for (i = 0; i < index; i++)
        {
            if (second_product_list[i].getProductID().equals(id))
            {
                System.out.println("\nHere is your product! \n");
                System.out.println("Name: " + second_product_list[i].getName());
                System.out.println("ProductID: " + second_product_list[i].getProductID());
                System.out.println("Category: " + second_product_list[i].getCategory());
                System.out.println("Price: " + second_product_list[i].getPrice());
                System.out.println("\n");
                product_exists = true;
                break;
            }
        }
        if (!product_exists) // when the product does not exist
        {
            System.out.println("\n Wrong ID !\n");
            return;
        }
        /*
         * Section where the user enters the product updates
         */
        System.out.println("\nPerform your updates:\n");
        System.out.print("Name: ");
        String name = input.nextLine();
        second_product_list[i].setName(name);
        System.out.print("\nCategory: ");
        String category = input.nextLine();
        second_product_list[i].setCategory(category);
        System.out.print("\nPrice: ");
        float price = input.nextFloat();
        second_product_list[i].setPrice(price);
        // Ask the user if he wants to save the changes or not
        System.out.print("\nDo you want to save the changes Y/N ? ");
        char save = input.next().charAt(0);
        save = Character.toLowerCase(save); // convert save lower case
        if (save == 'y')
        {
            save_updates(); // call of save_updates method
        }
        else
        {
            return;
        }
        // Output the changes made
        System.out.println("\n\nSuccessefully updated\n");
        System.out.println("Name: " + second_product_list[i].getName());
        System.out.println("ProductID: " + second_product_list[i].getProductID());
        System.out.println("Category: " + second_product_list[i].getCategory());
        System.out.println("Price: " + second_product_list[i].getPrice());
    }

    /**
     * save_updates: overwrites the file database 
     * Used in the update_product and remove_product method to apply all the changes
     */
    public void save_updates()
    {
        try {
            FileWriter myWriter = new FileWriter("file_product.txt", false);
            for (int i = 0; i < index; i++) {
                myWriter.write("Name: " + second_product_list[i].getName() + "\n");
                myWriter.write("ProductID: " + second_product_list[i].getProductID() + "\n");
                myWriter.write("Category: " + second_product_list[i].getCategory() + "\n");
                myWriter.write("Price: " + second_product_list[i].getPrice() + " RM" + "\n");
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file.");
            e.printStackTrace();
        }
    }
}
