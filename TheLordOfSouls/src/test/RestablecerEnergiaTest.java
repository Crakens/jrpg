package test;

import org.junit.Assert;
import org.junit.Test;

import principal.entes.enemigos.Enemigo;
import principal.entes.enemigos.Goblin;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Humano;
import principal.entes.personajes.Personaje;

public class RestablecerEnergiaTest {

	//### Historia de usuario 12 ###//
	@Test 
	public void restablecerEnergia(){
		Personaje perso=new Humano("Mujer");
		Especialidad c=new Guerrero();
		
		perso.setCasta(c);
		perso.bonificacionDeCasta();
		
		Enemigo enemigo=new Goblin();
		
		while(enemigo.estaVivo()&& perso.estaVivo()){
			
			perso.atacar(enemigo);
			enemigo.serEnergizado();
			
			if(enemigo.estaVivo()){
				enemigo.atacar(perso);
				perso.serEnergizado();
			}
		
		}

		Assert.assertEquals(35,perso.getEnergia());
		
		perso.serEnergizadoTotalmente();
		

		Assert.assertEquals(54,perso.getEnergia());
	}
}
