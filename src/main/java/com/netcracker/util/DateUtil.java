package com.netcracker.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public interface DateUtil {

    String ISO_DATE_FORMAT_ZERO_OFFSET = "yyyy-MM-dd";


    static Date provideDateFormat(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_DATE_FORMAT_ZERO_OFFSET);
        java.util.Date langDate = sdf.parse(date);
        return new Date(langDate.getTime());
    }
}

