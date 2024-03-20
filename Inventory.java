import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class Inventory extends BookKeeping
{
    String file;
    /**
     * default constructor
     */
    public Inventory()
    {
        new BookKeeping();
    }

    /**
     * remove book if availability is at 0
     * @param name
     */
    public void removeBook(String name)
    {
        ArrayList<Book> BookInventory = getBooks();
        for (int i=0;i < BookInventory.size(); i++)
        {
            if (BookInventory.get(i).getTitle().equals(name))
            {
                BookInventory.get(i).setAvailability(BookInventory.get(i).getAvailability() - 1);
                if (BookInventory.get(i).checkAvailability())
                {
                    BookInventory.remove(i);
                }
                break;
            }
        }
    }

    /**
     * extracts any pre-existing books from the file
     */
    public void extractInventory() throws FileNotFoundException {
        File infile = new File(file);
        Scanner in = new Scanner(infile);
        while (in.hasNextLine())
        {
            String line = in.nextLine();
            processInventoryLine(line);
        }
        in.close();
    }

    /**
     * processes each line in the inventory file
     * @param line
     */
    public void processInventoryLine(String line)
    {
        Scanner in = new Scanner(line);
        String author = in.next();
        author += " " + in.next();
        String title = in.next();
        title += " " + in.next();
        String genre = in.next();
        double price = in.nextDouble();
        int availability = in.nextInt();
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        book.setGenre(genre);
        book.setPrice(price);
        book.setAvailability(availability);
        ArrayList<Book> temp = getBooks();
        temp.add(book);
    }

    /**
     * add updated inventory to file
     */
    public void updateInventoryFile() throws FileNotFoundException
    {
        ArrayList<Book> temp = getBooks();
        PrintWriter out = new PrintWriter(file);
        for (int i=0; i < temp.size(); i++) {
            out.print(temp.get(i).getAuthor() + " " + temp.get(i).getTitle() + " "
                    + temp.get(i).getGenre() + " " + temp.get(i).getPrice() +
                    " " + temp.get(i).getAvailability() + "\n");
        }
        out.close();
    }

    /**
     * sets the inventory file
     * @param file
     */
    public void setInventoryFile(String file)
    {
        this.file = file;
    }

}
