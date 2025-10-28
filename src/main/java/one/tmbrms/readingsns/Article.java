package one.tmbrms.readingsns;

import java.util.List;
import java.util.stream.Collectors;

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
