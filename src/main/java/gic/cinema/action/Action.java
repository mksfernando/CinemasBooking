package gic.cinema.action;

import gic.cinema.components.CinemaHall;
import gic.cinema.input.util.InputOutputUtil;

public interface Action {
    String getDescription();

    void perform(InputOutputUtil inputOutputUtil, CinemaHall cinemaHall);
}
