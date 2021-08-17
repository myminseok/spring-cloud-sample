package com.example.cloudnativespring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class GreeterController {

    @Autowired
    private GreeterService greeter;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "salutation", defaultValue = "Hello") String salutation,
                        @RequestParam(value = "name", defaultValue = "Bob") String name) {
        Greeting greeting = greeter.greetRestTemplate(salutation, name);
        return greeting.getMessage();
    }


    @RequestMapping("/hello-webclient")
    public Mono<String> helloWebclient(@RequestParam(value = "salutation", defaultValue = "Hello") String salutation,
                                       @RequestParam(value = "name", defaultValue = "Bob") String name) {

        return greeter.greetWebClient(salutation, name);
    }
}