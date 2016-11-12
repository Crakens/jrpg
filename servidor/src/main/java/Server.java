package cs;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import baseDeDatos.SQLiteConnection;

public class Server {
	static int PUERTO_POR_DEFECTO = 5000;
	private ArrayList<UsuarioSocket> listaSocketsUsuarios;
	private SQLiteConnection conexionBD;
	
	private JTextArea txtLog;

	public JTextArea getTxtLog() {
		return txtLog;
	}

	public ArrayList<UsuarioSocket> getListaSocketsUsuarios() {
		return listaSocketsUsuarios;
	}
	
	public SQLiteConnection getConexionBD() {
		return conexionBD;
	}

	public Server(JTextField txtPuerto, JTextArea txtLog) {
		this.txtLog = txtLog;
		PUERTO_POR_DEFECTO = Integer.parseInt(txtPuerto.getText());
		conexionBD = new SQLiteConnection();
		
		listaSocketsUsuarios = new ArrayList<UsuarioSocket>();
		
		try {

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
	

}

