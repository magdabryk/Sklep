package pl.camp.it.sklep.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int amount;

    public Product() {
    }

    public Product(int id, String name, double price, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ID: ")
                .append(this.getId())
                .append(" Nazwa: ")
                .append(this.getName())
                .append(" Cena: ")
                .append(this.getPrice())
                .append(" Ilość: ")
                .append(this.getAmount()).toString();
    }
}
