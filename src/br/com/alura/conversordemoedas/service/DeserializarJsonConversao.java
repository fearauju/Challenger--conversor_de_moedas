package br.com.alura.conversordemoedas.service;

public record DeserializarJsonConversao(
        String result,
        String base_code,
        String target_code,
        Double conversion_rate
) {
}
