package Game.BattleQuest;

import java.util.Scanner;

public class BattleQuest {

    /**
     * Initializes and runs the game.
     * The game continues until either the player or the enemy is defeated.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        // Create player and enemy characters
        Character player = new Character("Player", "Warrior", 100, 10, 30);
        Character enemy = new Character("Enemy", "Mage", 80, 8, 30);

        // Add items to player's inventory
        player.addItem("Health Potion");
        player.addItem("Strength Potion");

        // Game loop: continue as long as both player and enemy are alive
        while (player.isAlive() && enemy.isAlive()) {
            int choice = 0;

            // Loop until valid input is received (between 1 and 7)
            while (choice < 1 || choice > 7) {
                System.out.println("\nChoose your action:");
                System.out.println("1. Attack");
                System.out.println("2. Defend");
                System.out.println("3. Use Magic");
                System.out.println("4. Heal");
                System.out.println("5. Use Special Move");
                System.out.println("6. Poison the enemy");
                System.out.println("7. Use an item");

                // Read player input
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice < 1 || choice > 7) {
                        System.out.println("That wasn't the correct input. Please choose a number between 1 and 7.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();  // Clear invalid input
                }
            }

            // Perform action based on player's choice
            switch (choice) {
                case 1:
                    player.attack(enemy);
                    break;
                case 2:
                    player.defend();
                    break;
                case 3:
                    player.useMagic(enemy);
                    break;
                case 4:
                    player.heal();
                    break;
                case 5:
                    player.useSpecialMove(enemy);
                    break;
                case 6:
                    player.poisonOpponent(enemy);
                    break;
                case 7:
                    System.out.println("Choose an item from your inventory:");
                    for (int i = 0; i < player.inventory.size(); i++) {
                        System.out.println((i + 1) + ". " + player.inventory.get(i));
                    }
                    int itemChoice = scanner.nextInt();
                    if (itemChoice > 0 && itemChoice <= player.inventory.size()) {
                        player.useItem(player.inventory.get(itemChoice - 1));
                    } else {
                        System.out.println("Invalid item choice.");
                    }
                    break;
            }

            // Apply poison damage if either player or enemy is poisoned
            if (player.isPoisoned) {
                player.applyPoisonDamage();
            }
            if (enemy.isPoisoned) {
                enemy.applyPoisonDamage();
            }

            // Enemy's turn if still alive
            if (enemy.isAlive()) {
                enemy.enemyTurn(player);  // Enemy randomly chooses to attack, defend, heal, etc.
            }

            // Display the health of both characters after each turn
            player.displayHealth();
            enemy.displayHealth();

            // Reduce special move cooldown each turn
            player.reduceCooldown();
            enemy.reduceCooldown();
        }

        // Determine the outcome of the game
        if (!player.isAlive()) {
            System.out.println("You have been defeated!");
        } else {
            System.out.println("You defeated the enemy!");
        }
        scanner.close();
    }
}
