package upn.miage.ecocyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.miage.ecocyam.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
