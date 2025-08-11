import java.util.Scanner;

public class AccessControl {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de dados do usuário
        System.out.println("Você tem permissão? (true/false): ");
        boolean hasPermission = scanner.nextBoolean(); // Lê um valor booleano (true ou false)
        System.out.println("Digite sua idade: ");
        int age = scanner.nextInt(); // Lê a idade como inteiro

        if (!hasPermission) {
            System.out.println("Acesso negado");
        } else {
            if (age >= 18) {
                System.out.println("Acesso permitido");
            } else {
                System.out.println("Idade insuficiente");
            }

        }
        /*
         * if (hasPermission && age >= 18) {
         * System.out.println("Acesso permitido");
         * } else if (!hasPermission) {
         * System.out.println("Acesso negado");
         * } else {
         * System.out.println("Idade insuficiente");
         * }
         */
        scanner.close();
    }
}