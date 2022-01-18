package month;

import java.text.DateFormatSymbols;

/**
 *  
 *
 *  
 */
public class MonthTest {
    /**
     * Gets to month name.
     * @return the month name as a string
     */
    public String getMonthName() {
        Integer month = 11;
        String monthName = new DateFormatSymbols().getMonths()[month];
        System.out.println(monthName);
        return monthName;
    }
}
