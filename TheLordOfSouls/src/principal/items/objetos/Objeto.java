package principal.items.objetos;

import java.awt.Rectangle;

import principal.sprites.Sprite;

public abstract class Objeto {

	// public static HojaSprites hojaObjetos = new
	// HojaSprites(Constantes.RUTA_OBJETOS, Constantes.LADO_SPRITE, false);

	protected final int id;
	protected final String nombre;
	protected final String descripcion;

	protected int cantidad;
	protected int cantidadMaxima;

	protected Rectangle posicionMenu;
	protected Rectangle posicionFlotante;

	public Objeto(final int id, final String nombre, final String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public abstract Sprite obtenerSprite();

	public int obtenerId() {
		return id;
	}

	public String obtenerNombre() {
		return nombre;
	}
}
