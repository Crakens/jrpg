package principal.items.objetos;


public class RegistroObjetos {

	public static Objeto obtenerObjeto(final int idObjeto) {

		Objeto objeto = null;

		switch (idObjeto) {
		// 0-1: objetos 
		case 0:
			objeto = new CuraSalud(idObjeto, "Cura salud", "Cura parcialmente al personaje inmediatamente despues de haberla recogido del mapa");
			break;
		case 1:
			objeto = new RecuperaMagia(idObjeto, "Recupera Magica", "Reestablece la magia parcialmente del personaje inmediatamente despues de haberla recogido del mapa");
			break;
		}
		return objeto;
	
	}
}