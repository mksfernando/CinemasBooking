package gic.cinema.action.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.action.Action;
import gic.cinema.components.CinemaHall;
import gic.cinema.input.util.InputOutputUtil;

public class ExitAction implements Action {
    @Override
    public String getDescription() {
        return APP_CONSTANTS.ACTION_DES_EXIT;
    }

    @Override
    public void perform(InputOutputUtil inputOutputUtil, CinemaHall cinemaHall) {
        inputOutputUtil.exit();
    }
}
