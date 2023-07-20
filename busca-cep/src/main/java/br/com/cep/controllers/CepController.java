package br.com.cep.controllers;

import br.com.cep.models.CepDTO;
import br.com.cep.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/cep")
public class CepController {
    @Autowired
    CepService cepService;


    @GetMapping(value = "/{cep}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CepDTO getCep(@PathVariable(value = "cep") String strCep) throws IOException {
        return cepService.getCepInMultiplesApis(strCep);
    }
}


