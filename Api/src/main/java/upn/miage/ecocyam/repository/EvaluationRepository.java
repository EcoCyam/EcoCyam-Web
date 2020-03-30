package upn.miage.ecocyam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.miage.ecocyam.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
}
