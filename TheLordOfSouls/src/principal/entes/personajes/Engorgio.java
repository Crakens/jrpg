package principal.entes.personajes;

public class Engorgio implements Hechizo{
	
	int gastaMagia=30; //el valor de energia que reduce realizar este hechizo//
	
	public void afectar(Atacable personaje) {
		personaje.serAgrandado();
	}

	public int getGastaEnergia() {
		
		return gastaMagia;
	}
	
}
