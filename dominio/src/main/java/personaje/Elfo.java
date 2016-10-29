package personaje;

public class Elfo extends Personaje{
	
	public Elfo(){
		
		 this.fuerza= 5;
		 this.fatiga=10;
		 this.energiaTot=60;
		 this.energia = energiaTot;
		 this.saludTot = 50;
		 this.salud= saludTot;
		 this.agilidad=10;
		 this.defensa=10;
		 this.recuperacion = 4;
		 this.raza="Elfo";
	}
	
	@Override
	protected boolean puedeAtacar() {
		
		return energia >= fatiga;
		
	}

	@Override
	public int calcularPuntosDeAtaque() {
		
		return fuerza + casta.calcularPuntosDeAtaque();
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		
		return defensa + casta.obtenerPuntosDeDefensa();
	}

}
