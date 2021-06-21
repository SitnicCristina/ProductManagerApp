package md.tekwill.entity.product;

import java.time.LocalDate;
import java.util.Objects;

public class Drink extends Product {

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate TOMORROW = LocalDate.now().plusDays(1);
    protected double DISCOUNT = 0.5;
    protected double volume;

    public Drink(String name, double price, LocalDate bestBefore, double volume) {
        super(name, price, bestBefore);
        this.volume = volume;
    }

    public double getVolume() { return volume; }

    public void setVolume(double volume) { this.volume = volume; }

    @Override
    public String getPrintText() {
        return "[" + id +"]" + " DRINK: " + name + " | " + price + " | " + bestBefore + " | " + volume;
    }

    @Override
    public double getPriceOnBill() {
        return (bestBefore.equals(TODAY) || bestBefore.equals(TOMORROW)) ? price*DISCOUNT: price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return Double.compare(drink.DISCOUNT, DISCOUNT) == 0 &&
                Double.compare(drink.volume, volume) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), DISCOUNT, volume);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "DISCOUNT=" + DISCOUNT +
                ", volume=" + volume +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", bestBefore=" + bestBefore +
                '}';
    }
}

