package gic.cinema;

public interface APP_CONSTANTS {
    // Messages
    String INPUT_MSG_CINEMA_HALL = "Please define movie Title and seating map in [Title] [Row] [SeatsPerRow] format: ";
    String ERROR_MSG_CINEMA_HALL = "Invalid input for [Title] [Row] [SeatsPerRow] format.";
    String ERROR_MSG_COLUMNS = "Invalid input for SeatsPerRow. Please enter a valid number between %s-%s.";
    String ERROR_MSG_ROWS = "Invalid input for Rows. Please enter a valid number between %s-%s.";
    String INPUT_MSG_OPTIONS = """
            Welcome to GIC Cinemas
            %s
            Please enter your selection:\s""";
    String ERROR_MSG_OPTIONS = "Invalid option selection.";

    String INPUT_MSG_BOOK_TICKETS = "Enter number of tickets to book, or enter blank to go back to main menu: ";
    String ERROR_MSG1_BOOK_TICKETS = "Invalid input for number of tickets.";
    String ERROR_MSG2_BOOK_TICKETS = "Sorry, there are only %s seats available.";

    String INPUT_MSG_REASSIGN = "Enter blank to accept seat selection, or enter new seat position: ";
    String ERROR_MSG1_REASSIGN = "Invalid seating position.";
    String ERROR_MSG2_REASSIGN = "Sorry, cannot reassign current seats to %s.";

    String INPUT_MSG_CHECK_BOOKINGS = "Enter booking id, or enter blank to go back to main menu: ";
    String ERROR_MSG1_CHECK_BOOKINGS = "Invalid input for Booking Id. Enter booking id in [GIC####] format.";
    String ERROR_MSG2_CHECK_BOOKINGS = "Booking Id %s does not exists. Please enter a valid Booking Id";

    String ACTION_DES_BOOK_TICKETS = "Book tickets for %s (%s seats available)";
    String ACTION_DES_CHECK_BOOKINGS = "Check bookings";
    String ACTION_DES_EXIT = "Exit";

    String RES_SUCCESSFUL_MSG = """
            Successfully reserved %s %s tickets.
            %s""";

    String RES_BOOKING_ID_MSG = """
            Booking id: %s
            Selected seats:""";

    String CONFIRMED_MSG = "Booking id: %s confirmed.";
    String EXIT_MSG = "Thank you for using GIC Cinemas system. Bye!";

    // Values
    String SPACE = " ";
    String HYPHEN = "-";
    String ROW_LBL_SPACE = "  ";
    String COL_SPACE = "   ";
    String COL_SPACE_SEP = "---";
    String SCREEN_INDICATOR = "S C R E E N ";
    String ORG_NAME = "GIC";
    char CHAR_A = 'A';
}
