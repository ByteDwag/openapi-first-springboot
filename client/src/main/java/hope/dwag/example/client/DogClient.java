package hope.dwag.example.client;

import hope.dwag.example.api.DogApi;
import hope.dwag.example.model.Dog;
import org.springframework.stereotype.Service;

@Service
public class DogClient {

  private final DogApi dogApi;

  public DogClient(DogApi dogApi) {
    this.dogApi = dogApi;
  }

  public Dog getDog(Integer id) {
    return dogApi.getDogById(id).block();
  }

}
