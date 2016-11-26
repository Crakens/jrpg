package principal.control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import principal.Constantes;
import principal.interfaz_usuario.Menu;

public class Teclado implements KeyListener {

	public Tecla arriba = new Tecla();
	public Tecla abajo = new Tecla();
	public Tecla izquierda = new Tecla();
	public Tecla derecha = new Tecla();

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaPulsada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaPulsada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaPulsada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaPulsada();
			break;
		case KeyEvent.VK_ESCAPE:
			new Menu();
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaLiberada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaLiberada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaLiberada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaLiberada();
			break;
		}
	}
	
	public void liberarTeclas(){
		arriba.teclaLiberada();
		abajo.teclaLiberada();
		izquierda.teclaLiberada();
		derecha.teclaLiberada();
	}
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
