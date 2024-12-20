package com.mancsego.docker_debug_hot_reload;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHello {

    @GetMapping("/say-hello")
    public Object sayHello () {
        return new Object () {
          public String say = "hello!";
        };
    }
}
