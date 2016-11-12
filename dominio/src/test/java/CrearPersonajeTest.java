package test;

import org.junit.Assert;
import org.junit.Test;

import personaje.Elfo;
import personaje.Humano;
import personaje.Orco;
import personaje.Personaje;

public class CrearPersonajeTest {

	//### Historia de usuario 1 ###//
	@Test
	public void crearHumano() {
		Personaje persHumano = new Humano();
		Assert.assertEquals(7, persHumano.getAgilidad());
		Assert.assertEquals(7,persHumano.getFuerza());
		Assert.assertEquals(7,persHumano.getDefensa());
		Assert.assertEquals(50,persHumano.getEnergia());
		Assert.assertEquals(60,persHumano.getSalud());
		Assert.assertEquals(12,persHumano.getFatiga());
		Assert.assertEquals(5,persHumano.getRecuperacion());
		Assert.assertEquals("Humano",persHumano.getRaza());
	}
	
	@Test
	public void crearOrco() {
		Personaje persOrco = new Orco();
		Assert.assertEquals(6, persOrco.getAgilidad());
		Assert.assertEquals(10,persOrco.getFuerza());
		Assert.assertEquals(7,persOrco.getDefensa());
		Assert.assertEquals(40,persOrco.getEnergia());
		Assert.assertEquals(70,persOrco.getSalud());
		Assert.assertEquals(10,persOrco.getFatiga());
		Assert.assertEquals(3,persOrco.getRecuperacion());
		Assert.assertEquals("Orco",persOrco.getRaza());
	}
	
	@Test
	public void crearElfo() {
		Personaje persElfo = new Elfo();
		Assert.assertEquals(10, persElfo.getAgilidad());
		Assert.assertEquals(5,persElfo.getFuerza());
		Assert.assertEquals(10,persElfo.getDefensa());
		Assert.assertEquals(60,persElfo.getEnergia());
		Assert.assertEquals(50,persElfo.getSalud());
		Assert.assertEquals(10,persElfo.getFatiga());
		Assert.assertEquals(4,persElfo.getRecuperacion());
		Assert.assertEquals("Elfo",persElfo.getRaza());
	}
	
}
