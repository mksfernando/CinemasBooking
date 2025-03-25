package gic.cinema.input.util;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.TestHelper;
import gic.cinema.input.model.impl.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class InputOutputUtilTest {

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
    void getCinemaHallInputs_All_Valid() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception 8 10"));
        // When
        CinemaHallInput cinemaHallInputs = inputOutputUtil.getCinemaHallInputs();

        // Then
        assertEquals("Inception", cinemaHallInputs.getChildren().get(0).getValue());
        assertEquals(8, cinemaHallInputs.getChildren().get(1).getValue());
        assertEquals(10, cinemaHallInputs.getChildren().get(2).getValue());
    }


    @Test
    void getCinemaHallInputs_Format_Invalid() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception8 10"));

        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG_CINEMA_HALL));
        }
    }

    @Test
    void getCinemaHallInputs_Rows_Negative() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception -1 10"));
        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG_ROWS, 1, 26)));
        }
    }

    @Test
    void getCinemaHallInputs_Rows_Exceed_Max() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception 27 10"));
        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG_ROWS, 1, 26)));
        }
    }

    @Test
    void getCinemaHallInputs_Rows_Invalid() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception @ 10"));
        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG_ROWS, 1, 26)));
        }
    }

    @Test
    void getCinemaHallInputs_Columns_Negative() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception 8 -1"));
        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG_COLUMNS, 1, 50)));
        }
    }

    @Test
    void getCinemaHallInputs_Columns_Exceed_Max() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception 8 51"));
        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG_COLUMNS, 1, 50)));
        }
    }

    @Test
    void getCinemaHallInputs_Columns_Invalid() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Inception 8 @"));
        try {
            // When
            inputOutputUtil.getCinemaHallInputs();
        } catch (NoSuchElementException e) {
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG_COLUMNS, 1, 50)));
        }
    }

    @Test
    void getOptionSelection_Valid() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("1"));

        // When
        OptionInput optionInput = inputOutputUtil.getOptionSelection(
                TestHelper.getCinemaHall_8_10(),
                TestHelper.getMenuOptionsList_8_10(),
                TestHelper.getMenuOptions());

        // Then
        assertEquals(1, optionInput.getValue());
    }

    @Test
    void getOptionSelection_Invalid_Not_Available() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("4"));
        try {
            // When
            inputOutputUtil.getOptionSelection(
                    TestHelper.getCinemaHall_8_10(),
                    TestHelper.getMenuOptionsList_8_10(),
                    TestHelper.getMenuOptions());
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG_OPTIONS));
        }
    }

    @Test
    void getOptionSelection_Invalid_Format() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("@A"));
        try {
            // When
            inputOutputUtil.getOptionSelection(
                    TestHelper.getCinemaHall_8_10(),
                    TestHelper.getMenuOptionsList_8_10(),
                    TestHelper.getMenuOptions());
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG_OPTIONS));
        }
    }

    @Test
    void getBookTicketsInput_Valid_Input() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("6"));

        // When
        BookTicketsInput bookTicketsInput = inputOutputUtil.getBookTicketsInput(10);

        // Then
        assertEquals(6, bookTicketsInput.getValue());
    }

    @Test
    void getBookTicketsInput_Empty_Input() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(System.lineSeparator()));

        // When
        BookTicketsInput bookTicketsInput = inputOutputUtil.getBookTicketsInput(10);

        // Then
        assertNull(bookTicketsInput);
    }

    @Test
    void getBookTicketsInput_Exceed_Available() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("11"));

        // When
        try {
            inputOutputUtil.getBookTicketsInput(10);
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.ERROR_MSG2_BOOK_TICKETS, 10)));
        }
    }

    @Test
    void getBookTicketsInput_Negative() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("-1"));

        // When
        try {
            inputOutputUtil.getBookTicketsInput(10);
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_BOOK_TICKETS));
        }
    }

    @Test
    void getBookTicketsInput_Invalid_Format() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("@"));

        // When
        try {
            inputOutputUtil.getBookTicketsInput(10);
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_BOOK_TICKETS));
        }
    }

    @Test
    void getReassignInput_Valid_Input() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("B6"));

        // When
        ReassignSeatsInput reassignInput = inputOutputUtil.getReassignInput(TestHelper.getCinemaHall_8_10());

        // Then
        assertEquals("B6", reassignInput.getValue());
    }

    @Test
    void getReassignInput_Empty_Input() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(System.lineSeparator()));

        // When
        ReassignSeatsInput reassignInput = inputOutputUtil.getReassignInput(TestHelper.getCinemaHall_8_10());

        // Then
        assertNull(reassignInput);
    }

    @Test
    void getReassignInput_Exceed_Range() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("Z50"));

        // When
        try {
            inputOutputUtil.getReassignInput(TestHelper.getCinemaHall_8_10());
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_REASSIGN));
        }
    }

    @Test
    void getReassignInput_Invalid_Row_Format() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("@50"));

        // When
        try {
            inputOutputUtil.getReassignInput(TestHelper.getCinemaHall_8_10());
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_REASSIGN));
        }
    }

    @Test
    void getReassignInput_Invalid_Col_Format() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("A##"));

        // When
        try {
            inputOutputUtil.getReassignInput(TestHelper.getCinemaHall_8_10());
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_REASSIGN));
        }
    }

    @Test
    void getCheckBookingsInput_Valid_Input() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("GIC0007"));

        // When
        CheckBookingsInput checkBookingsInput = inputOutputUtil.getCheckBookingsInput();

        // Then
        assertEquals("GIC0007", checkBookingsInput.getValue());
    }

    @Test
    void getCheckBookingsInput_Empty_Input() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(System.lineSeparator()));

        // When
        CheckBookingsInput checkBookingsInput = inputOutputUtil.getCheckBookingsInput();

        // Then
        assertNull(checkBookingsInput);
    }

    @Test
    void getCheckBookingsInput_Invalid_Format_ORG() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("gi9999"));

        // When
        try {
            inputOutputUtil.getCheckBookingsInput();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_CHECK_BOOKINGS));
        }
    }

    @Test
    void getCheckBookingsInput_Invalid_Format_Sequence() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner("GIC001"));

        // When
        try {
            inputOutputUtil.getCheckBookingsInput();
        } catch (NoSuchElementException e) {
            // Then
            assertTrue(outputStream.toString().contains(APP_CONSTANTS.ERROR_MSG1_CHECK_BOOKINGS));
        }
    }

    @Test
    void showReservationSuccessful() {
        // Given
        int numberOfTickets = 6;
        String movieTitle = "Inception";
        String bookingId = "GIC0003";
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(""));

        // When
        inputOutputUtil.showReservationSuccessful(numberOfTickets, movieTitle, bookingId);

        // Then
        assertTrue(outputStream.toString().contains(
                String.format(APP_CONSTANTS.RES_SUCCESSFUL_MSG, numberOfTickets, movieTitle,
                        String.format(APP_CONSTANTS.RES_BOOKING_ID_MSG, bookingId))));
    }

    @Test
    void showConfirmation() {
        // Given
        String bookingId = "GIC0003";
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(""));

        // When
        inputOutputUtil.showConfirmation(bookingId);

        // Then
        assertTrue(outputStream.toString().contains(String.format(APP_CONSTANTS.CONFIRMED_MSG, bookingId)));
    }

    @Test
    void showReassignFail() {
        // Given
        String errorMsg = APP_CONSTANTS.ERROR_MSG2_REASSIGN;
        String startPosition = "Z50";
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(""));

        // When
        inputOutputUtil.showReassignFail(errorMsg, startPosition);

        // Then
        assertTrue(outputStream.toString().contains(String.format(errorMsg, startPosition)));
    }

    @Test
    void showNotFoundMsg() {
        // Given
        String errorMsg = APP_CONSTANTS.ERROR_MSG2_REASSIGN;
        String bookingId = "GIC9999";
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(""));

        // When
        inputOutputUtil.showNotFoundMsg(errorMsg, bookingId);

        // Then
        assertTrue(outputStream.toString().contains(String.format(errorMsg, bookingId)));
    }

    @Test
    void exit() {
        // Given
        InputOutputUtil inputOutputUtil = new InputOutputUtil(TestHelper.getScanner(""));

        // When
        inputOutputUtil.exit();

        // Then
        assertTrue(outputStream.toString().contains(APP_CONSTANTS.EXIT_MSG));
    }
}