public class Customer
{
    String name;
    String phoneNumber;
    String emailAddress;

    //for payment card
    int type;
    int pin;
    String expdate;
    int discount;

    /**
     * default constructor
     */
    public Customer()
    {
        this.name = "";
        this.phoneNumber = "";
        this.emailAddress = "";
        this.discount = 0;
    }
    /**
     constructor that creates a person with a name, phone number, and address
     @param name
     @param phoneNumber
     @param emailAddress
     */
    public Customer(String name, String phoneNumber, String emailAddress)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.discount = 0;
    }

    /**
     * creates the card for the customer
     * @param type
     * @param pin
     * @param expdate
     */
    public void setCard(int type, int pin, String expdate)
    {
        this.type = type;
        this.pin = pin;
        this.expdate = expdate;
    }
    /**
     Returns the phone number of the customer
     @return
     */
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }

    /**
     * returns name of customer
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * returns email address
     * @return
     */
    public String getAddress() {
        return emailAddress;
    }


    /**
     * sets name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets phonenumber
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * set discount
     * @param discount
     */
    public void setDiscount(int discount)
    {
        this.discount = discount;
    }

    /**
     * return discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * sets email address
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * return information
     */
    public String toString()
    {
        return "Customer Name: "+ name + "\nEmail Address: " + emailAddress
                + "\nPhone Number: " + phoneNumber +" \nDiscounts: " + discount;
    }

    /**
     * return card type
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * return pin
     * @return
     */
    public int getPin() {
        return pin;
    }

    /**
     * return exp date
     * @return
     */
    public String getExpdate() {
        return expdate;
    }

}
