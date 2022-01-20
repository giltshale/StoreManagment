import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final int CREATE_USER = 1;
    private static final int LOG_IN = 2;
    private static final int END_PROGRAM = 3;

    public static void main(String[] args) {

    /*    Date date = new Date();
        Employee[] checkEmployee = new Employee[3];
        Customer[] checkCustomer = new Customer[3];
        //Employee a = new Employee("Gil", "5", "aaa", "Aa",1);
        Employee a=new Employee("Gil","5", "a","a", false, 3, date,new ShoppingCart(), 1);
        Employee b = new Employee("b", "5", "bbb", "Bb",false,2,date,new ShoppingCart(),2);
        Employee c = new Employee("c", "5", "ccc", "Cc",false,5,date,new ShoppingCart(),3);
        Customer d = new Customer("gil", "5", "gil", "gil", true);
        Customer e = new Customer("e", "5", "eee", "Ee", false);
        Customer f = new Customer("f", "5", "fff", "Ee", false);

        Store store = new Store();
        checkEmployee[0] = a;
        checkEmployee[1] = b;
        checkEmployee[2] = c;
        checkCustomer[0] = d;
        checkCustomer[1] = e;
        checkCustomer[2] = f;

        store.setEmployees(checkEmployee);
        store.setCustomers(checkCustomer);
*/

        Scanner scanner = new Scanner(System.in);
        int firstMenuChoose;
         Store store = new Store();
        while (true) {
            System.out.println("what would you like to do? " + "\n"
                    + "press accordingly :" + "\n" +
                    "1- for creating an account" + "\n" +
                    "2- for login to existing account" + "\n" +
                    "3- for ending the program");
            firstMenuChoose = scanner.nextInt();

            switch (firstMenuChoose) {
                case CREATE_USER:
                    store.createUser();
                    break;
                case LOG_IN:
                    Customer foundUser = store.userLogin();


                    if (foundUser != null && foundUser instanceof Employee) {
                        store.setCurrentUser(foundUser);
                        store.employeeMenu();

                        System.out.println("\n");
                    } else if (foundUser != null){
                        store.setCurrentUser(foundUser);
                        store.existingProducts();
                    }
                    break;
                case END_PROGRAM:
                    return;
                default:

            }
        }
    }
}