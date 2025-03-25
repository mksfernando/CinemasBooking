package gic.cinema.action;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.TestHelper;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.model.impl.BookTicketsInput;
import gic.cinema.input.model.impl.ReassignSeatsInput;
import gic.cinema.input.util.InputOutputUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookTicketsActionTest {
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
        assertEquals(APP_CONSTANTS.ACTION_DES_BOOK_TICKETS, new BookTicketsAction().getDescription());
    }

    @Test
    void perform_Without_Reassign() {
        // Given
        BookTicketsInput bookingTicketsInput = new BookTicketsInput();
        bookingTicketsInput.setValue(5);

        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        when(mockInputOutUtil.getBookTicketsInput(anyInt())).thenReturn(bookingTicketsInput);
        when(mockInputOutUtil.getReassignInput(any())).thenReturn(null);
        doNothing().when(mockInputOutUtil).showReservationSuccessful(anyInt(), anyString(), anyString());

        // Then
        BookTicketsAction bookTicketsAction = new BookTicketsAction();
        bookTicketsAction.perform(mockInputOutUtil, cinemaHall);

        assertEquals(75, cinemaHall.getAvailableSeats());
    }

    @Test
    void perform_With_Reassign() {
        // Given
        BookTicketsInput bookingTicketsInput = new BookTicketsInput();
        bookingTicketsInput.setValue(5);
        ReassignSeatsInput reassignSeatsInput = new ReassignSeatsInput();
        reassignSeatsInput.setValue("C07");

        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        when(mockInputOutUtil.getBookTicketsInput(anyInt())).thenReturn(bookingTicketsInput);
        when(mockInputOutUtil.getReassignInput(any())).thenReturn(reassignSeatsInput).thenReturn(null);
        doNothing().when(mockInputOutUtil).showReservationSuccessful(anyInt(), anyString(), anyString());

        // Then
        BookTicketsAction bookTicketsAction = new BookTicketsAction();
        bookTicketsAction.perform(mockInputOutUtil, cinemaHall);

        assertEquals(75, cinemaHall.getAvailableSeats());
    }

    @Test
    void perform_With_Reassigning_To_Out_Of_Bound_Position() {
        // Given
        String outOfBoundPosition = "H08";
        BookTicketsInput bookingTicketsInput = new BookTicketsInput();
        bookingTicketsInput.setValue(5);
        ReassignSeatsInput reassignSeatsInput = new ReassignSeatsInput();
        reassignSeatsInput.setValue(outOfBoundPosition);

        InputOutputUtil mockInputOutUtil = mock(InputOutputUtil.class);
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        when(mockInputOutUtil.getBookTicketsInput(anyInt())).thenReturn(bookingTicketsInput);
        when(mockInputOutUtil.getReassignInput(any())).thenReturn(reassignSeatsInput).thenReturn(null);
        doNothing().when(mockInputOutUtil).showReservationSuccessful(anyInt(), anyString(), anyString());
        doCallRealMethod().when(mockInputOutUtil).showReassignFail(anyString(), anyString());

        // Then
        BookTicketsAction bookTicketsAction = new BookTicketsAction();
        bookTicketsAction.perform(mockInputOutUtil, cinemaHall);

        assertEquals(75, cinemaHall.getAvailableSeats());
        assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG2_REASSIGN, outOfBoundPosition)));
    }
}