package main.java;

public class Message {
    private String recipient;
    private String messageText;
    private String flag; // Sent, Stored, Disregard
    private String messageHash;
    private String messageId;
    private String developer; // For Test Data 4

    // Constructor for standard messages
    public Message(String recipient, String messageText, String flag) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.flag = flag;
    }

    // Constructor for Developer message (Test Data 4)
    public Message(String developer, String messageText, String flag, boolean isDev) {
        this.developer = developer;
        this.messageText = messageText;
        this.flag = flag;
    }

    // Getters and Setters
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public String getFlag() { return flag; }
    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }
    public String getMessageId() { return messageId; }
    public void setMessageId(String messageId) { this.messageId = messageId; }
    public String getDeveloper() { return developer; }

    @Override
    public String toString() {
        return "Message{" +
                "recipient='" + recipient + '\'' +
                ", text='" + messageText + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}

