import java.util.Scanner;

public class areaRetangulo {
    public static void main(String[] args) {
        System.out.println("Olá! Vamos calcular a área de um retângulo.");
        var scanner = new Scanner(System.in);
        System.out.println("Por favor, digite o comprimento da base do retângulo:");
        double base = scanner.nextDouble();
        System.out.println("Agora, digite a altura do retângulo:");
        double altura = scanner.nextDouble();

        double area = base * altura; // Cálculo da área do retângulo

        System.out.println("A área do retângulo com base " + base + " e altura " + altura + " é: " + area);

        scanner.close(); // Fechar o scanner para evitar vazamento de recursos
    }
}
