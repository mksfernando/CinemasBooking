package gic.cinema.input.model.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;
import gic.cinema.input.model.impl.children.ColumnsInput;
import gic.cinema.input.model.impl.children.RowsInput;
import gic.cinema.input.model.impl.children.TitleInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CinemaHallInput extends CinemaInput<String[]> {
    private String[] value;
    private final List<CinemaInput<?>> children = new ArrayList<>(Arrays.asList(new TitleInput(), new RowsInput(), new ColumnsInput()));

    @Override
    public String getInputMsg() {
        return APP_CONSTANTS.INPUT_MSG_CINEMA_HALL;
    }

    @Override
    public String[] getValue() {
        return value;
    }

    @Override
    public void setValue(String[] value) {
        this.value = value;
    }

    @Override
    public boolean isValid(Integer... conditions) {
        return true;
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{APP_CONSTANTS.ERROR_MSG_CINEMA_HALL};
    }

    @Override
    public List<CinemaInput<?>> getChildren() {
        return children;
    }
}
