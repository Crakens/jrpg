package principal.items.equipados;

import principal.entes.personajes.Personaje;
import principal.entes.personajes.PersonajeEquipado;


public class ConGranAlmaCorrupta extends PersonajeEquipado {

	public ConGranAlmaCorrupta(Personaje personajeDecorado) {
		super(1, personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		
		return (int)(super.calcularPuntosDeAtaque()/2);
	}
	
}