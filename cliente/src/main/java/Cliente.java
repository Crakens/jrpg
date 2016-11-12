package cs;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import peticiones.CodigoPeticion;
import peticiones.Peticion;
import pojo.POJOLogin;


public class Cliente extends Thread{
	
	private Socket s;
	private DataInputStream in;
	private DataOutputStream out;
	private static String respuestaServer;
	private boolean estaConectado = false;
	private String nombreUsuario;

	
	
	public Cliente(String host){	
		try {
			s = new Socket(host, Server.PUERTO_POR_DEFECTO);
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			estaConectado = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEstaConectado() {
		return estaConectado;
	}

	public String getNombre() {
		return this.nombreUsuario;
	}	

	
	@Override
	public void run() {
		try {
			while(true) {
				respuestaServer = in.readUTF();
				System.out.println("SERVER: "+respuestaServer);

			}
		} catch (Exception e) {
			try {
				in.close();
				out.close();
				s.close();
			} catch (Exception e2) {
			}
			e.printStackTrace();
		}
	}
	

	public int loguearse(String nombre, String pass) {
		try {
			POJOLogin login = new POJOLogin(nombre, pass);
			out.writeUTF(login.getDatosEnviable());
			sleep(1000);	
			login.setRespuesta(respuestaServer);			
			int codigoRespuesta = Integer.parseInt(login.getRespuesta());
			switch (codigoRespuesta) {
			case CodigoPeticion.LOGEO_CORRECTO:
				this.nombreUsuario = nombre;
				//this.tipoDeCuenta = CodigoPeticion.LOGEO_CORRECTO;
				return CodigoPeticion.LOGEO_CORRECTO;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			//System.out.println("Error logueo"); //ACA DA EL ERROR
			e.printStackTrace();
		}
		return CodigoPeticion.LOGEO_INCORRECTO;
	}
	


	public void crearJugador(String nombrePartida, int minJ, int maxJ) {
		try {/*
			POJOCrearPartida partidaNueva = new POJOCrearPartida(nombrePartida, minJ, maxJ);
			out.writeUTF(partidaNueva.getDatosEnviable());*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

}
	
