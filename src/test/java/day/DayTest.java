package day;

/**
  */
public class DayTest {
    /**
     * Sets the active day.
     */
    public void setActiveDay() {
        Integer activeDay = 62;
        Integer monthDaysCount = 31;
        if (activeDay > monthDaysCount) {
            activeDay = monthDaysCount;
        }
        if (activeDay < 1) {
            activeDay = 1;
        }

        System.out.println(activeDay);
    }
}
