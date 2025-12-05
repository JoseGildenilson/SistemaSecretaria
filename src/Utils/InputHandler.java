package Utils;

import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    // . Ler a string e garante que não está vazia
    public static String lerString(String mensagem) {
        String entrada = "";

        while(true) {
            System.out.print(mensagem);
            entrada = scanner.nextLine();
           
            if(!entrada.isEmpty()) {
                return entrada;
            }
            System.out.println("Erro: O campo não pode ser vazio. Tente novamente.");
        }
    }

    // . Ler um inteiro e trata erro caso não seja válido
    public static int lerInt(String mensagem){
        while(true){
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine();
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e){
                System.out.println("Erro: Digite um número inteiro válido.");
            }
        }
    }

    // . Ler um double e trata erro caso não seja válido
    public static double lerDouble(String mensagem) {
        while(true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine().replace(",", ".");
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e){
                System.out.println("Erro: Digite um valor numérico válido (ex: 1200.50).");
            }
        }
    }

    // . Ler uma string e aceita input vazio (util para função atualizar)
    public static String lerStringOpcional(String mensagem){
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }


    // . Lê um Inteiro, mas se for vazio (ENTER), retorna um valor padrão (o antigo)
    public static int lerIntOpcional(String mensagem, int valorAntigo) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine();
                if (entrada.trim().isEmpty()) {
                    return valorAntigo;
                }
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido.");
            }
        }
    }

    // . Lê um Double, mas se for vazio (ENTER), retorna um valor padrão (o antigo)
    public static double lerDoubleOpcional(String mensagem, double valorAntigo) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.nextLine().replace(",", ".");
                if (entrada.trim().isEmpty()) {
                    return valorAntigo;
                }
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um valor numérico válido.");
            }
        }
    }
}