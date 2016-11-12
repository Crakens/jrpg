package interfaces;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs.Cliente;
import peticiones.CodigoPeticion;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(32, 89, 82, 14);
		contentPane.add(lblNewLabel);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(136, 88, 151, 20);
		contentPane.add(usuarioField);
		usuarioField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Harrington", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(10, 143, 116, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnInicioSesion = new JButton("Iniciar Sesi\u00F3n");
		btnInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = usuarioField.getText(),
						password = passwordField.getPassword().toString();
				//System.out.println(Cliente.loguearse(usuario,password));
				Cliente cliente = new Cliente("192.168.56.1");
				if(cliente.loguearse(usuario, password) != CodigoPeticion.LOGEO_INCORRECTO ) {
						System.out.println("Logueo incorrecto");
				}
			}
		});
		btnInicioSesion.setFont(new Font("Harrington", Font.PLAIN, 13));
		btnInicioSesion.setBackground(Color.WHITE);
		btnInicioSesion.setForeground(Color.BLUE);
		btnInicioSesion.setBounds(159, 185, 115, 23);
		contentPane.add(btnInicioSesion);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 142, 151, 20);
		contentPane.add(passwordField);
		
		JButton btnNewUsuario = new JButton("Crear Usuario");
		btnNewUsuario.setFont(new Font("Harrington", Font.PLAIN, 13));
		btnNewUsuario.setForeground(Color.BLUE);
		btnNewUsuario.setBackground(Color.WHITE);
		btnNewUsuario.setBounds(159, 219, 115, 23);
		contentPane.add(btnNewUsuario);
		
		JLabel label =new JLabel(""); 
		label.setIcon(new ImageIcon(Login.class.getResource("/interfaces/The Lord of Souls 3.png")));
		label.setBounds(0, 0, 434, 262);
		contentPane.add(label);
	}
}

