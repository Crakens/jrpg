package principal;

import principal.entes.Jugador;
import principal.entes.personajes.Personaje;
import principal.mapas.MapaTiled;

public class ElementosPrincipales {
	
	public static MapaTiled mapa = new MapaTiled(Constantes.RUTA_MAPA);
	public static Jugador jugador;
	
	public static void crearJugador(Personaje p){
		jugador= new Jugador(p);
	}
}
