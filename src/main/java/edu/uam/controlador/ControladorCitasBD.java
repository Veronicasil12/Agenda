package edu.uam.controlador;

import java.util.Date;
import java.sql.Time;



public class ControladorCitasBD {
    public Integer appointmentId;
    public String title, description, location;
    public Date date;
    public Time startTime, endTime;

   
    public ControladorCitasBD(Integer appointmentId, String title, String description, String location, Date date, Time startTime, Time endTime) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
