package gic.cinema.input.model.impl.children;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;

public class ColumnsInput extends CinemaInput<Integer> {
    private static final int MIN_COLUMNS = 1;
    private static final int MAX_COLUMNS = 50;
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
        return value >= MIN_COLUMNS && value <= MAX_COLUMNS;
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{String.format(APP_CONSTANTS.ERROR_MSG_COLUMNS, MIN_COLUMNS, MAX_COLUMNS)};
    }
}
