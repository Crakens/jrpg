package test;

import org.junit.Assert;
import org.junit.Test;

import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Hechicero;
import principal.entes.personajes.Humano;
import principal.entes.personajes.Personaje;

public class SoloLosQueSonMagosTest {

	//### Historia de usuario 15 ###//
	@Test
	public void soloLosQueSonMagos(){
		Personaje perso=new Humano("Mujer");
		Especialidad c=new Guerrero();
		
		perso.setCasta(c);
		perso.bonificacionDeCasta();
		
		Personaje hech=new Humano("Humano");
		Especialidad e=new Hechicero();
		
		hech.setCasta(e);
		hech.bonificacionDeCasta();
		
		//humanoGuerrero
		Assert.assertEquals(0, perso.getCasta().getMagia());
		//humanoHechicero
		Assert.assertEquals(50, hech.getCasta().getMagia());
		
	}
}
