package br.com.alura.conversordemoedas.service;

import br.com.alura.conversordemoedas.main.Menu;
import br.com.alura.conversordemoedas.util.Acoes;
import br.com.alura.conversordemoedas.util.ConexaoAPI;
import br.com.alura.conversordemoedas.util.Validacao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class ConversaoMoedas extends Menu {

    private static double valorCambioAtual;
    private static double valorCambioInverso;
    private static double qtdEscolhida;
    Scanner leitura = new Scanner(System.in);
    Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public void conversaoMoedaBaseCotacao() throws IOException, InterruptedException, DataFormatException {

        double resultado;
        String resposta;

        try {
            valorCambioAtual = obterValorMoedaCotada();
            valorCambioInverso = 1 / valorCambioAtual;


            while (continuar) {

                exibirMensagemComum(moedaBase, moedaCotada, valorCambioAtual);

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

                            qtdEscolhida = Double.parseDouble(resposta);

                            resultado = qtdEscolhida * valorCambioAtual;

                            System.out.println("********************");
                            System.out.printf("%.2f %s --> %s %.2f  %n",qtdEscolhida,moedaBase, moedaCotada, resultado);
                            System.out.println("********************");
                            System.out.println();

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
        } finally {
            leitura.close();
        }
    }

    private void conversaoInversa() throws IOException, InterruptedException, NumberFormatException, DataFormatException {

        double resultado;
        String respostaUsuario;

        while (continuar) {

            exibirMensagemComum(moedaCotada, moedaBase, valorCambioInverso);

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

                        qtdEscolhida = Double.parseDouble(respostaUsuario);

                        resultado = valorCambioInverso * qtdEscolhida;

                        System.out.println("********************");
                        System.out.printf("%.2f %s --> %s %.2f  %n",qtdEscolhida,moedaCotada, moedaBase, resultado);
                        System.out.println("********************");
                        System.out.println();
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

    private void exibirMensagemComum(String moedaBaseAtual, String moedaCotadaAtual, double valorCambio) {

        System.out.println("Moeda base escolhida: " + moedaBaseAtual);
        System.out.println();
        System.out.printf("Cada 1 %s equivale a: %.2f %s", moedaBaseAtual, valorCambio, moedaCotadaAtual);
        System.out.println();
        System.out.println("Atualizado, " + formatarData(LocalDate.now()) + " " + Time.valueOf(LocalTime.now()));
        System.out.println();
        System.out.println("Digite 'atualizar' para atualizar a taxa de câmbio.");
        System.out.println("Digite 'alterar' caso queira ver a conversão: " + moedaCotadaAtual + " --> " + moedaBaseAtual);
        System.out.println();
        System.out.print("Digite o valor a ser convertido: ");
        System.out.println();

    }

    public double obterValorMoedaCotada() throws IOException, InterruptedException {

        String json = ConexaoAPI.buscarValorConvertidoMoedaCotada();
        DeserializarJsonConversao deserializarJsonConversao = gson.fromJson(json, DeserializarJsonConversao.class);
        return deserializarJsonConversao.conversion_rate();

    }

    public String formatarData(LocalDate localDate) {

        DateTimeFormatter formatoBrasileiro = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatoBrasileiro);

    }
}

