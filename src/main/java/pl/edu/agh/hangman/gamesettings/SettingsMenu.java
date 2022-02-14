package pl.edu.agh.hangman.gamesettings;

public abstract class SettingsMenu {
    public abstract boolean doWhatUserHaveChosen();

    public abstract void setContent(String content);

    public abstract void print();

    public abstract void setUserChoiceAsFirstLetterByReadingFromInput(String message);

    public abstract String getUserChoice();

    public abstract String getName();

    protected abstract boolean execute();
}
