package principal.peticiones;

import java.io.Serializable;

import principal.cs.*;

public class PeticionLogueo implements Serializable{
	
	private String usuario;
	private char[] password;
	
	public PeticionLogueo(String u, char[] p) {
		this.usuario = u;
		this.password = p;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public char[] getPassword(){
		return this.password;
	}
}