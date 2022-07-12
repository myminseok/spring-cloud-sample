package com.example.greeter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GreeterController {

    private GreeterService greeterService;

    public GreeterController(GreeterService greeterService){
        this.greeterService = greeterService;
    }

    @RequestMapping(value = "/")
    public String home(){
        return this.hello("hello", "default");
    }


    @RequestMapping(value = "/hello")
    public String hello(@RequestParam(value = "salutation", defaultValue = "Hello") String salutation,
                        @RequestParam(value = "name", defaultValue = "Bob") String name) {
        Greeting greeting = greeterService.greetRestTemplate(salutation, name);
        return greeting.getMessage();
    }


    @RequestMapping("/hello-webclient")
    public Mono<String> helloWebclient(@RequestParam(value = "salutation", defaultValue = "Hello") String salutation,
                                       @RequestParam(value = "name", defaultValue = "Bob") String name) {

        return greeterService.greetWebClient(salutation, name);
    }

}