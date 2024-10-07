package Game;

import Game.BattleQuest.BattleQuest;
import Game.Dice.Dice;

import java.util.Scanner;

public class Game {

    public void start(Scanner scanner) {  // Receive the Scanner object from Main
        boolean backToMain = false;

        while (!backToMain) {
            int choice = 0;

            System.out.println("Choose a game to play:");
            System.out.println("1. Play BattleQuest");
            System.out.println("2. Play Dice Game");
            System.out.println("3. Go Back to Main Menu");

            while (choice < 1 || choice > 3) {
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 3) {
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();  // Clear invalid input
                }
            }

            switch (choice) {
                case 1:
                    BattleQuest battleQuest = new BattleQuest();
                    battleQuest.startGame();
                    break;
                case 2:
                    Dice diceGame = new Dice();
                    diceGame.startDiceGame();
                    break;
                case 3:
                    backToMain = true;  // Exit the game and return to the main menu
                    break;
            }
        }
    }
}
