package lk.iit.javacli;

import java.util.LinkedList;

public class TicketPool {
    private final LinkedList<Ticket> tickets = new LinkedList<>();
    private final int maxTicketCapacity;

    public TicketPool(Configuration config) {
        this.maxTicketCapacity = config.getMaxTicketCapacity();
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (tickets.size() >= maxTicketCapacity) {
            wait(); // Wait until there's space in the pool
        }

        tickets.add(ticket);
        notifyAll(); // Notify waiting consumers
    }

    public synchronized Ticket retrieveTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            wait(); // Wait until a ticket is available
        }

        Ticket ticket = tickets.removeFirst();
        notifyAll(); // Notify waiting vendors
        return ticket;
    }
}


