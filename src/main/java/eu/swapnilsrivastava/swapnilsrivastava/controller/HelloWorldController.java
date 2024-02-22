package eu.swapnilsrivastava.swapnilsrivastava.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
    
    @GetMapping("/hello-spring")
    public String HelloWorldSprings() {
        return "Hello World Spring";
    }

    @GetMapping("/hello-kafka")
    public void HelloKafka() {
        
    }
}
