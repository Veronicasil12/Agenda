package edu.uam.vistas;

import javax.swing.*;

import edu.uam.controlador.ControladorCalendario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class AppointmentPanel extends JPanel {

    private Date date;
    private JFrame appointmentFrame;
    private CalendarPanel calendarPanel;
    private JTextField nameTextField, locationTextField, startTimeTextField, endTimeTextField, notesTextField;
    private Time formattedStartTime, formattedEndTime;
    private ControladorCalendario manager = new ControladorCalendario();

    
    public AppointmentPanel(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, JFrame appointmentFrame) {
        this.calendarPanel = calendarPanel;
        this.appointmentFrame = appointmentFrame;
        this.date = calendarPanel.mainPanel.mainFrame.calendar.getDate(month, day, year);

        drawAppointmentPanel();
    }

   
    public void drawAppointmentPanel() {
        setLayout(new SpringLayout());
        String[] labels = {"Nombre", "Lugar", "Inicia", "Termina", "Nota", ""};
        int numPairs = labels.length;

        JButton saveButton = new JButton("Guardar");
        saveButton.setPreferredSize(new Dimension(200,40));
        saveButton.addActionListener(new saveAppointmentHandler());

        ArrayList<JTextField> textFieldList = listTextFields();

        // fill the panel
        for (int i = 0; i < numPairs; i++) {
            JLabel l = new JLabel(labels[i], JLabel.TRAILING);
            add(l);
            if (i+1 < numPairs) {
                add(textFieldList.get(i));
            }
            else {
                add(saveButton);
            }
        }

        // lay out the panel
        SpringUtilities.makeCompactGrid(this,//duda
            numPairs, 2, //rows, cols
            10, 10, //initX, initY
            10, 10 //xPad, yPad
        );
    }

    private ArrayList<JTextField> listTextFields() {//duda
        ArrayList<JTextField> textFieldList  = new ArrayList<>();
        textFieldList.add(nameTextField = new JTextField());
        textFieldList.add(locationTextField = new JTextField());
        textFieldList.add(startTimeTextField = new JTextField());
        textFieldList.add(endTimeTextField = new JTextField());
        textFieldList.add(notesTextField = new JTextField());

        return textFieldList;
    }

    
    private void showNameError() {//duda
       JOptionPane.showMessageDialog(null, "El nombre del evento debe ser completado.", "Nombre invalido", JOptionPane.ERROR_MESSAGE);
    }

    
    private void showTimeError() {
        JOptionPane.showMessageDialog(null,
               "El tiempo inicial o el tiempo final es invalido.\n" +
               "El formato permitido: (00 mediante 23) : (00 mediante 59).\n" +
               "La hora final debe der mayor que la hora de inicio.", "Tiempos invalidos",
       JOptionPane.ERROR_MESSAGE);
    }

    
    private void showSuccesMessage(String name) {
        JOptionPane.showMessageDialog(null, "El evento \""+name+"\" ha sido agregado con exito.", "Evento agregado", JOptionPane.PLAIN_MESSAGE);
    }

    
    private Boolean setFormattedTime(String time, Integer timeType) {//duda
        Boolean validated = true;
        Time formattedTime = new Time(new Date().getTime());

        // format time
        DateFormat formatter = new SimpleDateFormat("HH:mm");

        try {
            new SimpleDateFormat("HH:mm").parse(time);
            // good format
            formattedTime = new Time(formatter.parse(time).getTime());
        } catch (ParseException e) {
            // bad format
            validated = false;
        } finally {
            if (validated) {
                if (timeType == 0) {
                    // start time
                    formattedStartTime = formattedTime;
                }
                else if (timeType == 1){
                    // end time
                    formattedEndTime = formattedTime;
                }
            }
        }

        return validated;
    }

    
    class saveAppointmentHandler implements ActionListener {
        
        public void actionPerformed(ActionEvent e) {//duda como se usa 
            Boolean validName = true;
            Boolean validTimes = true;

            // get values
            String name = nameTextField.getText();
            String notes = notesTextField.getText();
            String location = locationTextField.getText();
            String startTime = startTimeTextField.getText().replaceAll("\\s+",""); // remove whitespace
            String endTime = endTimeTextField.getText().replaceAll("\\s+",""); // remove whitespace

            // fields to null of not filled in
            if (notes.isEmpty()) { notes = null; }
            if (location.isEmpty()) { location = null; }

            // validate name
            if (name == null || name.isEmpty()) {
                validName = false;
            }
            // validate times
            if (!setFormattedTime(startTime, 0) || !setFormattedTime(endTime, 1)) {
                validTimes = false;
            }
            if (validTimes) {
                // is end time greater then start time
                if (Integer.parseInt(startTime.replaceAll("[^\\d]","")) > Integer.parseInt(endTime.replaceAll("[^\\d]",""))) {
                    validTimes = false;
                }
            }

            if (validName && validTimes) {
                // add appointment
                manager.addAppointment(date, name, notes, location, formattedStartTime, formattedEndTime);
                // close frame
                appointmentFrame.setVisible(false);
                appointmentFrame.dispose();
                // repaint panels and show succes message
                calendarPanel.monthPanel.redrawMonthPanel();
                showSuccesMessage(name);
            }
            else {
                // show errors
                if(!validName) { showNameError(); }
                if(!validTimes) { showTimeError(); }
            }
        }
    }
}
