package com.proyectofinal.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class ManageDate {

    private static DateFormat ddMMyyyyFormat = new SimpleDateFormat(
	    "dd/MM/yyyy");

    private ManageDate() {
    }

    public static synchronized String getDateDDMMYYYY(Date date) {
	return ddMMyyyyFormat.format(date);
    }

    public Date changeStringToDate(String stringDate) {
	return null;
    }

    public static String toGmtString(Date date) {
	SimpleDateFormat sd = new SimpleDateFormat("dd-MM-YYYY");
	sd.setTimeZone(TimeZone.getTimeZone("GMT"));
	return sd.format(date);
    }

}
