package com.pichincha.exchange.external;

import com.pichincha.exchange.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExternalApi {
    private final WebClient webClient;

    public ExternalApi(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<User> getExternalUser(Integer id){
        return this.webClient.get().retrieve().bodyToFlux(User.class)
                .filter(user -> user.getId().equals(id))
                .singleOrEmpty();
    }
}
