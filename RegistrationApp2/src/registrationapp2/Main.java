package main.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MessageManager manager = new MessageManager();
        // Load static data for the menu
        manager.populateTestData(); 
        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to QuickChat.");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Stored Messages (Menu)");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter Recipient Number:");
                    String rec = scanner.nextLine();
                    System.out.println("Enter Message:");
                    String msgText = scanner.nextLine();
                    manager.addMessage(new Message(rec, msgText, "Sent"));
                    System.out.println("Message sent.");
                    break;
                case "2":
                    System.out.println("Coming Soon."); // Or implement display sent logic here
                    break;
                case "3":
                    storedMessagesMenu(manager, scanner);
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }

    private static void storedMessagesMenu(MessageManager manager, Scanner scanner) {
        boolean inSubMenu = true;
        while (inSubMenu) {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("a. Display sender and recipient of all stored messages");
            System.out.println("b. Display longest stored message");
            System.out.println("c. Search for a message ID");
            System.out.println("d. Search for messages by recipient");
            System.out.println("e. Delete a message using hash");
            System.out.println("f. Display full report");
            System.out.println("g. Back to Main Menu");
            System.out.print("Choice: ");
            
            String subChoice = scanner.nextLine().toLowerCase();

            switch (subChoice) {
                case "a":
                    manager.displayStoredSenderRecipient();
                    break;
                case "b":
                    System.out.println("Longest Message: " + manager.getLongestStoredMessage());
                    break;
                case "c":
                    System.out.println("Enter Message ID:");
                    String id = scanner.nextLine();
                    System.out.println(manager.searchById(id));
                    break;
                case "d":
                    System.out.println("Enter Recipient Number:");
                    String rec = scanner.nextLine();
                    if(manager.searchByRecipient(rec).isEmpty()) {
                        System.out.println("No messages found.");
                    } else {
                        manager.searchByRecipient(rec).forEach(System.out::println);
                    }
                    break;
                case "e":
                    System.out.println("Enter Message Hash:");
                    String hash = scanner.nextLine();
                    if (manager.deleteByHash(hash)) {
                        System.out.println("Message deleted successfully.");
                    } else {
                        System.out.println("Message hash not found.");
                    }
                    break;
                case "f":
                    manager.displayReport();
                    break;
                case "g":
                    inSubMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}