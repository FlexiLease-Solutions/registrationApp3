package main.java;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    // Using ArrayLists (Dynamic Arrays) as required by "populate arrays"
    private ArrayList<Message> sentMessages;
    private ArrayList<Message> storedMessages;
    private ArrayList<Message> disregardedMessages;
    private ArrayList<String> messageHashes;
    private ArrayList<String> messageIds;

    public MessageManager() {
        sentMessages = new ArrayList<>();
        storedMessages = new ArrayList<>();
        disregardedMessages = new ArrayList<>();
        messageHashes = new ArrayList<>();
        messageIds = new ArrayList<>();
    }

    // --- 1. Populate Arrays (No Hard-coding) ---
    // In a real app, this would take user input. For this assignment, we provide
    // a method to add a message to the correct array based on the Flag.
    public void addMessage(Message msg) {
        if (msg.getFlag().equalsIgnoreCase("Sent")) {
            sentMessages.add(msg);
        } else if (msg.getFlag().equalsIgnoreCase("Stored")) {
            storedMessages.add(msg);
        } else if (msg.getFlag().equalsIgnoreCase("Disregard")) {
            disregardedMessages.add(msg);
        }
        // Generate Hash and ID (Simplified for this example)
        String hash = Integer.toHexString(msg.hashCode());
        msg.setMessageHash(hash);
        msg.setMessageId("ID" + System.currentTimeMillis());
        
        messageHashes.add(hash);
        messageIds.add(msg.getMessageId());
    }

    // --- 2. Read JSON File (Stored Messages) ---
    // You need a file named 'messages.json' in your project root
    public void loadFromJson(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Message>>() {}.getType();
            ArrayList<Message> loadedMessages = gson.fromJson(reader, listType);
            
            if (loadedMessages != null) {
                for (Message msg : loadedMessages) {
                    // Ensure it goes to the stored array if it has the Stored flag
                    if(msg.getFlag().equalsIgnoreCase("Stored") || msg.getFlag().equalsIgnoreCase("Sent") || msg.getFlag().equalsIgnoreCase("Disregard")) {
                         addMessage(msg);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("JSON File not found or error: " + e.getMessage());
        }
    }

    // --- 3. Display Sender and Recipient of all Stored Messages ---
    public void displayStoredSenderRecipient() {
        for (Message msg : storedMessages) {
            System.out.println("Recipient: " + msg.getRecipient() + 
                               (msg.getDeveloper() != null ? " | Developer: " + msg.getDeveloper() : ""));
        }
    }

    // --- 4. Display the Longest Stored Message ---
    public String getLongestStoredMessage() {
        String longest = "";
        for (Message msg : storedMessages) {
            if (msg.getMessageText().length() > longest.length()) {
                longest = msg.getMessageText();
            }
        }
        return longest;
    }

    // --- 5. Search for Message ID and display Recipient/Message ---
    public String searchById(String id) {
        for (Message msg : storedMessages) {
            if (msg.getMessageId().equals(id)) {
                return "Recipient: " + msg.getRecipient() + " | Message: " + msg.getMessageText();
            }
        }
        return "Message ID not found.";
    }

    // --- 6. Search all messages for a particular recipient ---
    public List<Message> searchByRecipient(String recipient) {
        List<Message> results = new ArrayList<>();
        // Search across all arrays or just stored? Task says "all messages stored for a particular recipient"
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) {
                results.add(msg);
            }
        }
        return results;
    }

    // --- 7. Delete a message using the message hash ---
    public boolean deleteByHash(String hash) {
        // Iterate through all lists to find the message with the hash
        for (int i = 0; i < storedMessages.size(); i++) {
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                storedMessages.remove(i);
                messageHashes.remove(hash);
                return true;
            }
        }
        // Also check sent and disregarded if needed, but task implies stored.
        return false;
    }

    // --- 8. Display Report (Full Details) ---
    public void displayReport() {
        System.out.println("--- STORED MESSAGES REPORT ---");
        for (Message msg : storedMessages) {
            System.out.println("Hash: " + msg.getMessageHash());
            System.out.println("ID: " + msg.getMessageId());
            System.out.println("Recipient: " + msg.getRecipient());
            System.out.println("Message: " + msg.getMessageText());
            System.out.println("------------------------------");
        }
    }

    // Helper for testing (to populate arrays without user input)
    public void populateTestData() {
        // Test Data 1
        Message m1 = new Message("+27834557896", "Did you get the cake?", "Sent");
        addMessage(m1);
        // Test Data 2
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        addMessage(m2);
        // Test Data 3
        Message m3 = new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
        addMessage(m3);
        // Test Data 4
        Message m4 = new Message("0838884567", "It is dinner time!", "Sent", true); // Dev
        addMessage(m4);
        // Test Data 5
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you.", "Stored");
        addMessage(m5);
    }
    
    // Getters for testing
    public ArrayList<Message> getSentMessages() { return sentMessages; }
    public ArrayList<Message> getStoredMessages() { return storedMessages; }
}
