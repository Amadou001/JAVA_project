import java.util.Scanner;

public class Main
{
    public static void main(String [] args)
    {
        Product_actions obj = new Product_actions();
        Scanner input = new Scanner(System.in);
        do
        {
            System.out.println("_______________________MENU_____________________\n");
            System.out.println("a-) Add product  ----->  (Do not forget to save your changes before leaving!)");
            System.out.println("b-) Remove product ----> (Do not forget to load products from file before removing!)");
            System.out.println("c-) Update product");
            System.out.println("d-) Search product");
            System.out.println("e-) Display all product");
            System.out.println("f-) Save product to file -----> (Only when you made changes!)");
            System.out.println("g-) Load product from file\n");
            System.out.print("Enter the action you want to perform: ");
            char choice = input.next().charAt(0);
            System.out.println("\n");
            choice = Character.toLowerCase(choice);
            switch (choice) {
                case 'a':
                    obj.addProduct();
                    break;
                case 'b':
                    obj.remove_product();
                    break;
                case 'c':
                    obj.update_product();
                    break;
                case 'd':
                    obj.search_produt();
                    break;
                case 'e':
                    obj.display_all_products();
                    break;
                case 'f':
                    obj.save_product_to_file();
                    break;
                case 'g':
                    obj.load_product_from_file();
                    break;
            
                default:
                    System.out.println("\n Option does not exist !");
                    break;
            }

            System.out.println("\n Do you want to continue ? Enter y for Yes or anything else for No:");
            char again = input.next().charAt(0);
            again = Character.toLowerCase(again);
            if (again == 'y')
            {
                continue;
            }
            else
            {
                System.out.println("\nEND");
                break;
            }

        }while(true);
    }
}
