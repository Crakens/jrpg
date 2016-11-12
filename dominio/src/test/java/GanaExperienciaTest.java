package test;

import org.junit.Assert;
import org.junit.Test;

import enemigos.Enemigo;
import enemigos.Goblin;

import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Personaje;

public class GanaExperienciaTest {

	//### Historia de usuario 3 ###//
	@Test
	public void ganarExperiencia(){
		Personaje perso=new Humano();
		Especialidad c=new Guerrero();
		
		perso.setCasta(c);
		perso.bonificacionDeCasta();
		
		Enemigo enemigo=new Goblin();
		Assert.assertEquals(0, perso.getExperienciaAcum());
		enemigo.serAtacado(30);
		
		while(enemigo.estaVivo()){
			perso.atacar(enemigo);
		}
		
		perso.ganarExperiencia(enemigo.devolverExperiencia());
		Assert.assertEquals(10, perso.getExperienciaAcum());
		
	}
	
	//### Historia de usuario 4 ###//### Historia de usuario 5 ###//
	@Test
	public void subirDeNivel(){
		Personaje pers=new Humano();
		Especialidad c=new Guerrero();
		
		pers.setCasta(c);
		pers.bonificacionDeCasta();
		Enemigo enemigo=new Goblin();
		Assert.assertEquals(0, pers.getExperienciaAcum());
		enemigo.serAtacado(30);
		
		while(enemigo.estaVivo()){
			pers.atacar(enemigo);
			System.out.println("aca");
		}
		
		pers.ganarExperiencia(enemigo.devolverExperiencia());
		Assert.assertEquals(0, pers.getNivel());
		Assert.assertEquals(5, pers.getAgilidad());
		Assert.assertEquals(7,pers.getFuerza());
		Assert.assertEquals(7,pers.getDefensa());
		Assert.assertEquals(42,pers.getEnergia());
		Assert.assertEquals(64,pers.getSaludTotal());
		Assert.assertEquals(12,pers.getFatiga());
		Assert.assertEquals(5,pers.getRecuperacion());
		Assert.assertEquals("Humano",pers.getRaza());
		
		pers.ganarExperiencia(25);
		Assert.assertEquals(1, pers.getNivel());
		Assert.assertEquals(5, pers.getAgilidad());
		Assert.assertEquals(11,pers.getFuerza());
		Assert.assertEquals(7,pers.getDefensa());
		Assert.assertEquals(42,pers.getEnergia());
		Assert.assertEquals(65,pers.getSaludTotal());
		Assert.assertEquals(12,pers.getFatiga());
		Assert.assertEquals(5,pers.getRecuperacion());
		Assert.assertEquals("Humano",pers.getRaza());
		
	}
	
}
