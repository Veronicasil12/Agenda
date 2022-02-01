package edu.uam.vistas;

import javax.swing.*;


public class AppointmentFrame extends JFrame {
    
	public Integer frameWidth = 292, frameHeight = 352;
    private AppointmentPanel appointmentPanel; // for now private
    private CalendarPanel calendarPanel;
    private Integer month, day, year;

    
    public AppointmentFrame(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, Integer offsetX, Integer offsetY) {//duda
        this.month = month;
        this.day = day;
        this.year = year;
        this.calendarPanel = calendarPanel;
        initFrame(offsetX, offsetY);
    }

    
    private void initFrame(Integer offsetX, Integer offsetY){
        new JFrame();
        setTitle("Añadir Evento - "+String.format("%02d",(month+1))+"/"+String.format("%02d",day)+"/"+year);
        setResizable(false);
        setSize(frameWidth,frameHeight);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(offsetX, offsetY);
        //setAlwaysOnTop(true);

        // add content to frame
        appointmentPanel = new AppointmentPanel(month, day, year, calendarPanel, this);
        setContentPane(appointmentPanel);
        pack();
        setVisible(true);
    }
}
