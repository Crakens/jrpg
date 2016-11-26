package principal.mapas;

import java.awt.Rectangle;

public class CapaColisiones extends CapaTiled{

	private Rectangle[] colionables;
	
	public CapaColisiones(int acho, int alto, int x, int y, Rectangle [] colisionables) {
		super(acho, alto, x, y);
		
		this.colionables=colisionables;
	}

	public Rectangle[] obtenerColisionables(){
		return colionables;
	}
}
