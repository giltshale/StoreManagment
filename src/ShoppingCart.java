import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Products> products;
    private double totalToPay;

    public ShoppingCart() {
        this.products = new ArrayList<>();
        this.totalToPay = 0;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public void setTotalToPay(double totalToPay) {
        this.totalToPay = totalToPay;
    }

    public List<Products> getProducts() {
        return products;
    }

    public double getTotalToPay() {
        return totalToPay;
    }

    public void add(Products product, int amount) {
        for (int i = 0; i < amount; i++) {
            this.products.add(product);
            System.out.println(amount+ "pieces of "+product +" has been added succesufly !");
        }

    }

   /*  private void incArrayOfProducts(int amount) {
        List<Products> products1=new ArrayList<>();
        for(Products p: products)
            products1.add(p);
       Products[] products1 = new Products[products.length + amount];
        for (int i = 0; i < products.length; i++) {
            products1[i] = products[i];
        }
        this.products = products1;

    }*/
}



