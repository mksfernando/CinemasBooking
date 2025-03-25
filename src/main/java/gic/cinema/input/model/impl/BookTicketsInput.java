package gic.cinema.input.model.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;

public class BookTicketsInput extends CinemaInput<Integer> {
    private Integer value;

    @Override
    public String getInputMsg() {
        return APP_CONSTANTS.INPUT_MSG_BOOK_TICKETS;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean isValid(Integer... conditions) {
        return value > 0 && value <= conditions[0];
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{
                APP_CONSTANTS.ERROR_MSG1_BOOK_TICKETS,
                APP_CONSTANTS.ERROR_MSG2_BOOK_TICKETS
        };
    }
}
