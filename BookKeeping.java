import java.util.ArrayList;
public class BookKeeping
{
    private ArrayList<Book> books;

    /**
     * default constructor
     */
    BookKeeping()
    {
        books = new ArrayList<>();
    }

    /**
     * add book object to books list
     * @param book
     */
    public void addBook(Book book)
    {
        books.add(book);
    }

    /**
     * returns arraylist of books
     * @return
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * sets arraylist of books
     * @param books
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
}
