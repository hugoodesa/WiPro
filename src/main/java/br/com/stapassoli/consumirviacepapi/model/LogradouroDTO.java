package br.com.stapassoli.consumirviacepapi.model;


import br.com.stapassoli.consumirviacepapi.enums.RegioesBrasil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogradouroDTO {

    String cep;
    String logradouro;
    String complemento;
    String localidade;
    String uf;
    String ibge;
    String gia;
    String ddd;
    String siafi;

    Double frete;

    public void calcularaFrente(String regiao) {
        for (RegioesBrasil regiaoBrasil : RegioesBrasil.values()) {
            if(regiaoBrasil.getNome().equals(regiao)){
                frete = regiaoBrasil.getValorFrete();
                return;
            };
        }

        frete = RegioesBrasil.NENHUMA.getValorFrete();
    }
}
