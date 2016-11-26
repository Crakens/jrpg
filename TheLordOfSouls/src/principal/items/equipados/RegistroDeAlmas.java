package principal.items.equipados;

import principal.entes.personajes.Personaje;

public class RegistroDeAlmas {
	
	public static Personaje asignarAlma(int idAlma,Personaje p){
		
		//Un caso por cada alma creada//El valor del caso da el valor del alma asique importa el orden en que se lo ponga//
		
		switch (idAlma) {
		
		case 1:
		{
			return  new ConGranAlmaCorrupta(p);	
		}
		
		case 2:
		{
			return new ConGranAlmaOscura(p);
		}
		
		case 3:
		{
			return new ConGranAlmaHeroica(p);
		}
				
		case 4:
		{
			return new ConGranAlmaDelCoraje(p);
		}
				
		default:
			break;
		}
		
		return null;
	}
	
}
