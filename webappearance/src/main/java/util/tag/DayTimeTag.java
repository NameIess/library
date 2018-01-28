package util.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class DayTimeTag extends TagSupport {
    private static final Logger Log = LogManager.getLogger(DayTimeTag.class.getSimpleName());
    private static final String EASTERN_EUROPE_DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";
    private static final String UNITED_KINGDOM_DATE_PATTERN = "dd/MM/yyyy hh:mm:ss a";
    private static final String GERMAN_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String RUSSIAN_LOCALE_ALIAS = "ru";
    private static final String ENGLISH_LOCALE_ALIAS = "en";
    private static final String GERMAN_LOCALE_ALIAS = "de";
    private String locale;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public int doStartTag() throws JspException {
        String time = setDateTimeByLocale(getLocale());

        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }


    private String setDateTimeByLocale(String locale) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = null;
        if (locale.equals(RUSSIAN_LOCALE_ALIAS)) {
            formatter = DateTimeFormatter.ofPattern(EASTERN_EUROPE_DATE_PATTERN);
        } else if (locale.startsWith(ENGLISH_LOCALE_ALIAS) || locale.isEmpty()) {
            formatter = DateTimeFormatter.ofPattern(UNITED_KINGDOM_DATE_PATTERN);
        } else if (locale.startsWith(GERMAN_LOCALE_ALIAS)) {
            formatter = DateTimeFormatter.ofPattern(GERMAN_DATE_PATTERN);
        }

        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}


