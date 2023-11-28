package com.example.firstwebapp.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // Con SessionAttributes especificamos el atributo que queremos que persista en la session
// del usuario, en este caso tendríamos que especificar esta anotación en todos los controladores donde ocupemos el
// valor, el valor que es importado es el valor que colocamos en el modelo
public class WelcomeController {
//    private Logger logger = LoggerFactory.getLogger(getClass());

    // Porque estamos haciendo uso de Spring no debemos instanciar directamente
//    private AuthenticationService authenticationService = new AuthenticationService();

    // En este momento esta ruta está manejando ambas solicitudes POST Y GET, ebe especificarse el método
    //  por medio de RequestMethod
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", "carlosp2001");

        return "welcome";
    }
}
