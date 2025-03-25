package gic.cinema.input.model.impl;

import gic.cinema.APP_CONSTANTS;
import gic.cinema.input.model.CinemaInput;

public class CheckBookingsInput extends CinemaInput<String> {
    String value;

    @Override
    public String getInputMsg() {
        return APP_CONSTANTS.INPUT_MSG_CHECK_BOOKINGS;
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
        return value.length() == 7 && value.toUpperCase().startsWith(APP_CONSTANTS.ORG_NAME);
    }

    @Override
    public String[] getErrorMsg() {
        return new String[]{APP_CONSTANTS.ERROR_MSG1_CHECK_BOOKINGS, APP_CONSTANTS.ERROR_MSG2_CHECK_BOOKINGS};
    }
}
