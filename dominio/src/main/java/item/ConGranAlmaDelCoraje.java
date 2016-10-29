package item;

import personaje.Personaje;
import personaje.PersonajeEquipado;


public class ConGranAlmaDelCoraje extends PersonajeEquipado {

	public ConGranAlmaDelCoraje(Personaje personajeDecorado) {
		
		super(4,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() * 2;
	}
	
}