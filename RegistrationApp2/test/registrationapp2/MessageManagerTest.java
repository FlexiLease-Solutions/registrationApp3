package test.java;

import main.java.Message;
import main.java.MessageManager;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageManagerTest {
    private MessageManager manager;

    @Before
    public void setUp() {
        manager = new MessageManager();
        manager.populateTestData(); // Loads test data 1-5
    }

    // Test 1: Sent Messages Array correctly populated
    @Test
    public void testSentMessagesArrayPopulated() {
        // From Test Data 1 and 4
        assertEquals(2, manager.getSentMessages().size());
        
        // Check contents of first sent message (Test Data 1)
        Message first = manager.getSentMessages().get(0);
        assertEquals("+27834557896", first.getRecipient());
        assertEquals("Did you get the cake?", first.getMessageText());
    }

    // Test 2: Display the longest message
    @Test
    public void testLongestStoredMessage() {
        // "Where are you? You are late! I have asked you to be on time." is the longest
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = manager.getLongestStoredMessage();
        assertEquals(expected, actual);
    }

    // Test 3: Search for messageID
    @Test
    public void testSearchById() {
        // We need to grab the ID dynamically since it's generated
        String idToFind = manager.getStoredMessages().get(0).getMessageId();
        
        String result = manager.searchById(idToFind);
        assertTrue(result.contains("Recipient: +27838884567"));
        assertTrue(result.contains("Message: Where are you? You are late!"));
    }

    // Test 4: Delete message by Hash (Task requirement)
    @Test
    public void testDeleteMessageByHash() {
        String hashToDelete = manager.getStoredMessages().get(1).getMessageHash(); // Gets hash for "Ok, I am leaving..."
        int initialSize = manager.getStoredMessages().size();
        
        boolean deleted = manager.deleteByHash(hashToDelete);
        
        assertTrue(deleted);
        assertEquals(initialSize - 1, manager.getStoredMessages().size());
    }
    
    // Test 5: Search by Recipient
    @Test
    public void testSearchByRecipient() {
        // Both Test Data 2 and 5 have the same recipient
        assertEquals(2, manager.searchByRecipient("+27838884567").size());
    }
}
