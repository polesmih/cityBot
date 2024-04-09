package org.polesmih.bot.settings;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    @SneakyThrows
    public static String formattingDateTime(String tableDate) {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        Date date = dt.parse(tableDate);
        SimpleDateFormat newDateTime = new SimpleDateFormat("dd.MM.yyyy в HH:mm");
        tableDate = newDateTime.format(date);

        return tableDate;
    }

}
