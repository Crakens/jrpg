package principal.items.objetos;

import principal.Constantes;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;

public class CuraSalud extends Objeto {

	public static HojaSprites hojaConsumibles = new HojaSprites(Constantes.RUTA_OBJETOS, 24, false); //24 el tamaño del objeto

	public CuraSalud(int id, String nombre, String descripcion) {
		super(id, nombre, descripcion);
	}

	@Override
	public Sprite obtenerSprite() { 
		return hojaConsumibles.obtenerSprite(id);
	}

}
