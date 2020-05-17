package upn.miage.ecocyam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upn.miage.ecocyam.model.Evaluation;
import upn.miage.ecocyam.repository.EvaluationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @GetMapping()
    public ResponseEntity<List<Evaluation>> getAllEvaluation() {
        try {
            List<Evaluation> categories = new ArrayList<>(evaluationRepository.findAll());
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable("id") int id) {
        Optional<Evaluation> _evaluation = evaluationRepository.findById(id);
        return _evaluation.map(evaluation -> new ResponseEntity<>(evaluation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<List<Evaluation>> getEvaluationByItemId(@PathVariable("id") int id) {
        List<Evaluation> evaluations = new ArrayList<>(evaluationRepository.findByItem_ItemId(id));
        if (evaluations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        try {
            Evaluation _evaluation = evaluationRepository
                    .save(new Evaluation(evaluation));
            return new ResponseEntity<>(_evaluation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@RequestBody Evaluation evaluationModel, @PathVariable int id) {
        Optional<Evaluation> _evaluation = evaluationRepository.findById(id);

        if (_evaluation.isPresent()) {
            Evaluation evaluation = _evaluation.get();
            evaluation.setComment(evaluationModel.getComment());
            evaluation.setScore(evaluationModel.getScore());
            evaluation.setCriteria(evaluationModel.getCriteria());
            evaluation.setItem(evaluationModel.getItem());
            evaluation.setUser(evaluationModel.getUser());
            return new ResponseEntity<>(evaluationRepository.save(evaluation), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvaluation(@PathVariable int id) {
        try {
            evaluationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
