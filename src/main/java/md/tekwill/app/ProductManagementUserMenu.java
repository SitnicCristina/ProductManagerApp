package md.tekwill.app;

import md.tekwill.ShoppingCart;
import md.tekwill.entity.product.Product;
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
        System.out.println("[1] View all available products");
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
        System.out.println("--- ALL AVAILABLE PRODUCTS ---");
        for (Product product : productService.getAllNonExpired()) {
            System.out.println(product.getPrintText());
        }
    }

    private void viewShoppingCart() {
        if(cart.getProductList().isEmpty()){
            System.out.println("Empty!");
        }else{
            System.out.println("--- SHOPPING CART CONTENT ---");
        System.out.println(cart.getProductList());}
    }

    private void addProductToShoppingCart() {
        System.out.println("Input the id of the item to add to cart: ");
        int id = scanner.nextInt();
        cart.addProduct(productService.getById(id));
        System.out.println("Product with ID " + id + " successfully added!");
    }

    private void printBill() {
        if(cart.getProductList().isEmpty()){
            System.out.println("No product yet! ");
        }else{
            System.out.println(cart.getPrice());
        }

    }
}
