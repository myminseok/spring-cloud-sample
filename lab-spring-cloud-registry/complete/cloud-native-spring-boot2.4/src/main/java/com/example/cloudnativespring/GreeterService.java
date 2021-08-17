package com.example.cloudnativespring;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
public class GreeterService {


    @Autowired
    private final RestTemplate rest;

    @Autowired
    private WebClient.Builder webClient;

    private static final String BACKEND_SERVICE="greeter-messages";

    private static final String URI_TEMPLATE = UriComponentsBuilder.fromUriString("http://"+BACKEND_SERVICE+"/greeting")
            .queryParam("salutation", "{salutation}")
            .queryParam("name", "{name}")
            .build()
            .toUriString();




    public GreeterService(RestTemplate restTemplate, WebClient.Builder webClient) {
        this.rest = restTemplate;
        this.webClient= webClient;
    }

    public Greeting greetRestTemplate(String salutation, String name) {
        Assert.notNull(salutation, "salutation is required");
        Assert.notNull(name, "name is required");

        return rest.getForObject(URI_TEMPLATE, Greeting.class, salutation, name);
    }

    public Mono<String> greetWebClient(String salutation, String name) {
        Assert.notNull(salutation, "salutation is required");
        Assert.notNull(name, "name is required");

        StringBuffer uri = new StringBuffer().append("/greeting?salutation=").append(salutation).append("&name=").append(name);

        return webClient.baseUrl("http://"+BACKEND_SERVICE)
                .build().get()
                .uri(uri.toString())
                .retrieve().bodyToMono(String.class);

    }

}

class Greeting {

    private final String message;

    @JsonCreator
    public Greeting(@JsonProperty("message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}