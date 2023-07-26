package hope.dwag.example.configuration;

import hope.dwag.example.ApiClient;
import hope.dwag.example.api.DogApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GeneratedApiConfiguration {


    private final WebClient webClient;
    private final String dogApiUrl;

    public GeneratedApiConfiguration(@Value("${dog.api.url}") String dogApiUrl, WebClient webClient) {
        this.dogApiUrl = dogApiUrl;
        this.webClient = webClient;
    }

    @Bean
    public ApiClient apiClient(){
        ApiClient apiClient = new ApiClient(webClient);
        apiClient.setBasePath(dogApiUrl);
        return apiClient;
    }

    @Bean
    public DogApi dogApi() {
        return new DogApi(apiClient());
    }

}