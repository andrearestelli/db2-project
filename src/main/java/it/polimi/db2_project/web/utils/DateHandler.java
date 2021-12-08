package it.polimi.db2_project.web.utils;

import java.util.Calendar;
import java.util.Date;

public final class DateHandler{
    public static Date computeEndingDate(Date subscriptionDate, int validity_period){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(subscriptionDate);
        calendar.add(Calendar.MONTH,validity_period);
        return calendar.getTime();
    }
}
