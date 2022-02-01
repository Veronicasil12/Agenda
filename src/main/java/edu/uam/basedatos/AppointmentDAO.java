package edu.uam.basedatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;

import edu.uam.dominio.Appointment;

/**
 * <code>AppointmentDAO</code> handles database requests that are related to <code>Appointment</code>.
 * It is called by the <code>CalendarManager</code>.

 * @see edu.uam.logicanegocio.CalendarManager
 */
public class AppointmentDAO {
    private DatabaseConnection connection = new DatabaseConnection("jdbc:mysql://127.0.0.1/mi_agenda", "root","");//conexion a la base de datos
    /**
     * Gets all appointments of a given date.
     * @param date the date the appointments needs to be retrieved from
     */
    public ArrayList<Appointment> getAppointments(Date date) {//Forma de la fecha 
        ArrayList<Appointment> appointments = new ArrayList<Appointment>();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        if(date != null) {
            // Primero abrimos la conexion a la base de datos
            if (connection.open()) {
                // Si una conexion se configuro con exito. Ejecutar Select*
                ResultSet resultset = connection.executeQuery(
                        "SELECT * FROM appointment WHERE date = '" + dateString + "' ORDER BY startTime;");//duda

                if (resultset != null) {
                    try {
                        while (resultset.next()) {//duda
                            // obtener campos
                            Integer appointmentId = resultset.getInt("id");
                            String title = resultset.getString("title");
                            String description = resultset.getString("description");
                            String location = resultset.getString("location");
                            Time startTime = resultset.getTime("startTime");
                            Time endTime = resultset.getTime("endTime");

                            // Añadir cita a la lista 
                            Appointment appointment = new Appointment(appointmentId, title, description, location, date, startTime, endTime);
                            appointments.add(appointment);
                        }
                    } catch (SQLException e) {//problema con la base de datos 
                        System.out.println(e);
                    }
                }

                // We had a database connection opened. Since we're finished, duda
                // we need to close it.
                connection.close();
            }
        }

        return appointments;
    }

    /**
     * Inserts a new appointment in the database.
     * @param date the date the appointment needs to be added to
     * @param title the title of the appointment
     * @param location the location of the appointment
     * @param description a description of the appointment
     * @param startTime the starttime of the appointment
     * @param endTime the endtime of the appointment
     */
    public Boolean addAppointment(Date date, String title, String description, String location, Time startTime, Time endTime) {
        List<Integer> resultIds = new ArrayList<Integer>();

        if (date != null && title != null && date != null && startTime != null && endTime != null) {
            // primero se abre la conexion a la base de datos 
            if (connection.open()) {
                // si una conexion se configuro con exito. Ejecute la declaracion
                resultIds = connection.executePrepared("INSERT INTO appointment (title, description, location, date, startTime, endTime) VALUES(?,?,?,?,?,?);",
                        title,description,location,date,startTime,endTime);
            }

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.close();
        }
        return resultIds.isEmpty();
    }

    /**
     * Deletes an appointment from the database.
     * @param appointmentId the id of the appointment.
     */
    
    public boolean deleteAppointment(Integer appointmentId) {
        boolean result = false;
        if (appointmentId != null) {
            // Primero abra una conexion de base datos
            if (connection.open()) {
                // si una conexion se configuro con exito. Ejecute la declaracion
                result = connection.execute("DELETE FROM appointment WHERE id = '"+appointmentId+"';");
            }

            // We had a database connection opened. Since we're finished,
            // we need to close it.
            connection.close();
        }
        return result;
    }
}
