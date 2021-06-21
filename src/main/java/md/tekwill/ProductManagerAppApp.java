package md.tekwill;

import md.tekwill.app.ProductManagement;
import md.tekwill.app.ProductManagementImpl;
import md.tekwill.dao.InMemoryProductRepository;
import md.tekwill.dao.JDBCProductRepository;
import md.tekwill.dao.ProductRepository;
import md.tekwill.exceptions.ProductUpdateUnknownPropertyException;
import md.tekwill.service.ProductService;
import md.tekwill.service.ProductServiceImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ProductManagerAppApp {

    public static void main(String[] args) throws ProductUpdateUnknownPropertyException {
        //ProductRepository productRepository = new InMemoryProductRepository();
        ProductRepository productRepository = new JDBCProductRepository();
        ProductService productService = new ProductServiceImpl(productRepository);
        ProductManagement productManagement = new ProductManagementImpl(productService, new ShoppingCart(), new Scanner(System.in));

        productManagement.run();
       SpringApplication.run(ProductManagerAppApp.class, args);
    }
}
