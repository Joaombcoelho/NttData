import java.util.Scanner;

public class diferencaIdade {
    public static void main(String[] args) {
        System.out.println("Olá! Vamos calcular a diferença de idade entre duas pessoas.");
        var scanner = new Scanner(System.in);

        System.out.println("Digite o nome da primeira pessoa:");
        String nome1 = scanner.nextLine();
        System.out.println("Digite a idade da primeira pessoa:");
        int idade1 = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha pendente

        System.out.println("Digite o nome da segunda pessoa:");
        String nome2 = scanner.nextLine();
        System.out.println("Digite a idade da segunda pessoa:");
        int idade2 = scanner.nextInt();

        int diferencaIdade = Math.abs(idade1 - idade2); // Cálculo da diferença de idade

        System.out.println("A diferença de idade entre " + nome1 + " e " + nome2 + " é: " + diferencaIdade + " anos.");
        System.out.println(" e " + (idade1 > idade2 ? nome1 : nome2) + " é mais velho(a)" + " do que "
                + (idade1 > idade2 ? nome2 : nome1) + ".");

        scanner.close(); // Fechar o scanner para evitar vazamento de recursos
    }
}
