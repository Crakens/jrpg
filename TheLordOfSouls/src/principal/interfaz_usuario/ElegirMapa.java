package principal.interfaz_usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.cs.Cliente;
import principal.peticiones.CodigoPeticion;
import principal.peticiones.PeticionLevantarMapa;

import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class ElegirMapa extends JFrame  {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("");
	private JLabel lblSeleccionaElMapa;

	public  ElegirMapa(final Cliente cliente) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSeleccionaElMapa = new JLabel("Selecciona el mapa para jugar");
		lblSeleccionaElMapa.setForeground(Color.BLUE);
		lblSeleccionaElMapa.setFont(new Font("Harrington", Font.PLAIN, 25));
		lblSeleccionaElMapa.setBounds(57, 11, 332, 30);
		contentPane.add(lblSeleccionaElMapa);
		
		JLabel lblMapa = new JLabel("");
		lblMapa.setFont(new Font("Harrington", Font.BOLD, 13));
		lblMapa.setIcon(new ImageIcon(ElegirMapa.class.getResource(Constantes.RUTA_SELECCION_MAPA)));
		lblMapa.setBounds(67, 43, 300, 150);
		contentPane.add(lblMapa);
		
		JButton btnNewButton = new JButton("Seleccionar");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(cliente.pedirJoinMapa1() == CodigoPeticion.PONER_EN_MAPA_JUGADOR_CORRECTO)
            		JOptionPane.showMessageDialog(null, "Entraste al mapa correctamente");
            	else
            		JOptionPane.showMessageDialog(null, "Problema entrando al mapa");
				
				dispose();
				
				 Runnable miRunnable = new Runnable()
			      {
			         public void run()
			         {
			            try
			            {
			            	cliente.levantarMapa("TheLordOfSouls",Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO);			      
			            }
			            catch (Exception e)
			            {
			            	JOptionPane.showMessageDialog(null, "Se interrumpio el proceso que levanta el mapa","Error durante ElegirMapa", JOptionPane. ERROR_MESSAGE);
			            }
			         }
			      };
				 
			      Thread hilo = new Thread (miRunnable);
			      hilo.start();
			 }
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Harrington", Font.PLAIN, 13));
		btnNewButton.setBounds(313, 228, 107, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-->");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(242, 229, 61, 23);
		contentPane.add(btnNewButton_1);
		
		JButton button = new JButton("<--");
		button.setEnabled(false);
		button.setBounds(171, 229, 61, 23);
		contentPane.add(button);
		
		JLabel lblMapa_1 = new JLabel("Mapa 1");
		lblMapa_1.setForeground(Color.BLUE);
		lblMapa_1.setFont(new Font("Harrington", Font.BOLD, 15));
		lblMapa_1.setBounds(185, 204, 61, 14);
		contentPane.add(lblMapa_1);
		lblNewLabel.setIcon(new ImageIcon(ElegirMapa.class.getResource(Constantes.RUTA_SELECCION_PERSONAJE)));
		lblNewLabel.setBounds(0, 0, 434, 262);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
