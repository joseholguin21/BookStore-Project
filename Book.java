public class Book
{
    String title;
    String author;
    String genre;
    double price;
    int availability;

    /**
     * default constructor
     */
    public Book()
    {
        this.title = "";
        this.author = "";
        this.genre = "";
        this.price = 0;
        this.availability = 0;
    }
    /**
     * creates Item
     * @param title
     * @param price
     */
    public Book(String title, String author, String genre, double price, int availability)
    {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.availability = availability;
    }
    /**
     * return genre type
     * @return genre
     */
    public String getGenre()
    {
        return genre;
    }
    /**
     * return author of book
     * @return author
     */
    public String getAuthor()
    {
        return author;
    }
    /**
     * returns item name
     * @return
     */
    public String getTitle()
    {
        return this.title;
    }
    /**
     * return price
     * @return
     */
    public double getPrice()
    {
        return this.price;
    }

    /** return books availability
     * @return
     */
    public int getAvailability()
    {
        return this.availability;
    }

    /**
     * checks if the book is at 0 availability
     * @return
     */
    public boolean checkAvailability()
    {
        if (availability == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Set title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * set author
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * set Genre
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * set price
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * set availability
     * @param availability
     */
    public void setAvailability(int availability) {
        this.availability = availability;
    }
}
