package principal.interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import principal.peticiones.CodigoPeticion;
import principal.peticiones.PeticionRegistro;
import principal.cs.Cliente;

public class Registro extends JFrame {

	private JPanel contentPane;
	private JTextField usuarioField;
	private JTextField emailField;
	private JPasswordField passwordField;
	private JPasswordField password2Field;
	private Login ventLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro(new Login());
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Se interrumpio el proceso del Registro","Error durante el Registro", JOptionPane. ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Registro(Login ventL) {
		ventLogin = ventL;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\javi\\workspace\\JuegoTaller\\src\\interfaces\\icono.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCreaTuUsuario = new JLabel("Crea tu usuario Para The Lord of Souls");
		lblCreaTuUsuario.setForeground(Color.BLUE);
		lblCreaTuUsuario.setFont(new Font("Harrington", Font.BOLD, 20));
		lblCreaTuUsuario.setBounds(21, 11, 387, 50);
		contentPane.add(lblCreaTuUsuario);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario ");
		lblNombreDeUsuario.setFont(new Font("Harrington", Font.BOLD, 15));
		lblNombreDeUsuario.setForeground(Color.BLUE);
		lblNombreDeUsuario.setBounds(71, 63, 148, 34);
		contentPane.add(lblNombreDeUsuario);
		
		usuarioField = new JTextField();
		usuarioField.setBounds(229, 72, 182, 20);
		contentPane.add(usuarioField);
		usuarioField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(229, 166, 182, 20);
		contentPane.add(emailField);
		
		JButton btnNewButton = new JButton("Confirmar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String usuario = usuarioField.getText();
				char[] password = passwordField.getPassword();
				char[] password2 = password2Field.getPassword();
				String email = emailField.getText();
				if(new String(password).equals(new String(password2))){
					PeticionRegistro petReg = new PeticionRegistro(usuario,password,email);
					int respuesta = ventLogin.getCliente().registrarse(petReg);
					if(respuesta == CodigoPeticion.REGISTRO_CORRECTO) {
						JOptionPane.showMessageDialog(null, "Registrado correctamente.");
						dispose();
					}
					else{
						if(respuesta == CodigoPeticion.REGISTRO_INCORRECTO_USER_YA_EXISTE)
							JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese nombre, elegí otro.");
						else
							JOptionPane.showMessageDialog(null, "Registro fallido.");
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Passwords no coinciden, aprendé a escribir!");
				}
			}
		});
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Harrington", Font.PLAIN, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(171, 199, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setFont(new Font("Harrington", Font.BOLD, 15));
		lblEmail.setBounds(161, 160, 55, 28);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Harrington", Font.BOLD, 15));
		lblPassword.setBounds(126, 95, 93, 34);
		contentPane.add(lblPassword);
		
		JLabel lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasea.setForeground(Color.BLUE);
		lblConfirmarContrasea.setFont(new Font("Harrington", Font.BOLD, 15));
		lblConfirmarContrasea.setBounds(47, 124, 172, 28);
		contentPane.add(lblConfirmarContrasea);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(".\\src\\interfaces\\pergamino.png"));
		label.setBounds(0, 0, 434, 262);
		contentPane.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(229, 104, 179, 20);
		contentPane.add(passwordField);
		
		password2Field = new JPasswordField();
		password2Field.setBounds(229, 130, 182, 20);
		contentPane.add(password2Field);
	}
	
	public void dispose() {				//cuando cierro el registro me habilita el botón para abrir la ventana de registro en el Login
	    ventLogin.toggleBtnNewUsuario();
	    super.dispose();
	}
}
