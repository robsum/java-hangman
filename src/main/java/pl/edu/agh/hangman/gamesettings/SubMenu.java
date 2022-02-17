package pl.edu.agh.hangman.gamesettings;

import java.util.Scanner;

public abstract class SubMenu extends SettingsMenu {
    private String content;
    private Scanner scanner = new Scanner(System.in);
    private String userChoise;
    private final String name;

    public SubMenu(String name) {
        this.name = name;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void print() {
        System.out.println(content);
    }

    @Override
    public void setUserChoiceAsFirstLetterByReadingFromInput(String message) {
        System.out.print(message);
        String userChoise = scanner.nextLine();
        this.userChoise = userChoise.toUpperCase().substring(0, 1);
    }

    @Override
    public void setUserChoise(String word) {
        this.userChoise = word;
    }

    @Override
    public String getDataFromUser(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public String getUserChoice() {
        return userChoise;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    protected boolean execute() {
        print();
        setUserChoiceAsFirstLetterByReadingFromInput(">>");
        return doWhatUserHaveChosen();
    }
}
