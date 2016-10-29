package personaje;

public class Orco extends Personaje{
	
	int cantidadDeAtaques;
	
	public Orco(){
		
		 this.fuerza= 10;
		 this.fatiga=10;
		 this.energiaTot=40;
		 this.energia= energiaTot;
		 this.saludTot = 70;
		 this.salud=saludTot;
		 this.agilidad=6;
		 this.defensa=7; 
		 this.recuperacion=3;
		 this.raza="Orco";
	}
	
	
	@Override
	public void despuesDeAtacar() {
		
		cantidadDeAtaques++;
		
		
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		
		return fuerza + cantidadDeAtaques + casta.calcularPuntosDeAtaque();
	}

	@Override
	protected boolean puedeAtacar() {
		
		return energia >= fatiga;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		
		return defensa + casta.obtenerPuntosDeDefensa();
	}
	
}
