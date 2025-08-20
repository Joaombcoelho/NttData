package br.com.dio.model;
public class HangmanChar {
private final char character; 
    private boolean isVisible;
    private int position;

    public HangmanChar(char character) {
        this.character = character;
        this.isVisible = false;
    }

    public HangmanChar(char character, int position) {
        this.character = character;
        this.position = position;
        this.isVisible = true;
    }

    public char getCharacter() {
        return character;
    }

    public boolean isVisible() {
        return isVisible;
    }
    public boolean isInVisible() {
        return !isVisible;
    }

    public void enableVisibility() {
        this.isVisible = true;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.character;
        hash = 71 * hash + (this.isVisible ? 1 : 0);
        hash = 71 * hash + this.position;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HangmanChar other = (HangmanChar) obj;
        if (this.character != other.character) {
            return false;
        }
        if (this.isVisible != other.isVisible) {
            return false;
        }
        return this.position == other.position;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HangmanChar{");
        sb.append("character=").append(character);
        sb.append(", isVisible=").append(isVisible);
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }




}
