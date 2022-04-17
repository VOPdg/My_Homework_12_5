package guru.qa.test.pages.components;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class ColendarConponent {
    static String[] suffixes =
            {"0th", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th",
                    "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
                    "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
                    "30th", "31st"};

    public void setDateOfBirth(Date date) {
        $("[id=dateOfBirthInput]").click();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String dayStr = suffixes[day];
        int year = calendar.get(Calendar.YEAR);
        String monthStr = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        $("[class=react-datepicker__month-select]").selectOption(monthStr);
        $("[class=react-datepicker__year-select]").selectOption(String.valueOf(year));
        String selector = String.format("[aria-label='Choose %s, %s %s, %s']", calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH), monthStr, dayStr, year);
        $(selector).click();
    }
}
