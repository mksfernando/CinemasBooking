package gic.cinema.input.model.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;

import java.util.List;

public class OptionInput extends CinemaInput<Integer> {
    private Integer value;

    @Override
    public String getInputMsg() {
        return APP_CONSTANTS.INPUT_MSG_OPTIONS;
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
        return List.of(conditions).contains(value);
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{APP_CONSTANTS.ERROR_MSG_OPTIONS};
    }
}
