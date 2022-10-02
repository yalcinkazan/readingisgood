package com.project.readingisgood.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Date getNow() {
        return Calendar.getInstance().getTime();
    }

}
