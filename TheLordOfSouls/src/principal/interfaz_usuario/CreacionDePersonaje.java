package principal.interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.cs.Cliente;
import principal.entes.personajes.*;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreacionDePersonaje extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private final JLabel lblNewLabel_1 = new JLabel("");
	private JLabel lblNewLabel;
	private JComboBox seleccionaRaza;
	private JComboBox seleccionaCasta;
	private JComboBox seleccionaSexo;
	private Personaje p;
	private Especialidad c;

	public CreacionDePersonaje(final Cliente cliente) {
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Nombre del Personaje");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Harrington", Font.PLAIN, 14));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 115, 138, 14);
		lblNewLabel.setToolTipText("hola");
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Harrington", Font.BOLD, 15));
		textField.setBounds(160, 110, 186, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		seleccionaRaza = new JComboBox();
		seleccionaRaza.setBackground(Color.WHITE);
		seleccionaRaza.setForeground(Color.RED);
		seleccionaRaza.setToolTipText("");
		seleccionaRaza.setModel(new DefaultComboBoxModel(new String[] {"Humano", "Elfo", "Orco"}));
		seleccionaRaza.setFont(new Font("Harrington", Font.PLAIN, 13));
		seleccionaRaza.setMaximumRowCount(3);
		seleccionaRaza.setBounds(55, 73, 73, 20);
		contentPane.add(seleccionaRaza);
		
		seleccionaCasta = new JComboBox();
		seleccionaCasta.setBackground(Color.WHITE);
		seleccionaCasta.setForeground(Color.RED);
		seleccionaCasta.setFont(new Font("Harrington", Font.PLAIN, 13));
		seleccionaCasta.setModel(new DefaultComboBoxModel(new String[] {"Guerrero", "Ladron", "Hechicero"}));
		seleccionaCasta.setMaximumRowCount(3);
		seleccionaCasta.setBounds(185, 73, 97, 20);
		contentPane.add(seleccionaCasta);
		
		seleccionaSexo = new JComboBox();
		seleccionaSexo.setBackground(Color.WHITE);
		seleccionaSexo.setForeground(Color.RED);
		seleccionaSexo.setFont(new Font("Harrington", Font.PLAIN, 13));
		seleccionaSexo.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
		seleccionaSexo.setMaximumRowCount(2);
		seleccionaSexo.setBounds(332, 73, 73, 20);
		contentPane.add(seleccionaSexo);
		
		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setForeground(Color.BLUE);
		lblRaza.setFont(new Font("Harrington", Font.PLAIN, 14));
		lblRaza.setBackground(Color.WHITE);
		lblRaza.setBounds(10, 76, 35, 14);
		contentPane.add(lblRaza);
		
		JLabel lblCasta = new JLabel("Casta");
		lblCasta.setToolTipText("hola");
		lblCasta.setForeground(Color.BLUE);
		lblCasta.setFont(new Font("Harrington", Font.PLAIN, 14));
		lblCasta.setBackground(Color.WHITE);
		lblCasta.setBounds(143, 76, 35, 14);
		contentPane.add(lblCasta);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setToolTipText("hola");
		lblSexo.setForeground(Color.BLUE);
		lblSexo.setFont(new Font("Harrington", Font.PLAIN, 14));
		lblSexo.setBackground(Color.WHITE);
		lblSexo.setBounds(299, 76, 35, 14);
		contentPane.add(lblSexo);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object raza=seleccionaRaza.getSelectedItem();
				Object casta=seleccionaCasta.getSelectedItem();
				Object sexo=seleccionaSexo.getSelectedItem();
				String cadena;
				
				switch ((cadena=String.valueOf(raza))) {
						
				case "Orco":
				{
					p=new Orco(String.valueOf(sexo));	
					p.setNombrePersonaje(textField.getText());
					
					switch ((cadena=String.valueOf(casta))) {
						
					case "Hechicero":
					{
						c=new Hechicero();
						
						p.setCasta(c);
						p.getCasta().getHechicero().agregarHechizo("Engorgio", new Engorgio());
						
						break;
						
					}
							
					case "Ladron":
					{
						c=new Ladron();
						
						p.setCasta(c);
								
						break;
					}
							
					case "Guerrero":
					{
						c=new Guerrero();
						
						p.setCasta(c);
						
						break;
					}
					
					}
					
					break;
				}
							
				case "Humano":
				{
					p=new Humano(String.valueOf(sexo));
					p.setNombrePersonaje(textField.getText());
					switch ((cadena=String.valueOf(casta))) {
					
					case "Hechicero":
					{
						c=new Hechicero();
								
						p.setCasta(c);
						p.getCasta().getHechicero().agregarHechizo("Engorgio", new Engorgio());		
						break;
					}
							
					case "Ladron":
					{
						c=new Ladron();
						
						p.setCasta(c);
								
						break;
					}
							
					case "Guerrero":
					{
						c=new Guerrero();
						
						p.setCasta(c);
								
						break;
					}
					
					}
					
					break;
				}
					
				case "Elfo":
				{
					p=new Elfo(String.valueOf(sexo));
					p.setNombrePersonaje(textField.getText());
					
					switch ((cadena=String.valueOf(casta))) {
					
					case "Hechicero":
					{
						c=new Hechicero();
							
						p.setCasta(c);
						p.getCasta().getHechicero().agregarHechizo("Engorgio", new Engorgio());
						break;
					}
							
					case "Ladron":
					{
						c=new Ladron();
						
						p.setCasta(c);
						
						break;
					}
							
					case "Guerrero":
					{
						c=new Guerrero();
						
						p.setCasta(c);
						
						break;
					}
					
					}
			
					break;
				}
				}
				
				p.bonificacionDeCasta();
				ElementosPrincipales.crearJugador(p);
				dispose();
				ElegirMapa elegir = new ElegirMapa(cliente);
			}
			
				
		});
		
		btnConfirmar.setForeground(Color.BLUE);
		btnConfirmar.setFont(new Font("Harrington", Font.PLAIN, 13));
		btnConfirmar.setBackground(Color.WHITE);
		btnConfirmar.setBounds(163, 228, 105, 23);
		contentPane.add(btnConfirmar);
		
		JLabel lblCreaTuPersonaje = new JLabel("Crea Tu Personaje");
		lblCreaTuPersonaje.setForeground(Color.BLUE);
		lblCreaTuPersonaje.setFont(new Font("Harrington", Font.BOLD, 25));
		lblCreaTuPersonaje.setBounds(108, 32, 226, 30);
		contentPane.add(lblCreaTuPersonaje);
		lblNewLabel_1.setBackground(Color.BLACK);
		lblNewLabel_1.setIcon(new ImageIcon(CreacionDePersonaje.class.getResource(Constantes.RUTA_SELECCION_PERSONAJE)));
		lblNewLabel_1.setBounds(0, 0, 434, 262);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
