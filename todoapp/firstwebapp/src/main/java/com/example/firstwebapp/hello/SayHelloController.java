package com.example.firstwebapp.hello;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")

public class SayHelloController {
    @RequestMapping("say-hello")
    @ResponseBody // Esta anotación se utilia para que no busque explicitamente una vista, sino que mas bien envie como
// respuesta la cadena que se retorna
    public String sayHello() {
        return "Hello World!";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public StringBuffer sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("</head>");
        sb.append("</html>");


        return sb;
    }

    // sayHello.jsp
    //  Ruta donde se crean las vistas
    //  src/main/resources/META-INF/resources/WEB-INF/jsp
    @RequestMapping("say-hello-world-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }



}