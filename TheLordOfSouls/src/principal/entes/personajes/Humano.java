package principal.entes.personajes;

public class Humano extends Personaje{
	
	public Humano(String sexo){
		
		 this.sexo=sexo;
		 this.fuerza= 7;
		 this.fatiga=12;
		 this.energiaTot=50;
		 this.energia = energiaTot;
		 this.saludTot = 60;
		 this.salud= saludTot;
		 this.agilidad=7;
		 this.defensa=7; 
		 this.recuperacion = 5;
		 if (this.sexo == "Mujer") {
			 this.raza="Humana";
		}
		 else
			 this.raza="Humano";
	}
	
	@Override
	public int calcularPuntosDeAtaque(){
		
		return fuerza + casta.calcularPuntosDeAtaque();
	}
	
	@Override
	protected boolean puedeAtacar(){
		
		return energia>=fatiga;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		
		return defensa + casta.obtenerPuntosDeDefensa();
	}
	
		
}
