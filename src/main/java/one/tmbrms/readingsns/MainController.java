package one.tmbrms.readingsns;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import one.tmbrms.readingsns.entity.*;
import one.tmbrms.readingsns.repository.*;

@Controller
public class MainController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("articles", messageRepository.findAll().stream().map(m -> new Article(m)).toList());
        return "index";
    }
    
    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String user(Model model, @PathVariable int id){
        model.addAttribute("user", userRepository.findById(id).get());
        model.addAttribute("articles", messageRepository.findByUserId(id).stream().map(m -> new Article(m)).toList()); 
        return "user";
    }

    @PostMapping("/post")
    public String post(@RequestParam String userid, @RequestParam String isbn, @RequestParam String content){
        System.out.println(String.format("userid=%s, isbn=%s, content=%s", userid, isbn, content));

        User user = null;
        
        Book book = null;
        
        Message message = null;

        var article = new Article();
        article.user = user;
        article.book = book;
        article.message = message;
        
        
        return "redirect:/";
    }

    // This is greeting sample
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }

    // This is thymeleaf sample
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
}
