package test;

import org.junit.Assert;
import org.junit.Test;

import principal.entes.enemigos.Enemigo;
import principal.entes.enemigos.Goblin;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Orco;
import principal.entes.personajes.Personaje;

public class EnergiaTest {
	
	//### Historia de usuario 12 ###//### Historia de usuario 27 ###//
	@Test
	public void energiaTest(){
		
		Personaje personaje=new Orco("Mujer"); 
		Especialidad casta=new Guerrero();
		personaje.setCasta(casta);
		personaje.bonificacionDeCasta();
		
		Enemigo e= new Goblin();
							 
		personaje.atacar(e); //energia 44
		personaje.atacar(e); //energia 34
		personaje.atacar(e); //energia 24
		personaje.atacar(e); //energia 14
							 //energia 4 ya no puede atacar xq fatiga=10
		
		Assert.assertEquals(false, personaje.atacar(e));
		
		personaje.serEnergizadoTotalmente();
		
		Assert.assertEquals(44, personaje.getEnergia());
		
	}

}
