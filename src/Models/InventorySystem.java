import java.util.ArrayList;
import java.util.List;

public class InventorySystem {
    List<HealthFacility> healthFacilities;
    List<Product> products;
    List<Delivery> deliveries;
    private GovernmentRepresentative governmentRepresentative;
    private HealthRepresentative healthRepresentative;

    public InventorySystem() {
        this.healthFacilities = new ArrayList<>();
        this.products = new ArrayList<>();
        this.deliveries = new ArrayList<>();
    }

    public void setGovernmentRepresentative(GovernmentRepresentative governmentRepresentative) {
        this.governmentRepresentative = governmentRepresentative;
    }

    public void setHealthRepresentative(HealthRepresentative healthRepresentative) {
        this.healthRepresentative = healthRepresentative;
    }

    public void addHealthFacility(HealthFacility healthFacility) {
        healthFacilities.add(healthFacility);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }

    public void markDeliveryAsReceived(Delivery delivery) {
        delivery.markAsReceived();
    }

    public void displayReport() {
        System.out.println("-------- Inventory Report --------");
        System.out.println("Government Representative: " + governmentRepresentative.getName());
        System.out.println("Health Representative: " + healthRepresentative.getName());

        System.out.println("\nHealth Facilities:");
        for (HealthFacility facility : healthFacilities) {
            System.out.println("Name: " + facility.getName());
            System.out.println("Location: " + facility.getLocation());
            System.out.println();
        }

        System.out.println("Products:");
        for (Product product : products) {
            System.out.println("Name: " + product.getName());
            System.out.println("Quantity: " + product.getQuantity());
            System.out.println();
        }

        System.out.println("Deliveries:");
        for (Delivery delivery : deliveries) {
            System.out.println("Health Facility: " + delivery.getHealthFacility().getName());
            System.out.println("Product: " + delivery.getProduct().getName());
            System.out.println("Received: " + (delivery.isReceived() ? "Yes" : "No"));
            System.out.println();
        }
    }
}
