package lk.iit.javacli;

public class Consumer implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketRetrievalRate; // Time interval between ticket purchases in milliseconds

    public Consumer(TicketPool ticketPool, int ticketRetrievalRate) {
        this.ticketPool = ticketPool;
        this.ticketRetrievalRate = ticketRetrievalRate;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Retrieve a ticket from the pool
                Ticket ticket = ticketPool.retrieveTicket(); // This will wait if the pool is empty

                if (ticket != null) {
                    System.out.println("Consumer purchased ticket: " + ticket.getTicketId());
                }

                // Wait before attempting the next purchase
                Thread.sleep(ticketRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println("Consumer thread interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
