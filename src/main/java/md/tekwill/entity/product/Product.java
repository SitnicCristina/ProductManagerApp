package md.tekwill.entity.product;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Product implements Billable,Printable {
    protected int id;
    protected String name;
    protected double price;
    protected LocalDate bestBefore;

    protected Product(String name, double price, LocalDate bestBefore) {
        this.name = name;
        this.price = price;
        this.bestBefore = bestBefore;
    }

    public void setId(int id) { this.id = id; }
    public void setPrice(double price) { this.price = price; }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public LocalDate getBestBefore() { return bestBefore; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                bestBefore.equals(product.bestBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, bestBefore);
    }
}