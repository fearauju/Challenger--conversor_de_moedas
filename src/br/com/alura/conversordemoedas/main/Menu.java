package br.com.alura.conversordemoedas.main;

import br.com.alura.conversordemoedas.service.ConversaoMoedas;
import br.com.alura.conversordemoedas.service.GerenciarLog;
import br.com.alura.conversordemoedas.util.Acoes;
import br.com.alura.conversordemoedas.util.Validacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Menu {

    protected static String moedaBase;
    protected static String moedaCotada;
    private static final List<String>principaisMoedas = new ArrayList<>();
    protected static boolean continuar = true;

    public static void escolherMoedas() throws IOException, InterruptedException {

        try (Scanner leituraDados = new Scanner(System.in)) {

            String opcaoEscolhida;

            mensagemInicial();

            do {

                System.out.println("Moedas disponíveis: " + formatarMoedas());
                System.out.print("Digite a opção escolhida: ");

                leituraDados.skip("(\r\n|[\n\r\u2028\u2029\u0085])?"); // solução para esvaziar o buffer de entrada.
                opcaoEscolhida = leituraDados.nextLine().toUpperCase().trim();
                System.out.println();

                if(Validacao.campoVazio(opcaoEscolhida))continue;

                try{
                    if(validarEnum(opcaoEscolhida)){
                        Acoes acoes = Acoes.valueOf(opcaoEscolhida);

                        if (acoes == Acoes.SAIR) {
                            System.out.println();
                            System.out.println("********************");
                            System.out.println("Programa finalizado");
                            System.out.println("********************");
                            continuar = false;
                            break;
                        } else {
                            if (acoes.ordinal() >= Acoes.USD.ordinal() && acoes.ordinal() <= Acoes.ARS.ordinal()) {
                                moedaBase = acoes.getValor();
                                retiraMoedaBase();
                            }
                        }
                    }
                    else {
                        System.out.println("********************");
                        System.out.println("Escolha uma moeda entre as opções disponíveis");
                        System.out.println("********************");
                        System.out.println();
                        continue;
                    }

                }catch(IllegalArgumentException e){
                    System.out.println("********************");
                    System.out.println("Ocorreu um erro ao executar a ação " + Acoes.valueOf(opcaoEscolhida) + e.getMessage());
                    System.out.println("********************");
                    System.out.println();
                    continuar = false;
                }

                while (continuar){

                    System.out.println("Paridades disponíveis: " + formatarMoedas());
                    System.out.println();

                    System.out.println("Escolha uma moeda para ver seu valor em relação ao " + moedaBase);
                    System.out.print("Digite a moeda: ");

                    leituraDados.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
                    opcaoEscolhida = leituraDados.nextLine().toUpperCase().trim();
                    System.out.println();

                    if(Validacao.campoVazio(opcaoEscolhida))continue;

                    try{
                        if(validarEnum(opcaoEscolhida)){
                            Acoes acoes = Acoes.valueOf(opcaoEscolhida);

                            if (acoes.getValor().equals(moedaBase)){
                                System.out.println("******************");
                                System.out.println("Essa é a moeda base atual.Escolha uma entre " +
                                        "as disponíveis para prosseguir com a conversão");
                                System.out.println("******************");
                                System.out.println();
                            }
                            else
                            if (acoes == Acoes.SAIR) {
                                System.out.println();
                                System.out.println("********************");
                                System.out.println("Programa finalizado!");
                                System.out.println("********************");
                                continuar = false;
                                break;
                            } else {
                                if (acoes.ordinal() >= Acoes.USD.ordinal() && acoes.ordinal() <= Acoes.ARS.ordinal()) {
                                    moedaCotada = acoes.getValor();
                                    ConversaoMoedas conversaoMoedas = new ConversaoMoedas();
                                    conversaoMoedas.conversaoMoedaBaseCotacao();
                                    continuar = false;
                                }
                            }
                        }else {
                            System.out.println();
                            System.out.println("********************");
                            System.out.println("Escolha uma entre as opções disponíveis");
                            System.out.println("********************");
                            System.out.println();
                        }

                    }catch(IllegalArgumentException e){
                        System.out.println("********************");
                        System.out.println("Ocorreu um erro ao executar a ação " + opcaoEscolhida + e.getMessage());
                        System.out.println("********************");
                        System.out.println();
                    } catch (DataFormatException e) {
                        throw new RuntimeException(e);
                    }
                }

            } while (continuar);

            GerenciarLog gerenciarLog = new GerenciarLog();
            gerenciarLog.decisaoAdicionarInfoLog();

        }catch (RuntimeException e) {
            System.out.println("********************");
            System.out.println("Ocorreu um erro inesperado..." + e.getMessage());
            System.out.println("********************");
            System.out.println();
        }
    }

    public static void mensagemInicial(){
        adicionarMoedas();

        System.out.println("***********************");
        System.out.println();
        System.out.println("Bem vindo ao Conversor de paridades.");
        System.out.println();
        System.out.println("Antes de começar...");
        System.out.println();
        System.out.println("Digite a palavra sair para finalizar o programa.");
        System.out.println();
        System.out.println("Iniciando.......");
        System.out.println();

    }
    public static void adicionarMoedas(){
        principaisMoedas.add("USD");
        principaisMoedas.add("EUR");
        principaisMoedas.add("BRL");
        principaisMoedas.add("CAD");
        principaisMoedas.add("AUD");
        principaisMoedas.add("JPY");
        principaisMoedas.add("NZD");
        principaisMoedas.add("COP");
        principaisMoedas.add("CLP");
        principaisMoedas.add("ARS");
    }
    public static void retiraMoedaBase(){
        principaisMoedas.remove(moedaBase);
    }
    public static String formatarMoedas(){

        return String.join(", ",principaisMoedas);
    }

    public static Boolean validarEnum(String opcao){
        for(Acoes acoes : Acoes.values()){
            if(acoes.name().equalsIgnoreCase(opcao)){
                return  true;
            }
        }
        return false;
    }
}
