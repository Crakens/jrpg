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

	@Test
	public void quePuedoHechizar() {
		Personaje gimli = new Enano();
		Assert.assertEquals(120, gimli.getAltura());
		new Engorgio().afectar(gimli);
		Assert.assertEquals(240, gimli.getAltura());
	}
	
	@Test
	public void quePuedoAgregarHechizos() {
		Personaje gandalf = new Humano();
        Especialidad casta= new Hechicero();
        gandalf.setCasta(casta);
        gandalf.bonificacionDeCasta();
        
		Assert.assertEquals(0, gandalf.getCasta().getHechicero().getCantidadDeHechizos());
		gandalf.getCasta().getHechicero().agregarHechizo("engorgio", new Engorgio());
		Assert.assertEquals(1, gandalf.getCasta().getHechicero().getCantidadDeHechizos());
	}

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
	}
	
}
