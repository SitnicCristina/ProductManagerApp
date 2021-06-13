package md.tekwill.app;

import md.tekwill.service.ProductService;

import java.util.Scanner;

public class ProductManagementAdminMenu {

    private final Scanner scanner;
    private final ProductService productService;

    public ProductManagementAdminMenu(Scanner scanner, ProductService productService) {
        this.scanner = scanner;
        this.productService = productService;
    }

    public void showMenu(){
        System.out.println("Available options:");
        System.out.println("==============USER OPTIONS==============");
        System.out.println("[1] View all products");
        System.out.println("[2] View shopping cart");
        System.out.println("[3] Add product to shopping cart");
        System.out.println("[4] Print bill");
        System.out.println("========================================");
        System.out.println("[0] Exit");
        System.out.println("========================================");
    }
}
