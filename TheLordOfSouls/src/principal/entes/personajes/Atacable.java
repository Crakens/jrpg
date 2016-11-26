package principal.entes.personajes;

public interface Atacable {
	
	public void serAtacado(int daño);

	public boolean estaVivo();

	public void serAgrandado();

	public int getAgilidad();
}
