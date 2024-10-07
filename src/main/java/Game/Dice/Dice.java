package Game.Dice;

import java.util.Random;
import java.util.Scanner;

public class Dice {

    private Random random;

    public Dice() {
        random = new Random();
    }

    /**
     * Method to ask for user input and run the chosen option.
     */
    public void startDiceGame() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Loop until valid input is received (either 1 or 2)
        while (choice < 1 || choice > 2) {
            System.out.println("Choose an option:");
            System.out.println("1. Play Simple Poker Dice");
            System.out.println("2. Roll D&D Dice");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 2) {
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();  // Clear invalid input
            }
        }

        // Execute the appropriate game based on the user's choice
        if (choice == 1) {
            playPokerDice();
        } else {
            rollDnDDice(scanner);
        }

        scanner.close();
    }

    /**
     * Simulates a simple Poker Dice game.
     * Poker Dice uses 5 six-sided dice to simulate a poker hand.
     */
    public void playPokerDice() {
        System.out.println("Playing Poker Dice...");

        int[] pokerDice = new int[5];
        for (int i = 0; i < 5; i++) {
            pokerDice[i] = rollDice(6);
        }

        // Display the rolled dice
        System.out.println("You rolled: ");
        for (int dice : pokerDice) {
            System.out.print(dice + " ");
        }
        System.out.println("\nPoker dice rolled!");
        // You can expand this with actual poker hand rules, for now it's just a basic roll.
    }

    /**
     * Prompts the user to select a D&D dice type and rolls it.
     * D&D dice options include d4, d6, d8, d10, d12, d20, etc.
     */
    public void rollDnDDice(Scanner scanner) {
        System.out.println("Choose a D&D dice to roll:");
        System.out.println("1. d4");
        System.out.println("2. d6");
        System.out.println("3. d8");
        System.out.println("4. d10");
        System.out.println("5. d12");
        System.out.println("6. d20");

        int diceChoice = 0;
        while (diceChoice < 1 || diceChoice > 6) {
            if (scanner.hasNextInt()) {
                diceChoice = scanner.nextInt();
                if (diceChoice < 1 || diceChoice > 6) {
                    System.out.println("Invalid choice. Please choose a number between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();  // Clear invalid input
            }
        }

        int diceSides = 0;
        switch (diceChoice) {
            case 1: diceSides = 4; break;
            case 2: diceSides = 6; break;
            case 3: diceSides = 8; break;
            case 4: diceSides = 10; break;
            case 5: diceSides = 12; break;
            case 6: diceSides = 20; break;
        }

        int rollResult = rollDice(diceSides);
        System.out.println("You rolled a d" + diceSides + " and got: " + rollResult);
    }

    /**
     * Rolls a dice with the specified number of sides.
     *
     * @param sides number of sides on the dice
     * @return the result of the roll
     */
    private int rollDice(int sides) {
        return random.nextInt(sides) + 1;
    }
}
