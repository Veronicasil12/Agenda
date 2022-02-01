package edu.uam.controlador;

import java.util.Calendar;


public class CYear {
    private Integer activeYear, prevYear, nextYear, currentYear; // for now private

    
    public CYear() {
        setYears();
    }

    
    private void setYears() {
        setCurrentYear();
        setActiveYear(getCurrentYear());
        setPreviousYear();
        setNextYear();
    }

   
    public Integer getPreviousYear() {
        return prevYear;
    }

    
    public Integer getNextYear() {
        return nextYear;
    }

    
    public Integer getCurrentYear() {
        return currentYear;
    }

    
    public Integer getActiveYear() {
        return activeYear;
    }

    
    public void setActiveYear(Integer year) {
        activeYear = year;
    }

   
    public void setPreviousYear() {
        prevYear = activeYear-1;
    }

   
    public void setNextYear() {
        nextYear = activeYear+1;
    }

    
    public void setCurrentYear() {
        currentYear = Calendar.getInstance().get(Calendar.YEAR);//Regresa el año actual 
    }

}
