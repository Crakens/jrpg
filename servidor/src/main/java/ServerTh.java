package cs;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import partidaZR.Partida;
import partidaZR.Posicion;
import peticiones.CodigoPeticion;
import peticiones.Peticion;
import pojo.POJOPartidaConTablero;
public class ServerTh extends Thread{
	private Socket cSocket;
	private DataInputStream in;
	private DataOutputStream out;
	private Server server;	
	
	public Server getServer() {
		return server;
	}

	public Socket getcSocket() {
		return cSocket;
	}

	public ServerTh(Socket socket, Server server) {
		this.server = server;
		this.cSocket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			in = new DataInputStream(cSocket.getInputStream());
			out = new DataOutputStream(cSocket.getOutputStream());
			//out.writeUTF("Bienvenido a Zombie Rush");
			while(true) {	
				String entrada = in.readUTF();
				System.out.println("CLIENTE: "+entrada);
				Peticion peticion = new Peticion(entrada, this);
				String pojoRespuesta = peticion.getRespuesta();
				int tipoRespuesta = esParaTodos(pojoRespuesta); 
				if(tipoRespuesta != -1) {
					for (UsuarioSocket socket : this.getServer().getListaSocketsUsuarios()) {
						(new DataOutputStream(socket.getS().getOutputStream())).writeUTF(pojoRespuesta);;
					}
				} else {					
					out.writeUTF(pojoRespuesta);
				}				
			}
		} catch (IOException e) {
			// TODO: handle exception
			server.getListaSocketsUsuarios().remove(cSocket);
			try {
				in.close();
				out.close();
				cSocket.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int esParaTodos(String pojoRespuesta) {
		String[] datos = pojoRespuesta.split(Peticion.CARACTER_SEPARACION);
		try {
			if(Integer.parseInt(datos[0]) == CodigoPeticion.VINO_MAPA_NUEVO){
				String nombrePartida = datos[1];
				Partida aux = new Partida(nombrePartida);
				int pos = this.getServer().getPartidas().indexOf( aux );	
				return pos;
			}			
		} catch (Exception e) {
		}
		return -1;
	}
		
}
