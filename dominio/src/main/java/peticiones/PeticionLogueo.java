package peticiones;

import cs.*;

public class PeticionLogueo {
	
	private String respuesta;
	private ServerTh serverTh;
	
	public PeticionLogueo(String[] datosPeticion, ServerTh serverTh) {
		String usuario = datosPeticion[1],
			   password = datosPeticion[2];
		this.serverTh = serverTh;
		int codigoPeticion = existeUsuario(usuario, password); 
		
		switch (codigoPeticion) {
		/*
		case CodigoPeticion.LOGEO_CORRECTO_ADMIN:
			UsuarioSocket aux = new UsuarioSocket(serverTh.getcSocket());
			int pos = this.serverTh.getServer().getListaSocketsUsuarios().indexOf(aux);
			serverTh.getServer().getListaSocketsUsuarios().get(pos).setNombre(usuario);
			this.respuesta = CodigoPeticion.LOGEO_CORRECTO_ADMIN + "";
			break;
		*/
		case CodigoPeticion.LOGEO_CORRECTO:
			UsuarioSocket aux2 = new UsuarioSocket(serverTh.getcSocket());
			int pos2 = this.serverTh.getServer().getListaSocketsUsuarios().indexOf(aux2);
			serverTh.getServer().getListaSocketsUsuarios().get(pos2).setNombre(usuario);
			this.respuesta = CodigoPeticion.LOGEO_CORRECTO + "";
			break;
		default:
			this.respuesta = CodigoPeticion.LOGEO_INCORRECTO + "";
			break;
		}
	}

	private int existeUsuario(String usuario, String password) {
		return serverTh.getServer().getConexionBD().login(usuario, password);
	}

	public String getRespuesta() {
		return respuesta;
	}
}
