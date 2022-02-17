package pl.edu.agh.hangman.gamesettings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class MainMenu extends SettingsMenu {
    private final String name;
    private Map<String, SettingsMenu> menus = new HashMap<>();
    private String content;
    private Scanner scanner = new Scanner(System.in);
    private String userChoise;

    public MainMenu(String name) {
        this.name = name;
    }

    public void addSubMenu(String name, SettingsMenu menu) {
        menus.put(name, menu);
    }

    public boolean hasSubMenus() {
        return !menus.keySet().isEmpty();
    }

    public SettingsMenu getSubMenu(String name) {
        return menus.get(name);
    }

    public void removeSubMenu(String name) {
        menus.remove(name);
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
        if(userChoise.equals("")) {
            this.userChoise = "0";
            return;
        }
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