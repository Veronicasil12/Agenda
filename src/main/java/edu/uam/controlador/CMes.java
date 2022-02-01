package edu.uam.controlador;

import java.text.DateFormatSymbols;
import java.util.Calendar;


public class CMes {
    private Integer activeMonth, prevMonth, nextMonth, currentMonth; // for now private

    /**
     * Contstructor. Sets the global month-variables.
     */
    public CMes() {
        setMonths();
    }

    /**
     * Sets the global month-variables.
     */
    private void setMonths() {
        setCurrentMonth();
        setActiveMonth(currentMonth);
        setPreviousMonth();
        setNextMonth();
    }

    
    public Integer getPreviousMonth() {
        return prevMonth;
    }

   
    public Integer getNextMonth() {
        return nextMonth;
    }

    
    public Integer getCurrentMonth() {
        return currentMonth;
    }

    
    public Integer getActiveMonth() {
        return activeMonth;
    }

    
    public void setPreviousMonth() {
        if (activeMonth > 0) {
            prevMonth = activeMonth-1;
        }
        else {
            prevMonth = 11;
        }
    }

   
    public void setNextMonth() {
        if (activeMonth < 11) {
            nextMonth = activeMonth+1;
        }
        else {
            nextMonth = 0;
        }

    }

    
    public void setCurrentMonth() {
        currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    }

   
    public void setActiveMonth(Integer month) {
        if (month > 11) {
            activeMonth = 11;
        }
        else if (month < 0) {
            activeMonth = 0;
        }
        else {
            activeMonth = month;
        }
    }

    
    public String getMonthName(Integer month) {
        return new DateFormatSymbols().getMonths()[month];//Regresa el nombre del mes
    }

    
    public Integer getDayCount(Integer month, Integer year) {//Regresa cuantos dias tiene un mes
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    
    public Integer getFirstWeekDay(Integer month, Integer year) {//Regresa el primer dia de la semana
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
