package edu.uam.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import edu.uam.vistas.MainFrame;

/**
 * The class <code>Main</code> is the main startup class of the calendar application.
 
 * @version 1.0
 */
public class Main {
    /**
     * Defaults constructor. Inits the main frame
     * @param args
     */
    public static void main(String[] args) {
    	seleccionarGUI ();
        MainFrame mainFrame = new MainFrame();
        mainFrame.init();
    }
    private static void seleccionarGUI ()
	{
		String temaDelSistema = null ;
		
		String temaWindows = null ;
		String temaGTK = null ;
		String temaDefault = null ;
		
		UIManager.LookAndFeelInfo [] lf = UIManager.getInstalledLookAndFeels() ;
		
		for (UIManager.LookAndFeelInfo tema : lf )
		{
			if ( tema.getClassName().equals ( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" ) )
			{
				temaWindows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" ;
			}
/*			
			else if ()
			{
				temaMac = "" ;
			}
*/			
			else if ( tema.getClassName().equals ( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" ) )
			{
				temaGTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" ;
			}
			
			else
			{
				temaDefault = "javax.swing.plaf.metal.MetalLookAndFeel" ;
			}
		}

		if ( temaGTK != null )
			temaDelSistema = temaGTK ;
		
		else if ( temaWindows != null )
			temaDelSistema = temaWindows ;
		
		else
			temaDelSistema = temaDefault ;
	
		try
		{

			UIManager.setLookAndFeel( temaDelSistema );
		}
		
		catch ( Exception e)
		{
			System.out.println ( "Sin soporte. error en la version del tema" ) ;
		}
	}  
    
    
}
