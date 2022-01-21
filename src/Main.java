import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final int CREATE_USER = 1;
    private static final int LOG_IN = 2;
    private static final int END_PROGRAM = 3;

    public static void main(String[] args) {


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
                    } else if (foundUser != null) {
                        store.setCurrentUser(foundUser);
                        store.chooseProductAndBuy();
                    }
                    break;
                case END_PROGRAM:
                    return;
                default:

            }
        }
    }
}