package lk.iit.javacli;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Configuration {
    private int totalTickets;
    private int maxTicketCapacity;
    private int customerRetrievalRate;
    private int ticketReleaseRate;

    public Configuration( int maxTicketCapacity, int customerRetrievalRate, int ticketReleaseRate) {

        this.maxTicketCapacity = maxTicketCapacity;
        this.customerRetrievalRate = customerRetrievalRate;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void promptUserConfiguration() {
        Scanner scanner = new Scanner(System.in);

        maxTicketCapacity = ValidateInput(scanner, "Enter Max Ticket Capacity: ", 1, 10000);
        customerRetrievalRate = ValidateInput(scanner, "Enter Customer Retriever Rate: ", 1, 100000);
        ticketReleaseRate = ValidateInput(scanner, "Enter Ticket Release Rate: ", 1, 100000);
    }
        // ValidateInput method validates user inputs (Input type and input range)
    private int ValidateInput(Scanner scanner, String prompt, int min, int max) {
        int value = -1;
        while (true){
            try {
                System.out.print(prompt);
                value = scanner.nextInt();
                if (value >= min && value <= max) {
                    break;
                }else {
                    System.out.println("Please enter valid value between " + min + " and " + max);
                }
            }catch (InputMismatchException e) {
                System.out.println("Invalid entry. Please enter a valid input ");
                scanner.next();
            }
        }
        return value;
    }

    private void CalculateTotalTickets(int maxTicketCapacity) {

    }

    @Override
    public String toString() {
        return "Configuration [totalTickets=" + totalTickets + ", maxTicketCapacity=" + maxTicketCapacity + ", customerRetrievalRate=" + customerRetrievalRate + ", ticketReleaseRate=" + ticketReleaseRate + "]";
    }
}
