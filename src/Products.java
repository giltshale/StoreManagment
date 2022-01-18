public class Products {
    private String productName;
    private int price;
    private int howManyLeft;
    private boolean isAvailable;
    private double memberDiscount;

    public void setMemberDiscount(double memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public double getMemberDiscount() {
        return memberDiscount;
    }

    public Products(String productName, int price, int howManyLeft, boolean isAvailable,double memberDiscount) {
        this.productName = productName;
        this.price = price;
        this.howManyLeft = howManyLeft;
        this.isAvailable = isAvailable;
        this.memberDiscount=memberDiscount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHowManyLeft(int howManyLeft) {
        this.howManyLeft = howManyLeft;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getHowManyLeft() {
        return howManyLeft;
    }

    public boolean isAvailable() {
        return howManyLeft > 0;
    }

    public Products(String productName, int price, int howManyLeft) {
        this.price = price;
        this.productName = productName;
        this.howManyLeft = howManyLeft;

    }


}
