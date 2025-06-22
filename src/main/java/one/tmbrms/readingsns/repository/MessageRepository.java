package one.tmbrms.readingsns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import one.tmbrms.readingsns.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
    
