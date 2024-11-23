package lk.iit.javacli;

import java.util.Scanner;


public class RealTimeEventTicketingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = null;
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Ticket Management System =====");
            System.out.println("1. Configure System");
            System.out.println("2. Load Configuration");
            System.out.println("3. Display Configuration");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    if (config == null) {
                        config = new Configuration(0,0,0);
                    }
                    config.promptUserConfiguration(); // call config method to start configuration
                    String filePath = "config.json";

                    ConfigurationOperations.saveConfigurationToFile(config, filePath);
                    System.out.println("System configured successfully. \n");
                    break;


                case 2:
                    config = ConfigurationOperations.loadConfigurationFromFile("config.json");
                    if (config != null) {
                        System.out.println("System loaded successfully. \n");
                    } else {
                        System.out.println("Failed to load configuration. \n");
                    }
                    break;


                case 3:
                    if (config != null) {
                        displayConfiguration(config);
                    }else{
                        System.out.println("System is not configured. \n");
                    }
                    break;

                case 4:
                    exit = true;
                    System.out.println("Exiting... ");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
    private static void displayConfiguration(Configuration config) {
        System.out.println("System Configuration:");
        System.out.println("Max Ticket Capacity: " + config.getMaxTicketCapacity());
        System.out.println("Total Tickets: " + config.getTotalTickets());
        System.out.println("Customer Retrieval Rate: " + config.getCustomerRetrievalRate());
        System.out.println("Ticket Release Rate: " + config.getCustomerRetrievalRate());
        System.out.println();

    }



}
