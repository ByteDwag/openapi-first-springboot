package hope.dwag.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DogController implements DogApi {

    @Override
    public ResponseEntity<List<Dog>> getDogs() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
