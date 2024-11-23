package lk.iit.javacli;

public class Ticket {
    private final int ticketId;
    private boolean isAvailable;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.isAvailable = true;
    }

    public int getTicketId() {
        return ticketId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void markAsSold(){
        this.isAvailable = false;
    }
}
