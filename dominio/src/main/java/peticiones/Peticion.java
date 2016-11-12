package peticiones;
import cs.ServerTh;

public class Peticion {
	
	public static final String CARACTER_SEPARACION = ";";

	
	private String[] datosPeticion;
	private int codPeticion;
	private String respuesta;
	
	public Peticion (String peticion, ServerTh serverTh) {
		this.datosPeticion = peticion.split(CARACTER_SEPARACION);	
		this.codPeticion = Integer.parseInt(datosPeticion[0]);
		this.respuesta = "";
		
		switch (this.codPeticion) {
		case CodigoPeticion.LOGEO:
			PeticionLogueo petLog = new PeticionLogueo(datosPeticion, serverTh);
			this.respuesta = petLog.getRespuesta();
			break;		
		
			
		case CodigoPeticion.CREAR_JUGADOR:
			PeticionCrearJugador  petCrearP = new PeticionCrearJugador(datosPeticion, serverTh);
			this.respuesta = petCrearP.getRespuesta();
			break;

		
			
		default:
			break;
		}
		
	}	

	public String getRespuesta() {
		return respuesta;
	}
}