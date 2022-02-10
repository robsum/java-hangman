package pl.edu.agh.hangman;

import pl.edu.agh.hangman.words.choose.RandomWordChoose;
import pl.edu.agh.hangman.words.provider.WordsFromFile;

import java.util.Scanner;

public class GameSettings {
    private final Hangman hangman;
    private Scanner scanner = new Scanner(System.in);

    public GameSettings(Hangman hangman) {
        this.hangman = hangman;
    }

    public void run() {
        System.out.println("Write option and press enter in order to:");
        while (true) {
            System.out.println("s: go to settings | e: exit | any other: play");
            String choice = readDataFromUser("");

            switch (choice.toUpperCase().charAt(0)) {
                case 'S': {
                    //TODO implement it later
                    System.out.println("\tA = words from own library");
                    System.out.println("\t\tA1 = selected by length");
                    System.out.println("\t\tA2 = selected random");
                    System.out.println("\tB = words from WORDNIK");
                    System.out.println("\t\tB1 = selected by length");
                    System.out.println("\t\tB2 = selected random");
                    System.out.println("\tC = words given by user");

                    String subChoice = readDataFromUser("");
                    if (subChoice.toUpperCase().equals("A")) {
                        System.out.println("\t\toption A was chosen...");
                        //TODO
                    } else if (subChoice.toUpperCase().equals("B")) {
                        //TODO
                    } else if (subChoice.toUpperCase().equals("C")) {
                        //TODO
                    }
                    break;
                }
                case 'E' : {
                    hangman.setUpBeforeCloseTheGame();
                    hangman.endGame();
                }
                default: {
                    hangman.setUpGame(
                            new WordsFromFile("./src/main/resources/slowa.txt"),
                            new RandomWordChoose());
                    return;
                }
            }
        }
    }
    private String readDataFromUser(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}