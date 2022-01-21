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

    public Products(String productName, int price, int howManyLeft, boolean isAvailable, double memberDiscount) {
        this.productName = productName;
        this.price = price;
        this.howManyLeft = howManyLeft;
        this.isAvailable = isAvailable;
        this.memberDiscount = memberDiscount;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setHowManyLeft(int howManyLeft) {
        if (!this.isAvailable) {
            this.howManyLeft = 0;
        }else {
            this.howManyLeft = howManyLeft;
        }
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
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
        return isAvailable;
    }

    @Override
    public String toString() {
        return
                productName + " " +
                        +howManyLeft + " "
                        + memberDiscount;
    }
}
