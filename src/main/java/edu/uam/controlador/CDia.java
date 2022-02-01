package edu.uam.controlador;

import java.util.Calendar;


public class CDia {
    private Integer activeDay, prevDay, nextDay, currentDay;

    /**
     * Constructor. Sets the global day-variables.
     */
    public CDia() {
        setDays();
    }

    /**
     * Sets the global day-variables.
     */
    private void setDays() {
        setCurrentDay();
        setActiveDay(getCurrentDay(), null);
        setPreviousDay();
        setNextDay();
    }

    /**
     * Gets the previous day.
     * @return the previous day
     */
    public Integer getPreviousDay() {
        return prevDay;
    }

    /**
     * Gets the next day.
     * @return the next day
     */
    public Integer getNextDay() {
        return nextDay;
    }

    /**
     * Gets the current day.
     * @return the current day
     */
    public Integer getCurrentDay() {
        return currentDay;
    }

    /**
     * Gets the active day.
     * @return the active day
     */
    public Integer getActiveDay() {
        return activeDay;
    }

    
    public void setActiveDay(Integer day, Integer monthDays) {//Avisa cual es el activo 
        activeDay = day;
        if (monthDays != null) {
            if (activeDay > monthDays) {
                activeDay = monthDays;
            }
        }
        if (activeDay < 1) {
            activeDay = 1;
        }
    }

    /**
     * Sets the previous day, based on the active day.
     */
    public void setPreviousDay() {
        prevDay = activeDay-1;
    }

    /**
     * Sets the next daym based on the active day.
     */
    public void setNextDay() {
        nextDay = activeDay+1;
    }

    /**
     * Sets the current day.
     */
    public void setCurrentDay() {
        currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);//Regresa el dia actual 
    }

}
