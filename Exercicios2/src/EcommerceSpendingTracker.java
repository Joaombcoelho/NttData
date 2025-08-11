import java.util.Scanner;
import java.util.Locale;

public class EcommerceSpendingTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        System.out.println("Digite o número de compras realizadas:");
        int purchaseCount = scanner.nextInt();

        if (purchaseCount == 0) {
            System.out.println("Nenhuma compra registrada.");
        } else {
            double totalSpent = 0.0;

            for (int i = 0; i < purchaseCount; i++) {
                double purchaseValue = scanner.nextDouble();
                totalSpent += purchaseValue;
            }
            double media = totalSpent / purchaseCount;
            System.out.printf("%.2f%n", totalSpent);
            System.out.printf("%.2f%n", media);

        }

        scanner.close();
    }
}