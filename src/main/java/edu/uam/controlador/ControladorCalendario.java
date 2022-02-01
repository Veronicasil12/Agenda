package edu.uam.controlador;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class ControladorCalendario {
    private ControladorCitas appointment = new ControladorCitas();

    
    public ArrayList<ControladorCitasBD> getAppointments(Date date) {//Obtener
        return appointment.getAppointments(date);
    }

    
    public boolean addAppointment(Date date, String title, String description, String location, Time startTime, Time endTime) {
        return appointment.agregacita(date, title, description, location, startTime, endTime);//Agrega
    }

   
    public boolean deleteAppointment(Integer appointmentId) {
        return appointment.borrarcita(appointmentId);//Borra
    }
}
