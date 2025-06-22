package one.tmbrms.readingsns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import one.tmbrms.readingsns.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
    
