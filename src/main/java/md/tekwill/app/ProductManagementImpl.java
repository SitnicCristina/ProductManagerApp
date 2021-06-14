package md.tekwill.app;

import md.tekwill.ShoppingCart;
import md.tekwill.service.ProductService;

import java.util.Scanner;

public class ProductManagementImpl implements ProductManagement {

    private final Scanner scanner;
    private final ProductManagementAdminMenu adminMenu;
    private final ProductManagementUserMenu userMenu;

    public ProductManagementImpl(ProductService productService, ShoppingCart cart, Scanner scanner) {
        this.scanner = scanner;
        this.adminMenu = new ProductManagementAdminMenu(scanner, productService);
        this.userMenu = new ProductManagementUserMenu(productService, cart, scanner);
    }

    @Override
    public void run() {
        System.out.println("\n============================================");
        System.out.println("\n\tWelcome to Product Management App");
        System.out.println("\n============================================");
        System.out.println("Press 'Enter' to continue ...");
        String userString = scanner.nextLine();
        if (userString.equalsIgnoreCase("admin")) {
            boolean exitProgram = false;
            do {
                adminMenu.showMenu();
                int option = scanner.nextInt();
                exitProgram = adminMenu.handleAdminChoice(option);
            } while (!exitProgram);

        } else {
            boolean exitProgram = false;
            do {
                userMenu.showMenu();
                int option = scanner.nextInt();
                exitProgram = userMenu.handleUserChoice(option);
            } while (!exitProgram);
        }


    }
}
