package item;

import personaje.Personaje;
import personaje.PersonajeEquipado;


public class ConGranAlmaHeroica extends PersonajeEquipado {

	public ConGranAlmaHeroica(Personaje personajeDecorado) {
		super(3,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() + 10;
	}
	
}