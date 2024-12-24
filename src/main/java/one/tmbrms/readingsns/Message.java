package one.tmbrms.readingsns;

import java.time.Instant;

public class Message {
    public int id;
    public int userId;
    public int isbn;
    public String content;
    public Instant timestamp;

    public Message(int id, String content, String timestamp) {
        this.id = id;
        this.content = content;
        this.timestamp = Instant.parse(timestamp);
    }
}
