import java.util.Scanner;

public class OpeningState implements State {
    private final Context context;

    public OpeningState(Context context) {
        this.context = context;
    }

    @Override
    public void display() {
        Scanner sc = context.getScanner();

        while (true) {
            System.out.println("\n=== Opening/Login Menu ===");
            System.out.println("1. Client Login");
            System.out.println("2. Clerk Menu");
            System.out.println("3. Manager Menu");
            System.out.println("0. Exit");
            System.out.print("Select: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Client ID: ");
                    String id = sc.nextLine();
                    context.setClientID(id);
                    context.setStartRole("login");
                    context.changeState(context.getClientMenuState());
                    return;
                case "2":
                    context.setStartRole("clerk");
                    context.changeState(context.getClerkMenuState());
                    return;
                case "3":
                    context.changeState(context.getManagerMenuState());
                    return;
                case "0":
                    System.out.println("Exiting program...");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
