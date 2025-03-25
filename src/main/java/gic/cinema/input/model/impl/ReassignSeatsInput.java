package gic.cinema.input.model.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;

public class ReassignSeatsInput extends CinemaInput<String> {
    private String value;

    @Override
    public String getInputMsg() {
        return APP_CONSTANTS.INPUT_MSG_REASSIGN;
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
        int row = Character.toUpperCase(value.charAt(0)) - APP_CONSTANTS.CHAR_A;
        String column = value.substring(1);

        if (row >= 0 && row <= conditions[0]) {
            try {
                int col = Integer.parseInt(column);
                if (col <= conditions[1])
                    return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{APP_CONSTANTS.ERROR_MSG1_REASSIGN, APP_CONSTANTS.ERROR_MSG2_REASSIGN};
    }
}
