package principal.graficos;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

import principal.cs.Cliente;

public class Ventana extends JFrame implements WindowFocusListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5979421777239930009L;

	private String titulo;
	private Cliente cliente;
	
	public Ventana(String titulo, SuperFicieDibujo sd, Cliente c){
		this.titulo = titulo;
		this.cliente = c;
		
		configurarVentana(sd);
	}

	private void configurarVentana(final SuperFicieDibujo sd) {
		
		setTitle(titulo);

		setResizable(false);
		//setIconImage(image);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(sd, BorderLayout.CENTER);
		setUndecorated(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		requestFocus();
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		setFocusable(true);
		requestFocus();
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		setFocusable(true);
		requestFocus();
		
	}
}
