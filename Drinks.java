public class Drinks {
    private String name;
    private double price;
    public Drinks (String n, double p) {
        name = n;
        price = p;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
   public double getPrice() {
    return price;
   }
   public void setPrice(double price) {
    this.price = price;
   }
}
