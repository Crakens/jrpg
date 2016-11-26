package principal.cs;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.peticiones.*;


public class Cliente{
	
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private static String respuestaServer;
	private boolean estaConectado = false;
	private String nombreUsuario;
	private ClienThread cliThread;
	
	
	public Cliente(String host){	
		try {
			s = new Socket(host, Server.PUERTO_POR_DEFECTO);
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Unknown host exception en creando socket del lado cliente.","Error en Cliente", JOptionPane. ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException creando socket del lado cliente","Error en Cliente", JOptionPane. ERROR_MESSAGE);
		}
		try {
			ois = new ObjectInputStream(s.getInputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException creando OIS en el cliente","Error en Cliente", JOptionPane. ERROR_MESSAGE);
		}
		try {
			oos = new ObjectOutputStream(s.getOutputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException creando OOS en el cliente","Error en Cliente", JOptionPane. ERROR_MESSAGE);
		}
		this.cliThread = new ClienThread(ois);
		this.cliThread.start();
		estaConectado = true;
	}
	
	public boolean isEstaConectado() {
		return estaConectado;
	}

	public String getNombre() {
		return this.nombreUsuario;
	}	
	

	public int loguearse(PeticionLogueo petLog) {
		try {
			oos.writeObject(new Mensaje(CodigoPeticion.LOGEO,petLog));		//manda mje de login
			oos.flush();
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Error en el login al enviar petición por IOException","Error en login Cliente", JOptionPane. ERROR_MESSAGE);
		}
		
		Mensaje respuestaSv = levantarMensaje();
		return respuestaSv.getCodigo();

	}
	
	public int registrarse(PeticionRegistro petReg) {
		try {
			oos.writeObject(new Mensaje(CodigoPeticion.REGISTRO,petReg));
			oos.flush();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error en el registro al enviar petición por IOException","Error en  registrar Cliente", JOptionPane. ERROR_MESSAGE);
		}
		
		Mensaje respuestaSv = levantarMensaje();
		return respuestaSv.getCodigo();
	}


	public void crearJugador(String nombrePartida, int minJ, int maxJ) {
		try {/*
			POJOCrearPartida partidaNueva = new POJOCrearPartida(nombrePartida, minJ, maxJ);
			out.writeUTF(partidaNueva.getDatosEnviable());*/
		} catch (Exception e) {
			
		}
	}
	
	public void levantarMapa(String titulo, final int ancho, final int alto) {
		GestorPrincipal gp=new GestorPrincipal(titulo,ancho, alto, this);
		gp.iniciarJuego();
		gp.iniciarBuclePrincipal();
	}
	
	public Mensaje levantarMensaje(){
		return this.cliThread.getMensaje();
	}
	
	public int pedirJoinMapa1(){
		try {
			oos.writeObject(new Mensaje(CodigoPeticion.PONER_EN_MAPA_JUGADOR, null));
			oos.flush();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error en el registro al enviar petición por IOException","Error en entrar en  Mapa1 Cliente", JOptionPane. ERROR_MESSAGE);
		}
		Mensaje respuestaSv = levantarMensaje();
		return respuestaSv.getCodigo();
	}
}
	
