package br.com.stapassoli.consumirviacepapi.controller;

import br.com.stapassoli.consumirviacepapi.model.CEP;
import br.com.stapassoli.consumirviacepapi.model.LogradouroDTO;
import br.com.stapassoli.consumirviacepapi.service.RegiaoMunicipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RestController
@RequestMapping("/buscarCEP")
public class BuscarCepController {

    RestTemplate restTemplate;

    @Autowired
    RegiaoMunicipoService service;

    public BuscarCepController() {
        this.restTemplate = new RestTemplate();
    }


    @GetMapping
    public ResponseEntity<LogradouroDTO> buscarCEP (@RequestBody CEP cep) {
        String enderecoAPI = new StringBuilder()
                                    .append("https://viacep.com.br/ws/")
                                    .append(cep.getCep())
                                    .append("/json/")
                                    .toString();


        LogradouroDTO logradouro = restTemplate.getForEntity(enderecoAPI, LogradouroDTO.class).getBody();

        //Todo Tratar null pointer
        String codigoIBGE = Objects.nonNull(logradouro.getIbge()) ? logradouro.getIbge() : null;
        String regiaoBrasil = service.buscarRegiao(codigoIBGE);
        logradouro.calcularaFrente(regiaoBrasil);

        return ResponseEntity.ok(logradouro);
    }


}

