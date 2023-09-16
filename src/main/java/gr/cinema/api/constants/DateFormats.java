package gr.cinema.api.constants;

import java.time.format.DateTimeFormatter;

public final class DateFormats {
    private DateFormats() {
        throw new IllegalStateException("DateFormats class");
    }

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final String JsonDateFormat = "dd/MM/yyyy";
    public static final String JsonDateTimeFormat = "dd/MM/yyyy HH:mm:ss";
}
