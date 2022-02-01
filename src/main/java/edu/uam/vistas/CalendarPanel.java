package edu.uam.vistas;
import javax.swing.*;

import edu.uam.controlador.CCalendario;


public class CalendarPanel extends JPanel {
	private static final long serialVersionUID = 1L;
    private Integer calendarPanelWidth, calendarPanelHeight;
    private MainFrame mainFrame; // for now private
    public MainPanel mainPanel;
    public MonthPanel monthPanel;
    public CCalendario calendar;

    public CalendarPanel(MainPanel mainPanel) {
        mainFrame = mainPanel.mainFrame;
        this.mainPanel = mainPanel;
        setCalendarPanelDimensions();
        initCalendarPanel();
    }

    
    private void initCalendarPanel() {
        setLayout(null);
        setCalendarPanelBounds();
        drawMonthPanel();
    }

    
    public void resizeCalendarPanel() {
        setCalendarPanelDimensions();
        setCalendarPanelBounds();
        monthPanel.resizeMonthPanel();
    }

    
    private void setCalendarPanelDimensions() {
        calendarPanelWidth = mainFrame.getMainFrameWidth();
        calendarPanelHeight = mainFrame.getMainFrameHeight() - mainPanel.getTopPanelHeight();
    }

    
    private void setCalendarPanelBounds() {
        setBounds(0, mainPanel.getTopPanelHeight(), calendarPanelWidth, calendarPanelHeight);
    }

    
    public Integer getCalendarPanelWidth() {
        return calendarPanelWidth;
    }

    
    public Integer getCalendarPanelHeight() {
        return calendarPanelHeight;
    }

    
    public void drawMonthPanel() {
        monthPanel = new MonthPanel(CalendarPanel.this);
        add(monthPanel);
    }
}
