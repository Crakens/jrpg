package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CrearUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearUsuario frame = new CrearUsuario();
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
	public CrearUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\javi\\workspace\\JuegoTaller\\src\\interfaces\\icono.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		textField = new JTextField();
		textField.setBounds(229, 72, 182, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(229, 103, 182, 20);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(229, 130, 182, 20);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(229, 166, 182, 20);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("Confirmar");
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
	}
}
