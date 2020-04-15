package upn.miage.ecocyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.miage.ecocyam.model.Evaluation;
import upn.miage.ecocyam.model.Item;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    List<Evaluation> findByItem_ItemId(Integer itemId);
}
