package principal.items.equipados;

import principal.entes.personajes.Personaje;
import principal.entes.personajes.PersonajeEquipado;


public class ConGranAlmaDelCoraje extends PersonajeEquipado {

	public ConGranAlmaDelCoraje(Personaje personajeDecorado) {
		
		super(4,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() * 2;
	}
	
}