package principal.batallones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import principal.entes.enemigos.Enemigo;
import principal.entes.personajes.Atacable;

public class BatallonEnemigos {

protected List <Enemigo> batallon =new LinkedList <Enemigo>();
private List<Integer> almas= new ArrayList<Integer>();
private int experienciaTot=0;

	public boolean agregar(Enemigo enemigo){
		
		return batallon.add(enemigo);
	}

	public Atacable obtenerProximaVictima(int i){
		
		depurarBatallon();
		
		if(batallon.isEmpty()){
			throw new RuntimeException("El batallon de Personajes esta vacio");
		}
		
		return batallon.get(i);
	}

	
	public final void depurarBatallon(){
		
		Iterator <Enemigo> iter= batallon.iterator();
		
		while(iter.hasNext()){
			Enemigo e= iter.next();
			if(!e.estaVivo()){
				almas.add(e.devolverItem());
				experienciaTot+=e.devolverExperiencia();
				iter.remove();
			}
		}
	}
	
	public Enemigo getPersonaje(int i){
		
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
}
