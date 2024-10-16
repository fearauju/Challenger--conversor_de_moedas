package br.com.alura.conversordemoedas.service;

import br.com.alura.conversordemoedas.main.Menu;
import br.com.alura.conversordemoedas.util.Acoes;
import br.com.alura.conversordemoedas.util.ConexaoAPI;
import br.com.alura.conversordemoedas.util.Validacao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class ConversaoMoedas extends Menu {

    private static double valorCambioAtual;
    private static double valorCambioInverso;
    private static double valorEscolhido;
    private static double resultadoDaConversao;
    protected static final List<HistoricoConversoes> historicoConversoes = new ArrayList<>();

    Scanner leitura = new Scanner(System.in);
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void conversaoMoedaBaseCotacao() throws DataFormatException {

        String resposta;

        try {
            valorCambioAtual = obterValorMoedaCotada();
            valorCambioInverso = 1 / valorCambioAtual;


            while (continuar) {

                if(historicoConversoes.isEmpty()){
                    exibirMensagemInicial(moedaBase, moedaCotada, valorCambioAtual);
                }else{
                    exibirMensagemPosRegistroDeHistorico(moedaBase,moedaCotada,valorCambioAtual);
                }

                leitura.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                resposta = leitura.nextLine().toUpperCase().trim().replace(",", ".");
                System.out.println();

                if (Validacao.campoVazio(resposta)) continue;

                try {
                    if (Menu.validarEnum(resposta)) {

                        Acoes acoes = Acoes.valueOf(resposta);
                        switch (acoes) {
                            case ALTERAR -> conversaoInversa();
                            case ATUALIZAR -> {
                                valorCambioAtual = obterValorMoedaCotada();
                                valorCambioInverso = 1 / valorCambioAtual;
                            }
                            case HISTORICO ->
                                exibirHistoricoRecente();
                            case SAIR -> {
                                System.out.println("********************");
                                System.out.println("Programa finalizado.");
                                System.out.println("********************");
                                continuar = false;
                                return;
                            }
                        }
                    } else {
                        if (Validacao.ValidaNumero(resposta)) {

                            valorEscolhido = Double.parseDouble(resposta);

                            resultadoDaConversao = valorEscolhido * valorCambioAtual;

                            System.out.println("********************");
                            System.out.printf("%.2f %s --> %s %.2f  %n", valorEscolhido,moedaBase,
                                    moedaCotada, resultadoDaConversao);
                            System.out.println("********************");
                            System.out.println();

                            armazenarExibirHistoricoConversoes(moedaBase,valorCambioAtual,moedaCotada,
                                    valorCambioInverso,valorEscolhido,resultadoDaConversao);
                        }
                    }
                } catch (NumberFormatException | DataFormatException e) {

                    System.out.println("********************");
                    System.out.println("Erro de formato de número: " + e.getMessage());
                    System.out.println("********************");
                    System.out.println();

                } catch (IllegalArgumentException e) {

                    System.out.println("********************");
                    System.out.println("Ocorreu um erro ao executar a ação" + Acoes.valueOf(resposta) + " " + e.getMessage());
                    System.out.println("********************");
                    System.out.println();
                }
            }
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private void conversaoInversa() throws IOException, InterruptedException, NumberFormatException, DataFormatException {


        String respostaUsuario;

        while (continuar) {

            if(historicoConversoes.isEmpty()){
                exibirMensagemInicial(moedaCotada, moedaBase, valorCambioInverso);
            }else{
                exibirMensagemPosRegistroDeHistorico(moedaCotada, moedaBase,valorCambioInverso);
            }


            leitura.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            respostaUsuario = leitura.nextLine().toUpperCase().trim().replace(",", ".");
            System.out.println();

            if (Validacao.campoVazio(respostaUsuario)) continue;

            try {
                if (Menu.validarEnum(respostaUsuario)) {

                    Acoes acoes = Acoes.valueOf(respostaUsuario);

                    switch (acoes) {
                        case ALTERAR ->{
                            conversaoMoedaBaseCotacao();
                            System.out.println();
                            System.out.println();
                        }
                        case ATUALIZAR -> {
                            valorCambioInverso = 1 / obterValorMoedaCotada();
                            valorCambioAtual = 1 / valorCambioInverso;
                        }
                        case HISTORICO -> {
                            exibirHistoricoRecente();
                            return;
                        }
                        case SAIR -> {
                            System.out.println("********************");
                            System.out.println("Programa finalizado");
                            System.out.println("********************");
                            continuar = false;
                            return;
                        }
                    }
                } else {
                    if (Validacao.ValidaNumero(respostaUsuario)) {

                        valorEscolhido = Double.parseDouble(respostaUsuario);

                        resultadoDaConversao = valorCambioInverso * valorEscolhido;

                        System.out.println("********************");
                        System.out.printf("%.2f %s --> %s %.2f  %n", valorEscolhido,moedaCotada, moedaBase, resultadoDaConversao);
                        System.out.println("********************");
                        System.out.println();

                        armazenarExibirHistoricoConversoes(moedaCotada,valorCambioInverso,moedaBase,
                                valorCambioAtual, valorEscolhido,resultadoDaConversao);
                    }
                }
            } catch (IllegalArgumentException e) {

                System.out.println("********************");
                System.out.println("Ocorreu um erro ao executar a ação" + Acoes.valueOf(respostaUsuario) + e.getMessage());
                System.out.println("********************");
                System.out.println();

            }
        }
    }

    private void exibirMensagemInicial(String moedaBaseAtual, String moedaCotadaAtual, double valorCambio) {

        System.out.println("Moeda base escolhida: " + moedaBaseAtual);
        System.out.println();
        System.out.printf("Cada 1 %s <---> %.2f %s", moedaBaseAtual, valorCambio, moedaCotadaAtual);
        System.out.println();
        System.out.println("Atualizado, " + timeStamp());
        System.out.println();
        System.out.println("Digite 'atualizar' para atualizar a taxa de câmbio.");
        System.out.println("Digite 'alterar' caso queira ver a conversão: " + moedaCotadaAtual + " --> " + moedaBaseAtual);
        System.out.println();
        System.out.print("Digite o valor a ser convertido: ");
        System.out.println();

    }

    private void exibirMensagemPosRegistroDeHistorico(String moedaBaseAtual, String moedaCotadaAtual, double valorCambio){
        System.out.println("Moeda base escolhida: " + moedaBaseAtual);
        System.out.println();
        System.out.printf("Cada 1 %s <---> %.2f %s", moedaBaseAtual, valorCambio, moedaCotadaAtual);
        System.out.println();
        System.out.println("Atualizado, " + timeStamp());
        System.out.println();
        System.out.println("Digite 'atualizar' para atualizar a taxa de câmbio.");
        System.out.println("Digite 'alterar' caso queira ver a conversão: " + moedaCotadaAtual + " --> " + moedaBaseAtual);
        System.out.println("Digite 'historico' para ver o registro de conversões realizadas");
        System.out.println();
        System.out.print("Digite o valor a ser convertido: ");
        System.out.println();
    }

    private double obterValorMoedaCotada() throws IOException, InterruptedException {

        double valorMoedaCotada = 0;
        DeserializarJsonConversao deserializarJsonConversao ;

        try {
            String json = ConexaoAPI.buscarValorConvertidoMoedaCotada();
            deserializarJsonConversao = gson.fromJson(json, DeserializarJsonConversao.class);
            valorMoedaCotada = deserializarJsonConversao.conversion_rate();
        } catch (InterruptedIOException e) {
            System.out.println("Ocorreu um erro durante a requisição de dados da API");
        }

        return valorMoedaCotada;
    }

    public void exibirHistoricoRecente(){
        System.out.println();
        System.out.println("Exibindo histórico recente...");
        System.out.println();
        String historicoFormatacao = formatarHistorico();
        System.out.println(historicoFormatacao);
    }

    public  String formatarHistorico(){
        StringBuilder sb = new StringBuilder();
        for(HistoricoConversoes historicoConversoes1 : historicoConversoes){
            sb.append(historicoConversoes1.toString());
        }
        return  sb.toString();
    }

    private static void armazenarExibirHistoricoConversoes(
            String moedaBase, double valorMoedaBaseAtual,
            String moedaCotada, double valorMoedaCotadaAtual,
            double valorEscolhido, double resultadoDaConversao
    ){

       historicoConversoes.add(new HistoricoConversoes(moedaBase,valorMoedaBaseAtual,
               moedaCotada,valorMoedaCotadaAtual,valorEscolhido,resultadoDaConversao,
               timeStamp()));
    }

    public static String timeStamp() {

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}

