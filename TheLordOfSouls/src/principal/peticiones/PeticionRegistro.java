package principal.peticiones;

import java.io.Serializable;

import principal.cs.*;

public class PeticionRegistro implements Serializable{
	
	private String usuario;
	private char[] password;
	private String email;
	
	public PeticionRegistro(String u, char[] p, String e) {
		this.usuario = u;
		this.password = p;
		this.email = e;
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public char[] getPassword(){
		return this.password;
	}
	
	public String getEmail(){
		return this.email;
	}
}