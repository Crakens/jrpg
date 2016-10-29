package enemigos;

public class Goblin extends Enemigo{
	
	public Goblin(){
	
	this.nombre= "Goblin";
    this.energiaTot = 20;
	this.energia= energiaTot;
	this.salud= 40;
	this.experiencia= 10;
	this.idAlma= 3;
	this.agilidad = 5;
	this.fatiga = 10;
	this.recuperacion = 5; 
	
	}

	@Override
	protected int calcularPuntosDeAtaque() {
		return 18;
	}

	@Override
	public void serAtacado(int dano) {
		super.serAtacado(dano);
	}
}
