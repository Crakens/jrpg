package principal.interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Constantes;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SeleccionarPartida extends JFrame implements ItemListener{

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnCargar;
	private JLabel lblSinDatos_1;
	private JLabel lblNombre;
	private JLabel lblNewLabel_1;
	private JLabel lblSinDatos_2;
	private JLabel lblMapa;
	private JLabel lblNewLabel;
	private String nombrePersonaje="MiPersonaje"; //esta variable deberia tomar el nombre del personaje desde la base de datos//
	private String mapa="Mapa"; //esta variable deberia tomar el nombre del mapa desde la base de datos//
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeleccionarPartida frame = new SeleccionarPartida();
					frame.setVisible(true);
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Se interrumpio el proceso que carga las partidas","Error durante SeleccionarPartida", JOptionPane. ERROR_MESSAGE);
				}
			}

		});
	}

	/**
	 * Create the frame.
	 */
	public SeleccionarPartida() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Harrington", Font.PLAIN, 15));
		//aca Carga las partidas//
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sin datos", "MiPartida"}));
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.RED);
		comboBox.setBounds(51, 62, 256, 30);
		comboBox.addItemListener(this); //indispensable para que detecte que se cambia el valor de comboBox
		contentPane.add(comboBox);
		
		btnCargar = new JButton("Cargar");
		btnCargar.setFont(new Font("Harrington", Font.PLAIN, 15));
		btnCargar.setBackground(Color.WHITE);
		btnCargar.setForeground(Color.BLUE);
		btnCargar.setBounds(175, 202, 89, 23);
		contentPane.add(btnCargar);
		
		lblSinDatos_1 = new JLabel("Sin Datos");
		lblSinDatos_1.setFont(new Font("Harrington", Font.PLAIN, 15));
		lblSinDatos_1.setForeground(Color.RED);
		lblSinDatos_1.setBackground(Color.WHITE);
		lblSinDatos_1.setBounds(189, 107, 138, 14);
		contentPane.add(lblSinDatos_1);
		
		lblNombre = new JLabel("NombrePersonaje:");
		lblNombre.setForeground(Color.BLUE);
		lblNombre.setFont(new Font("Harrington", Font.BOLD, 15));
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setBackground(Color.WHITE);
		lblNombre.setBounds(41, 103, 138, 20);
		contentPane.add(lblNombre);
		
		lblNewLabel_1 = new JLabel("Selecciona la Partida a cargar");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Harrington", Font.BOLD, 25));
		lblNewLabel_1.setBounds(41, 21, 363, 30);
		contentPane.add(lblNewLabel_1);
		
		lblSinDatos_2 = new JLabel("Sin Datos");
		lblSinDatos_2.setForeground(Color.RED);
		lblSinDatos_2.setFont(new Font("Harrington", Font.PLAIN, 15));
		lblSinDatos_2.setBackground(Color.WHITE);
		lblSinDatos_2.setBounds(189, 138, 151, 14);
		contentPane.add(lblSinDatos_2);
		
		lblMapa = new JLabel("Mapa:");
		lblMapa.setVerticalAlignment(SwingConstants.TOP);
		lblMapa.setHorizontalAlignment(SwingConstants.LEFT);
		lblMapa.setForeground(Color.BLUE);
		lblMapa.setFont(new Font("Harrington", Font.BOLD, 15));
		lblMapa.setBackground(Color.WHITE);
		lblMapa.setBounds(124, 136, 55, 20);
		contentPane.add(lblMapa);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SeleccionarPartida.class.getResource(Constantes.RUTA_SELECCION_PERSONAJE)));
		lblNewLabel.setBounds(0, 0, 434, 262);
		contentPane.add(lblNewLabel);
		
	}

	@Override
	public void itemStateChanged(ItemEvent seleccion) {
		
		 if (seleccion.getSource()==comboBox) {
			 	
			 if ((String)comboBox.getSelectedItem() == "MiPartida") {
				 lblSinDatos_1.setText(nombrePersonaje);
		         lblSinDatos_2.setText(mapa);
			}
			 else
			 {
				 	lblSinDatos_1.setText("Sin Datos");
		            lblSinDatos_2.setText("Sin Datos");
			 } 
	     }
		 
	}
}
