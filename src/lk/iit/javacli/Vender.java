
package lk.iit.javacli;

import java.util.Random;

public class Vender implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final Random random;
    private final int vendorId; // Unique ID for the vendor

    public Vender(TicketPool ticketPool, int ticketReleaseRate, int vendorId) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.random = new Random();
        this.vendorId = vendorId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int ticketId = random.nextInt(1000); // Generate random ticket ID
                Ticket ticket = new Ticket(ticketId);

                // Add the ticket to the pool
                ticketPool.addTicket(ticket);
                System.out.println("Vender [" + vendorId + "] added ticket: " + ticketId);

                // Wait before adding the next ticket
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                System.out.println("Vender [" + vendorId + "] thread interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}







