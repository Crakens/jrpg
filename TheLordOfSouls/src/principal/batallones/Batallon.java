package principal.batallones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import principal.entes.enemigos.Enemigo;
import principal.entes.personajes.Atacable;
import principal.entes.personajes.Personaje;

public class Batallon {
	
protected List <Personaje> batallon =new LinkedList <Personaje>();
private List<Integer> almas=new ArrayList<Integer>();   //las almas que devuelve el batallon al derrotarlos
private int experienciaTot=0; //la experiencia que devuelve al derrotar el Batallon
	
//	public boolean atacar(Batallon otro){
//		
//		Atacable victima = otro.obtenerProximaVictima();
//		boolean combatiendo = false;
//		
//		for(Personaje atacante: batallon){
//			combatiendo=atacante.atacar(victima);
//		}
//		
//		return combatiendo;
//	}
	
	public boolean agregar(Personaje personaje){
		
		return batallon.add(personaje);
	}
	
	public Atacable obtenerProximaVictima(int i){
		
		depurarBatallon();
		
		if(batallon.isEmpty()){
			throw new RuntimeException("El batallon de Personajes esta vacio");
		}
		
		return batallon.get(i);
	}

	
	public final void depurarBatallon(){
		
		Iterator <Personaje> iter= batallon.iterator();
		
		while(iter.hasNext()){
			
			if(!iter.next().estaVivo()){
				Personaje e= iter.next();
				almas.add(e.perderItemMasValioso(e));
				experienciaTot+=e.devolverExperiencia();
				iter.remove();
			}
		}
	}
	
	public Personaje getPersonaje(int i){
		
		return this.batallon.get(i);
	}
	
	public int getTamBatallon(){
		
		return this.batallon.size();
	}
	
	public void ordeNarBatallonPorMayorAgilidad(){
		
		Collections.sort(this.batallon);
	}

	public List<Integer> getAlmas() {
		return almas;
	}

	public int getExperienciaTot() {
		return experienciaTot;
	}

	public void setPersonajeDecorado(int i, Personaje aux) {
		
		this.batallon.set(i, aux);
	}
}
