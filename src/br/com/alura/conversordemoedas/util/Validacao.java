package br.com.alura.conversordemoedas.util;

public class Validacao {


    public static boolean campoVazio(String resposta){

        if (resposta.isEmpty()) {
            System.out.println("********************");
            System.out.print("Digite uma opção válida para prosseguir com a conversão.\n");
            System.out.println("********************");
            System.out.println();
            return true;
        }

        return false;
    }

    public static boolean ValidaNumero(String resposta){
        // Regex para validar números inteiros e decimais
         if (!resposta.matches("^\\d{1,5}(\\.\\d{1,3})?$")) {
             System.out.println("***********************");
            System.out.print("Formato inválido.\n");
             System.out.println("***********************");
             System.out.println();
            return false;
        }
         return true;
    }
}
