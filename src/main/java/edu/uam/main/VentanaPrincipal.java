package edu.uam.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.tools.javac.Main;

import edu.uam.vistas.MainFrame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField Entrada_Usuario;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(177, 11, 55, 14);
		frame.getContentPane().add(lblNewLabel);
		
		Entrada_Usuario = new JTextField();
		Entrada_Usuario.setBounds(114, 36, 213, 20);
		frame.getContentPane().add(Entrada_Usuario);
		Entrada_Usuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setBounds(177, 80, 86, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(114, 113, 213, 20);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user= "Veronica";
				String pass= "veronica12";
			
				if(password.getText().toUpperCase().equals(pass.toUpperCase()) && Entrada_Usuario.getText().equals(user)) {
					edu.uam.main.Main.seleccionarGUI();
					MainFrame mainFrame = new MainFrame();
			        mainFrame.init();
			        frame.show(false);
				}
				else {
					JOptionPane.showMessageDialog(null,
			                
			                "Contraseña incorrecta",
			                "Error",
			                JOptionPane.INFORMATION_MESSAGE);
			    }

			}
		});
		btnNewButton.setBounds(177, 177, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
