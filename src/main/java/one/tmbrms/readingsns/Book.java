package one.tmbrms.readingsns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Book {
    public String name;
    public String isbn;
    
    public Book(String name, String isbn){
        this.name = name;
        this.isbn = isbn;
    }

    public String toCsv() {
        return String.format("%s,%s", name, isbn);
    }

    public static List<Book> getBooks() {
        List<String> lines = new ArrayList<String>();

        try {
            lines = Files.readAllLines(Paths.get("data/data.csv"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lines
            .stream()
            .map(line -> {
                var values = line.split(",");
                return new Book(values[3], values[4]);
            })
            .toList();
    }
}
