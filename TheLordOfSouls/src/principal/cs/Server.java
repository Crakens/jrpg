package principal.cs;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import baseDeDatos.SQLiteConnection;

public class Server extends Thread{
	static int PUERTO_POR_DEFECTO = 50000;
	private LinkedList<ObjectOutputStream> listaUsuariosMapa1;
	private LinkedList<ObjectOutputStream> listaUsuariosMapa2;
	private ServerSocket svSocket;
	private SQLiteConnection conexionBD;
	

	public LinkedList<ObjectOutputStream> getListaUsuariosMapa1() {
		return listaUsuariosMapa1;
	}
	
	public LinkedList<ObjectOutputStream> getListaUsuariosMapa2() {
		return listaUsuariosMapa2;
	}
	
	public SQLiteConnection getConexionBD() {
		return conexionBD;
	}

	public Server() {
		try {
			svSocket = new ServerSocket(PUERTO_POR_DEFECTO);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "handle exception","Error en crear Server", JOptionPane. ERROR_MESSAGE);
		}
		conexionBD = new SQLiteConnection();
		listaUsuariosMapa1 = new LinkedList<ObjectOutputStream>();
		listaUsuariosMapa2 = new LinkedList<ObjectOutputStream>();
	}

	public void run(){	// esto queda corriendo atendiendo las conexiones nuevas y creando nuevos threads por c/u
		Socket s = null;
		while(true){
			try {
				s = this.svSocket.accept();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Hubo un problema con la atención de un pedido de conexión","Error en run Server", JOptionPane. ERROR_MESSAGE);
			}
			new ServerThread(s,this).start();
		}
	}
	

	public static void main(String[] args) {
		new Server().start();
	}
	
	public void agregarAListaDeMapa1(ObjectOutputStream oos){
		this.listaUsuariosMapa1.add(oos);
	}
}

