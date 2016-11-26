package test;


import org.junit.Assert;
import org.junit.Test;

import principal.entes.personajes.Enano;
import principal.entes.personajes.Engorgio;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Hechicero;
import principal.entes.personajes.Humano;
import principal.entes.personajes.Personaje;

public class HechizosTests {

	//### Historia de usuario 28 ###//
	
	@Test
	public void queUnPersonajePuedeHechizarPorNombre() {
		
		Personaje gandalf = new Humano("Hombre");
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
