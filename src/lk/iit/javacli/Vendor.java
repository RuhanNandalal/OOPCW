
package lk.iit.javacli;

import java.util.Random;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate; // Time interval between ticket additions in milliseconds
    private final Random random;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Generate a random ticket ID
                int ticketId = random.nextInt(1000); // ID range: 0 to 999
                Ticket ticket = new Ticket(ticketId);

                // Add the ticket to the pool
                ticketPool.addTicket(ticket); // This will wait if the pool is full
                System.out.println("Vendor added ticket: " + ticketId);

                // Wait before adding the next ticket
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
