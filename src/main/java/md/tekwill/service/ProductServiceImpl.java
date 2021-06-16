package md.tekwill.service;

import md.tekwill.dao.ProductRepository;
import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.entity.product.FoodCategory;

import md.tekwill.entity.product.Product;
import md.tekwill.exceptions.ProductUpdateUnknownPropertyException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, double volume){
        Drink drink = new Drink(name,price,bestBefore,volume);
        productRepository.save(drink);
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, FoodCategory category){
        Food food = new Food(name,price,bestBefore,category);
        productRepository.save(food);
    }

    @Override
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllNonExpired(){
        List<Product> productList = productRepository.findAll();
        List<Product> expiredProductList = new ArrayList<>();
        for(Product product: productList){
            if(product.getBestBefore().compareTo(LocalDate.now()) > 0){
                expiredProductList.add(product);
            }
        }
        return expiredProductList;
    }

    @Override
    public List<Product> getAllExpired(){
        List<Product> productList = productRepository.findAll();
        List<Product> expiredProductList = new ArrayList<>();
        for(Product product: productList){
            if(product.getBestBefore().compareTo(LocalDate.now()) < 0){
                expiredProductList.add(product);
            }
        }
        return expiredProductList;
    }

    @Override
    public Product getById(int id){
        return productRepository.findById(id);
    }

    @Override
    public void update(int id, double price) throws ProductUpdateUnknownPropertyException{
        productRepository.update(id,price);
    }

    @Override
    public void update(int id, FoodCategory category) throws ProductUpdateUnknownPropertyException {
        productRepository.update(id,category);
    }

    @Override
    public void delete(int id){
        productRepository.delete(id);
    }

}
