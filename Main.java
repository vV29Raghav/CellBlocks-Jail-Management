import DataStructure.CriminalArray;
import DataStructure.FIRLinkedList;
import DataStructure.OfficerQueue;
import java.util.Scanner;
import ModelClasses.Criminal;
import ModelClasses.Fir;
import ModelClasses.Officer;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static OfficerQueue officerQueue = new OfficerQueue();
    private static CriminalArray criminalArray = new CriminalArray();
    private static FIRLinkedList firList = new FIRLinkedList();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice (1-14): ");

            switch (choice) {
                case 1:
                    addOfficer();
                    break;
                case 2:
                    addCriminal();
                    break;
                case 3:
                    addFIR();
                    break;
                case 4:
                    searchCriminal();
                    break;
                case 5:
                    searchFIR();
                    break;
                case 6:
                    displayAllOfficers();
                    break;
                case 7:
                    displayAllFIRs();
                    break;
                case 8:
                    updateCriminalStatus();
                    break;
                case 9:
                    updateFIR();
                    break;
                case 10:
                    deleteOfficer();
                    break;
                case 11:
                    deleteCriminal();
                    break;
                case 12:
                    deleteFIR();
                    break;
                case 13:
                    sortCriminals();
                    break;
                case 14:
                    System.out.println("Thank you for using the Crime Record Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Crime Record Management System ===");
        System.out.println("1. Add New Officer");
        System.out.println("2. Add New Criminal");
        System.out.println("3. Add New FIR");
        System.out.println("4. Search Criminal");
        System.out.println("5. Search FIR");
        System.out.println("6. Display All Officers");
        System.out.println("7. Display All FIRs");
        System.out.println("8. Update Criminal Status");
        System.out.println("9. Update FIR");
        System.out.println("10. Delete Officer");
        System.out.println("11. Delete Criminal");
        System.out.println("12. Delete FIR");
        System.out.println("13. Sort Criminals");
        System.out.println("14. Exit");
    }

    private static void addOfficer() {
        System.out.println("\n=== Add New Officer ===");
        int id;
        try {
            id = getIntInput("Enter Officer ID (integer): ");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Officer ID! Please enter a valid integer.");
            return;
        }
        String name = getStringInput("Enter Officer Name: ");
        String rank = getStringInput("Enter Officer Rank: ");

        Officer officer = new Officer(id, name, rank);
        officerQueue.addOfficer(officer);
        System.out.println("Officer added successfully!");
    }

    private static void addCriminal() {
        System.out.println("\n=== Add New Criminal ===");
        String id = getStringInput("Enter Criminal ID: ");
        String name = getStringInput("Enter Criminal Name: ");
        int age = getIntInput("Enter Criminal Age: ");
        String crimeType = getStringInput("Enter Crime Type: ");

        Criminal criminal = new Criminal(id, name, age, crimeType);
        criminalArray.insert(criminal);
        System.out.println("Criminal added successfully!");
    }

    private static void addFIR() {
        System.out.println("\n=== Add New FIR ===");
        String firNumber = getStringInput("Enter FIR Number: ");
        String description = getStringInput("Enter Description: ");
        String location = getStringInput("Enter Location: ");
        String criminalId = getStringInput("Enter Criminal ID: ");

        Criminal criminal = criminalArray.search(criminalId);
        if (criminal == null) {
            System.out.println("Criminal not found! Please add the criminal first.");
            return;
        }

        Fir fir = new Fir(firNumber, description, location, criminal);
        firList.addFIR(fir);
        System.out.println("FIR added successfully!");
    }

    private static void searchCriminal() {
        System.out.println("\n=== Search Criminal ===");
        String id = getStringInput("Enter Criminal ID to search: ");
        Criminal criminal = criminalArray.search(id);

        if (criminal != null) {
            System.out.println("\nCriminal found:");
            System.out.println(criminal);
        } else {
            System.out.println("Criminal not found!");
        }
    }

    private static void searchFIR() {
        System.out.println("\n=== Search FIR ===");
        String firNumber = getStringInput("Enter FIR Number to search: ");
        Fir fir = firList.searchFIR(firNumber);

        if (fir != null) {
            System.out.println("\nFIR found:");
            System.out.println(fir);
        } else {
            System.out.println("FIR not found!");
        }
    }

    private static void displayAllOfficers() {
        System.out.println("\n=== All Officers ===");
        if (officerQueue.isEmpty()) {
            System.out.println("No officers in the system.");
            return;
        }

        // Create a temporary queue to preserve officers
        OfficerQueue tempQueue = new OfficerQueue();

        while (!officerQueue.isEmpty()) {
            Officer officer = officerQueue.getNextOfficer();
            System.out.println(officer);
            System.out.println("------------------------");
            tempQueue.addOfficer(officer);
        }

        // Restore the officers back to the main queue
        while (!tempQueue.isEmpty()) {
            officerQueue.addOfficer(tempQueue.getNextOfficer());
        }
    }

    private static void displayAllFIRs() {
        System.out.println("\n=== All FIRs ===");
        if (firList.isEmpty()) {
            System.out.println("No FIRs in the system.");
            return;
        }
        firList.displayAllFIRs();
    }

    private static void updateCriminalStatus() {
        System.out.println("\n=== Update Criminal Status ===");
        String id = getStringInput("Enter Criminal ID: ");
        Criminal criminal = criminalArray.search(id);
        if (criminal != null) {
            System.out.println("Current Status: " + criminal.getStatus());
            String newStatus = getStringInput("Enter new status: ");
            criminal.setStatus(newStatus);
            System.out.println("Status updated successfully!");
        } else {
            System.out.println("Criminal not found!");
        }
    }

    private static void updateFIR() {
        System.out.println("\n=== Update FIR ===");
        String firNumber = getStringInput("Enter FIR Number to update: ");
        Fir fir = firList.searchFIR(firNumber);

        if (fir != null) {
            System.out.println("\nCurrent FIR Details:");
            System.out.println(fir);

            String newDescription = getStringInput("Enter new description (or press Enter to keep current): ");
            String newLocation = getStringInput("Enter new location (or press Enter to keep current): ");

            if (!newDescription.isEmpty()) {
                fir.setDescription(newDescription);
            }
            if (!newLocation.isEmpty()) {
                fir.setLocation(newLocation);
            }

            System.out.println("FIR updated successfully!");
        } else {
            System.out.println("FIR not found!");
        }
    }

    private static void deleteOfficer() {
        System.out.println("\n=== Delete Officer ===");
        int id;
        try {
            id = getIntInput("Enter Officer ID to delete (integer): ");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Officer ID! Please enter a valid integer.");
            return;
        }

        if (officerQueue.deleteOfficer(id)) {
            System.out.println("Officer deleted successfully!");
        } else {
            System.out.println("Officer not found!");
        }
    }

    private static void deleteCriminal() {
        System.out.println("\n=== Delete Criminal ===");
        String id = getStringInput("Enter Criminal ID to delete: ");

        if (criminalArray.delete(id)) {
            // Also delete associated FIRs
            firList.deleteFIRsByCriminalId(id);
            System.out.println("Criminal and associated FIRs deleted successfully!");
        } else {
            System.out.println("Criminal not found!");
        }
    }

    private static void deleteFIR() {
        System.out.println("\n=== Delete FIR ===");
        String firNumber = getStringInput("Enter FIR Number to delete: ");

        if (firList.deleteFIR(firNumber)) {
            System.out.println("FIR deleted successfully!");
        } else {
            System.out.println("FIR not found!");
        }
    }

    private static void sortCriminals() {
        System.out.println("\n=== Sort Criminals ===");
        System.out.println("1. Sort by ID");
        System.out.println("2. Sort by Name");
        System.out.println("3. Sort by Crime Priority");
        int choice = getIntInput("Enter your choice (1-3): ");

        switch (choice) {
            case 1:
                criminalArray.sortCriminals("id");
                System.out.println("Criminals sorted by ID!");
                break;
            case 2:
                criminalArray.sortCriminals("name");
                System.out.println("Criminals sorted by Name!");
                break;
            case 3:
                criminalArray.sortCriminals("crime");
                System.out.println("Criminals sorted by Crime Priority!");
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        // Display sorted criminals
        System.out.println("\nSorted Criminals:");
        criminalArray.displayAllCriminals();
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
} 