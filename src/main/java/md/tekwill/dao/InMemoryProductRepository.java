package md.tekwill.dao;

import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.entity.product.FoodCategory;
import md.tekwill.entity.product.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import md.tekwill.exceptions.ProductExistException;
import md.tekwill.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

    List<Product> productList;

    public InMemoryProductRepository() {
        Food sushi = new Food("Sushi",5.99, LocalDate.now().plusDays(7), FoodCategory.ANIMAL_SOURCE);
        sushi.setId(1);
        Food avocado = new Food("Avocado",1.99, LocalDate.now().plusDays(14), FoodCategory.FRUIT);
        avocado.setId(2);
        Food bread = new Food("Bread",0.99, LocalDate.now().plusDays(2), FoodCategory.GRAIN);
        bread.setId(3);

        Drink tea = new Drink("Tea",2.49, LocalDate.now().plusMonths(1), 1.5);
        tea.setId(3);
        Drink beer = new Drink("Beer",3.49, LocalDate.now().plusMonths(1), 0.5);
        beer.setId(3);
        Drink juice = new Drink("Juice",1.49, LocalDate.now().plusMonths(1), 2.0);
        juice.setId(3);
        Drink coffee = new Drink("Coffee",19.99, LocalDate.now().minusDays(2), 1.0);
        coffee.setId(3);

        productList.add(sushi);
        productList.add(avocado);
        productList.add(bread);
        productList.add(tea);
        productList.add(beer);
        productList.add(juice);
        productList.add(coffee);
    }

    @Override
    public void save(Product product){
        if(findByName(product.getName()) != null){
            throw new ProductExistException("Product with name " + product.getName() + "already exist!");
        }
        product.setId(getMaxId()+1);
        productList.add(product);
    }

    private int getMaxId(){
        int id = 0;
        for(Product product: productList){
            if(product.getId()>id){
                id = product.getId();
            }
        }
        return id;
    }

    @Override
    public List<Product> findAll(){
        return new ArrayList<>(productList);
    }

    @Override
    public Product findById(int id){
        Product product = null;
        for (Product p : productList) {
            if (p.getId() == id) {
                product = p;
            }
        }
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " does not exist in our system!");
        }
        return product;
    }

    @Override
    public Product findByName(String name){
        Product product = null;
        for (Product p : productList) {
            if (p.getName() == name) {
                product = p;
            }
        }
        if (product == null) {
            throw new ProductNotFoundException("Product with name " + name + " does not exist in our system!");
        }
        return product;
    }

    @Override
    public void update(int id, double newPrice) {
        Product product = findById(id);
        product.setPrice(newPrice);
    }

    @Override
    public void update(int id, FoodCategory newFoodCategory) {
        Food food = (Food) findById(id);
        food.setCategory(newFoodCategory);
    }

    @Override
    public void delete(int id) {
        Product product = findById(id);
        productList.remove(product);
    }
}
