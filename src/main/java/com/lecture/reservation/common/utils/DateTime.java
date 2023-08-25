package com.lecture.reservation.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by HoYoung on 2023/02/28.
 */
public class DateTime {

    public static ZoneId ZONE_UTC = ZoneId.of("UTC");
    public static ZoneId ZONE_KOREA = ZoneId.of("Asia/Seoul");
    public static DateTimeFormatter DEFUALT_DATE_FROMAT = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss");

    public static long toEpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(ZONE_UTC).withZoneSameInstant(zoneId).toInstant().toEpochMilli();
    }

    public static long toEpochMilli(LocalDateTime localDateTime) {
        return toEpochMilli(localDateTime, ZoneId.systemDefault());
    }

    public static boolean isBetween(LocalDateTime compareDate, LocalDateTime compareStartDate,
            LocalDateTime compareEndDate) {
        return !compareDate.isBefore(compareStartDate) && !compareDate.isAfter(compareEndDate);
    }

    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DEFUALT_DATE_FROMAT);
    }

    public static String format(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }
}
