package principal.mapas;

import java.awt.Rectangle;

import principal.sprites.Sprite;

public class Tile { //casilla del mapa con un dibujo//

	private final Sprite sprite;
	private final int id;
	private boolean solido;
	
	public Tile(Sprite sprite, final int id){
		this.sprite = sprite;
		this.id = id;
		this.solido = false;
	}
	
	public Tile(Sprite sprite, final int id, boolean solido){
		this.sprite = sprite;
		this.id = id;
		this.solido = solido;
	}

	public boolean isSolido() {
		return solido;
	}

	public void setSolido(final boolean solido) {
		this.solido = solido;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getId() {
		return id;
	}
	
	public Rectangle obtenerLimites(final int x, final int y){
		return new Rectangle( x, y, sprite.getAncho(), sprite.getAlto());	
	}
}
