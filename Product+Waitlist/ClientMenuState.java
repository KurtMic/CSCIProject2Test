import java.util.Scanner;

public class ClientMenuState implements State {
    private final Context context;

    public ClientMenuState(Context context) {
        this.context = context;
    }

    @Override
    public void display() {
        Scanner sc = context.getScanner();
        ProductCatalog catalog = context.getCatalog();
        String clientID = context.getClientID();

        while (true) {
            System.out.println("\n=== Client Menu (ID: " + clientID + ") ===");
            System.out.println("1. Show List of Products");
            System.out.println("2. Add Item to Wishlist");
            System.out.println("3. Display My Waitlist");
            System.out.println("4. Logout");
            System.out.print("Select: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    catalog.getAllProductInfo(System.out);
                    break;
                case "2":
                    System.out.print("Enter SKU to add to wishlist: ");
                    String sku = sc.nextLine();
                    catalog.addToWaitlistFile(sku, clientID + "@example.com", clientID);
                    break;
                case "3":
                    catalog.printClientWaitlist(context.getClientID());
                    break;
                case "4":
                    // Go back to clerk or opening, depending on start role
                    if ("clerk".equals(context.getStartRole())) {
                        context.changeState(context.getClerkMenuState());
                    } else {
                        context.changeState(context.getOpeningState());
                    }
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
