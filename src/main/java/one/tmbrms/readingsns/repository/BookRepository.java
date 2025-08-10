package one.tmbrms.readingsns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import one.tmbrms.readingsns.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
    
