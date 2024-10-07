import Game.Game;
import Util.Util;

import java.util.Scanner;

/**
 * Main class that runs the game.
 */
public class Main {

    /**
     * Main method to start the game.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Don't close the scanner here
        int choice = 0;
        boolean exitProgram = false;

        // Loop until the user decides to exit
        while (!exitProgram) {
            choice = 0;

            System.out.println("Which Software:");
            System.out.println("1. Game");
            System.out.println("2. Util");
            System.out.println("3. Exit Program");

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

            // Start the chosen software
            switch (choice) {
                case 1:
                    Game game = new Game();
                    game.start(scanner);  // Pass the scanner to avoid closing it
                    break;
                case 2:
                    Util util = new Util();
                    util.start(scanner);  // Pass the scanner to avoid closing it
                    break;
                case 3:
                    exitProgram = true;  // Exit the program loop
                    System.out.println("Exiting program.");
                    break;
            }
        }

        scanner.close();  // Close the scanner after all inputs are done
    }
}
