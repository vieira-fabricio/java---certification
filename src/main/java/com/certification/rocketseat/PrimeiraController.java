package com.certification.rocketseat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/primeiracontroller")
public class PrimeiraController {

    @GetMapping("/retornar")
    public String retornoPrimeiraController(){
        return "Criando minha primeira controller";
    }

    @PostMapping("/post")
    public String primeiroPost() {
        return "Meu primeiro POST";
    }
}
