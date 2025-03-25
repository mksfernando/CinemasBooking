package gic.cinema;

import gic.cinema.action.Action;
import gic.cinema.components.CinemaHall;
import gic.cinema.components.Seat;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class TestHelper {
    public static CinemaHall getCinemaHall_8_10() {
        return new CinemaHall("Inception", 8, 10);
    }

    public static Scanner getScanner(String input) {
        return new Scanner(new ByteArrayInputStream(input.getBytes()));
    }

    public static String getMenuOptionsList_8_10() {
        return """
                [1] Book tickets for %s (%s seats available)
                [2] Check bookings
                [3] Exit""";
    }

    public static Integer[] getMenuOptions() {
        return new Integer[]{1, 2, 3};
    }

    public static CinemaHall getCinemaHallWithDefaultBooking(String bookingId) throws NoSuchFieldException, IllegalAccessException {
        CinemaHall cinemaHall = new CinemaHall("Inception", 8, 10);
        Field field = CinemaHall.class.getDeclaredField("seats");
        field.setAccessible(true);
        Seat[][] seats = (Seat[][]) field.get(cinemaHall);

        seats[0][3].setBookingId(bookingId);
        seats[0][4].setBookingId(bookingId);
        seats[0][5].setBookingId(bookingId);
        seats[0][6].setBookingId(bookingId);

        return cinemaHall;
    }

    public static CinemaHall getCinemaHallWithExistingBooking(String bookingId) throws NoSuchFieldException, IllegalAccessException {
        CinemaHall cinemaHall = new CinemaHall("Inception", 8, 10);
        Field field = CinemaHall.class.getDeclaredField("seats");
        field.setAccessible(true);
        Seat[][] seats = (Seat[][]) field.get(cinemaHall);
        String existingBooking1 = "GIC0001";
        String existingBooking2 = "GIC0002";
        seats[2][1].setBookingId(existingBooking2);
        seats[2][2].setBookingId(existingBooking2);
        seats[2][3].setBookingId(existingBooking2);
        seats[2][4].setBookingId(existingBooking1);
        seats[2][5].setBookingId(existingBooking1);
        seats[2][6].setBookingId(existingBooking1);
        seats[2][7].setBookingId(existingBooking1);
        seats[2][8].setBookingId(existingBooking1);

        seats[0][3].setBookingId(bookingId);
        seats[0][4].setBookingId(bookingId);
        seats[0][5].setBookingId(bookingId);
        seats[0][6].setBookingId(bookingId);

        return cinemaHall;
    }

    public static CinemaHall getCinemaHallWithDefaultBookingOddCols(String bookingId) throws NoSuchFieldException, IllegalAccessException {
        CinemaHall cinemaHall = new CinemaHall("Inception", 8, 9);
        Field field = CinemaHall.class.getDeclaredField("seats");
        field.setAccessible(true);
        Seat[][] seats = (Seat[][]) field.get(cinemaHall);

        seats[0][2].setBookingId(bookingId);
        seats[0][3].setBookingId(bookingId);
        seats[0][4].setBookingId(bookingId);
        seats[0][5].setBookingId(bookingId);

        return cinemaHall;
    }

    public static Seat[][] getSeats(CinemaHall cinemaHall) throws NoSuchFieldException, IllegalAccessException {
        Field field = CinemaHall.class.getDeclaredField("seats");
        field.setAccessible(true);
        return (Seat[][]) field.get(cinemaHall);
    }

    public static void setActionInMap(int key, Action action, BookingEngine bookingEngine) throws NoSuchFieldException, IllegalAccessException {
        Field field = BookingEngine.class.getDeclaredField("actionMap");
        field.setAccessible(true);
        Map<Integer, Action> actionMap = (Map<Integer, Action>) field.get(bookingEngine);
        actionMap.put(key, action);
    }
}
