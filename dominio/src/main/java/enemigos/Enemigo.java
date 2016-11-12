package enemigos;

import personaje.Atacable;
import personaje.Personaje;


public abstract class Enemigo implements Atacable, Comparable < Enemigo >{

	protected String nombre;
	protected int energiaTot;
	protected int energia;
	protected int salud;
	protected int experiencia;
	protected int idAlma;
	protected int agilidad;
	protected int fatiga;
	protected int recuperacion;
	protected int altura=1;
	
	public final boolean atacar(Atacable atacado) {
		
		if(atacado.estaVivo()){
			if(puedeAtacar()){
				System.out.println("El enemigo realiza un ataque");
				atacado.serAtacado(calcularPuntosDeAtaque()); //modificado//
				energia-= fatiga ; //sugiero metodo aparte para calcular la energia que gasta//
			}
			
			return true;
		}
		
		return false;
	}

	public boolean estaVivo(){
		
		if(this.salud<=0){
			return false;
		}
		
		return true;
	}
	
	protected boolean puedeAtacar() {
		return energia >= fatiga;
	}
	
	protected abstract int calcularPuntosDeAtaque();
	
	
	@Override
	public void serAtacado(int dano) {
		this.salud -= dano;
	}
	
	public void serAgrandado(){
		
		this.altura *= 2;
	}
	
	public int devolverItem(){
		
		return idAlma;
	}
	
	public int devolverExperiencia(){
		
		return experiencia;
	}
	
	public int getAgilidad() {
		return agilidad;
	}

	public void serEnergizado() {
		
		if(this.energia + this.recuperacion > this.energiaTot)
			this.energia = energiaTot;
		else
			this.energia+= recuperacion;
	}
	
	public int compareTo(Enemigo e) { 
		
		if(this.getAgilidad() > e.getAgilidad())
				return -1;
		
		if(this.getAgilidad() < e.getAgilidad())
				return 1;
			
		return 0;
	}
}
