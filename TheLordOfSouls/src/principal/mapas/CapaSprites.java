package principal.mapas;

public class CapaSprites extends CapaTiled{

	private int [] sprites;
	
	public CapaSprites(int acho, int alto, int x, int y, int [] sprites) {
		super(acho, alto, x, y);
		this.sprites= sprites;
	}
	
	public int[] obtenerArraySprites(){
		return sprites;
	}
}
