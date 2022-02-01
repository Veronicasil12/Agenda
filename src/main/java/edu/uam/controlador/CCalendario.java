package edu.uam.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CCalendario {
    public CYear year;
    public CMes month;
    public CSemana week;
    public CDia day;

    /**
     * Constructor of the CCalendario object.
     */
    public CCalendario() {
        initCalendar();
    }

    
    void initCalendar() {
        year = new CYear();
        month = new CMes();
        week = new CSemana();
        day = new CDia();
    }

   
    public void toPrevMonth() {//Verifica cual es el mes anterior y lo activa 
        if (month.getActiveMonth() == 0) {
            // el mes activo es enero, cree uno nuevo en dicienbre 
            month.setActiveMonth(11);
            // Establecer año nuevo 
            year.setActiveYear(year.getPreviousYear());
        }
        else {
            // Etablecer el mes anterior como mes activo.
            month.setActiveMonth(month.getPreviousMonth());
        }

        day.setActiveDay(1, month.getDayCount(month.getActiveMonth(), year.getActiveYear()));
        week.setActiveWeek(week.getWeekNumber(getDate(month.getActiveMonth(), day.getActiveDay(), year.getActiveYear())));

        day.setPreviousDay();
        day.setNextDay();
        week.setPreviousWeek();
        week.setNextWeek();
        month.setPreviousMonth();
        month.setNextMonth();
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by moving to the current month.
     */
    public void toCurrentMonth() {
        day.setActiveDay(1, month.getDayCount(month.getActiveMonth(), year.getActiveYear()));
        day.setPreviousDay();
        day.setNextDay();
        week.setActiveWeek(week.getCurrentWeek());
        week.setPreviousWeek();
        week.setNextWeek();
        month.setActiveMonth(month.getCurrentMonth());
        month.setPreviousMonth();
        month.setNextMonth();
        year.setActiveYear(year.getCurrentYear());
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by moving one month later.
     */
    public void toNextMonth() {
        if (month.getActiveMonth() == 11) {
            // active month is december, set new one to januari
            month.setActiveMonth(0);
            // set new year
            year.setActiveYear(year.getNextYear());
        }
        else {
            // set next month to active month
            month.setActiveMonth(month.getNextMonth());
        }

        day.setActiveDay(1, month.getDayCount(month.getActiveMonth(), year.getActiveYear()));
        week.setActiveWeek(week.getWeekNumber(getDate(month.getActiveMonth(), day.getActiveDay(), year.getActiveYear())));

        day.setPreviousDay();
        day.setNextDay();
        week.setPreviousWeek();
        week.setNextWeek();
        month.setPreviousMonth();
        month.setNextMonth();
        year.setPreviousYear();
        year.setNextYear();
    }

    /**
     * Updates the active date by overriding month, day, year and week.
     */
    public void toDate(Integer month, Integer day, Integer year) {
        if (month > 12) {
            month = 1;
        }

        this.year.setActiveYear(year);
        this.month.setActiveMonth(month-1); // zero based
        this.day.setActiveDay(day, this.month.getDayCount(month-1, year));
        this.week.setActiveWeek(this.week.getWeekNumber(getDate(month, day, year)));
        this.day.setPreviousDay();
        this.day.setNextDay();
        this.month.setPreviousMonth();
        this.month.setNextMonth();
        this.year.setPreviousYear();
        this.year.setNextYear();
        this.week.setPreviousWeek();
        this.week.setNextWeek();
    }

    /**
     * Returns the weeknumber based on a given date.
     * @param month the month of the given date
     * @param day the day of the given date
     * @param year the year of the given date
     * @return
     */
    public Date getDate(Integer month, Integer day, Integer year) {
        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        String dateString = (month+1)+"/"+day+"/"+year;
        Date date = new Date();

        try {
            date = formatter.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}