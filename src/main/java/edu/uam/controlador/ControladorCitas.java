package edu.uam.controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.util.List;


public class ControladorCitas {
    private ConexionBaseDatos connection = new ConexionBaseDatos("jdbc:mysql://127.0.0.1/mi_agenda", "root","");//conexion a la base de datos
    
    public ArrayList<ControladorCitasBD> getAppointments(Date date) {//Forma de la fecha 
        ArrayList<ControladorCitasBD> controladorCitasBDs = new ArrayList<ControladorCitasBD>();
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);

        if(date != null) {
            // Primero abrimos la conexion a la base de datos
            if (connection.open()) {
                // Si una conexion se configuro con exito. Ejecutar Select*
                ResultSet resultset = connection.executeQuery( //Se obtienen todos los datos
                        "SELECT * FROM appointment WHERE date = '" + dateString + "' ORDER BY startTime;");//duda

                if (resultset != null) {
                    try {
                        while (resultset.next()) {
                            // obtener campos
                            Integer appointmentId = resultset.getInt("id");
                            String title = resultset.getString("title");
                            String description = resultset.getString("description");
                            String location = resultset.getString("location");
                            Time startTime = resultset.getTime("startTime");
                            Time endTime = resultset.getTime("endTime");

                            // Añadir cita a la lista 
                            ControladorCitasBD controladorCitasBD = new ControladorCitasBD(appointmentId, title, description, location, date, startTime, endTime);
                            controladorCitasBDs.add(controladorCitasBD);
                        }
                    } catch (SQLException e) {//problema con la base de datos 
                        System.out.println(e);
                    }
                }

              
                connection.close();
            }
        }

        return controladorCitasBDs;
    }

   
     
    public Boolean agregacita(Date date, String title, String description, String location, Time startTime, Time endTime) {
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

    
    public boolean borrarcita(Integer appointmentId) {
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
