package enemigos;

public class Dragon extends Enemigo{

	int ataquesRecibidos;
	
	
	public Dragon(){
		
		this.nombre= "Dragon";
		this.energiaTot= 50;
		this.energia= energiaTot;
		this.salud= 120;
		this.experiencia= 300;
		this.idAlma= 4;
		this.agilidad = 3;
		this.fatiga = 15;
		this.recuperacion = 5;
	}
	
	@Override
	protected int calcularPuntosDeAtaque() {
		return (50 + ataquesRecibidos);
	}

	@Override
	public void serAtacado(int dano) {
		
		super.serAtacado(dano);
		this.ataquesRecibidos++;
	}
}
