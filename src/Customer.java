import java.util.Date;

public class Customer {
    protected String firstName;
    protected String lastName;
    protected ShoppingCart shoppingCart;
    private String userName;
    private String password;
    private int numberOfBuys;
    private boolean isMember;
    private Date lastVisit;
    private double allBuysSum;
    private double currentSum;

    public double getCurrentSum() {
        return this.currentSum;
    }

    public void setCurrentSum(double currentSum) {
        this.currentSum = currentSum;
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
        this.currentSum = 0;
        this.isMember = isVIP;
        this.currentSum = 0;
    }

    public Customer(String userName, String password, String firstName, String lastName,
                    boolean isMember, int numberOfBuys, int allBuysSum,
                    Date timesOfVisit, ShoppingCart shoppingCart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.isMember = isMember;
        this.lastVisit = timesOfVisit;
        this.shoppingCart = shoppingCart;
        this.numberOfBuys = numberOfBuys;
        this.allBuysSum = allBuysSum;
        this.currentSum = 0;

    }


    public void setAllBuysSum(double add) {
        this.allBuysSum += add;
    }

    public double getAllBuysSum() {
        return allBuysSum;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public boolean isMember() {
        return isMember;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setNumberOfBuys(int numberOfBuys) {
        this.numberOfBuys = numberOfBuys;
    }

    public int getNumberOfBuys() {
        return numberOfBuys;
    }

    public void addBuy() {
        //this.allBuysSum += add;
        // this.date = new Date();
        this.numberOfBuys += 1;
    }

    @Override
    public String toString() {
        String printedText;
        String fullName = this.firstName + " " + this.lastName;

        if (isMember) {
            printedText = fullName + " (VIP)!\n";

        } else {
            printedText = fullName + "!\n";
        }
        printedText += " " + "number of buys: " +
                "" + this.numberOfBuys + "\n total sum of all buys: " + this.allBuysSum
                + " \n lats purchase made at: " + this.lastVisit + "\n";

        return printedText;
    }

}