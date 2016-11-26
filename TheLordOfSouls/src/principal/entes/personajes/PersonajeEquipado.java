package principal.entes.personajes;

public abstract class PersonajeEquipado extends Personaje{
	
	Personaje personajeDecorado;
	int idAlma;
	
	public PersonajeEquipado( Integer idAlma, Personaje personajeDecorado) {
		
		this.personajeDecorado = personajeDecorado; 
		this.idAlma = idAlma;
		this.equipado = this;
		this.nombrePersonaje = personajeDecorado.nombrePersonaje;
		this.fuerza = personajeDecorado.fuerza;
		this.energia = personajeDecorado.energia;
		this.energiaTot = personajeDecorado.energiaTot;
		this.salud = personajeDecorado.salud;
		this.saludTot = personajeDecorado.saludTot;
		this.agilidad = personajeDecorado.agilidad;
		this.defensa = personajeDecorado.defensa;
		this.fatiga = personajeDecorado.fatiga;
		this.casta = personajeDecorado.casta;
		this.raza = personajeDecorado.raza;
		this.nivel = personajeDecorado.nivel;
		this.experienciaAcum = personajeDecorado.experienciaAcum;
		this.siguienteNivel = personajeDecorado.siguienteNivel;
		this.recuperacion = personajeDecorado.recuperacion;
	}

	@Override
	protected boolean puedeAtacar() {
		return this.personajeDecorado.puedeAtacar();
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		return this.personajeDecorado.calcularPuntosDeAtaque();
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		return this.personajeDecorado.obtenerPuntosDeDefensa();
	}
	
	public String toString() {
		
		return "nombrePersonaje=" + personajeDecorado.nombrePersonaje
				+ "\nCaracteristicas [energia=" + personajeDecorado.energia 
				+ ", Ataque=" + this.calcularPuntosDeAtaque() 
				+", salud=" + personajeDecorado.salud + "/" + personajeDecorado.saludTot 
				+ ", agilidad=" + personajeDecorado.agilidad
				+ ", defensa=" + this.obtenerPuntosDeDefensa() 
				+ ", magia=" + personajeDecorado.casta.magia  + "/" + personajeDecorado.casta.magiaTot
				+ ",casta=" + personajeDecorado.casta.nombre 
				+ ", raza="+ personajeDecorado.raza + "]" 
				+ "\nDescripcion Casta: " + personajeDecorado.casta.descripcion;
	}
	
}
