package item;

import personaje.Personaje;
import personaje.PersonajeEquipado;


public class ConGranAlmaOscura extends PersonajeEquipado {

	public ConGranAlmaOscura(Personaje personajeDecorado) {
		super(2,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() - 10;
	}
	
}