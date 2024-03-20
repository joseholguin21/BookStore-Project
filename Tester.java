//Online Bookstore Management Project
//Creator: Jose Holguin
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileNotFoundException;
public class Tester
{
    public static void main(String[] args) throws FileNotFoundException {
        OnlineBookstoreManagementSystem store = new OnlineBookstoreManagementSystem();
        Inventory invent = new Inventory();
        Scanner in = new Scanner(System.in);
        boolean checkcustomer = false;
        //option to add more books to the inventory
        System.out.println("Add books to inventory: ");
        while (true)
        {
            System.out.println("Enter title (press q to exit): ");
            String title = in.nextLine();
            if (title.equals("q"))
            {
                break;
            }
            System.out.println("Enter author: ");
            String author = in.nextLine();
            System.out.println("Enter genre type: Cooking, Non-Fiction, History, or Fiction: ");
            String genre = in.next();
            System.out.println("Enter price: ");
            double price = in.nextDouble();
            System.out.println("Enter availability: ");
            int availability = in.nextInt();
            invent.addBook(new Book(title, author, genre, price, availability));
            in.nextLine();
        }


        invent.setInventoryFile("Inventory.txt");
        invent.extractInventory();
        store.setInventory(invent);
        store.setCustomerFileName("Customer.txt");
        //adds pre-existing customers from file to customer list
        store.extractCustomers();
        outerloop:
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("press q to exit");
            System.out.println("Are you a new customer, enter (y/n)?");
            String choice = input.next();
            if (choice.equals("q")) {
                break;
            }


            Customer person = new Customer();
            input.nextLine();
            if (choice.equals("y")) {
                System.out.println("Enter your first and last name: ");
                String name = input.nextLine();
                System.out.println("Enter your contact information: ");
                String contact = input.next();
                System.out.println("Enter your email address: ");
                String email = input.next();
                int type = 0;
                //check to make sure type is 4, 5 or 6
                do {
                    System.out.println("Enter card type 4~6: ");
                    type = input.nextInt();
                    if (type < 4 || type > 6) {
                        System.out.println("Type must be 4, 5, or 6!!");
                    }
                } while (type < 4 || type > 6);
                System.out.println("Enter card pin and expiration date: ");
                int pin = input.nextInt();
                String expdate = input.next();
                person.setCard(type, pin, expdate);
                person.setName(name);
                person.setEmailAddress(email);
                person.setPhoneNumber(contact);
                store.registerCustomer(person);
                checkcustomer = true;
            } else if (choice.equals("n")) {
                String name;
                System.out.println("Enter your full name: ");
                name = input.nextLine();
                if (store.searchCustomer(name)) {
                    System.out.println("Found!");
                    int j;
                    for (j = 0; j < store.getCustomers().size(); j++) {
                        if (store.getCustomers().get(j).getName().equals(name))
                            break;
                    }
                    person = store.getCustomers().get(j);
                    checkcustomer = true;
                } else {
                    System.out.println("You do not exist in the system! Register with a membership.");
                    checkcustomer = false;
                }
            }

            if (checkcustomer) {
                ShoppingCart cart = new ShoppingCart();
                //display menu
                while (true) {
                    store.menu();
                    int number = input.nextInt();
                    if (number == 1) {
                        store.browseBooks();
                    } else if (number == 2) {
                        input.nextLine();
                        System.out.println("Add items to your shopping cart, press q to stop.");
                        while (true) {
                            System.out.println("Enter book name: ");
                            String bookname = input.nextLine();
                            if (bookname.equals("q")) {
                                break;
                            }
                            if (!store.checkInventory(bookname)) {
                                System.out.println("Book does not exist!");
                            } else {
                                ArrayList<Book> list = invent.getBooks();
                                int i;
                                for (i = 0; i < list.size(); i++) {
                                    if (list.get(i).getTitle().equals(bookname)) {
                                        break;
                                    }
                                }
                                cart.addBook(list.get(i));
                                invent.removeBook(bookname);
                            }
                        }
                    } else if (number == 3) {
                        cart.display();
                        System.out.println();
                    } else if (number == 4) {
                        boolean check = false;
                        if (person.getDiscount() != 0) {
                            String yesOrNo;
                            System.out.println("Use discounts? (y/n):  ");
                            yesOrNo = input.next();
                            if (yesOrNo.equals("y")) {
                                check = true;
                            }
                        }
                        store.placeOrder(person, cart, check);
                        int type;
                        int pin;
                        String expdate;
                        Payment process = new Payment(person, cart.getTotal());
                        do {
                            System.out.println("Enter card type 4~6, card pin, and expiration date: ");
                            type = input.nextInt();
                            pin = input.nextInt();
                            expdate = input.next();
                        } while (!process.validatePayment(type, pin, expdate));
                        double balance = 0;
                        do {
                            System.out.print("Enter payment $");
                            balance = input.nextDouble();
                        } while (!process.makePurchase(balance));
                        System.out.println();
                        break;
                    } else if (number == 5) {
                        System.out.println("Bookstore MemberShip Personal Information: ");
                        System.out.println(person);
                        System.out.println();
                    } else if (number == 6) {
                        store.returnInvoiceHistory();
                    } else if (number == 7) {
                        break outerloop;
                    }
                }
            }
        }

        //updates file
        store.updateCustomers();
        invent.updateInventoryFile();
    }
}

/**
 * Sample Run:
 * Add books to inventory:
 * Enter title (press q to exit):
 * C++ Guide
 * Enter author:
 * Ryan Q.
 * Enter genre type: Cooking, Non-Fiction, History, or Fiction:
 * Non-Fiction
 * Enter price:
 * 35
 * Enter availability:
 * 5
 * Enter title (press q to exit):
 * q
 * press q to exit
 * Are you a new customer, enter (y/n)?
 * n
 * Enter your first and last name:
 * John Stamos
 * Found!
 * 1. Browse Books
 * 2. Add items to Cart
 * 3. Display current Cart
 * 4. Place Order
 * 5. Membership Details
 * 6. Store Order History
 * 7. Exit Store
 * Enter 1~6:
 * 5
 * Bookstore MemberShip Personal Information:
 * Customer Name: John Stamos
 * Email Address: email@email.com
 * Phone Number: 6267688813
 * Discounts: 0
 *
 * 1. Browse Books
 * 2. Add items to Cart
 * 3. Display current Cart
 * 4. Place Order
 * 5. Membership Details
 * 6. Store Order History
 * 7. Exit Store
 * Enter 1~6:
 * 1
 * ===========================< Books >===========================
 * Author            Title                Genre    Price    Available
 *    Ryan Q.          C++ Guide     Non-Fiction    35.00        5
 * Elle Frank    American Horror         History    70.50        5
 *   Tommy B.       Chef Recipes         Cooking    30.00        5
 *     Jim C.          Space War     Non-Fiction    15.50        4
 *   Terry S.     Alien Invasion     Non-Fiction    12.25        5
 *    Alex Z.   Rhetorical Poems         Fiction    60.50        8
 * Harry C.D.         Super Rock     Non-Fiction    40.00        3
 *    John E.   Canadian History         History    23.25        3
 * 1. Browse Books
 * 2. Add items to Cart
 * 3. Display current Cart
 * 4. Place Order
 * 5. Membership Details
 * 6. Store Order History
 * 7. Exit Store
 * Enter 1~6:
 * 2
 * Add items to your shopping cart, press q to stop.
 * Enter book name:
 * Canadian History
 * Enter book name:
 * C++ Guide
 * Enter book name:
 * Alien Invasion
 * Enter book name:
 * q
 * 1. Browse Books
 * 2. Add items to Cart
 * 3. Display current Cart
 * 4. Place Order
 * 5. Membership Details
 * 6. Store Order History
 * 7. Exit Store
 * Enter 1~6:
 * 3
 * Current Books in cart!
 * Canadian History
 * C++ Guide
 * Alien Invasion
 *
 * 1. Browse Books
 * 2. Add items to Cart
 * 3. Display current Cart
 * 4. Place Order
 * 5. Membership Details
 * 6. Store Order History
 * 7. Exit Store
 * Enter 1~6:
 * 4
 * =======================================
 *            I N V O I C E
 * Customer Details:
 * John Stamos
 * email@email.com
 * 6267688813
 *
 * Book                             Price
 * Canadian History                 23.25
 * C++ Guide                        35.00
 * Alien Invasion                   12.25
 *
 * Discounts Applied: 0
 * Total: $70.50
 * =======================================
 *
 * Enter card type 4~6, card pin, and expiration date:
 * 4 1188 06/06
 * Correct.
 * Enter payment $70.50
 * Payment completed!!
 *
 * press q to exit
 * Are you a new customer, enter (y/n)?
 * q
 */