package gic.cinema.input.model.impl.children;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;

public class RowsInput extends CinemaInput<Integer> {
    private static final int MIN_ROWS = 1;
    private static final int MAX_ROWS = 26;
    private Integer value;

    @Override
    public String getInputMsg() {
        return "";
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
        return value >= MIN_ROWS && value <= MAX_ROWS;
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{String.format(APP_CONSTANTS.ERROR_MSG_ROWS, MIN_ROWS, MAX_ROWS)};
    }
}
