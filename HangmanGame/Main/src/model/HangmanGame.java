package model;

import exception.GameIsFinishedException;
import exception.LetterAlreadyInputException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static model.HangmanGameStatus.LOSE;
import static model.HangmanGameStatus.PENDING;
import static model.HangmanGameStatus.WIN;

public class HangmanGame {

    private final static int HANGMAN_INITIAL_LINE_LENGTH = 9; 
    private final int linesize;

    private final int hangmanInitialSize;
    private final List<HangmanChar> characters;
    private final LinkedList<HangmanChar> hangmanPath;
    private final List<Character> failAttempts = new ArrayList<>();

    private String hangman;
    private HangmanGameStatus hangmanGameStatus;

    public HangmanGame(final List<HangmanChar> characters) {
        var whiteSpace = " ".repeat(characters.size());
        var charactersSpace = "-".repeat(characters.size());

        // 🔹 Ajuste: linesize conta corretamente a largura da linha
        this.linesize = HANGMAN_INITIAL_LINE_LENGTH + 1; // +1 para o \n
        this.hangmanPath = new LinkedList<>(buildHangmanPathsPosition());
        this.hangmanGameStatus = PENDING;

        buildHangmanDesign(whiteSpace, charactersSpace);
        this.characters = setCharacterSpacesPositionInGame(characters);
        this.hangmanInitialSize = hangman.length();
    }

    public HangmanGameStatus getHangmanGameStatus() {
        return hangmanGameStatus;
    }

    public void inputCharacter(final char character) {
        if (this.hangmanGameStatus != PENDING) {
            var message = this.hangmanGameStatus == WIN ?
                    "Parabéns, você ganhou!!!!!!!" :
                    "Você perdeu, tente novamente";
            throw new GameIsFinishedException(message);
        }

        // Todas as ocorrências da letra
        var found = this.characters.stream()
                .filter(c -> c.getCharacter() == character)
                .collect(Collectors.toList());

        // Checa se já foi digitada e revelada
        boolean alreadyRevealed = failAttempts.contains(character) ||
                (found.stream().allMatch(HangmanChar::isVisible) && !found.isEmpty());

        if (alreadyRevealed) {
            throw new LetterAlreadyInputException("A letra '" + character + "' já foi informada anteriormente");
        }

        if (found.isEmpty()) {
            failAttempts.add(character);
            if (failAttempts.size() >= 6) {
                this.hangmanGameStatus = LOSE;
            }
            if (!hangmanPath.isEmpty()) {
                rebuildHangman(this.hangmanPath.removeFirst());
            }
        } else {
            // Revela todas as ocorrências
            found.forEach(HangmanChar::enableVisibility);
            rebuildHangman(found.toArray(HangmanChar[]::new));
        }

        // Checa vitória
        if (this.characters.stream().noneMatch(HangmanChar::isInvisible)) {
            this.hangmanGameStatus = WIN;
        }
    }

    @Override
    public String toString() {
        return hangman;
    }

    private List<HangmanChar> buildHangmanPathsPosition() {
        final var HEAD_LINE = 4;
        final var BODY_LINE = 6;
        final var LEGS_LINE = 7;

        return new ArrayList<>(
                List.of(
                        new HangmanChar('0', this.linesize * HEAD_LINE + 4),
                        new HangmanChar('|', this.linesize * BODY_LINE + 2),
                        new HangmanChar('/', this.linesize * BODY_LINE + 1),
                        new HangmanChar('\\', this.linesize * BODY_LINE + 3),
                        new HangmanChar('/', this.linesize * LEGS_LINE + 9),
                        new HangmanChar('\\', this.linesize * LEGS_LINE + 11)
                )
        );
    }

    private List<HangmanChar> setCharacterSpacesPositionInGame(final List<HangmanChar> characters) {
        final var LINE_LETTER = 9;
        for (int i = 0; i < characters.size(); i++) {
            characters.get(i).setPosition(this.linesize * LINE_LETTER + HANGMAN_INITIAL_LINE_LENGTH + i);
        }
        return characters;
    }

    private void rebuildHangman(final HangmanChar... hangmanChars) {
        var hangmanBuilder = new StringBuilder(this.hangman);
        Stream.of(hangmanChars).forEach(
                h -> hangmanBuilder.setCharAt(h.getPosition(), h.getCharacter())
        );

        var failMessage = this.failAttempts.isEmpty() ? "" : " Tentativas: " + this.failAttempts;
        this.hangman = hangmanBuilder.substring(0, hangmanInitialSize) + failMessage;
    }

    private void buildHangmanDesign(final String whiteSpace, final String charactersSpace) {
        hangman = "  ------- " + whiteSpace + System.lineSeparator() +
                  "|       | " + whiteSpace + System.lineSeparator() +
                  "|         " + whiteSpace + System.lineSeparator() +
                  "|         " + whiteSpace + System.lineSeparator() +
                  "|         " + whiteSpace + System.lineSeparator() +
                  "|         " + whiteSpace + System.lineSeparator() +
                  "=======   " + charactersSpace + System.lineSeparator();
    }
}
