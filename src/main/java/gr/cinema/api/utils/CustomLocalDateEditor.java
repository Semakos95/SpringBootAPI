package gr.cinema.api.utils;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateEditor extends PropertyEditorSupport {

    private final DateTimeFormatter formatter;

    public CustomLocalDateEditor() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            LocalDate localDate = LocalDate.parse(text, formatter);
            setValue(localDate);
        } else {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        LocalDate localDate = (LocalDate) getValue();
        if (localDate != null) {
            return localDate.format(formatter);
        } else {
            return "";
        }
    }
}
