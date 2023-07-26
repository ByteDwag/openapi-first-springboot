package hope.dwag.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@Slf4j
public class CatController implements CatApi {
    private List<Cat> cats = new ArrayList<>();
    private int nextId = 1;

    @Override
    public ResponseEntity<Cat> createCat(Cat cat) {
        cat.setId(nextId++);
        cats.add(cat);
        log.info("Created Cat :  " + cat);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCatById(Integer id) {
        boolean removed = cats.removeIf(cat -> cat.getId().equals(id));
        if (removed) {
            log.info("Deleted Cat :  " + id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Cat> getCatById(Integer id) {
        for (Cat cat : cats) {
            if (cat.getId().equals(id)) {
                return new ResponseEntity<>(cat, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Cat>> getCats() {
        return new ResponseEntity<>(cats, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Cat> updateCatById(Integer id, Cat cat) {
        for (int i = 0; i < cats.size(); i++) {
            if (cats.get(i).getId().equals(id)) {
                cat.setId(id);
                cats.set(i, cat);
                log.info("Updated Cat :  " + cat);
                return new ResponseEntity<>(cat, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
