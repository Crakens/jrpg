package test;


import org.junit.Assert;
import org.junit.Test;

import personaje.Enano;
import personaje.Engorgio;
import personaje.Especialidad;
import personaje.Hechicero;
import personaje.Humano;
import personaje.Personaje;

public class HechizosTests {

	//### Historia de usuario 28 ###//
	
	@Test
	public void queUnPersonajePuedeHechizarPorNombre() {
		
		Personaje gandalf = new Humano();
        Especialidad casta= new Hechicero();
        gandalf.setCasta(casta);
        gandalf.bonificacionDeCasta();
		
		gandalf.getCasta().getHechicero().agregarHechizo("engorgio", new Engorgio());
		Personaje gimli = new Enano();
		Assert.assertEquals(120, gimli.getAltura());
		gandalf.getCasta().getHechicero().hechizar("engorgio", gimli);
		Assert.assertEquals(240, gimli.getAltura());
		Assert.assertEquals(false, gandalf.getCasta().getHechicero().hechizar("engorgio", gimli));
	}
	
}
