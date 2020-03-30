package upn.miage.ecocyam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upn.miage.ecocyam.model.Criteria;
import upn.miage.ecocyam.repository.CriteriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/criterias")
public class CriteriaController {

    @Autowired
    private CriteriaRepository criteriaRepository;

    @GetMapping()
    public ResponseEntity<List<Criteria>> getAllCriteria() {
        try {
            List<Criteria> categories = new ArrayList<>(criteriaRepository.findAll());
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Criteria> getCriteriaById(@PathVariable("id") int id) {
        Optional<Criteria> _criteria = criteriaRepository.findById(id);
        return _criteria.map(criteria -> new ResponseEntity<>(criteria, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Criteria> createCriteria(@RequestBody Criteria criteria) {
        try {
            Criteria _criteria = criteriaRepository
                    .save(new Criteria(criteria));
            return new ResponseEntity<>(_criteria, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Criteria> updateCriteria(@RequestBody Criteria criteriaModel, @PathVariable int id) {
        Optional<Criteria> _criteria = criteriaRepository.findById(id);

        if (_criteria.isPresent()) {
            Criteria criteria = _criteria.get();
            criteria.setName(criteriaModel.getName());
//            criteria.setEvaluation(criteriaModel.getEvaluation());
            return new ResponseEntity<>(criteriaRepository.save(criteria), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCriteria(@PathVariable int id) {
        try {
            criteriaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
