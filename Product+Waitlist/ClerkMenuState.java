import java.util.Scanner;

public class ClerkMenuState implements State {
    private final Context context;

    public ClerkMenuState(Context context) {
        this.context = context;
    }

    @Override
    public void display() {
        Scanner sc = context.getScanner();
        ProductCatalog catalog = context.getCatalog();

        while (true) {
            System.out.println("\n=== Clerk Menu ===");
            System.out.println("1. Show List of Products");
            System.out.println("2. Become a Client");
            System.out.println("3. Logout");
            System.out.print("Select: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    catalog.getAllProductInfo(System.out);
                    break;
                case "2":
                    System.out.print("Enter Client ID: ");
                    String id = sc.nextLine();
                    context.setClientID(id);
                    context.setStartRole("clerk");
                    context.changeState(context.getClientMenuState());
                    return;
                case "3":
                    context.changeState(context.getOpeningState());
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
