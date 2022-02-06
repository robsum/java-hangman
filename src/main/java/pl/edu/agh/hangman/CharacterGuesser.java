package pl.edu.agh.hangman;

public class CharacterGuesser {
    public static boolean isCharacterInString(String character, String string) {
        return string.contains(String.valueOf(character));
    }
}
