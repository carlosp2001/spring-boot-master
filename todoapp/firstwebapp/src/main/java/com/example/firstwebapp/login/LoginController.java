package com.example.firstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    // Model se utiliza para transferir información entre el controlador y la vista
     @RequestMapping("login")
    public String login(@RequestParam String name, ModelMap model) { // Esta es la forma de obtener un parámetro por medio del query
        model.put("name", name);
//        System.out.println("Request param is: " + name); // No se recomienda utilizar System.out para entorno de
        logger.info("Request param is {}", name);
        // producción
        return "login";
    }
}
