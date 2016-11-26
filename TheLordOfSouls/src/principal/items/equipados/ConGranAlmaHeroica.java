package principal.items.equipados;

import principal.entes.personajes.Personaje;
import principal.entes.personajes.PersonajeEquipado;


public class ConGranAlmaHeroica extends PersonajeEquipado {

	public ConGranAlmaHeroica(Personaje personajeDecorado) {
		super(3,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return super.calcularPuntosDeAtaque() + 10;
	}
	
}