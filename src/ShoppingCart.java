public class ShoppingCart {
    private Products[] products;
    private double totalToPay;

    public ShoppingCart() {
        this.products = new Products[0];
        this.totalToPay = 0;
    }

    public void setProducts(Products[] products) {
        this.products = products;
    }

    public void setTotalToPay(double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public Products[] getProducts() {
        return products;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public void add(Products product, int amount) {
        incArrayOfProducts(amount);
        for (int i = 0; i < amount; i++) {
            this.products[products.length - amount + i] = product;
        }

    }

    private void incArrayOfProducts(int amount) {

        Products[] products1 = new Products[products.length + amount];
        for (int i = 0; i < products.length; i++) {
            products1[i] = products[i];
        }
        this.products = products1;

    }
}



