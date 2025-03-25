package gic.cinema.input.model.impl.children;

import gic.cinema.input.model.CinemaInput;

public class TitleInput extends CinemaInput<String> {
    private String value;

    @Override
    public String getInputMsg() {
        return "";
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isValid(Integer... conditions) {
        return true;
    }

    @Override
    public String[] getErrorMsg() {
        return new String[0];
    }
}
