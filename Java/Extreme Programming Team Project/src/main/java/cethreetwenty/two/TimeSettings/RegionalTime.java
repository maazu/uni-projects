package cethreetwenty.two.TimeSettings;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegionalTime {


    protected static String getCurrentTime() {
        LocalTime localTime = LocalTime.now();
        return String.valueOf(localTime.toString("HH:mm:ss"));
    }

    protected static String getTime(String country) {
        country = evaluateCountry(country);
        DateTime nowLocal = new DateTime(DateTimeZone.UTC).withZone(DateTimeZone.forID(country));
        String localTime = nowLocal.toString("dd-MM-yyyy HH:mm:ss");
        return  localTime ;
    }

    protected static String evaluateCountry(String country) {
        String filteredSpaces;
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(country);

        filteredSpaces = country.replaceAll("\\(.*\\)", "");
        country = filteredSpaces.trim();
        return country;
    }

    protected static String convertTime(String Time,String hours) {
        String am[] = Time.split(" ");
        if (hours == "12 Hours") {
            try {
                DateFormat f1 = new SimpleDateFormat("HH:mm:ss"); //HH for hour of the day (0 - 23)
                Date d = f1.parse(Time);
                DateFormat f2 = new SimpleDateFormat("hh:mm:ss aa");
                String convertedTime = f2.format(d).toUpperCase();
                return convertedTime;
            } catch (Exception e) {
                System.out.println(e);
                return Time;

            }
        }
        if (hours == "24 Hours") {
            try {
                DateFormat f1 = new SimpleDateFormat("hh:mm:ss aa"); //HH for hour of the day (0 - 23)
                Date d = f1.parse(Time);
                DateFormat f2 = new SimpleDateFormat("HH:mm:ss");
                String convertedTime = f2.format(d).toUpperCase();
                return convertedTime;
            } catch (Exception e) {
                System.out.println(e);
                return Time;
            }
        }
        return Time;
    }

    protected static String formatDate(String dateFormat) {
        LocalDate date = LocalDate.now();
        String formattedDate = "Invalid Format";
        if(dateFormat == "yyyy-M-dd"){
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyy-MM-dd");
            formattedDate = date.toString(fmt);
            return formattedDate;
        }
        if(dateFormat =="dd-MM-yyyy"){
            DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
            formattedDate = date.toString(fmt);
            return formattedDate;
        }

        if(dateFormat =="d MMMM, yyyy"){
            DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM, yyyy");
            formattedDate =  date.toString(fmt);
            return formattedDate;
        }
        if(dateFormat =="d MMM, yyyy"){
            DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMM, yyyy");
            formattedDate =  date.toString(fmt);
            return formattedDate;
        }
      return formattedDate;
    }


}