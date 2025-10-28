package one.tmbrms.readingsns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import one.tmbrms.readingsns.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByUserId(int userId);
}
    
