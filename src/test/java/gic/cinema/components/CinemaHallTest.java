package gic.cinema.components;

import gic.cinema.TestHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CinemaHallTest {
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
    void initHall() {
        // When
        CinemaHall cinemaHall = new CinemaHall("Inception", 8, 10);

        // Then
        assertEquals("Inception", cinemaHall.getMovieTitle());
        assertEquals(8, cinemaHall.getRows());
        assertEquals(10, cinemaHall.getCols());
        assertEquals(80, cinemaHall.getAvailableSeats());
    }

    @Test
    void print_Single_Booking() {
        // Given
        String bookingId = "GIC0001";
        String expected =
                System.lineSeparator() +
                        "          S C R E E N          " + System.lineSeparator() +
                        "-------------------------------" + System.lineSeparator() +
                        "H  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "G  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "F  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "E  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "D  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "C  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "B  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "A  .  .  .  o  o  o  o  .  .  . " + System.lineSeparator() +
                        "  01 02 03 04 05 06 07 08 09 10 " + System.lineSeparator();
        try {
            // When
            CinemaHall cinemaHall = TestHelper.getCinemaHallWithDefaultBooking(bookingId);
            cinemaHall.print(bookingId);

            // Then
            assertEquals(expected, outputStream.toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void print_Multiple_Bookings() {
        // Given
        String bookingId = "GIC0004";
        String expected =
                System.lineSeparator() +
                        "          S C R E E N          " + System.lineSeparator() +
                        "-------------------------------" + System.lineSeparator() +
                        "H  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "G  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "F  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "E  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "D  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "C  .  #  #  #  #  #  #  #  #  . " + System.lineSeparator() +
                        "B  .  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "A  .  .  .  o  o  o  o  .  .  . " + System.lineSeparator() +
                        "  01 02 03 04 05 06 07 08 09 10 " + System.lineSeparator();
        try {
            // When
            CinemaHall cinemaHall = TestHelper.getCinemaHallWithExistingBooking(bookingId);
            cinemaHall.print(bookingId);

            // Then
            assertEquals(expected, outputStream.toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void print_Odd_Number_Of_Columns() {
        // Given
        String bookingId = "GIC0001";
        String expected =
                System.lineSeparator() +
                        "          S C R E E N       " + System.lineSeparator() +
                        "----------------------------" + System.lineSeparator() +
                        "H  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "G  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "F  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "E  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "D  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "C  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "B  .  .  .  .  .  .  .  .  . " + System.lineSeparator() +
                        "A  .  .  o  o  o  o  .  .  . " + System.lineSeparator() +
                        "  01 02 03 04 05 06 07 08 09 " + System.lineSeparator();
        try {
            // When
            CinemaHall cinemaHall = TestHelper.getCinemaHallWithDefaultBookingOddCols(bookingId);
            cinemaHall.print(bookingId);

            // Then
            assertEquals(expected, outputStream.toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void reserveSeats_Even_Number() {
        // Given
        String bookingId = "GIC0001";
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        cinemaHall.reserveSeats(4, bookingId);

        // Then
        try {
            Seat[][] seats = TestHelper.getSeats(cinemaHall);
            assertNull(seats[0][0].bookingId);
            assertNull(seats[0][1].bookingId);
            assertNull(seats[0][2].bookingId);
            assertEquals(bookingId, seats[0][3].bookingId);
            assertEquals(bookingId, seats[0][4].bookingId);
            assertEquals(bookingId, seats[0][5].bookingId);
            assertEquals(bookingId, seats[0][6].bookingId);
            assertNull(seats[0][7].bookingId);
            assertNull(seats[0][8].bookingId);
            assertNull(seats[0][9].bookingId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void reserveSeats_Odd_Number() {
        // Given
        String bookingId = "GIC0001";
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        cinemaHall.reserveSeats(3, bookingId);

        // Then
        try {
            Seat[][] seats = TestHelper.getSeats(cinemaHall);
            assertNull(seats[0][0].bookingId);
            assertNull(seats[0][1].bookingId);
            assertNull(seats[0][2].bookingId);
            assertEquals(bookingId, seats[0][3].bookingId);
            assertEquals(bookingId, seats[0][4].bookingId);
            assertEquals(bookingId, seats[0][5].bookingId);
            assertNull(seats[0][6].bookingId);
            assertNull(seats[0][7].bookingId);
            assertNull(seats[0][8].bookingId);
            assertNull(seats[0][9].bookingId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void testReserveSeats_With_Starting_Row() {
        // Given
        String bookingId = "GIC0001";
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        cinemaHall.reserveSeats(2, 4, bookingId);

        // Then
        try {
            Seat[][] seats = TestHelper.getSeats(cinemaHall);
            assertNull(seats[2][0].bookingId);
            assertNull(seats[2][1].bookingId);
            assertNull(seats[2][2].bookingId);
            assertEquals(bookingId, seats[2][3].bookingId);
            assertEquals(bookingId, seats[2][4].bookingId);
            assertEquals(bookingId, seats[2][5].bookingId);
            assertEquals(bookingId, seats[2][6].bookingId);
            assertNull(seats[2][7].bookingId);
            assertNull(seats[2][8].bookingId);
            assertNull(seats[2][9].bookingId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void confirmBooking() {
        // Given
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        cinemaHall.confirmBooking(4);

        // Then
        assertEquals(76, cinemaHall.getAvailableSeats());
    }

    @Test
    void reAssignSeats_Valid_Position() {
        // Given
        String bookingId = "GIC0021";
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();
        cinemaHall.reserveSeats(6, bookingId);

        // When
        boolean result = cinemaHall.reAssignSeats("C04", bookingId);

        // Then
        assertTrue(result);

        // Initial reservation should be cleared
        try {
            Seat[][] seats = TestHelper.getSeats(cinemaHall);
            assertNull(seats[0][0].bookingId);
            assertNull(seats[0][1].bookingId);
            assertNull(seats[0][2].bookingId);
            assertNull(seats[0][3].bookingId);
            assertNull(seats[0][4].bookingId);
            assertNull(seats[0][5].bookingId);
            assertNull(seats[0][6].bookingId);
            assertNull(seats[0][7].bookingId);
            assertNull(seats[0][8].bookingId);
            assertNull(seats[0][9].bookingId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }

        // Reservation should be reassigned to new position
        try {
            Seat[][] seats = TestHelper.getSeats(cinemaHall);
            assertNull(seats[2][0].bookingId);
            assertNull(seats[2][1].bookingId);
            assertNull(seats[2][2].bookingId);
            assertEquals(bookingId, seats[2][3].bookingId);
            assertEquals(bookingId, seats[2][4].bookingId);
            assertEquals(bookingId, seats[2][5].bookingId);
            assertEquals(bookingId, seats[2][6].bookingId);
            assertEquals(bookingId, seats[2][7].bookingId);
            assertEquals(bookingId, seats[2][8].bookingId);
            assertNull(seats[2][9].bookingId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail();
        }
    }

    @Test
    void reAssignSeats_OutOfBound_Position() {
        // Given
        String bookingId = "GIC0021";
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();
        cinemaHall.reserveSeats(6, bookingId);

        // When
        boolean result = cinemaHall.reAssignSeats("H07", bookingId);

        // Then
        assertFalse(result);
    }

    @Test
    void reAssignSeats_Null_Position() {
        // Given
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        boolean result = cinemaHall.reAssignSeats(null, "GIC0021");

        // Then
        assertFalse(result);
    }

    @Test
    void isBookingExists_True() {
        // Given
        String bookingId = "GIC0021";
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();
        cinemaHall.reserveSeats(6, bookingId);

        // Then
        assertTrue(cinemaHall.isBookingExists(bookingId));
    }

    @Test
    void isBookingExists_False() {
        // Given
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();
        cinemaHall.reserveSeats(6, "GIC0021");

        // Then
        assertFalse(cinemaHall.isBookingExists("GIC1111"));
    }

    @Test
    void incrementBookingsCount() {
        // Given
        CinemaHall cinemaHall = TestHelper.getCinemaHall_8_10();

        // When
        cinemaHall.incrementBookingsCount();
        cinemaHall.incrementBookingsCount();

        // Then
        assertEquals(2, cinemaHall.getBookingsCount());
    }
}