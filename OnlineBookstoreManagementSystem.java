import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
public class OnlineBookstoreManagementSystem {
    private Inventory inventory;
    private ArrayList<Customer> customers;
    private ArrayList<Order> orders;
    private String filenameC;

    /**
     * constructor
     */
    public OnlineBookstoreManagementSystem() {
        inventory = new Inventory();
        customers = new ArrayList<>();
        orders = new ArrayList<>();
    }

    /**
     * add a customer to the customer list
     * @param customer
     */
    public void registerCustomer(Customer customer)
    {
        customers.add(customer);
    }

    /**
     * update customer file with new or pre-existing customers
     */
    public void updateCustomers() throws FileNotFoundException
    {
        PrintWriter out = new PrintWriter(filenameC);
        for (int i=0; i < customers.size(); i++) {
            out.print(customers.get(i).getName() + " " + customers.get(i).getAddress() + " "
                    + customers.get(i).getPhoneNumber() + " " + customers.get(i).getDiscount()
                    + " " + customers.get(i).getType() + " " + customers.get(i).getPin()
                    + " " + customers.get(i).getExpdate() + "\n");
        }
        out.close();
    }

    /**
     * add customers from file list to arraylist
     */
    public void extractCustomers() throws FileNotFoundException {
        File infile = new File(filenameC);
        Scanner in = new Scanner(infile);
        while (in.hasNextLine())
        {
            String line = in.nextLine();
            processLine(line);
        }
        in.close();
    }

    /**
     * process each line in the file and adds them to the customer list
     * @param line
     */
    public void processLine(String line)
    {
        Scanner in = new Scanner(line);
        String name = in.next();
        name += " " + in.next();
        String email = in.next();
        String phonenumber = in.next();
        int discounts = in.nextInt();
        int type = in.nextInt();
        int pin = in.nextInt();
        String expdate = in.next();
        Customer person = new Customer();
        person.setName(name);
        person.setPhoneNumber(phonenumber);
        person.setEmailAddress(email);
        person.setDiscount(discounts);
        person.setCard(type, pin, expdate);
        customers.add(person);
    }

    /**
     * display formatted available books to the user
     */
    public void browseBooks() {
        // Display available books to the user
        System.out.println("===========================< Books >===========================");
        ArrayList<Book> books = inventory.getBooks();
        System.out.println("Author            Title                Genre    Price    Available");
        for (int i=0; i < books.size(); i++)
        {
            System.out.printf("%10s %18s %15s %8.2f %8d%n", books.get(i).getAuthor(), books.get(i).getTitle(),
                    books.get(i).getGenre(), books.get(i).getPrice(), books.get(i).getAvailability());
        }
    }

    /**
     * prints invoice and places order
     * @param customer
     * @param cart
     */
    public void placeOrder(Customer customer, ShoppingCart cart, boolean check) {
        Order register = new Order(cart, customer, check);
        register.CalculateTotal();
        register.generateInvoice();
        System.out.println(register.returnInvoice());
        cart.setTotal(register.returnTotal());
        orders.add(register);
        System.out.println();
    }

    /**
     * Displays menu
     */
    public void menu()
    {
        System.out.println("1. Browse Books");
        System.out.println("2. Add items to Cart");
        System.out.println("3. Display current Cart");
        System.out.println("4. Place Order");
        System.out.println("5. Membership Details");
        System.out.println("6. Store Order History");
        System.out.println("7. Exit Store");
        System.out.println("Enter 1~6: ");
    }

    /**
     * checks if the book is in the inventory
     * @return
     */
    boolean checkInventory(String book)
    {
        ArrayList<Book> books = inventory.getBooks();
        boolean check = false;
        for (int i=0; i < books.size(); i++)
        {
            if (books.get(i).getTitle().equals(book))
            {
                check = true;
            }
        }
        return check;
    }
    
    // Other methods for managing the online bookstore system

    /**
     * checks if a customer is already registered
     * @param name
     * @return
     */
    public boolean searchCustomer(String name)
    {
        boolean check = false;
        for (int i=0; i < customers.size(); i++)
        {
            if (customers.get(i).getName().equals(name))
            {
                check = true;
                break;
            }
        }
        return check;
    }

    /**
     * return entire store order invoices
     */
    public void returnInvoiceHistory()
    {
        for (int i=0; i < orders.size(); i++)
        {
            System.out.println(orders.get(i).returnInvoice());
        }
    }

    /**
     * sets the inventory
     * @param inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * returns customer list
     * @return
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * sets the filename
     */
    public void setCustomerFileName(String filenameC)
    {
        this.filenameC = filenameC;
    }



}