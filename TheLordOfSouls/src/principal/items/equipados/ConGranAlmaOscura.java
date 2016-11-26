package principal.items.equipados;

import principal.entes.personajes.Personaje;
import principal.entes.personajes.PersonajeEquipado;


public class ConGranAlmaOscura extends PersonajeEquipado {

	public ConGranAlmaOscura(Personaje personajeDecorado) {
		super(2,personajeDecorado);
	}

	@Override
	public int calcularPuntosDeAtaque() {
		 if(super.calcularPuntosDeAtaque() - 3 < 1)
		 	return 1;
		 else
			return super.calcularPuntosDeAtaque() - 3;
	}
	
}