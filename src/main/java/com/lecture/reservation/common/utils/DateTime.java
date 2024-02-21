package com.lecture.reservation.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by HoYoung on 2023/02/28.
 */
public class DateTime {
    
    public static TimeZone DEFUALT_TIME_ZONE = TimeZone.getTimeZone("GMT+9:00")
    public static ZoneId ZONE_UTC = ZoneId.of("UTC");
    public static ZoneId ZONE_KOREA = ZoneId.of("Asia/Seoul");
    public static DateTimeFormatter DEFUALT_DATE_FROMAT = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss");

    public static long toEpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(ZONE_UTC).withZoneSameInstant(zoneId).toInstant().toEpochMilli();
    }

    public static long toEpochMilli(LocalDateTime localDateTime) {
        return toEpochMilli(localDateTime, ZONE_KOREA);
    }

    public static boolean isBetween(LocalDateTime compareDate, LocalDateTime compareStartDate,
            LocalDateTime compareEndDate) {
        return compareDate.isAfter(compareStartDate) && compareDate.isBefore(compareEndDate);
    }

    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DEFUALT_DATE_FROMAT);
    }

    public static String format(LocalDateTime localDateTime, String format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    public static LocalDateTime toLocalDateTime(long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atZone(ZONE_KOREA).toLocalDateTime();
    }
}
