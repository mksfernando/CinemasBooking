package gic.cinema.input.util;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.model.CinemaInput;
import gic.cinema.input.model.impl.*;

import java.util.Scanner;

public class InputOutputUtil {
    Scanner scanner;

    public InputOutputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public CinemaHallInput getCinemaHallInputs() {
        CinemaHallInput cinemaHallInput = new CinemaHallInput();
        while (true) {
            System.out.print(cinemaHallInput.getInputMsg());
            String userInput = scanner.nextLine();
            String[] inputs = userInput.split(APP_CONSTANTS.SPACE);
            if (inputs.length == 3) {
                if (validateAndSetInputs(cinemaHallInput, inputs))
                    break;
            } else {
                System.out.println(cinemaHallInput.getErrorMsg()[0]);
            }
        }
        return cinemaHallInput;
    }

    private boolean validateAndSetInputs(CinemaHallInput cinemaHallInput, String[] inputs) {
        for (int i = 0; i < cinemaHallInput.getChildren().size(); i++) {
            CinemaInput input = cinemaHallInput.getChildren().get(i);
            try {
                if (input.getType() == String.class) {
                    input.setValue(inputs[i]);
                } else if (input.getType() == Integer.class) {
                    input.setValue(Integer.parseInt(inputs[i]));
                }
            } catch (NumberFormatException e) {
                System.out.println(input.getErrorMsg()[0]);
                return false;
            }
            if (!input.isValid()) {
                System.out.println(input.getErrorMsg()[0]);
                return false;
            }
        }
        return true;
    }

    public OptionInput getOptionSelection(CinemaHall cinemaHall, String menuOptionsList, Integer[] options) {
        OptionInput optionInput = new OptionInput();
        while (true) {
            System.out.println();
            System.out.printf(
                    optionInput.getInputMsg(),
                    String.format(menuOptionsList, cinemaHall.getMovieTitle(), cinemaHall.getAvailableSeats())
            );
            String userInput = scanner.nextLine();
            try {
                optionInput.setValue(Integer.parseInt(userInput));
            } catch (NumberFormatException e) {
                System.out.println(optionInput.getErrorMsg()[0]);
                continue;
            }
            if (optionInput.isValid(options))
                break;
            else
                System.out.println(optionInput.getErrorMsg()[0]);
        }
        return optionInput;
    }

    public BookTicketsInput getBookTicketsInput(int availableSeats) {
        BookTicketsInput bookTicketsInput = new BookTicketsInput();
        while (true) {
            System.out.print(bookTicketsInput.getInputMsg());
            String userInput = scanner.nextLine();
            if (userInput == null || userInput.isBlank()) {
                return null;
            }
            try {
                int numberOfTickets = Integer.parseInt(userInput);
                bookTicketsInput.setValue(numberOfTickets);
                if (bookTicketsInput.isValid(availableSeats))
                    break;
                else if (numberOfTickets > availableSeats)
                    System.out.printf(bookTicketsInput.getErrorMsg()[1] + System.lineSeparator(), availableSeats);
                else
                    System.out.println(bookTicketsInput.getErrorMsg()[0]);
            } catch (NumberFormatException e) {
                System.out.println(bookTicketsInput.getErrorMsg()[0]);
            }
        }
        return bookTicketsInput;
    }

    public ReassignSeatsInput getReassignInput(CinemaHall cinemaHall) {
        ReassignSeatsInput reassignSeatsInput = new ReassignSeatsInput();
        while (true) {
            System.out.println();
            System.out.print(reassignSeatsInput.getInputMsg());
            String userInput = scanner.nextLine();
            if (userInput == null || userInput.isBlank())
                return null;

            reassignSeatsInput.setValue(userInput);
            if (reassignSeatsInput.isValid(cinemaHall.getRows(), cinemaHall.getCols()))
                break;
            else
                System.out.println(reassignSeatsInput.getErrorMsg()[0]);
        }
        return reassignSeatsInput;
    }

    public CheckBookingsInput getCheckBookingsInput() {
        CheckBookingsInput checkBookingsInput = new CheckBookingsInput();
        while (true) {
            System.out.println();
            System.out.print(checkBookingsInput.getInputMsg());
            String userInput = scanner.nextLine();
            if (userInput == null || userInput.isBlank())
                return null;
            checkBookingsInput.setValue(userInput);
            if (checkBookingsInput.isValid())
                break;
            else
                System.out.println(checkBookingsInput.getErrorMsg()[0]);
        }
        return checkBookingsInput;
    }

    public void showConfirmation(String bookingId) {
        System.out.printf(APP_CONSTANTS.CONFIRMED_MSG, bookingId.toUpperCase());
        System.out.println();
    }

    public void showReservationSuccessful(int numberOfTickets, String movieTitle, String bookingId) {
        System.out.println();
        System.out.printf(APP_CONSTANTS.RES_SUCCESSFUL_MSG, numberOfTickets, movieTitle,
                String.format(APP_CONSTANTS.RES_BOOKING_ID_MSG,bookingId.toUpperCase()));
        System.out.println();
    }

    public void showSeatsForBooking(String bookingId) {
        System.out.println();
        System.out.printf(APP_CONSTANTS.RES_BOOKING_ID_MSG, bookingId.toUpperCase());
        System.out.println();
    }

    public void showReassignFail(String errorMsg, String startPosition) {
        System.out.printf(errorMsg, startPosition);
        System.out.println();
    }

    public void showNotFoundMsg(String errorMsg, String bookingId) {
        System.out.printf(errorMsg, bookingId.toUpperCase());
        System.out.println();
    }

    public void exit() {
        System.out.println(APP_CONSTANTS.EXIT_MSG);
    }
}
