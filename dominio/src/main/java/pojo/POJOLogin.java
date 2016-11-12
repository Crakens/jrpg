package pojo;

import peticiones.CodigoPeticion;
import peticiones.Peticion;

public class POJOLogin {

	private String 	datosEnviable,
					respuesta;
	
	public POJOLogin(String usuario, String contrasenia) {
		datosEnviable = CodigoPeticion.LOGEO
				+ Peticion.CARACTER_SEPARACION
				+ usuario
				+ Peticion.CARACTER_SEPARACION
				+ contrasenia
				+ Peticion.CARACTER_SEPARACION;
		//this.respuesta = "--.--";
	}

	public String getDatosEnviable() {
		return datosEnviable;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getRespuesta() {
		return respuesta;
	}
	
	
	
}
