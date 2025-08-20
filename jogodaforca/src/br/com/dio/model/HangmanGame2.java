package br.com.dio.model;
import java.util.List;
import br.com.dio.model.HangmanChar2;

public class HangmanGame2 {

    private String hangman;

    public HangmanGame2(final List<HangmanChar> characters) {
        var whiteSpace = " ".repeat(characters.size());
        var charactersSpace = "-".repeat(characters.size());
        buildHangmanDesign(whiteSpace, charactersSpace);
    }

    @Override
    public String toString() {
        return hangman;
    }

    private void buildHangmanDesign(final String whiteSpace, final String charactersSpace) {
        hangman = "-----" + whiteSpace + System.lineSeparator() +
                  "|    |" + whiteSpace + System.lineSeparator() +
                  "|     " + whiteSpace + System.lineSeparator() +
                  "|     " + whiteSpace + System.lineSeparator() +
                  "|     " + whiteSpace + System.lineSeparator() +
                  "|     " + whiteSpace + System.lineSeparator() +
                  "======" + whiteSpace + System.lineSeparator();
    }
}
