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

    public void showMenu() {
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

    public boolean handleUserChoice(int option) {
        switch (option) {
            case 1:
                viewAllNonExpiredProducts();
                break;
            case 2:
                viewShoppingCart();
                break;
            case 3:
                addProductToShoppingCart();
                break;
            case 4:
                printBill();
                break;
            case 0:
                System.out.println("The app is closed. BYE ");
                return true;
            default:
                System.out.println("Your option is wrong! Please enter again: \n");
                break;
        }
        return false;
    }

    private void viewAllNonExpiredProducts() {

    }

    private void viewShoppingCart() {

    }

    private void addProductToShoppingCart() {

    }

    private void printBill() {

    }
}
