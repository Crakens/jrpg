package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Hechicero;
import personaje.Humano;
import personaje.Personaje;

public class SoloLosQueSonMagosTest {

	//### Historia de usuario 15 ###//
	@Test
	public void soloLosQueSonMagos(){
		Personaje perso=new Humano();
		Especialidad c=new Guerrero();
		
		perso.setCasta(c);
		perso.bonificacionDeCasta();
		
		Personaje hech=new Humano();
		Especialidad e=new Hechicero();
		
		hech.setCasta(e);
		hech.bonificacionDeCasta();
		
		//humanoGuerrero
		Assert.assertEquals(0, perso.getCasta().getMagia());
		//humanoHechicero
		Assert.assertEquals(50, hech.getCasta().getMagia());
		
	}
}
