package gic.cinema.action;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.model.impl.CheckBookingsInput;
import gic.cinema.input.util.InputOutputUtil;

public class CheckBookingsAction implements Action {
    @Override
    public String getDescription() {
        return APP_CONSTANTS.ACTION_DES_CHECK_BOOKINGS;
    }

    @Override
    public void perform(InputOutputUtil inputOutputUtil, CinemaHall cinemaHall) {
        CheckBookingsInput checkBookingsInput;
        do {
            checkBookingsInput = inputOutputUtil.getCheckBookingsInput();
            if (checkBookingsInput != null) {
                if (cinemaHall.isBookingExists(checkBookingsInput.getValue())) {
                    inputOutputUtil.showSeatsForBooking(checkBookingsInput.getValue());
                    cinemaHall.print(checkBookingsInput.getValue());
                } else
                    inputOutputUtil.showNotFoundMsg(checkBookingsInput.getErrorMsg()[1], checkBookingsInput.getValue());
            }
        } while (checkBookingsInput != null);
    }
}
