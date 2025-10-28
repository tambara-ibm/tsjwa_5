package one.tmbrms.readingsns.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
    public String name;
    
    @Id
    public String isbn;
    
    public Book(String name, String isbn){
        this.name = name;
        this.isbn = isbn;
    }

    public Book() {
        // Default constructor for JPA
    }

    public String toCsv() {
        return String.format("%s,%s", name, isbn);
    }
}
