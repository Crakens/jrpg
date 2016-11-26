package principal.entes.personajes;

import java.util.Scanner;

public abstract class Personaje implements Atacable,Comparable < Personaje >{
	
	protected String nombrePersonaje;
	protected int fuerza;
	protected int energia;
	protected int energiaTot;
	protected int salud;
	protected int saludTot;
	protected int agilidad;
	protected int defensa;
	protected int fatiga;
	public Especialidad casta; //Guerrero, Hechicero, Ladron ...etc// //modificado//
	protected String raza; //Humano, Orco, Elfo //
	protected int nivel=0; //cero el nivel inicial del personaje/
	protected int experienciaAcum=0;
	protected int siguienteNivel=30; //por defeto 30 de experiencia para pasar a nivel 1//
	protected PersonajeEquipado equipado;
	public int altura=1;
	protected int recuperacion;
	protected String sexo;
	
	////
	
	public final boolean atacar(Atacable atacado){
		
		if(atacado.estaVivo()){
			if(puedeAtacar()){
			
				atacado.serAtacado(calcularPuntosDeAtaque());
				this.energia-=this.fatiga;
				despuesDeAtacar();
			}
			
			return true; //si esta vivo por mas que no halla podido atacar el comabte sigue//
		}
		
		return false; //una ves el atacable esta muerto el combate termina//
		
	}
	
	public int getEnergiaTot() {
		return energiaTot;
	}

	protected void despuesDeAtacar(){ }
	
	protected abstract boolean puedeAtacar();
	
	public abstract int calcularPuntosDeAtaque();
	
	public abstract int obtenerPuntosDeDefensa();
	
	public boolean estaVivo(){
		
		if(this.salud<=0)
			return false;
		
		return true;
	}
	
	public void serAtacado(int danio){
		
		if((danio - obtenerPuntosDeDefensa()) > 0)
			this.salud-=(danio - obtenerPuntosDeDefensa()); //el danio se reduce en base a la defensa del personaje//
		else
			this.salud-= 1; //danio por defecto//cuando la defensa supera a el danio//
	}
	
	public void serCuradoTotalmente(){
		this.salud = this.saludTot;
	}
	
	public void serCurado(int cura){ //se usaria cuando el Hechicero cura o cuando se este en la posada descansando//
		if (cura + this.salud >= this.saludTot) {
			this.salud= saludTot;
		}
		else
			this.salud+= cura;
	}
	
	public void serEnergizadoTotalmente(){ //se usaria al finalizar cada batalla//
		
		this.energia= this.energiaTot;
	}
	
	public void serEnergizado(){ //se usaria al finalizar cada batalla//
		
		if(this.energia + recuperacion > this.energiaTot)
			this.energia = this.energiaTot;
		else
			this.energia+=recuperacion;
	}
	
	public Especialidad getCasta() {
		return casta;
	}
	
	public void setCasta(Especialidad casta) {
		
		this.casta = casta;
	}
	
	public void serAgrandado(){
		
		this.altura *= 2;
	}
	
	public int getSaludTotal(){
			
			return this.saludTot;
	}


	public int getSalud(){
		
		return this.salud;
	}
	
	public int getEnergia() {
		return energia;
	}
	
	public void setEnergia(int energia) {
		this.energia = energia;
	}
	
	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	
	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	public String getNombrePersonaje() {
		return nombrePersonaje;
	}

	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}
	
	public int getAgilidad() {
		return agilidad;
	}
	
	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getFatiga() {
		return fatiga;
	}

	public void setFatiga(int fatiga) {
		this.fatiga = fatiga;
	}

	public int getRecuperacion() {
		return recuperacion;
	}

	public void setRecuperacion(int recuperacion) {
		this.recuperacion = recuperacion;
	}

	public int getExperienciaAcum() {
		return experienciaAcum;
	}

	public void setExperienciaAcum(int experienciaAcum) {
		this.experienciaAcum = experienciaAcum;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getSiguienteNivel() {
		return siguienteNivel;
	}

	public void setSiguienteNivel(int siguienteNivel) {
		this.siguienteNivel = siguienteNivel;
	}
	
	public void bonificacionDeCasta(){
		
		this.saludTot+=this.casta.salud;
		this.salud = this.saludTot;
		this.defensa+=this.casta.defensa;
		this.energiaTot+=this.casta.energia;
		this.energia = this.energiaTot;
		this.agilidad+=this.casta.agilidad;
	}

	@Override
	public String toString() {
		return "nombrePersonaje=" + nombrePersonaje 
				+ "\nCaracteristicas [energia=" + energia 
				+ ", Ataque=" + calcularPuntosDeAtaque() 
				+", salud=" + salud + "/" + saludTot 
				+ ", agilidad=" + agilidad
				+ ", defensa=" + obtenerPuntosDeDefensa() 
				+ ", magia=" + casta.magia + "/" + casta.magiaTot
				+ ",casta=" + casta.nombre 
				+ ", raza=" + raza + "]" 
				+ "\nDescripcion Casta: " + casta.descripcion;
	}
	
	public void ganarExperiencia(int exp){
		
		this.experienciaAcum+=exp;
		subirNivel();
	}
	
	public void subirNivel(){
		
		if(experienciaAcum > siguienteNivel){
			nivel++;
			experienciaAcum-= siguienteNivel; //resetea experienciaAcumulada para volver a contar//
			siguienteNivel +=siguienteNivel + 30; //el tope para subir de nuevo de nivel es cada ves mas alto//puse 300 por poner un numero// 
		}
	}
	
	public int perderItemMasValioso(Personaje personajeDecorado){
		
		Personaje p=personajeDecorado;
		Personaje pAnterior= null, pAnteriorAlMasValioso=null, itemMasValioso = null;
		int valorDelItem = 0;
		
		while(p.equipado != null){
			
			if(p.equipado.idAlma > valorDelItem ){
				
				valorDelItem = p.equipado.idAlma;
				itemMasValioso = p.equipado;
				pAnteriorAlMasValioso = pAnterior;
			}
			
			pAnterior = p.equipado;
			p = p.equipado.personajeDecorado;
		}
		
		if(pAnteriorAlMasValioso != null){
			pAnteriorAlMasValioso.equipado.personajeDecorado = itemMasValioso.equipado.personajeDecorado; //Este anda Barbaro//
			personajeDecorado = pAnteriorAlMasValioso;
		}
		else{
			
			if(itemMasValioso != null){
	
				personajeDecorado = itemMasValioso.equipado.personajeDecorado; //No funciona!!!!!!!//
				
			}
			
		}
		
		return valorDelItem;
	}

	public int devolverExperiencia() {
		
		return experienciaAcum/2; //el matar un personaje t devuelve la mitad de su experiencia//
	}
	
	public int compareTo(Personaje p) { 
		
		if(this.getAgilidad() > p.getAgilidad())
				return -1;
		
		if(this.getAgilidad() < p.getAgilidad())
				return 1;
			
		return 0;
	}

	public void incrementarFuerza() {
		this.fuerza++;
	}
	
	public void incrementarDefensa() {
		this.defensa++;
	}
	
	public void incrementarAgilidad() {
		this.agilidad++;
	}
	
	public void incrementarSaludTotal() {
		this.saludTot++;
	}
	
	public void incrementarEnergiaTotal() {
		this.energiaTot++;
	}
}

