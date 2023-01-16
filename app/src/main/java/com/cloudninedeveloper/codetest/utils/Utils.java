package com.cloudninedeveloper.codetest.utils;

import androidx.lifecycle.MutableLiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author myothiha
 * Created 15/01/2023 at 4:44 PM.
 **/
public class Utils {

    public static MutableLiveData<Boolean> isOnline = new MutableLiveData<>();
    //this is server datetime
    public static Date dateFromServerDate(String data) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        Date date = parser.parse(data);
        return date;
    }

    //CurrentDateTimeStandard
    public static String DateTimeStandard(Date date) {
        String pattern = "dd-MM-YYYY";
        return dateAsString(date, pattern);
    }

    //Convert Date to String Date
    static String dateAsString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        if (date != null)
            return simpleDateFormat.format(date);
        else
            throw new NullPointerException("Date can't parse");
    }

}

