package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclado implements KeyListener{

	private final static int numeroTeclas = 120;
	private final boolean [] teclas = new boolean[numeroTeclas];
	
	public boolean arriba; //recomendable public y no geter and seter xq asi es mas fluido durante el fuego//
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;
	
	public boolean salir;
	
	public void actualizar(){
		
		arriba= teclas[KeyEvent.VK_W]; //asigna el respectivo valor de la tecla w a la variable arriba//
		abajo= teclas[KeyEvent.VK_S]; //idem//
		izquierda= teclas[KeyEvent.VK_A]; //idem//
		derecha= teclas[KeyEvent.VK_D]; //idem//
		salir= teclas[KeyEvent.VK_ESCAPE];
	}
	
	public void keyPressed(KeyEvent e) { //metodo de manter presionada una tecla//recive como parametro un evento de tecla//
	
		teclas[e.getKeyCode()]=true; //asigna el valor true a la tecla correspondiente en el array//
	}

	public void keyReleased(KeyEvent e) { //metodo de dejar de presionar una tecla//
		
		teclas[e.getKeyCode()]=false;
	}

	public void keyTyped(KeyEvent e) { //metodo de pulsar una ves una tecla//
		
		
	}

}
