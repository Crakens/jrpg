package cs;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import partidaZR.Posicion;
import peticiones.CodigoPeticion;
import peticiones.Peticion;
import pojo.POJOActualizarIndices;
import pojo.POJOAgregarJugador;
import pojo.POJOCrearPartida;
import pojo.POJOEmpezarPartida;
import pojo.POJOLogin;
import pojo.POJOMoverJugadores;
import pojo.POJOMovimiento;
import pojo.POJOPartidaConTableroJugable;
import pojo.POJOPartidasEnLinea;
import pojo.POJOProximaRonda;
import pojo.POJORegistrar;
import pojo.POJOTodosInfectados;
import ventanasZR.ZRCliente;
import ventanasZR.ZRPartida;

public class Cliente extends Thread{
	
	private Socket s;
	private DataInputStream in;
	private DataOutputStream out;
	private static String respuestaServer;
	private boolean estaConectado = false;
	private String nombreUsuario;
	private String nombrePartida;
	private POJOPartidaConTableroJugable datosPartida;
	private int indiceUsuario;
	private int indicePartida;
	private int tipoDeCuenta;
	private int tipoDeJugador;
	private int fila, columna;
	private ZRCliente zrCliente;
	private boolean estaEnPartida = false;
	
	public int getIndiceUsuario() {
		return indiceUsuario;
	}

	public void setZrCliente(ZRCliente zrCliente) {
		this.zrCliente = zrCliente;
	}

	public int getTipoDeJugador() {
		return tipoDeJugador;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public int getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public Cliente(String host){	
		try {
			s = new Socket(host, Server.PUERTO_POR_DEFECTO);
			in = new DataInputStream(s.getInputStream());
			out = new DataOutputStream(s.getOutputStream());
			estaConectado = true;
			indiceUsuario = -1;
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

	public POJOPartidaConTableroJugable getDatosPartida() {
		return datosPartida;
	}

	@Override
	public void run() {
		try {
			while(true) {
				respuestaServer = in.readUTF();
				System.out.println("SERVER: "+respuestaServer);
				//sleep(1000);
				cambioElTablero(respuestaServer);
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
	
	private void cambioElTablero(String respuesta) {
		String[] datos = respuesta.split(Peticion.CARACTER_SEPARACION);
		try {
			//System.out.println("if: "+ this.nombrePartida +" == "+datos[1]);
			if(Integer.parseInt(datos[0]) == CodigoPeticion.VINO_MAPA_NUEVO
					&& this.nombrePartida.equals(datos[1])){
				System.out.println("<<<<--MAPA NUEVO-->>>>");
				this.datosPartida = new POJOPartidaConTableroJugable(respuestaServer, this.nombreUsuario); 
				//respuestaServer = "";
				ZRPartida.estaJugando = true;
				this.tipoDeJugador = datosPartida.getTipoJugador();
				this.fila = datosPartida.getFila();
				this.columna = datosPartida.getColumna();
				//this.actualizarIndices();
				sleep(1000);
				//System.out.println(this.datosPartida);
				if(estaEnPartida == false) {
					estaEnPartida=true;
					ZRCliente.irA(ZRCliente.PARTIDA);
				}
				zrCliente.Partida.repaint();
			}			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public int registrarse(String nombre, String pass, String nick, String pregSec, String respSecre) {
		try {
			POJORegistrar reg = new POJORegistrar(nombre, pass, nick, pregSec, respSecre);
			System.out.println(reg.getDatosEnviable());
			out.writeUTF(reg.getDatosEnviable());
			Thread.sleep(2000);
			reg.setRespuesta(respuestaServer);
			System.out.println(reg.getRespuesta());
			int codigoRespuesta = Integer.parseInt(reg.getRespuesta());
			return CodigoPeticion.REGISTRAR_CORRECTO;			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error registro");
		}
		return CodigoPeticion.REGISTRAR_INCORRECTO;
	}
	
	public int loguearse(String nombre, String pass) {
		try {
			POJOLogin login = new POJOLogin(nombre, pass);
			out.writeUTF(login.getDatosEnviable());
			sleep(1000);	
			login.setRespuesta(respuestaServer);			
			int codigoRespuesta = Integer.parseInt(login.getRespuesta());
			//System.out.println(codigoRespuesta+" "+CodigoPeticion.LOGEO_CORRECTO);
			switch (codigoRespuesta) {
			case CodigoPeticion.LOGEO_CORRECTO_ADMIN:
				this.nombreUsuario = nombre;
				this.tipoDeCuenta = CodigoPeticion.LOGEO_CORRECTO_ADMIN;
				return CodigoPeticion.LOGEO_CORRECTO_ADMIN;

			case CodigoPeticion.LOGEO_CORRECTO_USUARIO:
				this.nombreUsuario = nombre;
				this.tipoDeCuenta = CodigoPeticion.LOGEO_CORRECTO_USUARIO;
				return CodigoPeticion.LOGEO_CORRECTO_USUARIO;
				
			default:
				break;
			}
			
		} catch (Exception e) {
			System.out.println("Error logueo");
		}
		return CodigoPeticion.LOGEO_INCORRECTO;
	}
	
	public String verPartidas() {
		try {
			POJOPartidasEnLinea partidasEnLinea = new POJOPartidasEnLinea();
			out.writeUTF(partidasEnLinea.getDatosEnviable());
			//System.out.println("Partidas: \n"+respuestaServer);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return respuestaServer;
	}
	

	public void crearPartida(String nombrePartida, int minJ, int maxJ) {
		try {
			POJOCrearPartida partidaNueva = new POJOCrearPartida(nombrePartida, minJ, maxJ);
			out.writeUTF(partidaNueva.getDatosEnviable());
		} catch (Exception e) {
			
		}
	}
		
	public void agregarJugadorALaPartida(String partida) {
		try {
			POJOAgregarJugador agregarJ = new POJOAgregarJugador(partida, this.nombreUsuario);
			out.writeUTF(agregarJ.getDatosEnviable());
			sleep(100);
			agregarJ.setRespuesta(respuestaServer);
			respuestaServer = "";
			this.nombrePartida = partida;
			this.indicePartida = agregarJ.getIndicePartida();
			this.indiceUsuario = agregarJ.getIndiceUsuario();
			if(this.indiceUsuario == 0)
				zrCliente.Lobby.mostrarBotonEmpezarPartida();
		} catch (Exception e) {

		}
	}
	
	public void empezarPartida() {
		try {
			POJOEmpezarPartida empezarPart = new POJOEmpezarPartida(this.nombrePartida);
			//System.out.println(empezarPart.getDatosEnviable());
			out.writeUTF(empezarPart.getDatosEnviable());
			//sleep(1000);	
			//System.out.println(respuestaServer);
			/*this.datosPartida = new POJOPartidaConTableroJugable(respuestaServer, this.nombreUsuario); 
			respuestaServer = "";
			this.tipoDeJugador = datosPartida.getTipoJugador();
			this.fila = datosPartida.getFila();
			this.columna = datosPartida.getColumna();
			this.actualizarIndices();
			ZRCliente.irA(ZRCliente.PARTIDA);*/
		} catch (Exception e) {
		}
	}
	
	public void moverJugador(int movimiento) {
		try {
			//System.out.println(this.indicePartida+" "+this.indiceUsuario+" "+movimiento);
			POJOMovimiento mov = new POJOMovimiento(this.nombrePartida, this.nombreUsuario, movimiento);
			out.writeUTF(mov.getDatosEnviable());
		} catch (Exception e) {
		}
	}
	
	public void moverJugadores() {
		try {
			POJOMoverJugadores movJ = new POJOMoverJugadores(this.indicePartida);
			out.writeUTF(movJ.getDatosEnviable());
			sleep(1000);
			/*this.datosPartida = new POJOPartidaConTableroJugable(respuestaServer, this.nombreUsuario); 	
			respuestaServer = "";
			this.tipoDeJugador = datosPartida.getTipoJugador();
			this.fila = datosPartida.getFila();
			this.columna = datosPartida.getColumna();*/
		} catch (Exception e) {
		}
	}
	
	public int todosInfectados() {
		try {
			POJOTodosInfectados todosInfectados = new POJOTodosInfectados(this.indicePartida);
			out.writeUTF(todosInfectados.getDatosEnviable());
			sleep(1000);
			todosInfectados.setRespuesta( respuestaServer );
			respuestaServer = "";
			String[] rta = respuestaServer.split(Peticion.CARACTER_SEPARACION);
			return Integer.parseInt(rta[0]);
		} catch (Exception e) {

		}
		return CodigoPeticion.TODOS_INFECTADOS_INCORRECTO;
	}
	
	public void proximaRonda() {
		try {
			POJOProximaRonda proxRonda = new POJOProximaRonda(this.indicePartida);
			out.writeUTF(proxRonda.getDatosEnviable());
			sleep(1000);
			/*this.datosPartida = new POJOPartidaConTableroJugable(respuestaServer, this.nombreUsuario); 
			respuestaServer = "";
			this.tipoDeJugador = datosPartida.getTipoJugador();
			this.fila = datosPartida.getFila();
			this.columna = datosPartida.getColumna();*/
		} catch (Exception e) {
		}
	}
	
	public void actualizarIndices() {
		try {
			POJOActualizarIndices actualizarIndices = new POJOActualizarIndices(this.nombrePartida, this.nombreUsuario);
			out.writeUTF(actualizarIndices.getDatosEnviable());
			//sleep(2000);
			actualizarIndices.setRespuesta(respuestaServer);
			System.out.println("ACTUALIZA INDICES --> "+respuestaServer);
			//respuestaServer = "";
			this.indicePartida = actualizarIndices.getIndicePartida();
			this.indiceUsuario = actualizarIndices.getIndiceJugador();
			//System.out.println("ACTUALIZO INDICES: IndicePartida: "+indicePartida+" IndiceUsuario: "+indiceUsuario);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	/*
	public static void main(String[] args) {
		Cliente cli = new Cliente("localhost");
		Cliente cli2 = new Cliente("localhost");
		cli.start();
		cli2.start();
				
		int codigo = cli.loguearse("Ariel", "admin"); 
		if(codigo != CodigoPeticion.LOGEO_INCORRECTO)
			if(codigo == CodigoPeticion.LOGEO_CORRECTO_ADMIN)
				System.out.println("Bienvenido Admin");
			else
				System.out.println("Bienvendio usuario");
		else
			System.out.println("Logeo incorrecto");
		
		cli2.loguearse("Federico", "admin");
		
		cli2.agregarJugadorALaPartida("Quiero cerebro");
		cli.agregarJugadorALaPartida("Quiero cerebro");
		cli.empezarPartida();
		cli2.empezarPartida();
		
		System.out.println("PARTIDA DEFAULT");
		System.out.println(cli.datosPartida);
		
		cli2.moverJugador(Posicion.DERECHA);
		cli2.moverJugador(Posicion.ABAJO);
		cli.moverJugador(Posicion.ABAJO);
		cli.moverJugador(Posicion.DERECHA);
		
		cli2.moverJugadores();
		cli.moverJugadores();
		
		System.out.println("PARTIDA MOVIDA");
		System.out.println(cli.datosPartida);
		System.out.println(cli2.datosPartida);
		
		System.out.println("PARTIDA REINICIADA");
		System.out.println(cli.datosPartida);
		System.out.println(cli2.datosPartida);
		
		System.out.println("Fin.");
		
	}
*/
	
	
}
