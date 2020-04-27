package upn.miage.ecocyam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upn.miage.ecocyam.model.Evaluation;
import upn.miage.ecocyam.model.Item;
import upn.miage.ecocyam.model.SearchModel;
import upn.miage.ecocyam.repository.EvaluationRepository;
import upn.miage.ecocyam.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @GetMapping()
    public ResponseEntity<List<Item>> getAllItem() {
        try {
            List<Item> categories = new ArrayList<>(itemRepository.findAll());
            categories.forEach(this::calculateAndSetItemOverallScore);
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") int id) {
        Optional<Item> _item = itemRepository.findById(id);
        _item.ifPresent(this::calculateAndSetItemOverallScore);
        return _item.map(item -> new ResponseEntity<>(item, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        try {
            Item _item = itemRepository
                    .save(new Item(item));
            calculateAndSetItemOverallScore(_item);
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item itemModel, @PathVariable int id) {
        Optional<Item> _item = itemRepository.findById(id);

        if (_item.isPresent()) {
            Item item = _item.get();
            item.setName(itemModel.getName());
            item.setCategory(itemModel.getCategory());
            item.setUsers(itemModel.getUsers());
            calculateAndSetItemOverallScore(item);
            return new ResponseEntity<>(itemRepository.save(item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable int id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<List<Item>> searchItem(@RequestBody SearchModel model) {
        try {
            List<Item> searchedItems = new ArrayList<>();

            if(model.getBarcode().trim().length()>0){
                searchedItems.addAll(itemRepository.findByBarcode(model.getBarcode()));
            }else{
                searchedItems.addAll(itemRepository.findByNameContaining(model.getKeyword()));
            }
            if (searchedItems.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            searchedItems.forEach(this::calculateAndSetItemOverallScore);
            return new ResponseEntity<>(searchedItems, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
