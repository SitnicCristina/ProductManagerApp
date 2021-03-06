package md.tekwill.service;

import md.tekwill.dao.ProductRepository;
import md.tekwill.entity.product.Drink;
import md.tekwill.entity.product.Food;
import md.tekwill.entity.product.FoodCategory;

import md.tekwill.entity.product.Product;
import md.tekwill.exceptions.ProductExistsException;
import md.tekwill.exceptions.ProductNotFoundException;
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
        productRepository.save(new Drink(name,price,bestBefore,volume));
    }

    @Override
    public void create(String name, double price, LocalDate bestBefore, FoodCategory category){
        if (productRepository.findByName(name) != null) {
            throw new ProductExistsException("Product with name " + name + " already exists!");
        }
        productRepository.save(new Food(name,price,bestBefore,category));
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
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product with id " + id + " not found!");
        }
        return product;
    }

    @Override
    public void update(int id, double volume) throws ProductUpdateUnknownPropertyException{
        Product product = getById(id);
        if (product instanceof Drink) {
            productRepository.update(id, volume);
            return;
        }
        throw new ProductUpdateUnknownPropertyException("Product with id " + id + " is not drink!");
    }

    @Override
    public void update(int id, FoodCategory category) throws ProductUpdateUnknownPropertyException {
        Product product = getById(id);
        if (product instanceof Food) {
            productRepository.update(id, category);
            return;
        }
        throw new ProductUpdateUnknownPropertyException("Product with id " + id + " is not food!");
    }

    @Override
    public void delete(int id){
        productRepository.delete(id);
    }

}
