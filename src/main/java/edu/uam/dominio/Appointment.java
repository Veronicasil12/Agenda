package edu.uam.dominio;

import java.util.Date;
import java.sql.Time;

/**
 * The class <code>Appointent</code> represents an appointment.
 * They are served via the <code>CalendarManager</code>.
 * @version 1.0
 * @see Category
 * @see edu.uam.logicanegocio.CalendarManager
 */

public class Appointment {//duda
    public Integer appointmentId;
    public String title, description, location;
    public Date date;
    public Time startTime, endTime;

    /**
     * Constructor. Sets the given variables/
     */
    public Appointment(Integer appointmentId, String title, String description, String location, Date date, Time startTime, Time endTime) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
