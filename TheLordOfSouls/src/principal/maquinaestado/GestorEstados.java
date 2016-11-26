package principal.maquinaestado;

import java.awt.Graphics;

import principal.maquinaestado.estados.juego.GestorJuego;

public class GestorEstados {

	private EstadoJuego [] estados;
	private EstadoJuego estadoActual;
	
	public GestorEstados(){
		iniciarEstados();
		iniciarEstadoActual();	
	}

	private void iniciarEstados(){
		estados = new EstadoJuego[1];
		estados[0] = new GestorJuego();
		//Añadir e iniciar los demas estados a medida que los creemos//
	}
	
	private void iniciarEstadoActual() {
		estadoActual = estados[0]; //Porque es el unico que tengo ahora
	}
			
	public void actualizar(){
		estadoActual.actualizar();
	}
	
	public void dibujar(final Graphics g){
		estadoActual.dibujar(g);
	}
	
	public void cambiarEstadoActual(final int estadoNuevo){
		estadoActual = estados[estadoNuevo];
	}
	
	public EstadoJuego obtenerEstadoJuego(){
		return estadoActual;
	}
}
