package Util;

import Util.Math.Calculator;
import Util.Network.Connection;

import java.util.Scanner;

public class Util {

    public void start(Scanner scanner) {  // Receive the Scanner object from Main
        boolean backToMain = false;

        while (!backToMain) {
            int choice = 0;

            System.out.println("Which Util would you like to use?");
            System.out.println("1. Network Connection");
            System.out.println("2. Calculator");
            System.out.println("3. Go Back to Main Menu");

            while (choice < 1 || choice > 2) {
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

            switch (choice) {
                case 1:
                    Connection connection = new Connection();
                    connection.scanNetworkInterfaces(scanner);  // Pass the scanner here
                    break;
                case 2:
                    Calculator calculator = new Calculator();
                    calculator.startCalculator(scanner);
                case 3:
                    backToMain = true;  // Go back to the main menu
                    break;
            }
        }
    }
}
