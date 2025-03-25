package gic.cinema;

import gic.cinema.action.Action;
import gic.cinema.action.BookTicketsAction;
import gic.cinema.action.CheckBookingsAction;
import gic.cinema.action.ExitAction;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.model.impl.CinemaHallInput;
import gic.cinema.input.model.impl.OptionInput;
import gic.cinema.input.util.InputOutputUtil;

import java.util.Map;
import java.util.TreeMap;

public class BookingEngine {
    private final Map<Integer, Action> actionMap = new TreeMap<>();
    private final InputOutputUtil inputOutputUtil;
    private String menuOptionsList;

    public BookingEngine(InputOutputUtil inputOutputUtil) {
        initActionMap();
        initManuOptions();
        this.inputOutputUtil = inputOutputUtil;
    }

    public void start() {
        // Get and initialize Cinema Hall Inputs
        CinemaHallInput cinemaHallInput = inputOutputUtil.getCinemaHallInputs();

        // Initialize Cinema Hall
        CinemaHall cinemaHall = new CinemaHall(
                (String) cinemaHallInput.getChildren().get(0).getValue(),
                (int) cinemaHallInput.getChildren().get(1).getValue(),
                (int) cinemaHallInput.getChildren().get(2).getValue()
        );

        OptionInput selectedOption;
        do {
            // Option Selection
            selectedOption = inputOutputUtil.getOptionSelection(cinemaHall, this.menuOptionsList, actionMap.keySet().toArray(new Integer[0]));

            // Handle Option
            actionMap.get(selectedOption.getValue()).perform(inputOutputUtil, cinemaHall);
        } while (actionMap.get(selectedOption.getValue()).getClass() != ExitAction.class);
    }

    private void initActionMap() {
        actionMap.put(1, new BookTicketsAction());
        actionMap.put(2, new CheckBookingsAction());
        actionMap.put(3, new ExitAction());
    }

    private void initManuOptions() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Action> entry : actionMap.entrySet()) {
            sb.append(String.format("[%s] %s", entry.getKey(), entry.getValue().getDescription()));
            sb.append(System.lineSeparator());
        }
        this.menuOptionsList = sb.toString();
    }

}
