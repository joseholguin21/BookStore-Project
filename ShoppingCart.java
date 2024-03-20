import java.util.ArrayList;
public class ShoppingCart extends BookKeeping
{

    double total;
    /**
     * default constructor
     */
    ShoppingCart()
    {
        new BookKeeping();
        total=0;
    }

    /**
     * displays books in current customers cart
     */
    void display()
    {
        ArrayList<Book> books = getBooks();
        System.out.println("Current Books in cart!");
        for (int i=0; i < books.size(); i++)
        {
            System.out.println(books.get(i).getTitle());
        }
    }

    /**
     * sets total of entire cart
     * @param total
     */
    void setTotal(double total)
    {
        this.total = total;
    }


    /**
     * returns total price of entire cart
     * @return
     */
    public double getTotal() {
        return total;
    }
}
