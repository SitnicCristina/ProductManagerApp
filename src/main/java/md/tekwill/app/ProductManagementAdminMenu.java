package md.tekwill.app;

import md.tekwill.entity.product.FoodCategory;
import md.tekwill.entity.product.Product;
import md.tekwill.service.ProductService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ProductManagementAdminMenu {

    private final Scanner scanner;
    private final ProductService productService;

    public ProductManagementAdminMenu(Scanner scanner, ProductService productService) {
        this.scanner = scanner;
        this.productService = productService;
    }

    public void showMenu() {
        System.out.println("Available options:");
        System.out.println("==============ADMIN OPTIONS=============");
        System.out.println("[1] View all products");
        System.out.println("[2] View all expired products");
        System.out.println("[3] Add new product");
        System.out.println("[4] Update food product");
        System.out.println("[5] Update drink food");
        System.out.println("[6] Remove product");
        System.out.println("========================================");
        System.out.println("[0] Exit");
        System.out.println("========================================");
    }

    public boolean handleAdminChoice(int option) {
        switch (option) {
            case 1:
                viewAllProducts();
                break;
            case 2:
                viewAllExpiredProducts();
                break;
            case 3:
                addNewProduct();
                break;
            case 4:
                updateFood();
                break;
            case 5:
                updateDrink();
                break;
            case 6:
                removeProduct();
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

    private void viewAllProducts() {
        System.out.println("--- ALL EXISTING PRODUCTS ---");
        for (Product product : productService.getAll()) {
            System.out.println(product.getPrintText());
        }
    }

    private void viewAllExpiredProducts() {
        System.out.println("--- ALL EXPIRED PRODUCTS ---");
        for (Product product : productService.getAllExpired()) {
            System.out.println(product.getPrintText());
        }
    }

    private void addNewProduct() {
        System.out.println("--- ADD NEW PRODUCT ---");
        System.out.println("Input the name of the product: ");
        scanner.nextLine();
        String addNewName = scanner.nextLine();

        System.out.println("Input the price of the product: ");
        double addNewPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Input the best before of the product (such '2021-12-03'): ");
        String addNewDate = scanner.nextLine();
        DateTimeFormatter readDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedAddNewDate = LocalDate.parse(addNewDate, readDateTimeFormatter);

        System.out.println("It is a food or a drink? ");
        String addTypeProduct = scanner.nextLine();

        if (addTypeProduct.equalsIgnoreCase("food")) {
            System.out.println("What category is that? (Categories[ANIMAL_SOURCE, FRUIT, GRAIN]): ");
            String addNewCategory = scanner.nextLine();
            productService.create(addNewName, addNewPrice, parsedAddNewDate, FoodCategory.valueOf(addNewCategory.toUpperCase()));
            System.out.println("Food " + addNewName + " successfully created!");
        } else if (addTypeProduct.equalsIgnoreCase("drink")) {
            System.out.println("What volume is that? ");
            double addNewVolume = scanner.nextDouble();
            productService.create(addNewName, addNewPrice, parsedAddNewDate, addNewVolume);
            System.out.println("Drink " + addNewName + " successfully created!");
        }
    }

    private void updateFood() {
        System.out.println("Input the id of food to update: ");
        int id = scanner.nextInt();
        System.out.println("Chose another category to update (Categories[ANIMAL_SOURCE, FRUIT, GRAIN]): ");
        String updateCategoryTo = scanner.nextLine();
        productService.update(id,FoodCategory.valueOf(updateCategoryTo));
        System.out.println("Product with ID " + id + " is successfully updated");
    }

    private void updateDrink() {
        System.out.println("Input the id of drink to update: ");
        int id = scanner.nextInt();
        System.out.println("Chose another volume to update: ");
        double updateVolumeTo = scanner.nextDouble();
        productService.update(id,updateVolumeTo);
        System.out.println("Product with ID " + id + " is successfully updated");
    }

    private void removeProduct() {
        System.out.println("Input the id of the product to delete: ");
        int id = scanner.nextInt();
        productService.delete(id);
        System.out.println("Product with ID " + id + " is successfully deleted");
    }
}
