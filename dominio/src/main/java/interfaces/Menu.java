package interfaces;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

import javax.swing.ImageIcon;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Elegir Otro Mapa");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Harrington", Font.BOLD, 20));
		btnNewButton.setBounds(86, 76, 205, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cambiar de Personaje");
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Harrington", Font.BOLD, 20));
		btnNewButton_1.setBounds(63, 117, 250, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Harrington", Font.BOLD, 20));
		btnSalir.setBounds(142, 151, 89, 23);
		contentPane.add(btnSalir);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/interfaces/The Lord of Souls 3.png")));
		lblNewLabel.setBounds(0, 0, 396, 252);
		contentPane.add(lblNewLabel);
	}
}
