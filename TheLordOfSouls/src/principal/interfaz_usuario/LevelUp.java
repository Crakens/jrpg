package principal.interfaz_usuario;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Constantes;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Orco;
import principal.entes.personajes.Personaje;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LevelUp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2207037128697060019L;
	private JPanel contentPane;
	private JLabel lblfondo;
	private JLabel lblAtributoFuerza;
	private JLabel lblPuntos;
	private JLabel lblAtributosEnergiaTotal;
	private JLabel lblAtributosSaludTotal;
	private JLabel lblAtributosAgilidad;
	private JLabel lblAtributosDefensa;
	private Personaje per;
	private int puntos=5;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personaje p=new Orco("Hombre");
					Especialidad c=new Guerrero();
					p.setCasta(c);
					p.bonificacionDeCasta();
					
					LevelUp frame = new LevelUp(p);
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
	public LevelUp(Personaje p) {
		per=p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnFuerza = new JButton("+");
		btnFuerza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				per.incrementarFuerza();
				puntos--;
				lblPuntos.setText("Te quedan " + puntos + " puntos para distribuir ");
				lblAtributoFuerza.setText("" + per.getFuerza());
			}
		});
		btnFuerza.setBackground(Color.WHITE);
		btnFuerza.setBounds(116, 103, 41, 23);
		contentPane.add(btnFuerza);
		
		JButton btnDefensa = new JButton("+");
		btnDefensa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				per.incrementarDefensa();
				puntos--;
				lblPuntos.setText("Te quedan " + puntos + " puntos para distribuir ");
				lblAtributosDefensa.setText("" + per.getDefensa());
			}
		});
		btnDefensa.setBackground(Color.WHITE);
		btnDefensa.setBounds(116, 137, 41, 23);
		contentPane.add(btnDefensa);
		
		JButton btnAgilidad = new JButton("+");
		btnAgilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				per.incrementarAgilidad();
				puntos--;
				lblPuntos.setText("Te quedan " + puntos + " puntos para distribuir ");
				lblAtributosAgilidad.setText("" + per.getAgilidad());
			}
		});
		btnAgilidad.setBackground(Color.WHITE);
		btnAgilidad.setBounds(116, 171, 41, 23);
		contentPane.add(btnAgilidad);
		
		JButton btnSaludTotal = new JButton("+");
		btnSaludTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				per.incrementarSaludTotal();
				puntos--;
				lblPuntos.setText("Te quedan " + puntos + " puntos para distribuir ");
				lblAtributosSaludTotal.setText("" + per.getSaludTotal());
			}
		});
		btnSaludTotal.setBackground(Color.WHITE);
		btnSaludTotal.setBounds(337, 103, 41, 23);
		contentPane.add(btnSaludTotal);
		
		JButton bntEnergiaTotal = new JButton("+");
		bntEnergiaTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				per.incrementarEnergiaTotal();
				puntos--;
				lblPuntos.setText("Te quedan " + puntos + " puntos para distribuir ");
				lblAtributosEnergiaTotal.setText("" + per.getEnergiaTot());
			}
		});
		bntEnergiaTotal.setBackground(Color.WHITE);
		bntEnergiaTotal.setBounds(337, 137, 41, 23);
		contentPane.add(bntEnergiaTotal);
		
		lblAtributoFuerza = new JLabel("" + per.getFuerza());
		lblAtributoFuerza.setForeground(Color.RED);
		lblAtributoFuerza.setFont(new Font("Harrington", Font.BOLD, 15));
		lblAtributoFuerza.setBounds(167, 105, 46, 14);
		contentPane.add(lblAtributoFuerza);
		
		lblPuntos = new JLabel("Te quedan 5 puntos para distribuir ");
		lblPuntos.setForeground(Color.BLUE);
		lblPuntos.setFont(new Font("Harrington", Font.BOLD, 18));
		lblPuntos.setBounds(68, 217, 361, 14);
		contentPane.add(lblPuntos);
		
		JLabel lblTitulo = new JLabel("\u00A1\u00A1Haz Subido de nivel!!");
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setFont(new Font("Harrington", Font.BOLD, 24));
		lblTitulo.setBounds(97, 11, 267, 31);
		contentPane.add(lblTitulo);
		
		lblAtributosEnergiaTotal = new JLabel("" + per.getEnergiaTot());
		lblAtributosEnergiaTotal.setForeground(Color.RED);
		lblAtributosEnergiaTotal.setFont(new Font("Harrington", Font.BOLD, 15));
		lblAtributosEnergiaTotal.setBounds(383, 139, 46, 14);
		contentPane.add(lblAtributosEnergiaTotal);
		
		lblAtributosSaludTotal = new JLabel("" + per.getSaludTotal());
		lblAtributosSaludTotal.setForeground(Color.RED);
		lblAtributosSaludTotal.setFont(new Font("Harrington", Font.BOLD, 15));
		lblAtributosSaludTotal.setBounds(383, 105, 46, 14);
		contentPane.add(lblAtributosSaludTotal);
		
		lblAtributosAgilidad = new JLabel("" + per.getAgilidad());
		lblAtributosAgilidad.setForeground(Color.RED);
		lblAtributosAgilidad.setFont(new Font("Harrington", Font.BOLD, 15));
		lblAtributosAgilidad.setBounds(167, 173, 46, 14);
		contentPane.add(lblAtributosAgilidad);
		
		lblAtributosDefensa = new JLabel("" + per.getDefensa());
		lblAtributosDefensa.setForeground(Color.RED);
		lblAtributosDefensa.setFont(new Font("Harrington", Font.BOLD, 15));
		lblAtributosDefensa.setBounds(167, 139, 46, 14);
		contentPane.add(lblAtributosDefensa);
		
		JLabel lblFuerza = new JLabel("Fuerza");
		lblFuerza.setVerticalAlignment(SwingConstants.TOP);
		lblFuerza.setForeground(Color.BLUE);
		lblFuerza.setFont(new Font("Harrington", Font.BOLD, 15));
		lblFuerza.setHorizontalAlignment(SwingConstants.LEFT);
		lblFuerza.setBounds(42, 103, 64, 23);
		contentPane.add(lblFuerza);
		
		JLabel lblDefensa = new JLabel("Defensa");
		lblDefensa.setVerticalAlignment(SwingConstants.TOP);
		lblDefensa.setHorizontalAlignment(SwingConstants.LEFT);
		lblDefensa.setForeground(Color.BLUE);
		lblDefensa.setFont(new Font("Harrington", Font.BOLD, 15));
		lblDefensa.setBounds(42, 137, 64, 23);
		contentPane.add(lblDefensa);
		
		JLabel lblAgilidad = new JLabel("Agilidad");
		lblAgilidad.setVerticalAlignment(SwingConstants.TOP);
		lblAgilidad.setHorizontalAlignment(SwingConstants.LEFT);
		lblAgilidad.setForeground(Color.BLUE);
		lblAgilidad.setFont(new Font("Harrington", Font.BOLD, 15));
		lblAgilidad.setBounds(42, 171, 64, 23);
		contentPane.add(lblAgilidad);
		
		JLabel lblSaludTotal = new JLabel("Salud Total");
		lblSaludTotal.setVerticalAlignment(SwingConstants.TOP);
		lblSaludTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaludTotal.setForeground(Color.BLUE);
		lblSaludTotal.setFont(new Font("Harrington", Font.BOLD, 15));
		lblSaludTotal.setBounds(234, 103, 93, 23);
		contentPane.add(lblSaludTotal);
		
		JLabel lblEnergiaTotal = new JLabel("Energia Total");
		lblEnergiaTotal.setVerticalAlignment(SwingConstants.TOP);
		lblEnergiaTotal.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnergiaTotal.setForeground(Color.BLUE);
		lblEnergiaTotal.setFont(new Font("Harrington", Font.BOLD, 15));
		lblEnergiaTotal.setBounds(213, 137, 114, 23);
		contentPane.add(lblEnergiaTotal);
		
		JLabel lblTitulo2 = new JLabel("Selecciona que atributos mejorar");
		lblTitulo2.setForeground(Color.RED);
		lblTitulo2.setFont(new Font("Harrington", Font.BOLD, 18));
		lblTitulo2.setBounds(70, 53, 308, 23);
		contentPane.add(lblTitulo2);
		
		lblfondo = new JLabel("");
		lblfondo.setFont(new Font("Harrington", Font.BOLD, 15));
		lblfondo.setIcon(new ImageIcon(SeleccionarPartida.class.getResource(Constantes.RUTA_SELECCION_PERSONAJE)));
		lblfondo.setBounds(5, 5, 424, 252);
		contentPane.add(lblfondo);
	}
	
	public boolean puntosDistribuidos(){
		return puntos<=0;
	}
}
