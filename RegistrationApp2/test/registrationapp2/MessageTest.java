package registrationapp2;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike", 1);
        assertTrue("Message should be ready to send", msg.getMessageText().length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        StringBuilder longMsg = new StringBuilder();
        for (int i = 0; i < 260; i++) longMsg.append("a");
        Message msg = new Message("+27718693002", longMsg.toString(), 1);
        assertFalse("Message exceeds 250", msg.getMessageText().length() <= 250);
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Hello", 1);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message("08575975889", "Hello", 2); 
        assertNotEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageSentOptions() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}

