import java.util.Scanner;

public class areaQuadrado {
    public static void main(String[] args) {
        System.out.println("Olá! Vamos calcular a área de um quadrado.");
        var scanner = new Scanner(System.in);
        System.out.println("Por favor, digite o comprimento do lado do quadrado:");
        double lado = scanner.nextDouble();

        double area = lado * lado; // Cálculo da área do quadrado

        System.out.println("A área do quadrado com lado " + lado + " é: " + area);

        scanner.close(); // Fechar o scanner para evitar vazamento de recursos
    }
}
