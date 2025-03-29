package one.tmbrms.readingsns;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model){

        model.addAttribute("first","ファースト");
        model.addAttribute("second","セカンド");
        model.addAttribute("third","サード");

        var books = new ArrayList<Book>();

        var book = new Book("シャーロック★ホームズの凱旋", "9784120057342");
        books.add(book);

        book = new Book("入門 モダンLinux", "9784814400218");
        books.add(book);

        model.addAttribute("books", books);

        return "thymeleaf";
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("articles", Article.getArticles());
        return "index";
    }
    
    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", User.getUsers());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable int id){

        Stream<Article> articles = Article.getArticles().stream()
            .filter(a -> a.getUser().getId() == id);

        model.addAttribute("user", User.getUser(id));
        model.addAttribute("articles", articles.toList()); 
        return "user";
    }

    @PostMapping("/post")
    public String post(@RequestParam String userid, @RequestParam String isbn, @RequestParam String content){
        System.out.println(String.format("userid=%s, isbn=%s, content=%s", userid, isbn, content));

        var user = User.getUsers().stream()
            .filter(u -> u.getId() == Integer.parseInt(userid))
            .findFirst()
            .orElse(null);
        
        var book = Book.getBooks().stream()
            .filter(b -> b.isbn.equals(isbn))
            .findFirst()
            .orElse(null);
        
        var message = Message.create(content);

        var article = new Article();
        article.user = user;
        article.book = book;
        article.message = message;
        
        try {
            Files.writeString(
                Paths.get("data/data.csv"), 
                "\n" + article.toCsv(),
                StandardOpenOption.APPEND); 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "redirect:/";
    }

}
