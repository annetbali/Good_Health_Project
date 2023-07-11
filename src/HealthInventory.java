import java.util.*;
public class InventoryManagementSystem {
    private static boolean loggedIn = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventorySystem inventorySystem = new InventorySystem();

        while (true) {
            System.out.println("-------- Inventory Management System --------");
            System.out.println("1. Log In");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                if (loggedIn) {
                    System.out.println("You are already logged in.");
                } else {
                    loggedIn = true;
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();

                    if (authenticateUser(username, password)) {
                        System.out.println("Logged in successfully!");
                        inventoryManagementMenu(scanner, inventorySystem);
                        loggedIn = false;
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                }
            } else if (choice == 2) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean authenticateUser(String username, String password) {
        // Perform authentication logic here (e.g., check against a predefined admin username and password)
        // Return true if authentication is successful, false otherwise
        return username.equals("admin") && password.equals("password");
    }

    private static void inventoryManagementMenu(Scanner scanner, InventorySystem inventorySystem) {
        while (true) {
            System.out.println("-------- Inventory Management Menu --------");
            System.out.println("1. Add Health Facility");
            System.out.println("2. Add Product");
            System.out.println("3. Add Delivery");
            System.out.println("4. Mark Delivery as Received");
            System.out.println("5. Generate Report");
            System.out.println("6. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the Health Facility: ");
                    String facilityName = scanner.nextLine();
                    System.out.print("Enter the location of the Health Facility: ");
                    String facilityLocation = scanner.nextLine();
                    HealthFacility healthFacility = new HealthFacility(facilityName, facilityLocation);
                    inventorySystem.addHealthFacility(healthFacility);
                    System.out.println("Health Facility added successfully!");
                    break;
                case 2:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the Product: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter the quantity of the Product: ");
                    int productQuantity = scanner.nextInt();
                    Product product = new Product(productName, productQuantity);
                    inventorySystem.addProduct(product);
                    System.out.println("Product added successfully!");
                    break;
                case 3:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the Health Facility: ");
                    String deliveryFacilityName = scanner.nextLine();
                    HealthFacility deliveryFacility = findHealthFacility(inventorySystem, deliveryFacilityName);
                    if (deliveryFacility == null) {
                        System.out.println("Health Facility not found. Please try again.");
                        break;
                    }

                    System.out.print("Enter the name of the Product: ");
                    String deliveryProductName = scanner.nextLine();
                    Product deliveryProduct = findProduct(inventorySystem, deliveryProductName);
                    if (deliveryProduct == null) {
                        System.out.println("Product not found. Please try again.");
                        break;
                    }

                    Delivery delivery = new Delivery(deliveryFacility, deliveryProduct);
                    inventorySystem.addDelivery(delivery);
                    System.out.println("Delivery added successfully!");
                    break;
                case 4:
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter the name of the Health Facility: ");
                    String receivedFacilityName = scanner.nextLine();
                    HealthFacility receivedFacility = findHealthFacility(inventorySystem, receivedFacilityName);
                    if (receivedFacility == null) {
                        System.out.println("Health Facility not found. Please try again.");
                        break;
                    }

                    System.out.print("Enter the name of the Product: ");
                    String receivedProductName = scanner.nextLine();
                    Product receivedProduct = findProduct(inventorySystem, receivedProductName);
                    if (receivedProduct == null) {
                        System.out.println("Product not found. Please try again.");
                        break;
                    }

                    Delivery receivedDelivery = findDelivery(inventorySystem, receivedFacility, receivedProduct);
                    if (receivedDelivery == null) {
                        System.out.println("Delivery not found. Please try again.");
                        break;
                    }

                    inventorySystem.markDeliveryAsReceived(receivedDelivery);
                    System.out.println("Delivery marked as received successfully!");
                    break;
                case 5:
                    inventorySystem.displayReport();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static HealthFacility findHealthFacility(InventorySystem inventorySystem, String facilityName) {
        for (HealthFacility facility : inventorySystem.healthFacilities) {
            if (facility.getName().equalsIgnoreCase(facilityName)) {
                return facility;
            }
        }
        return null;
    }

    private static Product findProduct(InventorySystem inventorySystem, String productName) {
        for (Product product : inventorySystem.products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    private static Delivery findDelivery(InventorySystem inventorySystem, HealthFacility facility, Product product) {
        for (Delivery delivery : inventorySystem.deliveries) {
            if (delivery.getHealthFacility() == facility && delivery.getProduct() == product) {
                return delivery;
            }
        }
        return null;
    }
}
