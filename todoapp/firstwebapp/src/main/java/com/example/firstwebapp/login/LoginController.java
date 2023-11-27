package com.example.firstwebapp.login;

import com.example.firstwebapp.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    // Porque estamos haciendo uso de Spring no debemos instanciar directamente
//    private AuthenticationService authenticationService = new AuthenticationService();

    private AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Model se utiliza para transferir información entre el controlador y la vista
    // En este momento esta ruta está manejando ambas solicitudes POST Y GET, ebe especificarse el método
    //  por medio de RequestMethod
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String goToLogin() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {

        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            model.put("password", password);
            return "welcome";
        }
        model.put("errorMessage", "Invalid Credentials! Please try again");
        return "login";
    }
}
