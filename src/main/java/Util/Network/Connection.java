package Util.Network;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

/**
 * Connection class to check for active network interfaces, including Wi-Fi and Ethernet.
 */
public class Connection {

    /**
     * Scans and prints information about the network interfaces based on user selection.
     * The user can choose to check either Wi-Fi, Ethernet, or both, and then return to the menu.
     *
     * @param scanner the scanner instance to be used for user input
     */
    public void scanNetworkInterfaces(Scanner scanner) {
        boolean goBack = false;  // Flag to loop back to the menu

        // Loop to allow user to go back to menu
        do {
            int choice = 0;

            // Ask the user which connection type they want to check
            while (choice < 1 || choice > 3) {
                System.out.println("Which connections would you like to check?");
                System.out.println("1. Wi-Fi");
                System.out.println("2. Ethernet");
                System.out.println("3. Both");

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

            // Scan the network interfaces based on user choice
            try {
                // Get all network interfaces
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

                if (interfaces == null) {
                    System.out.println("No network interfaces found.");
                    return;
                }

                // Loop through all the interfaces
                for (NetworkInterface networkInterface : Collections.list(interfaces)) {
                    // Skip interfaces that are not up (inactive)
                    if (!networkInterface.isUp()) {
                        continue;
                    }

                    boolean isWifi = networkInterface.getDisplayName().toLowerCase().contains("wi-fi")
                            || networkInterface.getName().toLowerCase().contains("wlan");
                    boolean isEthernet = networkInterface.getDisplayName().toLowerCase().contains("ethernet")
                            || networkInterface.getName().toLowerCase().contains("eth");

                    // Filter based on user choice
                    if ((choice == 1 && isWifi) || (choice == 2 && isEthernet) || choice == 3) {
                        System.out.println("Interface Name: " + networkInterface.getName());
                        System.out.println("Display Name: " + networkInterface.getDisplayName());
                        System.out.println("Is Loopback: " + networkInterface.isLoopback());
                        System.out.println("Is Virtual: " + networkInterface.isVirtual());
                        System.out.println("Is Up: " + networkInterface.isUp());
                        System.out.println("Supports Multicast: " + networkInterface.supportsMulticast());

                        if (isWifi) {
                            System.out.println("This is a Wi-Fi connection.");
                        } else if (isEthernet) {
                            System.out.println("This is an Ethernet connection.");
                        }

                        System.out.println("----------------------------------");
                    }
                }
            } catch (SocketException e) {
                System.out.println("Error occurred while scanning network interfaces: " + e.getMessage());
            }

            // Ask if the user wants to go back to the menu
            System.out.println("Would you like to go back to the menu? (y/n)");
            String response = scanner.next();
            if (response.equalsIgnoreCase("y")) {
                goBack = true;
            } else {
                System.out.println("Exiting program.");
                goBack = false; // This actually just means to exit the loop and return to the main menu.
            }

        } while (!goBack); // Continue until the user chooses to go back

        // No need to close the scanner here, as it's managed by the main class.
    }
}
