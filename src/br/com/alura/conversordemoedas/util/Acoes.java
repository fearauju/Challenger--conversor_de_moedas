package br.com.alura.conversordemoedas.util;

public enum Acoes {
    ALTERAR("ALTERAR"),
    ATUALIZAR("ATUALIZAR"),
    SAIR("SAIR"),
    HISTORICO("HISTORICO"),
    USD("USD"),
    BRL("BRL"),
    EUR("EUR"),
    CAD("CAD"),
    AUD("AUD"),
    JPY("JPY"),
    NZD("NZD"),
    COP("COP"),
    CLP("CLP"),
    ARS("ARS");

    private final String valor;

    Acoes(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}