package cs;

import java.net.Socket;

public class UsuarioSocket {
	private Socket s;
	private String nombre;
	
	
	public Socket getS() {
		return s;
	}

	public UsuarioSocket(Socket s) {
		this.s = s;	
		this.nombre = "Desconocido";
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String n) {
		this.nombre = n;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioSocket other = (UsuarioSocket) obj;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}	
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
