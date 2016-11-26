package test;

import org.junit.Assert;
import org.junit.Test;

import principal.entes.personajes.Engorgio;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Hechicero;
import principal.entes.personajes.Humano;
import principal.entes.personajes.Orco;
import principal.entes.personajes.Personaje;

public class AtaquesDeMago {
	
	//### Historia de usuario 22 ###//### Historia de usuario 28 ###//
	@Test 
	
	public void ataqueDeMagoSinMagia(){
		Personaje perso=new Humano("Hombre");
		Especialidad mago=new Hechicero();
		perso.setCasta(mago);
		perso.bonificacionDeCasta();
		
		Personaje enemigo=new Orco("Mujer");
		Especialidad guerrero=new Guerrero();
		enemigo.setCasta(guerrero);
		enemigo.bonificacionDeCasta();
		
		Assert.assertEquals(50,perso.getCasta().getMagia());
		Assert.assertEquals(7,perso.getFuerza());
		Assert.assertEquals(44,perso.getEnergia());
		Assert.assertEquals(17,perso.calcularPuntosDeAtaque());
		
		//ataque normal
		perso.atacar(enemigo);
		
		Assert.assertEquals(50,perso.getCasta().getMagia());
		Assert.assertEquals(7,perso.getFuerza());
		Assert.assertEquals(32,perso.getEnergia());
		Assert.assertEquals(17,perso.calcularPuntosDeAtaque());
		
		// utilizar hechizo
		perso.getCasta().getHechicero().agregarHechizo("Engorgio", new Engorgio());
		perso.getCasta().getHechicero().hechizar("Engorgio",enemigo);
		
		Assert.assertEquals(20,perso.getCasta().getMagia());
		Assert.assertEquals(7,perso.getFuerza());
		Assert.assertEquals(32,perso.getEnergia());
		Assert.assertEquals(17,perso.calcularPuntosDeAtaque());
	}
}
