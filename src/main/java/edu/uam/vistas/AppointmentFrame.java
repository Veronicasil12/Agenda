package edu.uam.vistas;

import javax.swing.*;

/**
 * The <code>AppointmentFrame</code> ensures the window of the popup used to add an appointment.
 * It is called by <code>DayPanel</code>.
 *
 * @version 1.0
 * @see DayPanel
 */
public class AppointmentFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;//duda todo
	public Integer frameWidth = 292, frameHeight = 352;
    private AppointmentPanel appointmentPanel; // for now private
    private CalendarPanel calendarPanel;
    private Integer month, day, year;

    /**
     * Constructor. Calls the initialization of the frame.
     */
    public AppointmentFrame(Integer month, Integer day, Integer year, CalendarPanel calendarPanel, Integer offsetX, Integer offsetY) {//duda
        this.month = month;
        this.day = day;
        this.year = year;
        this.calendarPanel = calendarPanel;
        initFrame(offsetX, offsetY);
    }

    /**
     * Inits the frame.
     */
    private void initFrame(Integer offsetX, Integer offsetY){//duda
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
