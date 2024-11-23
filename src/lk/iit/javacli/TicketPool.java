package lk.iit.javacli;


//import java.util.concurrent.ConcurrentLinkedQueue;
//
//public class TicketPool {
//    private final ConcurrentLinkedQueue<Ticket> tickets = new ConcurrentLinkedQueue<>();
//    private final int maxTicketCapacity;
//
//    public TicketPool(Configuration config) {
//        this.maxTicketCapacity = config.getMaxTicketCapacity();
//    }
//
//    // Method to add a ticket
//    public boolean addTicket(Ticket ticket) {
//        if (tickets.size() < maxTicketCapacity) {
//            tickets.offer(ticket);
//            return true;
//        } else {
//            System.out.println("Ticket pool is at max capacity.");
//            return false; // Indicate that the ticket could not be added
//        }
//    }
//
//    // Method to retrieve a ticket
//    public Ticket retrieveTicket() {
//        Ticket ticket = tickets.poll();
//        if (ticket != null) {
//            ticket.markAsSold(); // Mark ticket as sold when it’s retrieved
//        }
//        return ticket;
//    }
//
//    // Method to check the current size of the ticket pool
//    public int getCurrentTicketCount() {
//        return tickets.size();
//    }
//}
//

import java.util.LinkedList;

public class TicketPool {
    private final LinkedList<Ticket> tickets = new LinkedList<>();
    private final int maxTicketCapacity;

    public TicketPool(Configuration config) {
        this.maxTicketCapacity = config.getMaxTicketCapacity();
    }

    public synchronized void addTicket(Ticket ticket) throws InterruptedException {
        while (tickets.size() >= maxTicketCapacity) {
            System.out.println("Ticket pool is full. Vendor is waiting...");
            wait(); // Wait until there's space in the pool
        }

        tickets.add(ticket);
        System.out.println("Added ticket: " + ticket.getTicketId());
        notifyAll(); // Notify waiting consumers that a ticket is available
    }

    public synchronized Ticket retrieveTicket() throws InterruptedException {
        while (tickets.isEmpty()) {
            System.out.println("Ticket pool is empty. Consumer is waiting...");
            wait(); // Wait until a ticket is available
        }

        Ticket ticket = tickets.removeFirst();
        System.out.println("Retrieved ticket: " + ticket.getTicketId());
        notifyAll(); // Notify waiting vendors that space is available
        return ticket;
    }
}

