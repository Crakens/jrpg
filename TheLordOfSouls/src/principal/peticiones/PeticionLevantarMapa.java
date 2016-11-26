package principal.peticiones;

import java.io.Serializable;

import principal.Constantes;
import principal.GestorPrincipal;

public class PeticionLevantarMapa  implements Serializable{

	String titulo;
	int ancho=0;
	int alto=0;
	
	public PeticionLevantarMapa( String titulo,  int ancho,  int alto){
		this.titulo=titulo;
		this.ancho=ancho;
		this.alto=alto;
		/*
		GestorPrincipal gp=new GestorPrincipal("TheLordOfSouls", Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO);
		gp.iniciarJuego();
		gp.iniciarBuclePrincipal();
		*/
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}
}
