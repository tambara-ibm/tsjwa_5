package one.tmbrms.readingsns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    public int id;
    public String name;
    public String icon;

    public User(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String toCsv() {
        return String.format("%d,%s,%s", id, name, icon);
    }

    public static List<User> getUsers() {
        List<String> lines = new ArrayList<String>();
        // 
        try {
            lines = Files.readAllLines(Paths.get("data/data.csv"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        List<User> ret = lines
            .stream()
            .map(line -> {
                var values = line.split(",");
                return new User(Integer.parseInt(values[0]), values[1], values[2]);
            })
            .collect(Collectors.groupingBy(User::getId))
            .values().stream().map(user -> user.get(0))
            .sorted(Comparator.comparing(User::getId)).toList();

        return ret;
    }

    public static User getUser(int id) {
        return getUsers().stream()
            .filter(u -> u.getId() == id)
            .findFirst()
            .orElse(null);
    }
}
