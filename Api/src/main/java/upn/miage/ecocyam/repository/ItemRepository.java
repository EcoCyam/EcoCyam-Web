package upn.miage.ecocyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.miage.ecocyam.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
