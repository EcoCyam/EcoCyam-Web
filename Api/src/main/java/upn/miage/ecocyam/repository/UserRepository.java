package upn.miage.ecocyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.miage.ecocyam.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByEmail(String email);
}
