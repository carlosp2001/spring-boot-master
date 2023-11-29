package com.example.firstrestapi.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Controller
@RestController // Colocamos el rest controller porque no queremos que las rutas busquen las vistas, simplemente
// queremos devolver una respuesta
public class HelloWorldResource {
    @RequestMapping("/hello-world")
//    @ResponseBody
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    // Path Variable or Path Params
    @RequestMapping("/hello-world-path-param/{name}")
    public HelloWorldBean helloWorldPathParam(@PathVariable String name) {
        return new HelloWorldBean("Hello World, " + name);
    }

    // Multiple params
    @RequestMapping("/hello-world-path-param/{name}/message/{message}")
    public HelloWorldBean helloWorldMultiplePathParam(@PathVariable String name, @PathVariable String message) {
        return new HelloWorldBean("Hello World " + name + "," + message);
    }
}
