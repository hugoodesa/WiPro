package br.com.stapassoli.consumirviacepapi;

public enum RegioesBrasil {

    SUL("Sul",17.30),
    SUDESTE("Sudeste",7.85),
    CENTRO_OESTE("Centro-Oeste",12.50),
    NORDESTE("Nordeste",15.98),
    NENHUMA("Nenhuma",0.0),
    NORTE("Norte",20.83);

    private String nome;
    private Double valorFrete;

    RegioesBrasil(String nome, Double valorFrete) {
        this.nome = nome;
        this.valorFrete = valorFrete;
    }

    public String getNome() {
        return nome;
    }

    public Double getValorFrete() {
        return valorFrete;
    }
}
