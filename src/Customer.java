import java.util.Date;

public class Customer {    // implements StoreInterface
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected int numberOfBuys;
    protected ShoppingCart shoppingCart;
    protected boolean isMember;
    protected String lastVisit;
    private int allBuysSum;
    private double currentSum;
    private Date date;


    public double getCurrentSum() {
        return this.currentSum;
    }

    public void setCurrentSum(double currentSum) {

        this.currentSum += currentSum;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Customer(String userName, String password, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isMember = false;
        this.lastVisit = null;
        this.shoppingCart = new ShoppingCart();
        this.numberOfBuys = 0;
        this.allBuysSum = 0;
        this.currentSum=0;
        this.date = new Date();
        this.currentSum=0;
    }

    public Customer(String userName, String password, String firstName, String lastName, boolean isVIP) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isMember = false;
        this.lastVisit = null;
        this.shoppingCart = new ShoppingCart();
        this.numberOfBuys = 0;
        this.allBuysSum = 0;
        this.date = new Date();
        this.currentSum=0;
        this.isMember = isVIP;
        this.date = new Date();
        this.currentSum=0;
    }

    public Customer(String userName, String password, String firstName, String lastName,
                    boolean isMember, int numberOfBuys, int allBuysSum,
                    String timesOfVisit, ShoppingCart shoppingCart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isMember = isMember;
        this.lastVisit = timesOfVisit;
        this.shoppingCart = shoppingCart;
        this.numberOfBuys = numberOfBuys;
        this.allBuysSum = allBuysSum;
        this.currentSum=0;

    }

    public Customer(Customer other) {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.userName = other.userName;
        this.password = other.password;
        this.isMember = other.isMember;
        this.lastVisit = other.lastVisit;
        this.date = other.date;
        this.currentSum=other.currentSum;
        this.shoppingCart=other.shoppingCart;
    }

    public void setAllBuysSum(int allBuysSum) {

        this.allBuysSum += allBuysSum;
    }

    public int getAllBuysSum() {
        return allBuysSum;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public boolean isMember() {
        return isMember;
    }

    public String getLastVisit() {
        return lastVisit;
    }

    public void setNumberOfBuys(int numberOfBuys) {
        this.numberOfBuys = numberOfBuys;
    }

    public int getNumberOfBuys() {
        return numberOfBuys;
    }

    public void addBuy() {
        this.numberOfBuys += 1;
    }

    @Override
    public String toString() {
        String printedText = "";
        String fullName = this.firstName +this.lastName;

        if (isMember) {
            printedText = fullName + " (VIP)!";

        } else {
            printedText = fullName + "!";
        }
        return printedText;
    }





    /*public Costumer logIn() {
        {
            String userName = "";
            String password = "";
            Scanner scanner = new Scanner(System.in);
            boolean userFound = false;
            Costumer costumer = null;
            do {
                System.out.println("please enter user name for login: ");
                userName = scanner.nextLine();
                System.out.println("please enter your password");
                password = scanner.nextLine();
                for (int i = 0; i < users.length; i++) {
                    if (users[i].getUserName().equals(userName) &&
                            users[i].getPassword().equals(password)) {
                        user = users[i];
                        userFound = true;
                        break;
                    }

                }
            } while (!userFound);
            return user;

        }

}
*/
}