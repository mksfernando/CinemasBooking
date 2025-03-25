package gic.cinema.components;

public class Seat {
    String seatNumber;
    String bookingId;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}

