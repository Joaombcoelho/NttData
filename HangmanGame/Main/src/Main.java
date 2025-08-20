import exception.GameIsFinishedException;
import exception.LetterAlreadyInputException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.HangmanChar;
import model.HangmanGame;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {
        // 🔹 Alteração 1: usando Collectors.toList() no lugar de .toList()
        List<HangmanChar> characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new)
                .collect(Collectors.toList());

        System.out.println(characters);
        var hangmanGame = new HangmanGame(characters);

        System.out.println("Bem vindo ao jogo da forca! Tente adivinhar a palavra!");
        System.out.println(hangmanGame);

        var option = -1;
        while (option != 3) {
            System.out.println("Selecione uma das opções:");
            System.out.println("1. Informar uma letra");
            System.out.println("2. Verificar Status do Jogo");
            System.out.println("3. Sair do Jogo");

            option = scanner.nextInt();

            switch (option) {
                case 1 -> inputCharacter(hangmanGame);
                case 2 -> System.out.println(hangmanGame.getHangmanGameStatus());
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println(hangmanGame);
        }
    }

    private static void inputCharacter(HangmanGame hangmanGame) {
        System.out.println("Digite uma letra:");
        var character = scanner.next().charAt(0);

        try {
            hangmanGame.inputCharacter(character);
        } catch (LetterAlreadyInputException ex) {
            System.out.println(ex.getMessage());
            System.out.println(hangmanGame);
        } catch (GameIsFinishedException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
