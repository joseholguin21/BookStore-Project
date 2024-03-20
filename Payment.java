//validates payment and processes payment
public class Payment {

    Customer person;
    double total;
    /**
     * default constructor
     */
    public Payment(Customer person, double total)
    {
        this.person = person;
        this.total = total;
    }

    /**
     * checks if card details are correct
     * @param type
     * @param pin
     * @param expdate
     * @return
     */
    public boolean validatePayment(int type, int pin, String expdate)
    {
        if (person.getType() == type && person.getPin() == pin && person.getExpdate().equals(expdate))
        {
            System.out.println("Correct.");
            return true;
        }
        else
        {
            System.out.println("Incorrect details, try again.");
            return false;
        }
    }

    /**
     * makes purchase by recieving amount
     * @param balance
     */
    public boolean makePurchase(double balance)
    {
        if (balance < total)
        {
            System.out.println("Insufficient Funds, enter correct amount");
            return false;
        }
        else
        {
            System.out.println("Payment completed!!");
            return true;
        }
    }



}

