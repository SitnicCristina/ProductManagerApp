package md.tekwill.app;

import md.tekwill.ShoppingCart;
import md.tekwill.service.ProductService;

import java.util.Scanner;

public class ProductManagementUserMenu {

    private final ProductService productService;
    private final ShoppingCart cart;
    private final Scanner scanner;

    public ProductManagementUserMenu(ProductService productService, ShoppingCart cart, Scanner scanner) {
        this.productService = productService;
        this.cart = cart;
        this.scanner = scanner;
    }

    public void showMenu(){
        System.out.println("Available options:");
        System.out.println("==============ADMIN OPTIONS=============");
        System.out.println("[1] View all products");
        System.out.println("[2] View all expired products");
        System.out.println("[3] Add new product");
        System.out.println("[4] Update food product");
        System.out.println("[5] Update drink food");
        System.out.println("[6] remove product");
        System.out.println("========================================");
        System.out.println("[0] Exit");
        System.out.println("========================================");
    }
}
