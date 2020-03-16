package upn.miage.ecocyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import upn.miage.ecocyam.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
