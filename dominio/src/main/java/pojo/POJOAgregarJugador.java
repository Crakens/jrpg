package pojo;

import peticiones.CodigoPeticion;
import peticiones.Peticion;

public class POJOAgregarJugador {

	private String datosEnviable;
	private int indicePartida,
				indiceUsuario;
	
	public POJOAgregarJugador(String partida, String nombre) {
		
		datosEnviable = CodigoPeticion.AGREGAR_JUGADOR
				+Peticion.CARACTER_SEPARACION
				+partida
				+Peticion.CARACTER_SEPARACION
				+nombre
				+Peticion.CARACTER_SEPARACION;		
	}
	
	public void setRespuesta(String rta) {
		
		String datos[] = rta.split(Peticion.CARACTER_SEPARACION);
		
		this.indicePartida = Integer.parseInt(datos[1]);
		this.indiceUsuario = Integer.parseInt(datos[2]);
		
	}

	public int getIndicePartida() {
		return indicePartida;
	}

	public int getIndiceUsuario() {
		return indiceUsuario;
	}

	public String getDatosEnviable() {
		return datosEnviable;
	}
	
}
