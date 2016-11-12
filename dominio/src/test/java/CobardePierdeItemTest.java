package test;

import org.junit.Assert;
import org.junit.Test;

import combates.Combate;
import enemigos.Enemigo;
import enemigos.Goblin;
import item.RegistroDeAlmas;
import personaje.Elfo;
import personaje.Especialidad;
import personaje.Hechicero;
import personaje.Personaje;

public class CobardePierdeItemTest {

	//### Historia de usuario 23 ###//### Historia de usuario 24 ###//
	@Test
	public void cobardePierdeItem() {
		
		Personaje java= new Elfo();
		java.setNombrePersonaje("Java");
		Especialidad c= new Hechicero(); 
		java.setCasta(c);
		java.bonificacionDeCasta();
		java=RegistroDeAlmas.asignarAlma(3, java); //me equipo AlmaHeroica//
		java=RegistroDeAlmas.asignarAlma(2, java); //me equipo AlmaOscura//
		
		Assert.assertEquals(15, java.calcularPuntosDeAtaque());
		
		Enemigo e= new Goblin();
		
		Combate com= new Combate();
		
		com.combatir(java, e);
		
		Assert.assertEquals(true, com.getHuir()); // como el cobarde hullo se le quita el mejor item siempre y cuando este no sea el ultimo item adquerido
		
		Assert.assertEquals(5, java.calcularPuntosDeAtaque()); //como su mejor item era el AlmaHeroica le resta dies al ataque
		
	}
	
}
