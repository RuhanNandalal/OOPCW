package lk.iit.javacli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RealTimeEventTicketingSystem {
    private static boolean isRunning = false; // To control thread execution
    private static List<Thread> vendorThreads = new ArrayList<>();
    private static List<Thread> consumerThreads = new ArrayList<>();
    private static Configuration config;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Ticket Management System =====");
            System.out.println("1. Configure System");
            System.out.println("2. Load Configuration");
            System.out.println("3. Display Configuration");
            System.out.println("4. Start Simulation");
            System.out.println("5. Stop Simulation");
            System.out.println("6. Exit\n");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            System.out.println();

            switch (option) {
                case 1:
                    configureSystem();
                    break;
                case 2:
                    loadConfiguration();
                    break;
                case 3:
                    displayConfiguration();
                    break;
                case 4:
                    startSimulation();
                    break;
                case 5:
                    stopSimulation();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting...");
                    stopSimulation(); // Ensure threads are stopped before exiting
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void configureSystem() {
        if (config == null) {
            config = new Configuration(0, 0, 0);
        }
        config.promptUserConfiguration();
        ConfigurationOperations.saveConfigurationToFile(config, "config.json");
        System.out.println("System configured successfully.\n");
    }

    private static void loadConfiguration() {
        config = ConfigurationOperations.loadConfigurationFromFile("config.json");
        if (config != null) {
            System.out.println("Configuration loaded successfully.\n");
        } else {
            System.out.println("Failed to load configuration.\n");
        }
    }

    private static void displayConfiguration() {
        if (config == null) {
            System.out.println("System is not configured.\n");
            return;
        }
        System.out.println("System Configuration:");
        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Ticket Release Rate: " + config.getTicketReleaseRate());
        System.out.println();
    }


    private static void startSimulation() {
        if (config == null) {
            System.out.println("Please configure the system before starting the simulation.\n");
            return;
        }
        if (isRunning) {
            System.out.println("Simulation is already running.\n");
            return;
        }

        System.out.println("Starting simulation...");
        isRunning = true;

        TicketPool ticketPool = new TicketPool(config);

        // Initialize vender threads
        for (int i = 1; i <= 2; i++) {
            Thread venderThread = new Thread(new Vender(ticketPool, config.getTicketReleaseRate(), i));
            vendorThreads.add(venderThread);
            venderThread.start();
        }

        // Initialize consumer threads
        for (int i = 1; i <= 2; i++) {
            Thread consumerThread = new Thread(new Consumer(ticketPool, config.getCustomerRetrievalRate(), i));
            consumerThreads.add(consumerThread);
            consumerThread.start();
        }
        System.out.println("Simulation started.\n");
    }


    private static void stopSimulation() {
        if (!isRunning) {
            System.out.println("Simulation is not running.\n");
            return;
        }

        System.out.println("Stopping simulation...");
        isRunning = false;

        // Interrupt all threads
        for (Thread thread : vendorThreads) {
            thread.interrupt();
        }
        for (Thread thread : consumerThreads) {
            thread.interrupt();
        }

        // Clear thread lists
        vendorThreads.clear();
        consumerThreads.clear();
        System.out.println("Simulation stopped.\n");
    }
}
