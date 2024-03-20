
import java.util.ArrayList;
public class Order
{
    private ArrayList<Book> items;
    private static final int DISCOUNT_TAG = 100;
    private static final int DISCOUNT = 10;
    private double total;
    private double temptotal;
    private int finalDiscount=0;
    private String invoice;
    private Customer customer;
    boolean check;
    private int multiple=0;

    /**
     Constructor that accepts ShoppingCart as a parameter.
     @param cart
     @param customer
     @param check
     */
    public Order(ShoppingCart cart, Customer customer, boolean check)
    {
        items = new ArrayList<>(cart.getBooks());
        this.customer = customer;
        this.check = check;
    }

    /**
     * method that calculates the total subtracting the discount each time
     * conditions are met
     */
    public void CalculateTotal()
    {
        total = 0;
        finalDiscount=0;
        for (int i=0; i < items.size(); i++)
        {
            this.total = this.total + items.get(i).getPrice();
            temptotal = temptotal + items.get(i).getPrice();
            //boolean check = discountReached();
            if (discountReached())
            {
                temptotal = temptotal%DISCOUNT_TAG;
            }
        }
    }
    /**
     Checks if the discount amount is reached or not
     @return true or false
     */
    boolean discountReached()
    {
        if ((int)temptotal/DISCOUNT_TAG >= 1) //150/100 = 1, 250/100 = 2
        {
            multiple = (int)(temptotal/DISCOUNT_TAG);
            finalDiscount += multiple;
            customer.setDiscount(finalDiscount);
            return true;
        }
        else {
            return false;
        }
    }
    /**
     generates an invoice
     @return total
     */
    public void generateInvoice()
    {
        int discountsapplied;
        if (check)
        {
            discountsapplied = customer.getDiscount() * DISCOUNT;
            total = total - discountsapplied;
            customer.setDiscount(0);
        }
        else
        {
            discountsapplied = 0;
        }
        invoice = "";
        invoice+= "=======================================\n";
        invoice+= "           I N V O I C E               ";
        invoice += "\nCustomer Details: \n";
        invoice += customer.getName() + " \n";
        invoice += customer.getAddress() + "\n";
        invoice += customer.getPhoneNumber();
        invoice +=  String.format("%n%n%-30s%8s%n",
                "Book", "Price");
        for (int i=0; i < items.size(); i++)
        {
            invoice += String.format("%-30s%8.2f%n", items.get(i).getTitle(), items.get(i).getPrice());
        }
        invoice += String.format("%nDiscounts Applied: %1d%n", discountsapplied);
        invoice += String.format("Total: $%5.2f%n", this.total);
        invoice += "=======================================";
    }


    /**
     * returns total
     * @return
     */
    public double returnTotal()
    {
        return this.total;
    }


    /**
     * returns invoice
     * @return
     */
    public String returnInvoice()
    {
        return invoice;
    }

}
