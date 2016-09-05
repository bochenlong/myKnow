package org.bochenlong.time8;

import org.bochenlong.print.PrintUt;

import javax.swing.text.DateFormatter;
import javax.swing.text.ZoneView;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Created by bcl on 2016/9/5.
 */
public class Time8 {
    public static void main(String[] args) {
        /**
         * 日期
         */
        LocalDate date = LocalDate.now();
        date = LocalDate.parse("2015-04-01",DateTimeFormatter.ISO_LOCAL_DATE);
        date = LocalDate.parse("20150401",DateTimeFormatter.BASIC_ISO_DATE);
        PrintUt.print("当前日期：", date);
        // 创建20160901日期 注意你创建9.31会报错
        date = LocalDate.of(2016, 9, 1);
        PrintUt.print("创建日期：", date);
        // 获取日期的年、月、日
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        PrintUt.print(year, ":", month, ":", day);
        PrintUt.print("日期的所在全年天数", date.getDayOfYear());
        // 更底层的调用
        year = date.get(ChronoField.YEAR);
        month = date.get(ChronoField.MONTH_OF_YEAR);
        day = date.get(ChronoField.DAY_OF_YEAR);
        day = date.get(ChronoField.DAY_OF_WEEK);
        // ...

        /**
         * 时间
         */
        LocalTime time = LocalTime.of(13, 45, 20);
        time = LocalTime.parse("10:15:30");
        time = LocalTime.now();
        PrintUt.print("当前时间：", time);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        PrintUt.print("小时：", hour, "，分钟：", minute, "，秒：", second);

        /**
         * 日期和时间
         */
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        dateTime = LocalDateTime.of(2016, 9, 1, 12, 1, 30);
        dateTime = date.atTime(13, 44);
        PrintUt.print("dateTime：", dateTime);
        dateTime = time.atDate(date);
        PrintUt.print("dateTime：", dateTime);

        /**
         * 日期时间转换相应的日期、时间
         */
        LocalDate date1 = dateTime.toLocalDate();
        LocalTime time1 = dateTime.toLocalTime();

        /**
         * 机器的日期和时间
         */
        Instant instant = Instant.now();
        PrintUt.print("当期时间：", instant);
        instant = Instant.ofEpochSecond(3);// 可以传入第二个参数正负值对第一个值进行加减
        PrintUt.print("3秒时间：", instant);

        /**
         * Duration 基于秒、纳秒衡量时间的长短
         */
        time1 = LocalTime.of(13, 11);
        LocalTime time2 = LocalTime.of(14, 22, 30);
        Duration duration = Duration.between(time1, time2);
        PrintUt.print("时间相差：", duration.toMinutes());

//        date1 = LocalDate.now();
//        LocalDate date2 = LocalDate.of(2016,10,1);
//        duration = Duration.between(date1,date2); 注意你不能仅向between传递一个LocalDate对象参数
//        PrintUt.print("日期相差：",duration.toMinutes());

        /**
         * Period 基于年、月、日
         */
        Period period = Period.between(LocalDate.of(2016, 8, 1), LocalDate.now());
        PrintUt.print("日期相差：年", period.getYears(), "月", period.getMonths(), "日", period.getDays());


        /**
         * 日期操作
         */
        date1 = LocalDate.of(2016, 9, 1);
        date1 = date1.withYear(2015).withMonth(8).
                withDayOfMonth(12).plusDays(2).plus(2, ChronoUnit.DAYS);
        PrintUt.print("日期：", date1);

        /**
         * TemporalAdjuster
         */
        // 月的第一天
        date1 = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        // 第二个周的周五
        date1 = date1.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.FRIDAY));
        PrintUt.print("日期：", date1);

        // 工作日T+2，节假日顺延
        date1 = LocalDate.of(2016,9,7);
        date1 = LocalDate.of(2016,9,8);
        date1 = LocalDate.of(2016,9,9);
        date1 = LocalDate.of(2016,9,10);
        date1 = LocalDate.of(2016,9,11);
        date1 = date1.with(temporal -> {
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 2;
            if (dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 4;
            if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 3;
            if (dayOfWeek == DayOfWeek.SUNDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        PrintUt.print("到账时间：",date1);

        // 可以封装TemporalAdjuster 重复使用
//        TemporalAdjuster t2 = TemporalAdjusters.ofDateAdjuster(temporal -> {
//            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
//            int dayToAdd = 2;
//            if (dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 4;
//            if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 3;
//            if (dayOfWeek == DayOfWeek.SUNDAY) dayToAdd = 2;
//            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
//        });
//        date1 = date1.with(t2);

        /**
         * 字符化
         */
        String s = date1.format(DateTimeFormatter.BASIC_ISO_DATE);
        PrintUt.print(s);
        s = date1.format(DateTimeFormatter.ISO_LOCAL_DATE);
        PrintUt.print(s);

        // 自定义 DateTimeFormatter
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.CHINA);
        s = date1.format(dtf);
        PrintUt.print(s);

        dtf = new DateTimeFormatterBuilder().appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(".")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(".")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINA);

        /**
         * 时间添加时区信息
         */
        ZonedDateTime zdt = LocalDate.now().atStartOfDay(ZoneId.of("Europe/Paris"));
        zdt = LocalDateTime.now().atZone(ZoneId.of("Europe/Paris"));
        PrintUt.print("巴黎时间：",zdt.toLocalDateTime());

        // 通过ZoneId LocalDateTime 和Instant可以相互转换
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");
        Instant i = LocalDateTime.now().toInstant(newYorkOffset);
        i = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(i,ZoneId.of("Europe/Paris"));

        // ZoneOffset 时间差 当前时区和UTC/格林尼治的固定偏差
        // 比如
        newYorkOffset = ZoneOffset.of("-05:00");
        LocalDateTime ldt1 = LocalDateTime.now();
        OffsetDateTime odt = OffsetDateTime.of(ldt1,newYorkOffset);
        PrintUt.print(odt.toLocalDateTime());

        // 别的日历系统 ThaiBuddhistDate/MinguoDate/JapaneseDate/HijrahDate
    }
}
