package lk.iit.javacli;

public class Consumer implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketRetrievalRate;
    private final int consumerId; // Unique ID for the consumer

    public Consumer(TicketPool ticketPool, int ticketRetrievalRate, int consumerId) {
        this.ticketPool = ticketPool;
        this.ticketRetrievalRate = ticketRetrievalRate;
        this.consumerId = consumerId;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Retrieve a ticket from the pool
                Ticket ticket = ticketPool.retrieveTicket();

                if (ticket != null) {
                    System.out.println("Consumer [" + consumerId + "] purchased ticket: " + ticket.getTicketId());
                }

                // Wait before attempting the next purchase
                Thread.sleep(ticketRetrievalRate);
            } catch (InterruptedException e) {
                System.out.println("Consumer [" + consumerId + "] thread interrupted.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

