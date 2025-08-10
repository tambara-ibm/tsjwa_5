package one.tmbrms.readingsns;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import one.tmbrms.readingsns.entity.Book;
import one.tmbrms.readingsns.entity.Message;
import one.tmbrms.readingsns.entity.User;
import one.tmbrms.readingsns.repository.MessageRepository;

public class Article {
    public User user;
    public Book book;
    public Message message;

    public Article(String csv) {
        var values = csv.split(",");
        user = new User(Integer.parseInt(values[0]), values[1], values[2]);
        book = new Book(values[3], values[4]);
        message = null;
    }

    public Article(){}

    public String toCsv() {
        return String.format("%s,%s,%s", user.toCsv(), book.toCsv(), message.toCsv());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setBook(Book book) {
        this.book = book;
    }
    
    public void setMessage(Message message) {
        this.message = message;
    }

    /* 
    public static List<Article> getArticles() {
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get("data/data.csv"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return lines
            .stream()
            .map(line -> new Article(line))
            .collect(Collectors.toList());
    }
    */
    public static List<Article> getArticles(MessageRepository messageRepository) {
        List<Message> articles = messageRepository.findAll();

        return articles.stream().map(db -> {
            Article article = new Article();
            article.setUser(db.user);
            article.setBook(db.book);
            article.setMessage(db);
            return article;
        }).collect(Collectors.toList());
        

    }
}
