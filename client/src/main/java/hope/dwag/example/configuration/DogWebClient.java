package hope.dwag.example.configuration;

import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Configuration
public class DogWebClient {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter((request, next) -> {
                    ClientRequest newRequest = ClientRequest.from(request)
                            .headers(headersConsumer)
                            .build();
                    long startTime = System.currentTimeMillis();
                    return next.exchange(newRequest)
                            .doOnNext(response ->
                                    log.info("{}, {} for {} in {}ms {}",
                                            request.method(),
                                            response.statusCode(),
                                            request.url(),
                                            System.currentTimeMillis() - startTime
                                            ));
                }).build();
    }

    private final Consumer<HttpHeaders> headersConsumer = headers -> {
        headers.set("DOG-HEADER", "DOG");
    };
}