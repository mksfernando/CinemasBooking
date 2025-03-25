package gic.cinema.action.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.action.Action;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.model.impl.BookTicketsInput;
import gic.cinema.input.model.impl.ReassignSeatsInput;
import gic.cinema.input.util.InputOutputUtil;

public class BookTicketsAction implements Action {

    @Override
    public String getDescription() {
        return APP_CONSTANTS.ACTION_DES_BOOK_TICKETS;
    }

    @Override
    public void perform(InputOutputUtil inputOutputUtil, CinemaHall cinemaHall) {
        // Get user input
        BookTicketsInput bookTicketsInput = inputOutputUtil.getBookTicketsInput(cinemaHall.getAvailableSeats());

        // Return to Main Menu
        if (bookTicketsInput == null)
            return;

        // Generate Booking Id
        String bookingId = generateBookingId(cinemaHall);

        // Reserve Seats
        cinemaHall.reserveSeats(bookTicketsInput.getValue(), bookingId);
        inputOutputUtil.showReservationSuccessful(bookTicketsInput.getValue(), cinemaHall.getMovieTitle(), bookingId);
        cinemaHall.print(bookingId);

        // Reassign Seats
        ReassignSeatsInput reassignSeatsInput;
        do {
            reassignSeatsInput = inputOutputUtil.getReassignInput(cinemaHall);
            if (reassignSeatsInput == null) {
                cinemaHall.confirmBooking(bookTicketsInput.getValue());
                inputOutputUtil.showConfirmation(bookingId);
            } else {
                if (cinemaHall.reAssignSeats(reassignSeatsInput.getValue(), bookingId)) {
                    inputOutputUtil.showSeatsForBooking(bookingId);
                    cinemaHall.print(bookingId);
                } else
                    inputOutputUtil.showReassignFail(reassignSeatsInput.getErrorMsg()[1], reassignSeatsInput.getValue());
            }
        } while (reassignSeatsInput != null);
    }


    private String generateBookingId(CinemaHall cinemaHall) {
        cinemaHall.incrementBookingsCount();
        return APP_CONSTANTS.ORG_NAME + String.format("%04d", cinemaHall.getBookingsCount());
    }
}
