import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.isDigit;

public class Store {
    public static final int ZERO = 0;
    public static final int PRODUCT_AVAILABLE = 1;
    public static final int PRODUCT_NOT_AVAILABLE = 0;
    private static final int EMPLOYEE = 1;
    private static final int CUSTOMER = 0;
    private static final int NONE_RELEVANT_VALUE = -1;
    private static final int PASS_LENGTH = 6;
    private static final int VIP = 1;
    private static final int PRINT_ALL_CUSTOMERS = 1;
    private static final int PRINT_ALL_VIP_CUSTOMERS = 2;
    private static final int CUSTOMERS_WITH_AT_LEAST_ONE_BUY = 3;
    private static final int MOST_BUYING_CUSTOMER = 4;
    private static final int ADD_PRODUCT = 5;
    private static final int CHANGE_PRODUCT_STATUS = 6;
    private static final int BUY_AS_CLIENT = 7;
    private static final int MAIN_MENU = 8;
    private static final int ONE_HUNDRED = 100;
    private static final double REGULAR_WORKER_DISCOUNT = 0.9;
    private static final double MANAGER_DISCOUNT = 0.8;
    private static final double BOARD_MANAGER_DISCOUNT = 0.7;


    private Customer[] employees;
    private Customer[] customers;
    private Products[] products;
    private Customer currentUser;

    public void setCurrentUser(Customer currentUser) {
        this.currentUser = currentUser;
    }

    public Store() {
        this.employees = new Customer[0];
        this.customers = new Customer[0];
        this.products = new Products[15];
        products[0] = new Products("bisli", 20, 6, true, 10);
        String[] suply = new String[]{"bamba ", "meat ", "fish ", "orange juice "
                , "banana", "cucumbers ", "tamato ", "eggplant's", "shampoo",
                "gumigum", "lettuce", "steak", "onions", "melon"};
        Random random = new Random();
        int price;
        Random random1 = new Random();
        int howManyPieces;
        int[] discount = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 1; i < 15; i++) {
            price = random.nextInt(100);
            howManyPieces = random1.nextInt(20);
            this.products[i] = new Products(suply[i - 1], price, howManyPieces, true, discount[i]);
        }

    }

    public void setProducts(Products[] products) {
        this.products = products;
    }

    public Products[] getProducts() {
        return this.products;
    }

    public Customer[] getEmployees() {
        return this.employees;
    }

    public void setEmployees(Customer[] employees) {
        this.employees = employees;
    }

    public void setCustomers(Customer[] customers) {
        this.customers = customers;
    }

    public Customer[] getCustomers() {
        return this.customers;
    }

    private void printExistingProducts() {

        for (int i = 0; i < this.products.length; i++) {
            if (products[i].getHowManyLeft() < 1) {
                products[i].setAvailable(false);
            }
            if (products[i].isAvailable()) {
                System.out.println((i) + "." + products[i].getProductName());
                System.out.println("price: " + products[i].getPrice());
                System.out.println("vip discount " + products[i].getMemberDiscount());
                System.out.println("number of items " + products[i].getHowManyLeft());
            }
        }
    }

    private double discounts(int selectedProduct) {
        double discount = 1;
        if (currentUser instanceof Employee) {
            if (((Employee) currentUser).getRank() == Employee.REGULAR_WORKER) {
                discount = REGULAR_WORKER_DISCOUNT;
            } else if (((Employee) currentUser).getRank() == Employee.MANAGER) {
                discount = MANAGER_DISCOUNT;
            } else {
                discount = BOARD_MANAGER_DISCOUNT;
            }
        } else {
            if (currentUser.isMember()) {
                discount = 1 - (this.products[selectedProduct].getMemberDiscount() / ONE_HUNDRED);
            }
        }
        return discount;
    }

    public void existingProducts() {
        Scanner scanner = new Scanner(System.in);

        int selectedProduct;

        do {
            printExistingProducts();
            int wantedNumberOfItems;
            do {
                System.out.println("please choose product to buy ");
                selectedProduct = scanner.nextInt();
            } while (selectedProduct > 0 && selectedProduct != NONE_RELEVANT_VALUE && selectedProduct > this.products.length);
            if (selectedProduct != NONE_RELEVANT_VALUE) {
                do {
                    System.out.println("how many items do you want ? ");
                    wantedNumberOfItems = scanner.nextInt();
                } while (this.products[selectedProduct].getHowManyLeft() < wantedNumberOfItems);
                double discount = discounts(selectedProduct);
                double addToPay = (this.products[selectedProduct].getPrice() * wantedNumberOfItems) * discount;
                this.currentUser.getShoppingCart().setTotalToPay(addToPay);
                addToCart(selectedProduct, wantedNumberOfItems);
                this.currentUser.setLastVisit(new Date());

                currentUser.setCurrentSum(this.currentUser.getShoppingCart().getTotalToPay());
                this.currentUser.addBuy(this.currentUser.getShoppingCart().getTotalToPay());
                this.currentUser.setAllBuysSum(this.currentUser.getAllBuysSum() + this.currentUser.getShoppingCart().getTotalToPay());

            }
        } while (selectedProduct != NONE_RELEVANT_VALUE);


        System.out.println("your total amount is :  " + this.currentUser.getShoppingCart().getTotalToPay());
    }

    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        String productName;

        System.out.println("products name : ");
        productName = scanner.nextLine();
        int price;
        int amount = 0;
        do {
            System.out.println("what is the price of product? : ");
            price = scanner.nextInt();
        } while (price <= ZERO);
        do {
            System.out.println("how many items pieces you add? : ");
            amount = scanner.nextInt();
        } while (amount <= ZERO);
        double memberDiscount;
        do {
            System.out.println("vip club member discount percentage (number 1-100)? :  ");
            memberDiscount = scanner.nextDouble();
        } while (memberDiscount < ZERO || memberDiscount > ONE_HUNDRED);

        memberDiscount = price - price * ((ONE_HUNDRED - memberDiscount) / ONE_HUNDRED);
        Products product = new Products(productName, price, amount, true, memberDiscount);

        currentUser.shoppingCart.setTotalToPay(currentUser.getCurrentSum());
        Products[] newProductArray = new Products[products.length + 1];
        for (int i = 0; i < this.products.length; i++) {
            newProductArray[i] = this.products[i];
        }
        newProductArray[this.products.length] = product;
        this.products = newProductArray;

    }

    private void addToCart(int selectedProduct, int amountOfProduct) {

        currentUser.getShoppingCart().add(products[selectedProduct], amountOfProduct);
        products[selectedProduct].setHowManyLeft(products[selectedProduct].getHowManyLeft() - amountOfProduct);
    }

    private boolean hasDigit(String str) {
        boolean hasDigit = false;
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            if (isDigit(currentChar)) {
                hasDigit = true;
                break;
            }
        }
        return hasDigit;
    }

    public void createUser() {
        Scanner scanner = new Scanner(System.in);


        int customerOrEmployee = NONE_RELEVANT_VALUE;

        while (customerOrEmployee != EMPLOYEE && customerOrEmployee != CUSTOMER) {
            System.out.println("Are you employee or costumers ? (employee = 1 , costumer = 0 .");
            customerOrEmployee = scanner.nextInt();
        }
        if (customerOrEmployee == EMPLOYEE) {

            Customer[] newEmployees = new Customer[this.employees.length + 1];
            for (int i = 0; i < this.employees.length; i++) {
                newEmployees[i] = this.employees[i];
            }
            int rank = 0;
            while (rank != Employee.REGULAR_WORKER && rank != Employee.MANAGER && rank != Employee.BOARD_MEMBER) {
                System.out.println("Are you regular worker , manager, or board member ? " + "\n" +
                        "(regular worker = 1 , manager = 2 ,board member=3 )");
                rank = scanner.nextInt();

            }

            Employee e = new Employee(userName(false), password(), firstName(), lastName(), rank);
            newEmployees[newEmployees.length - 1] = e;
            this.employees = newEmployees;


        } else {

            Customer[] newCustomers = new Customer[this.customers.length + 1];

            for (int i = 0; i < this.customers.length; i++) {
                newCustomers[i] = this.customers[i];
            }

            int ans;
            do {
                System.out.println("Are you member ? (1 = yes , 0 = no .)");
                ans = scanner.nextInt();

            } while (ans > 1 || ans < 0);
            boolean isVIP;
            isVIP = ans == VIP;

            Customer c = new Customer(userName(true), password(), firstName(), lastName(), isVIP);
            newCustomers[newCustomers.length - 1] = c;
            this.customers = newCustomers;

        }

    }

    public Customer userLogin() {
        Scanner scanner = new Scanner(System.in);

        String userName;
        int userType;
        String password;
        do {
            System.out.println(" are you customer or employee ? (1-employee, 0= customer ");
            userType = scanner.nextInt();
            scanner.nextLine();

        } while (userType < CUSTOMER || userType > EMPLOYEE);

        Customer availableUser;
        boolean valid;
        do {
            valid = false;
            System.out.println("enter userName");
            userName = scanner.nextLine();
            System.out.println("please enter your password ");
            password = scanner.nextLine();
            availableUser = findUser(userName, password, userType);
            if (availableUser != null) {
                valid = true;
            }

        } while (!valid);

        System.out.println(availableUser.toString());

        return availableUser;
    }

    public Customer findUser(String user, String password, int userType) {
        Customer[] people = this.customers;
        if (userType == EMPLOYEE) {
            people = employees;
        }
        for (Customer customer : people) {
            if (customer.getUserName().equals(user) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null;
    }

    private String firstName() {
        Scanner scanner = new Scanner(System.in);

        String firstName = "";
        boolean correctFirstName;
        do {
            correctFirstName = false;
            System.out.println("Enter wanted first name ");
            firstName = scanner.nextLine();
            if (hasDigit(firstName)) {
                System.out.println("This first name has digit, try again please ");
                correctFirstName = true;
            }
        } while (correctFirstName);
        return firstName;
    }

    private String userName(boolean searchCustomer) {
        Scanner scanner = new Scanner(System.in);

        String newUserName = "";
        boolean exists = true;


        do {
            System.out.println("Enter wanted user name ");
            newUserName = scanner.next();

            if (searchCustomer) {
                exists = userExist(this.customers, newUserName);

            } else {
                exists = userExist(this.employees, newUserName);

            }
        } while (exists || hasDigit(newUserName));
        return newUserName;
    }

    private boolean userExist(Customer[] customers, String newUserName) {
        boolean exists = false;
        if (customers.length < 1 && !hasDigit(newUserName)) {
            return false;
        }
        for (Customer customer : customers) {
            exists = false;

            if (customer.getUserName().equals(newUserName)) {
                System.out.println("This name is occupied, try again please ");
                exists = true;
                break;
            }
        }

        return exists;
    }

    private String lastName() {
        Scanner scanner = new Scanner(System.in);

        boolean correctLastName;
        String lastName = "";

        do {
            correctLastName = false;
            System.out.println("Enter wanted last name ");
            lastName = scanner.nextLine();
            if (hasDigit(lastName)) {
                System.out.println("This last name has digit, try again please ");
                correctLastName = true;
            }
        } while (correctLastName);
        return lastName;
    }

    private String password() {
        Scanner scanner = new Scanner(System.in);

        String password;
        do {
            System.out.println("Enter password ");
            password = scanner.nextLine();
            if (password.length() < PASS_LENGTH) {
                System.out.println("password must be more then 6 characters ");
            }

        } while (password.length() < PASS_LENGTH);
        return password;
    }

    private void changeStatusOfProduct() {
        Scanner scanner = new Scanner(System.in);
        int availableProduct;
        boolean available = false;
        for (int i = 0; i < this.products.length; i++) {
            if (products[i].getHowManyLeft() > 0) {
                System.out.println((i) + "." + products[i].getProductName());
                System.out.println("price: " + products[i].getPrice());
                System.out.println("vip discount " + products[i].getMemberDiscount());
                System.out.println("number of items " + products[i].getHowManyLeft());
            }
        }
        int size = this.products.length - 1;
        int index;
        do {
            System.out.println("choose product : numbers from 0 to " + size);
            index = scanner.nextInt();
        } while (index > size || index < 0);


        do {
            System.out.println(" the product is available ? ( available = 1, not available = 0");
            availableProduct = scanner.nextInt();
        } while (availableProduct != PRODUCT_AVAILABLE && availableProduct != PRODUCT_NOT_AVAILABLE);

        if (availableProduct == PRODUCT_AVAILABLE) {
            available = true;
        }
        this.products[index].setAvailable(available);

    }

    public void printAllCustomers() {
        if (this.customers.length > 0) {
            for (int i = 0; i < this.customers.length; i++) {
                System.out.println(i + 1);
                System.out.println(this.customers[i]);
            }
        } else {
            System.out.println("there is no costumers");
        }
    }

    /*  private boolean updateAvailability(Products[] products) {
          boolean available = false;
          for (Products product : products) {
              if (product.isAvailable()) {
                  available = true;
                  System.out.println("the product is available !");
              }
          }
          return available;
      }
  */
    public void employeeMenu() {
        Scanner scanner = new Scanner(System.in);

        int userChoice;
        do {
            System.out.println("what would you like to do? " + "\n"
                    + "press accordingly :" + "\n" +
                    "1- print all clients" + "\n" +
                    "2- print all vip member clients" + "\n" +
                    "3- print all clients with one purchase at least" + "\n" +
                    "4- print  client with biggest sum of Shopping in store " + "\n" +
                    "5- add new product to store" + "\n" +
                    "6- change product status" + "\n" +
                    "7- buy as client" + "\n" +
                    "8- back to main menu ");

            userChoice = scanner.nextInt();

            switch (userChoice) {
                case PRINT_ALL_CUSTOMERS:
                    printAllCustomers();
                    continue;
                case PRINT_ALL_VIP_CUSTOMERS:
                    printAllVipMember();
                    continue;
                case CUSTOMERS_WITH_AT_LEAST_ONE_BUY:
                    System.out.println(Arrays.toString(this.customers) + "\n");
                    printCustomersWithAtLeastOnePurchase();
                    continue;

                case MOST_BUYING_CUSTOMER:
                    mostProfitCustomerAtStore();
                    continue;

                case ADD_PRODUCT:
                    addProduct();
                    continue;

                case CHANGE_PRODUCT_STATUS:
                    changeStatusOfProduct();
                    continue;
                case BUY_AS_CLIENT:
                    buyAsClient();
                    continue;
                case MAIN_MENU:
                    return;
                default:

            }
        } while (userChoice != MAIN_MENU);

    }

    private void buyAsClient() {
        existingProducts();
    }

    public void mostProfitCustomerAtStore() {

        if (this.customers.length > 0) {
            double mostProfitSum = this.customers[0].getAllBuysSum();
            // Customer mostProfitEmployee=this.employees[0].getAllBuysSum()
            int index = NONE_RELEVANT_VALUE;
            for (int i = 0; i < this.customers.length; i++) {
                if (this.customers[i].getAllBuysSum() > mostProfitSum) {
                    mostProfitSum = this.customers[i].getAllBuysSum();
                    index = i;
                }
            }
            if (index == NONE_RELEVANT_VALUE) {
                index = 0;
                System.out.println("the customer that buy the most is: " +
                        this.customers[index].toString() + "\n");
            }
        } else {
            System.out.println("list of customers id empty ");
        }
    }

    public void printAllVipMember() {
        int counter = 1;
        if (customers.length < 1) {
            System.out.println("there is no vip customers");
        }
        for (int i = 0; i < this.customers.length; i++) {
            if (customers[i].isMember()) {
                System.out.println(counter);
                System.out.println(this.customers[i]);
                counter++;
            }
        }
    }

    public void printCustomersWithAtLeastOnePurchase() {

        if (customers.length < 1) {
            System.out.println("no customer have made purchase yet ");
            return;
        }
        for (int i = 0; i < this.customers.length; i++) {
            if (this.customers[i].getNumberOfBuys() > 0) {
                System.out.println(this.customers[i].toString());
            }
        }

    }

}

