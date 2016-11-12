package test;

import org.junit.Assert;
import org.junit.Test;
import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Personaje;
import enemigos.Enemigo;
import enemigos.Goblin;

public class RestablecerEnergiaTest {

	//### Historia de usuario 12 ###//
	@Test 
	public void restablecerEnergia(){
		Personaje perso=new Humano();
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
