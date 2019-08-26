package com.mute.winter.core.utility.helper;

import android.content.Context;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Locale;
import java.util.StringTokenizer;

public class DateTimeHelper {

    public static String getCurrentUTCTimeForDatabase(){
        return ISODateTimeFormat.dateTime().print(new DateTime(DateTimeZone.UTC));
    }

    public static DateTime getUTCDateTimeFromString(@NonNull String dateTimeString){
        return new DateTime(dateTimeString, DateTimeZone.UTC);
    }

    public static String getDateTimeForDatabase(@NonNull DateTime dateTime){
        return ISODateTimeFormat.dateTime().print(dateTime);
    }

    public static int getDaysLeft(@NonNull DateTime futureDate){
        DateTime future;
        try {
            future = new DateTime()
                    .withDayOfMonth(futureDate.getDayOfMonth())
                    .withMonthOfYear(futureDate.getMonthOfYear())
                    .withYear(futureDate.getYear());
        }catch (IllegalFieldValueException v){
            //value 31 of dayOfMonth causes an exception
            //must be in the range [1,30]
            future = futureDate;
        }

        DateTime current = getCurrentDate();
        if (future.isAfter(current)) {
            return Days.daysBetween(current, future.plusDays(1)).getDays();
        }

        return -1;
    }

    public static DateTime getCurrentDate(){
        return new DateTime(DateTimeZone.UTC).withTime(0, 0, 0, 0);
    }

    public static boolean isDateInFuture(@NonNull DateTime futureDate) {
        return futureDate.isAfter(getCurrentDate());
    }

    public static boolean compareDate(@NonNull DateTime date1, @NonNull DateTime date2){
        return formatDate(date1).equals(formatDate(date2));
    }

    public static boolean compareMonthOfYear(@NonNull DateTime date1, @NonNull DateTime date2) {
        return date1.getMonthOfYear() == date2.getMonthOfYear();
    }

    public static boolean compareTime(@NonNull DateTime time1, @NonNull DateTime time2){
        return formatTime(time1).endsWith(formatTime(time2));
    }

    public static DateTime getDateTimeFromString(@NonNull String timeString) {
        return getDateTimeFromString(timeString, ":");
    }

    public static DateTime getDateTimeFromString(@NonNull String timeString, String separator) {
        StringTokenizer tokenizer = new StringTokenizer(timeString, separator);

        String token1 = tokenizer.nextToken();
        String token2 = tokenizer.nextToken();
        String token3 = tokenizer.nextToken();

        int hour = Integer.parseInt(token1);
        int minute = Integer.parseInt(token2);
        int second = Integer.parseInt(token3);

        return new DateTime()
                .withHourOfDay(hour)
                .withMinuteOfHour(minute)
                .withSecondOfMinute(second);
    }

    @Nullable
    public static DateTime DateTimeFromString(@NonNull String dateTimeString){
        if (dateTimeString.length() > 0) {
            return new DateTime(dateTimeString);
        }
        return null;
    }

    public static String dateTimeToStringForDatabase(@Nullable DateTime dateTime){
        if (dateTime != null){
            return dateTime.toString();
        }
        return "";
    }

    public static int calculateAge(@NonNull DateTime dateOfBirth, @NonNull DateTime now){
        int age = now.getYear() - dateOfBirth.getYear();
        if (now.getDayOfYear() < dateOfBirth.getDayOfYear()){
            age--;
        }
        return age;
    }

    public static boolean hasBirthday(@NonNull DateTime dateOfBirth, @NonNull DateTime now){
        return dateOfBirth.getDayOfMonth() == now.getDayOfMonth() && dateOfBirth.getMonthOfYear() == now.getMonthOfYear();
    }

    public static DateTime convertToLocalTime(@NonNull DateTime dateTime){
        return new DateTime(DateTimeZone.getDefault())
                .withMillis(dateTime.getMillis());
    }

    public static boolean is24HourFormat(Context context){
        return DateFormat.is24HourFormat(context);
    }

    /**
     * =============================================================================================
     * format time
     * =============================================================================================
     */

    public static String formatTime(@Nullable DateTime date){
        return formatTime(date, null);
    }

    public static String formatTime(@Nullable DateTime date, @Nullable Locale locale){
        if (date != null){
            DateTimeFormatter formatter = DateTimeFormat.shortTime();
            return date.toString(formatter.withLocale(locale));
        }
        return "";
    }

    /**
     * =============================================================================================
     * format date
     * =============================================================================================
     */

    public static String formatDate(@Nullable DateTime date){
        return formatDate(date, null);
    }

    public static String formatDate(@Nullable DateTime date, @Nullable Locale locale){
        if (date != null){
            DateTimeFormatter formatter = DateTimeFormat.shortDate();
            return date.toString(formatter.withLocale(locale));
        }
        return "";
    }

    /**
     * =============================================================================================
     * format with pattern
     * =============================================================================================
     */

    public static String formatDateTime(@Nullable DateTime date, String pattern){
        if (date != null){
            return date.toString(pattern);
        }
        return "";
    }
}
