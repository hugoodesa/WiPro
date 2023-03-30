package br.com.stapassoli.consumirviacepapi.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegiaoMunicipoService {

    RestTemplate restTemplate;

    public RegiaoMunicipoService() {
        this.restTemplate = new RestTemplate();
    }

    public String buscarRegiao(String codigoIBGE){

        if(codigoIBGE == null){
            return null;
        }

        String url = new StringBuilder("https://servicodados.ibge.gov.br/api/v1/localidades/municipios/")
                .append(codigoIBGE)
                .append("/distritos")
                .toString();

        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(url, Object.class);

        JSONObject json = new JSONObject(responseEntity);
        JSONArray body = (JSONArray) json.get("body");
        JSONObject firstData = body.optJSONObject(0);
        JSONObject municipio = firstData.optJSONObject("municipio");
        JSONObject microrregiao = (JSONObject) municipio.get("microrregiao");
        JSONObject mesorregiao = (JSONObject) microrregiao.get("mesorregiao");
        JSONObject UF = (JSONObject) mesorregiao.get("UF");
        JSONObject regiao = (JSONObject) UF.get("regiao");

       return (String) regiao.get("nome");

    }


}
