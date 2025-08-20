package br.com.dio.model;

import java.util.List;

public class HangmanGame {

    private String hangman;

    public HangmanGame(final List<HangmanChar> characters) {
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
