package one.tmbrms.readingsns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public String toCsv() {
        return String.format("%d,%s,%s", id, content, timestamp.toString());
    }

    public static Message create(String content){
        return new Message(getNextId(), content, Instant.now().toString());
    }

    private static int getNextId(){
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get("data/messages.csv"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lines.stream()
            .map(line -> {
                var values = line.split(",");
                return Integer.parseInt(values[5]);
            }).max(Integer::compareTo).orElse(1);
    }
}
