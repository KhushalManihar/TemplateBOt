public class Pizza {
    //A new class must be made. You should change this to match what your class should be named.
    private String name;
    private double price;
    public Pizza (String n, double p) {
        name = n;
        price = p;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }
}
