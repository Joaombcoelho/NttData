import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Olá estou iniciando o programa e vou precisar de algumas informações.");
        var scanner = new Scanner(System.in);
        System.out.println("Digite seu nome");
        String nome = scanner.nextLine();
        System.out.println("Olá " + nome + ", seja bem-vindo por favor digite sua idade");
        int idade = scanner.nextInt();

        // Aqui você pode adicionar mais lógica ou funcionalidades conforme necessário
        // Por exemplo, você pode pedir mais informações ou realizar cálculos

        scanner.close(); // Fechar o scanner para evitar vazamento de recursos
        System.out.println("Vi que preencheu corretamente as informações, " + nome + "!" +
                " Sua idade é " + idade + " anos. Obrigado por participar!");

    }
}
