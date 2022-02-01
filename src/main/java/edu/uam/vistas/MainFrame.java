package edu.uam.vistas;

import edu.uam.controlador.CCalendario;
import edu.uam.controlador.ControladorCalendario;

import javax.swing.*; 
import java.awt.*;


public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
    public Integer frameWidth, frameHeight;
    public MainPanel mainPanel;
    public CCalendario calendar;
    public ControladorCalendario manager;

    
    public MainFrame() {
        calendar = new CCalendario();
        manager = new ControladorCalendario();
    
    }
    /*
     * Inicializa el frame
     */
    public void init() {
        initFrame();
    }
    
    private void initFrame(){
        new JFrame();
        setFrameDimension(false);
        setTitle("Java Calendar");
        //setSize(frameWidth,frameHeight);
        setSize(1200,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));

        // add content to frame
        mainPanel = new MainPanel(MainFrame.this);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);

        // add menubar
        //setJMenuBar(getCustomMenuBar());

        setResizable(false);
        pack();
        setVisible(true);
    }

    
    public Integer getMainFrameHeight() {
        return frameHeight;
    }

    
    public Integer getMainFrameWidth() {
        return frameWidth;
    }

    
    public void setFrameDimension(boolean resized) {
        if (resized) {
            // window is being resized
            Dimension windowSize = getBounds().getSize();
            frameWidth = (int) windowSize.getWidth();
            frameHeight = (int) windowSize.getHeight();
        }
        else {
            // first time startup
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            frameWidth = (int) screenSize.getWidth();
            frameHeight = (int) screenSize.getHeight();
        }
    }

   
}