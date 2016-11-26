package principal.interfaz_usuario;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import principal.Constantes;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	private boolean cancelar=false;

	public Menu() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*JButton btnNewButton = new JButton("Elegir Otro Mapa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ElegirMapa();
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setFont(new Font("Harrington", Font.BOLD, 20));
		btnNewButton.setBounds(86, 76, 205, 23);
		contentPane.add(btnNewButton);
		*/
		/*
		JButton btnNewButton_1 = new JButton("Cambiar de Personaje");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SeleccionarPartida();
			}
		});
		btnNewButton_1.setForeground(Color.RED);
		btnNewButton_1.setFont(new Font("Harrington", Font.BOLD, 20));
		btnNewButton_1.setBounds(63, 117, 250, 23);
		contentPane.add(btnNewButton_1);
		*/
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setForeground(Color.RED);
		btnSalir.setFont(new Font("Harrington", Font.BOLD, 20));
		btnSalir.setBounds(142, 151, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setForeground(Color.RED);
		btnVolver.setFont(new Font("Harrington", Font.BOLD, 20));
		btnVolver.setBounds(130, 195, 118, 23);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource(Constantes.RUTA_MENU_LOGIN)));
		lblNewLabel.setBounds(0, 0, 396, 252);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
