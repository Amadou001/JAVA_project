import java.io.*;
/**
 * Creation of the file_product.txt file
 */
public class Create_file
{
     public void file_creation()
     {
          try
          {
              File myfile = new File("file_product.txt");
             if (myfile.createNewFile()) // when the file is newly created
             {
                  System.out.println("\nNew database\n");
             }
             else // when the file already exists
             {
                  System.out.println("\nFile updated \n");
             }
          }
          catch(IOException e)
          {
              e.printStackTrace();
          }
     }
}


