package Game.BattleQuest;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a character in the game, with attributes like health, attack power, mana, and
 * special abilities. This class also includes methods for combat and inventory management.
 */
public class Character {

    private String characterClass;
    private String name;
    private int health;
    private int maxHealth;
    private int attackPower;
    private int mana;
    private int specialMoveCooldown;
    private boolean isDefending;
    List<String> inventory;
    private int experience;
    private int level;
    boolean isPoisoned;
    private int poisonTurns;

    /**
     * Constructor to initialize a character.
     * @param name the name of the character
     * @param characterClass the class of the character (e.g., Warrior, Mage)
     * @param health initial health of the character
     * @param attackPower initial attack power of the character
     * @param mana initial mana of the character
     */
    public Character(String name, String characterClass, int health, int attackPower, int mana) {
        this.name = name;
        this.characterClass = characterClass;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.mana = mana;
        this.specialMoveCooldown = 0;
        this.experience = 0;
        this.inventory = new ArrayList<>();
        this.level = 1;
        this.isDefending = false;
        this.isPoisoned = false;
        this.poisonTurns = 0;

        // Modify stats based on character class
        if (characterClass.equals("Mage")) {
            this.attackPower += 5;  // Mages have stronger magic attacks
        } else if (characterClass.equals("Warrior")) {
            this.health += 20;  // Warriors have more health
        }
    }

    /**
     * Checks if the character is still alive (health > 0).
     * @return true if the character is alive, false otherwise
     */
    public boolean isAlive() {
        return health > 0;
    }

    /**
     * Attacks an opponent character, with a chance for a critical hit.
     * @param opponent the opponent character to attack
     */
    public void attack(Character opponent) {
        int damage = attackPower;
        if (Math.random() < 0.2) {  // 20% chance for a critical hit
            damage *= 2;
            System.out.println("Critical hit!");
        }
        if (opponent.isDefending) {
            damage /= 2;
            opponent.isDefending = false;
            System.out.println(opponent.name + " is defending and takes half damage!");
        }
        opponent.health -= damage;
        System.out.println(name + " attacks " + opponent.name + " for " + damage + " damage!");
    }

    /**
     * Heals the character, restoring health up to the maximum health.
     */
    public void heal() {
        int healingAmount = 20;
        health += healingAmount;
        if (health > maxHealth) {  // Prevent overhealing
            health = maxHealth;
        }
        System.out.println(name + " heals for " + healingAmount + " health! Current health: " + health);
    }

    /**
     * Puts the character in a defending state, reducing damage taken for the next attack.
     */
    public void defend() {
        System.out.println(name + " is defending this turn!");
        isDefending = true;
    }

    /**
     * Uses magic to attack an opponent, consuming mana.
     * @param opponent the opponent character to use magic on
     */
    public void useMagic(Character opponent) {
        int magicCost = 10;
        if (mana >= magicCost) {
            int magicDamage = attackPower * 2;
            opponent.health -= magicDamage;
            mana -= magicCost;
            System.out.println(name + " uses magic on " + opponent.name + " for " + magicDamage + " damage!");
            System.out.println(name + " has " + mana + " mana left.");
        } else {
            System.out.println(name + " doesn't have enough mana to use magic!");
        }
    }

    /**
     * Displays the current health of the character.
     */
    public void displayHealth() {
        System.out.println(name + "'s health: " + health);
    }

    /**
     * Uses a powerful special move on an opponent. This move has a cooldown period.
     * @param opponent the opponent character to use the special move on
     */
    public void useSpecialMove(Character opponent) {
        if (specialMoveCooldown == 0) {
            int specialDamage = attackPower * 3;
            opponent.health -= specialDamage;
            specialMoveCooldown = 3;  // Cooldown for 3 turns
            System.out.println(name + " uses a special move on " + opponent.name + " for " + specialDamage + " damage!");
        } else {
            System.out.println("Special move is on cooldown for " + specialMoveCooldown + " turns.");
        }
    }

    /**
     * Reduces the cooldown for the special move by 1 turn.
     */
    public void reduceCooldown() {
        if (specialMoveCooldown > 0) {
            specialMoveCooldown--;
        }
    }

    /**
     * Adds an item to the character's inventory.
     * @param item the item to be added
     */
    public void addItem(String item) {
        inventory.add(item);
        System.out.println(name + " obtained: " + item);
    }

    /**
     * Uses an item from the character's inventory.
     * @param item the item to use
     */
    public void useItem(String item) {
        if (inventory.contains(item)) {
            switch (item) {
                case "Health Potion":
                    heal();
                    break;
                case "Strength Potion":
                    attackPower += 5;
                    System.out.println(name + " used a Strength Potion and gained +5 attack power!");
                    break;
                // Add more cases for different items
            }
            inventory.remove(item);
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    /**
     * Determines the enemy's action during their turn. The action can be to attack,
     * defend, or heal.
     * @param player the player character to interact with during the enemy's turn
     */
    public void enemyTurn(Character player) {
        int action = (int) (Math.random() * 3);  // 0: Attack, 1: Defend, 2: Heal
        switch (action) {
            case 0:
                attack(player);
                break;
            case 1:
                defend();
                break;
            case 2:
                heal();
                break;
        }
    }

    /**
     * Poisons the opponent, causing them to take damage over time.
     * @param opponent the opponent character to poison
     */
    public void poisonOpponent(Character opponent) {
        opponent.isPoisoned = true;
        opponent.poisonTurns = 3;  // Poison lasts for 3 turns
        System.out.println(opponent.name + " has been poisoned!");
    }

    /**
     * Applies poison damage to the character if they are poisoned.
     */
    public void applyPoisonDamage() {
        if (isPoisoned) {
            health -= 5;  // Poison damage per turn
            poisonTurns--;
            System.out.println(name + " takes 5 poison damage! " + poisonTurns + " turns left.");
            if (poisonTurns == 0) {
                isPoisoned = false;
                System.out.println(name + " is no longer poisoned.");
            }
        }
    }
}
