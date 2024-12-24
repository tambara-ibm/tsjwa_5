package one.tmbrms.readingsns;

public class Article {
    public User user;
    public Book book;
    public Message message;
    public void setUser(int id, String name, String icon) {
        user = new User(id, name, icon);
    }

    public void setBook(String name, String isbn) {
        book = new Book(name, isbn);
    }
    
    public void setMessage(int id, String content, String timestamp) {
        message = new Message(id, content, timestamp);
    }
}
