import java.util.Scanner;

public class ManagerMenuState implements State {
    private final Context context;

    public ManagerMenuState(Context context) {
        this.context = context;
    }

    @Override
    public void display() {
        Scanner sc = context.getScanner();
        ProductCatalog catalog = context.getCatalog();

        while (true) {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1. Add Product");
            System.out.println("2. Display Waitlists");
            System.out.println("3. Receive Shipment");
            System.out.println("4. Become Clerk");
            System.out.println("5. Logout");
            System.out.print("Select: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter SKU: ");
                    String sku = sc.nextLine();
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter price: ");
                    double price = Double.parseDouble(sc.nextLine());
                    catalog.addProduct(new Product(name, sku, desc, qty, price));
                    System.out.println("Product added successfully.");
                    break;
                case "2":
                    catalog.printAllWaitlists();
                    break;
                case "3":
                    System.out.print("Enter SKU to receive: ");
                    String rSku = sc.nextLine();
                    System.out.print("Enter quantity received: ");
                    int amount = Integer.parseInt(sc.nextLine());
                    new Shipment(catalog).receiveShipment(rSku, amount);
                    break;
                case "4":
                    context.changeState(context.getClerkMenuState());
                    return;
                case "5":
                    context.changeState(context.getOpeningState());
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
