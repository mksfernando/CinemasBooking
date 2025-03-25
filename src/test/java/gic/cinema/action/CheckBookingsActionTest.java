package gic.cinema.action;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.TestHelper;
import gic.cinema.action.impl.CheckBookingsAction;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.model.impl.CheckBookingsInput;
import gic.cinema.input.util.InputOutputUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class CheckBookingsActionTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream actualOutput = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        System.setOut(actualOutput);
    }

    @Test
    void getDescription() {
        assertEquals(APP_CONSTANTS.ACTION_DES_CHECK_BOOKINGS, new CheckBookingsAction().getDescription());
    }

    @Test
    void perform_Existing_Booking_Id() {
        // Given
        String bookingId = "GIC0001";
        CheckBookingsInput checkBookingsInput = new CheckBookingsInput();
        checkBookingsInput.setValue(bookingId);

        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();
        cinemaHall.reserveSeats(5, bookingId);

        // When
        when(mockInputOutUtil.getCheckBookingsInput()).thenReturn(checkBookingsInput).thenReturn(null);
        doCallRealMethod().when(mockInputOutUtil).showSeatsForBooking(anyString());

        // Then
        CheckBookingsAction checkBookingsAction = new CheckBookingsAction();
        checkBookingsAction.perform(mockInputOutUtil, cinemaHall);

        assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.RES_BOOKING_ID_MSG, bookingId)));
    }

    @Test
    void perform_Non_Existing_Booking_Id() {
        // Given
        String bookingId = "GIC0002";
        CheckBookingsInput checkBookingsInput = new CheckBookingsInput();
        checkBookingsInput.setValue(bookingId);

        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();
        cinemaHall.reserveSeats(5, "GIC0001");

        // When
        when(mockInputOutUtil.getCheckBookingsInput()).thenReturn(checkBookingsInput).thenReturn(null);
        doCallRealMethod().when(mockInputOutUtil).showNotFoundMsg(anyString(), anyString());

        // Then
        CheckBookingsAction checkBookingsAction = new CheckBookingsAction();
        checkBookingsAction.perform(mockInputOutUtil, cinemaHall);

        assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG2_CHECK_BOOKINGS, bookingId)));
    }
}