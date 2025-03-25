package gic.cinema.components;

import gic.cinema.APP_CONSTANTS;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class CinemaHall {
    private final String movieTitle;
    private final int rows;
    private final int cols;
    private final Seat[][] seats;
    private int availableSeats;
    private int bookingsCount;

    // This queue holds the seats temporarily for Reassignment
    private final Queue<Seat> defaultBooking = new ArrayDeque<>();

    public CinemaHall(String movieTitle, int rows, int cols) {
        this.movieTitle = movieTitle;
        this.rows = rows;
        this.cols = cols;
        seats = new Seat[this.rows][this.cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                seats[row][col] = new Seat((char) (APP_CONSTANTS.CHAR_A + row) + String.valueOf(col));
            }
        }
        this.availableSeats = rows * cols;
    }

    public void print(String bookingId) {
        // Screen Label
        printScreenLabel();

        // Separator Line
        printSeparator();

        // Row Label and Seats
        printRowLabelsAndSeats(bookingId);

        // Column Labels
        printColumnLabels();
    }

    private void printColumnLabels() {
        System.out.print(APP_CONSTANTS.ROW_LBL_SPACE);
        for (int col = 0; col < cols; col++) {
            System.out.printf("%02d ", col + 1);
        }
        System.out.println();
    }

    private void printRowLabelsAndSeats(String bookingId) {
        for (int row = rows - 1; row >= 0; row--) {
            System.out.print((char) (APP_CONSTANTS.CHAR_A + row) + APP_CONSTANTS.SPACE);
            for (int col = 0; col < this.cols; col++) {
                if (seats[row][col].bookingId == null)
                    System.out.print(" . ");
                else if (bookingId.equalsIgnoreCase(seats[row][col].bookingId))
                    System.out.print(" o ");
                else
                    System.out.print(" # ");
            }
            System.out.println();
        }
    }

    private void printSeparator() {
        System.out.print(APP_CONSTANTS.HYPHEN);
        for (int col = 0; col < cols; col++) {
            System.out.print(APP_CONSTANTS.COL_SPACE_SEP);
        }
        System.out.println();
    }

    private void printScreenLabel() {
        System.out.println();

        int middle = cols / 2;
        System.out.print(APP_CONSTANTS.SPACE);
        for (int col = 0; col < cols; col++) {
            if (cols % 2 == 0) {
                if (col == middle - 2)
                    System.out.print(APP_CONSTANTS.SCREEN_INDICATOR);
                else if (col < middle - 2 || col > middle + 1)
                    System.out.print(APP_CONSTANTS.COL_SPACE);
            } else {
                if (col == middle - 1)
                    System.out.print(APP_CONSTANTS.SCREEN_INDICATOR);
                else if (col < middle - 1 || col > middle + 2)
                    System.out.print(APP_CONSTANTS.COL_SPACE);
            }
        }
        System.out.println();
    }

    public void reserveSeats(int numberOfSeats, String bookingId) {
        reserveSeats(0, numberOfSeats, bookingId);
    }

    public void reserveSeats(int startRow, int numberOfSeats, String bookingId) {
        int reservedCount = 0;
        L1:
        for (int row = startRow; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (reservedCount == numberOfSeats)
                    break L1;
                int currentMid = getMiddleMostColumn(col);
                if (seats[row][currentMid].bookingId == null) {
                    seats[row][currentMid].setBookingId(bookingId);
                    defaultBooking.offer(seats[row][currentMid]);
                    reservedCount++;
                }
            }
        }
    }

    public void confirmBooking(int numberOfSeats) {
        this.availableSeats -= numberOfSeats;
        defaultBooking.clear();
    }

    public boolean reAssignSeats(String startPosition, String bookingId) {
        if (startPosition == null)
            return false;
        int startRow = Character.toUpperCase(startPosition.charAt(0)) - APP_CONSTANTS.CHAR_A;
        int startCol = Integer.parseInt(startPosition.substring(1)) - 1;
        int seatCount = defaultBooking.size();
        if (checkReassign(startRow, startCol, bookingId, seatCount)) {
            // Clear previous booking
            for (int i = 0; i < seatCount; i++) {
                Objects.requireNonNull(defaultBooking.poll()).bookingId = null;
            }

            // Reassign to right direction on specified row
            for (int col = startCol; col < cols && seatCount > 0; col++) {
                if (seats[startRow][col].bookingId == null || seats[startRow][col].bookingId.equalsIgnoreCase(bookingId)) {
                    seats[startRow][col].bookingId = bookingId;
                    defaultBooking.offer(seats[startRow][col]);
                    seatCount--;
                }
            }

            // Reassign Overflow if any
            reserveSeats(startRow + 1, seatCount, bookingId);
            return true;
        }
        return false;
    }

    public boolean isBookingExists(String bookingId) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (bookingId.equalsIgnoreCase(seats[row][col].bookingId))
                    return true;
            }
        }
        return false;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public int getBookingsCount() {
        return bookingsCount;
    }

    public void incrementBookingsCount() {
        this.bookingsCount++;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    private boolean checkReassign(int startRow, int startCol, String bookingId, int requiredSeats) {
        int vacantSeats = 0;
        for (int row = startRow; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (row == startRow && col < startCol)
                    continue;
                if (seats[row][col].bookingId == null || seats[row][col].bookingId.equalsIgnoreCase(bookingId))
                    vacantSeats++;

                if (vacantSeats >= requiredSeats)
                    return true;
            }
        }
        return false;
    }

    private int getMiddleMostColumn(int index) {
        int currentMidMostCol;
        int middleIndex = (cols - 1) / 2;
        if (index % 2 == 0)
            currentMidMostCol = middleIndex - (index / 2);
        else
            currentMidMostCol = middleIndex + (index / 2) + 1;

        return currentMidMostCol;
    }
}
