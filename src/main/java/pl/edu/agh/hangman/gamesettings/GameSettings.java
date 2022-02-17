package pl.edu.agh.hangman.gamesettings;

import pl.edu.agh.hangman.Hangman;
import pl.edu.agh.hangman.words.choose.ByLengthWordChoose;
import pl.edu.agh.hangman.words.choose.FirstGivenWordChoose;
import pl.edu.agh.hangman.words.choose.RandomWordChoose;
import pl.edu.agh.hangman.words.choose.WordsChooseStrategy;
import pl.edu.agh.hangman.words.provider.WordsFromFile;
import pl.edu.agh.hangman.words.provider.WordsFromWORDNIK;
import pl.edu.agh.hangman.words.provider.WordsGivenByUser;
import pl.edu.agh.hangman.words.provider.WordsProvider;
import sun.tools.jar.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameSettings {
    private static final String DEFAULT_LIBRARY_OF_WORDS_FILE_PATH =
            "./src/main/resources/slowa.txt";
    private final Hangman hangman;
    private Scanner scanner = new Scanner(System.in);
    private SettingsMenu settingsMenu;
    private WordsProvider wordsProvider = new WordsFromFile(DEFAULT_LIBRARY_OF_WORDS_FILE_PATH);
    private WordsChooseStrategy wordsChooseStrategy = new RandomWordChoose();

    public GameSettings(Hangman hangman) {
        this.hangman = hangman;
        setMainMenu();
    }

    private void setMainMenu() {
        SettingsMenu subMenuWordChooseStrategyByLengthWord = new SubMenu("wordChoose-byLengthWord") {
            @Override
            public boolean doWhatUserHaveChosen() {
                String givenLength = getUserChoice();
                if (!givenLength.matches("[0-9]+")) {
                    System.out.println("! Given length is wrong - must be positive number > 0.\n");
                    return false;
                }
                int wordLength = Integer.valueOf(givenLength);
                wordsChooseStrategy = new ByLengthWordChoose(wordLength);
                System.out.printf("\t\t\tWords by given length of %d were set up.\n", wordLength);
                return true;
            }
        };

        subMenuWordChooseStrategyByLengthWord.setContent("\t\tWord length:");

        MainMenu menuWordChooseStrategy = new MainMenu("wordChoose") {
            @Override
            public boolean doWhatUserHaveChosen() {
                switch (getUserChoice()) {
                    case "1": {
                        // random choice
                        wordsChooseStrategy = new RandomWordChoose();
                        System.out.println("\t\t\tRandom choice was set up.");
                        return true;
                    }
                    case "2": {
                        // by length
                        SettingsMenu subMenu = this.getSubMenu(subMenuWordChooseStrategyByLengthWord.getName());

                        boolean isGivenDataWrong = true;
                        while (isGivenDataWrong) {
                            isGivenDataWrong = !subMenu.execute();
                        }
                        return true;
                    }
                    case "3": {
                        // first given step by step
                        wordsChooseStrategy = new FirstGivenWordChoose();
                        System.out.println("\t\t\tWords by first order were set up.");
                        return true;
                    }
                }
                return false;
            }
        };

        menuWordChooseStrategy.addSubMenu(subMenuWordChooseStrategyByLengthWord.getName(), subMenuWordChooseStrategyByLengthWord);

        menuWordChooseStrategy.setContent("\tSelecting word algorithm:\n" +
                "\t\t>> 1: random choice\n" +
                "\t\t   2: by length\n" +
                "\t\t   3: from first to last");

        SubMenu submenuWordsProviderGivenByUser = new SubMenu("wordsProvider-givenByUser") {
            @Override
            public boolean doWhatUserHaveChosen() {
                StringBuilder words = new StringBuilder();

                int wordId = 1;
                while (true) {
                    String wordFromUser = getDataFromUser("\t\t\tWord " + wordId++ + ": (EXIT for terminate)");
                    String word = wordFromUser.toUpperCase();
                    if (word.equals("EXIT")) {
                        break;
                    }
                    if (!word.matches("[A-Z ĄŚĆŻŹÓŁŃĘ]+")) {
                        System.out.println("\t\t! Given word is wrong. Must be letters only. This word will not be added.");
                        wordId--;
                        continue;
                    }
                    words.append(word + ",");
                }

                if (words.length() == 0) {
                    words.append("NO WORDS,");
                }

                words.replace(words.length() - 1, words.length(), "");
                this.setUserChoise(words.toString());

                return true;
            }
        };

        submenuWordsProviderGivenByUser.setContent("\tGive words (write EXIT for stop):\n");

        MainMenu menuWordsProvider = new MainMenu("wordsProvider") {
            @Override
            public boolean doWhatUserHaveChosen() {
                switch (getUserChoice()) {
                    case "1": {
                        // default library
                        wordsProvider = new WordsFromFile(DEFAULT_LIBRARY_OF_WORDS_FILE_PATH);
                        System.out.println("\t\t\tDefault words provider was set up.");
                        return true;
                    }
                    case "2": {
                        // page Wordnik
                        //TODO: implement wordnik
                        wordsProvider = new WordsFromWORDNIK();
                        System.out.println("\t\t\tWords from Wordnik were set up.");
                        return true;
                    }
                    case "3": {
                        // given by User
                        SettingsMenu subMenu = this.getSubMenu(submenuWordsProviderGivenByUser.getName());
                        subMenu.print();
                        subMenu.doWhatUserHaveChosen();

                        String userChoice = subMenu.getUserChoice();

                        ArrayList<String> words = new ArrayList<>();
                        String[] split = userChoice.split(",");
                        for (String word : split) {
                            words.add(word);
                        }

                        wordsProvider = new WordsGivenByUser(words);
                        System.out.printf("\t\t\tWords from user were set up: %s\n", words.toString());
                        return true;
                    }
                }
                return false;
            }
        };
        menuWordsProvider.setContent("\tSelect words provider:\n" +
                "\t\t>> 1: game library\n" +
                "\t\t   2: page Wordnik\n" +
                "\t\t   3: given by User");

        menuWordsProvider.addSubMenu(submenuWordsProviderGivenByUser.getName(), submenuWordsProviderGivenByUser);

        MainMenu mainMenu = new MainMenu("main") {
            @Override
            public boolean doWhatUserHaveChosen() {
                switch (getUserChoice()) {
                    case "1": {
                        // word algorithm
                        boolean doneCorrectly = false;
                        do {
                            doneCorrectly = getSubMenu(menuWordChooseStrategy.getName()).execute();
                        } while (!doneCorrectly);
                        return false;
                    }
                    case "2": {
                        // words provider
                        boolean doneCorrectly = false;
                        do {
                            doneCorrectly = getSubMenu(menuWordsProvider.getName()).execute();
                        } while (!doneCorrectly);
                        return false;
                    }
                    case "P": {
                        System.out.println("\t\t\tExited from main menu.");
                        System.out.println("--------------------------------");
                        return true;
                    }
                }
                return false;
            }
        };
        mainMenu.setContent("Game settings:\n" +
                "\t\t1: selecting word algorithm\n" +
                "\t\t2: selecting words provider\n" +
                "\t\tP: play");
        mainMenu.addSubMenu(menuWordChooseStrategy.getName(), menuWordChooseStrategy);
        mainMenu.addSubMenu(menuWordsProvider.getName(), menuWordsProvider);

        this.settingsMenu = mainMenu;
    }

    public void run() {
        boolean exitedFromMainMenu;
        do {
            exitedFromMainMenu = settingsMenu.execute();
        } while (!exitedFromMainMenu);
        hangman.setUpGame(wordsProvider, wordsChooseStrategy);
    }

    private String readDataFromUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}