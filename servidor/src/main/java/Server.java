package cs;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import partidaZR.Partida;
import pojo.POJOPartidaSinTablero;
import conexionBD.ConexionBD;

public class Server {
	static int PUERTO_POR_DEFECTO = 5000;
	private ArrayList<UsuarioSocket> listaSocketsUsuarios;
	private ConexionBD conexionBD;
	private ArrayList<POJOPartidaSinTablero> pojoPartidasEnLinea;	
	private JTextArea txtLog;
	private ArrayList<Partida> partidas;
	
	public ArrayList<Partida> getPartidas() {
		return partidas;
	}

	public JTextArea getTxtLog() {
		return txtLog;
	}

	public ArrayList<UsuarioSocket> getListaSocketsUsuarios() {
		return listaSocketsUsuarios;
	}
	
	public ConexionBD getConexionBD() {
		return conexionBD;
	}

	public ArrayList<POJOPartidaSinTablero> getPojoPartidasEnLinea() {
		return pojoPartidasEnLinea;
	}

	public Server(JTextField txtPuerto, JTextArea txtLog) {
		this.txtLog = txtLog;
		PUERTO_POR_DEFECTO = Integer.parseInt(txtPuerto.getText());
		conexionBD = new ConexionBD(txtLog);
		pojoPartidasEnLinea = conexionBD.listarPartidasAlServer();
		listaSocketsUsuarios = new ArrayList<UsuarioSocket>();
		partidas = new ArrayList<Partida>();
		try {
			construirPartidasEnBaseAPOJOPartidas();
			ServerSocket svSocket = new ServerSocket(PUERTO_POR_DEFECTO);
			// Escuchar a clientes de forma constante
			while(true) {
				//mostrarUsuariosConectados();
				//System.out.println("Escuchando en el puerto: " + PUERTO_POR_DEFECTO);
				escribirLog("Escuchando en el puerto: " + PUERTO_POR_DEFECTO);
				// Aceptar la conexión
				Socket cSocket = svSocket.accept();
				escribirLog("Se conectó: " + cSocket.getLocalAddress());
				UsuarioSocket aux = new UsuarioSocket(cSocket);
				listaSocketsUsuarios.add(aux);
				//ServerTh sThread = new ServerTh(cSocket, listaSocketsUsuarios, pojoPartidasEnLinea, conexionBD);
				ServerTh sThread = new ServerTh(cSocket, this);
				sThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void construirPartidasEnBaseAPOJOPartidas() {
		for (POJOPartidaSinTablero partidaVacia : pojoPartidasEnLinea) {
			Partida p = new Partida(partidaVacia);
			partidas.add(p);
		}
	}

	private void mostrarUsuariosConectados() {
		escribirLog("-------------------Usuarios------------------------");
		escribirLog("Lista de usuarios conectados:");
		for (int i = 0; i < this.listaSocketsUsuarios.size(); i++) {
			escribirLog((i+1)+": "+listaSocketsUsuarios.get(i));
		}
		escribirLog("---------------------------------------------------");
	}
	
	public void escribirLog (String cadena) {
		this.txtLog.append(cadena+"\n");
	}
	
	public void actualizarPartidas() {
		pojoPartidasEnLinea = conexionBD.listarPartidasAlServer();
	}
}
