package upn.miage.ecocyam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upn.miage.ecocyam.model.Criteria;
import upn.miage.ecocyam.model.Evaluation;
import upn.miage.ecocyam.model.EvaluationModel;
import upn.miage.ecocyam.model.Item;
import upn.miage.ecocyam.repository.CriteriaRepository;
import upn.miage.ecocyam.repository.EvaluationRepository;
import upn.miage.ecocyam.repository.ItemRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CriteriaRepository criteriaRepository;

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

        List<Evaluation> evaluationsForUserNote = evaluations.stream()
                .filter(p -> p.getCriteria().getCriteriaId() == 3)
                .collect(Collectors.toList());

        Evaluation eval = evaluations.stream()
                .peek(num -> System.out.println("will filter " + num))
                .filter(x -> x.getCriteria().getCriteriaId() == 3)
                .findFirst()
                .get();

        eval.setScore(evaluationsForUserNote.stream().mapToDouble(Evaluation::getScore).average().orElse(Double.NaN));

        evaluations.removeAll(evaluationsForUserNote);
        evaluations.add(eval);


        return new ResponseEntity<>(evaluations, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody EvaluationModel evaluation) {
        try {
            Evaluation eval = new Evaluation(evaluation);
            Optional<Item> _item = itemRepository.findById(evaluation.getItemId());
            _item.ifPresent(eval::setItem);
            Optional<Criteria> _criteria = criteriaRepository.findById(3);
            _criteria.ifPresent(eval::setCriteria);

            Evaluation _evaluation = evaluationRepository
                    .save(eval);
            this.calculateAndSetItemOverallScore(eval.getItem());
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

    private void calculateAndSetItemOverallScore(Item item){
        try{
            List<Evaluation> evaluations = new ArrayList<>(evaluationRepository.findByItem_ItemId(item.getItemId()));
            if(!evaluations.isEmpty()){
                double scoreEcology = 0;
                double scoreDurability = 0;
                double scoreUser = 0;
                int nbUser = 0;

                for (Evaluation eval: evaluations) {
                    switch (eval.getCriteria().getCriteriaId()){
                        case 1:
                            scoreEcology += (eval.getScore() * 2.0) / 4.0;
                            break;
                        case 2:
                            scoreDurability += (eval.getScore()) / 4.0;
                            break;
                        case 3:
                            scoreUser += (eval.getScore()) / 4.0;
                            nbUser++;
                            break;
                        default:
                            return;
                    }
                }
                item.setOverallScore(scoreEcology + scoreDurability + (nbUser != 0 ? scoreUser/nbUser : 0));
                itemRepository.save(item);
            }else{
                item.setOverallScore(0.0);
                itemRepository.save(item);
            }
        }catch(Exception e){
            System.err.println(e.toString());
        }
    }


}
