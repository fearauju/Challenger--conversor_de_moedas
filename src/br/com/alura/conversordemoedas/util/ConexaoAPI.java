package br.com.alura.conversordemoedas.util;

import br.com.alura.conversordemoedas.main.Menu;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class ConexaoAPI extends Menu {

    public static String buscarValorConvertidoMoedaCotada() throws IOException, InterruptedException {

        String url = "https://v6.exchangerate-api.com/v6/105c73abd7e14555580ba6ab/pair/"
                + moedaBase + "/" + moedaCotada;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // se conexão estiver ok
        if (response.statusCode() == 200) {
            return response.body();

        } else {
            throw new IOException("Erro durante a requisição. Verifique a url");
        }
    }
}