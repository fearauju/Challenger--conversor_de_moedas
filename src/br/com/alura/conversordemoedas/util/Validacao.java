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
        }else if(Double.parseDouble((resposta)) <= 0){
            System.out.println();
            System.out.println("********************");
            System.out.println("Digite valores acima de zero para realizar a conversão");
            System.out.println("********************");
            System.out.println();
            return false;
        }else if(resposta.startsWith("0")){
            System.out.println();
            System.out.println("********************");
            System.out.println("Não digite 0 como primeiro dígito");
            System.out.println("********************");
            System.out.println();
            return false;
        }
         return true;
    }
}
