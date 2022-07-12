package com.example.greetermessages;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

    private Log log = LogFactory.getLog(MessagesController.class);

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "salutation", defaultValue = "Hello") String salutation, @RequestParam(value = "name", defaultValue = "Bob") String name) {
        log.info(String.format("Now saying \"%s\" to %s", salutation, name));
        return new Greeting(salutation, name);
    }

}

class Greeting {

    private static final String template = "%s, %s!";

    private String message;

    public Greeting(String salutation, String name) {
        this.message = String.format(template, salutation, name);
    }

    public String getMessage() {
        return this.message;
    }

}