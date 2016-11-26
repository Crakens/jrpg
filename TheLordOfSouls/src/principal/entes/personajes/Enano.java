package principal.entes.personajes;

public class Enano extends Personaje{

	public Enano() {
		this.altura = 120;
	}

	@Override
	protected boolean puedeAtacar() {
		
		return false;
	}

	@Override
	public int calcularPuntosDeAtaque() {
		
		return 10;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		
		return super.defensa;
	}
}
