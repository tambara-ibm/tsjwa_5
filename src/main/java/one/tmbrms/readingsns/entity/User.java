package one.tmbrms.readingsns.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    public int id;
    public String name;
    public String icon;

    public User(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public User() {
        // Default constructor for JPA
    }

    public String toCsv() {
        return String.format("%d,%s,%s", id, name, icon);
    }
}
