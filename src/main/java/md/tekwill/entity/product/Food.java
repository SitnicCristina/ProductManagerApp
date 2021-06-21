package md.tekwill.entity.product;

import java.time.LocalDate;
import java.util.Objects;

public class Food extends Product{

    public static final LocalDate THREEDAYS = LocalDate.now().plusDays(3);
    protected double DISCOUNT = 0.8;
    protected FoodCategory category;

    public Food(String name, double price, LocalDate bestBefore, FoodCategory category) {
        super(name, price, bestBefore);
        this.category = category;
    }

    public FoodCategory getCategory() { return category; }

    public void setCategory(FoodCategory category) { this.category = category; }

    @Override
    public double getPriceOnBill() {
        return bestBefore.equals(THREEDAYS) ? price*DISCOUNT : price;
    }

    @Override
    public String getPrintText() {
        return "[" + id +"]" + " FOOD: " + name + " | " + price + " | " + bestBefore + " | " + category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return Double.compare(food.DISCOUNT, DISCOUNT) == 0 && Objects.equals(category, food.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), DISCOUNT, category);
    }

    @Override
    public String toString() {
        return "Food{" +
                "DISCOUNT=" + DISCOUNT +
                ", category=" + category +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", bestBefore=" + bestBefore +
                '}';
    }
}
