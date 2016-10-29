package Batallones;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personaje.Atacable;
import personaje.Personaje;

public class Batallon {
	
protected List <Personaje> batallon =new LinkedList <Personaje>();
	
	public void atacar(Batallon otro){
		
		Atacable victima = otro.obtenerProximaVictima();
		
		for(Personaje atacante: batallon){
			atacante.atacar(victima);
		}
	}
	
	public boolean agregar(Personaje personaje){
		
		return batallon.add(personaje);
	}
	
	public Atacable obtenerProximaVictima(){
		
		depurarBatallon();
		
		if(batallon.isEmpty()){
			throw new RuntimeException("El batallon de Personajes esta vacio");
		}
		
		return batallon.get(0);
	}

	
	public final void depurarBatallon(){
		
		Iterator <Personaje> iter= batallon.iterator();
		
		while(iter.hasNext()){
			
			if(!iter.next().estaVivo())
				iter.remove();
		}
	}
}
