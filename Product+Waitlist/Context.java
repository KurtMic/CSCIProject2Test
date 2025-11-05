import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Context {
    private State currentState;
    private final ProductCatalog catalog;
    private final Scanner scanner;
    private String currentClientID;
    private String startRole;

    private final OpeningState openingState;
    private final ClientMenuState clientMenuState;
    private final ClerkMenuState clerkMenuState;
    private final ManagerMenuState managerMenuState;

    public Context() {
        scanner = new Scanner(System.in);
        catalog = new ProductCatalog();

        // ✅ Auto-load product data file at startup
        File productFile = new File("productcatalog.txt");
        if (productFile.exists()) {
            try (FileInputStream fis = new FileInputStream(productFile)) {
                catalog.store(fis);
                System.out.println("Loaded product catalog from " + productFile.getName());
            } catch (IOException e) {
                System.out.println("Error loading productcatalog.txt: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ productcatalog.txt not found. Product list will be empty.");
        }

        // Initialize states
        openingState = new OpeningState(this);
        clientMenuState = new ClientMenuState(this);
        clerkMenuState = new ClerkMenuState(this);
        managerMenuState = new ManagerMenuState(this);

        currentState = openingState;
    }

    public void start() {
        currentState.display();
    }

    public void changeState(State nextState) {
        currentState = nextState;
        currentState.display();
    }

    public Scanner getScanner() { return scanner; }
    public ProductCatalog getCatalog() { return catalog; }

    public void setClientID(String id) { this.currentClientID = id; }
    public String getClientID() { return currentClientID; }

    public void setStartRole(String role) { this.startRole = role; }
    public String getStartRole() { return startRole; }

    public OpeningState getOpeningState() { return openingState; }
    public ClientMenuState getClientMenuState() { return clientMenuState; }
    public ClerkMenuState getClerkMenuState() { return clerkMenuState; }
    public ManagerMenuState getManagerMenuState() { return managerMenuState; }
}
