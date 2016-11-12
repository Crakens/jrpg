package test;

import item.RegistroDeAlmas;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import combates.Combate;
import enemigos.Enemigo;
import enemigos.Goblin;
import batallones.Batallon;
import batallones.BatallonEnemigos;
import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Personaje;

public class VencerBatallonDeEnemigosTest {

	//### Historia de usuario 14 ###//### Historia de usuario 16 ###//
	@Test
	public void ganaItemPorCadaEnemigoDelBatallon(){
	
		Personaje perso1=new Humano();
		Especialidad c1=new Guerrero();
		
		perso1.setCasta(c1);
		perso1.bonificacionDeCasta();
		
		Personaje perso2=new Humano();
		Especialidad c2=new Guerrero();
		
		perso2.setCasta(c2);
		perso2.bonificacionDeCasta();
		
		Batallon b= new Batallon();
		b.agregar(perso1);
		b.agregar(perso2);
		
		Assert.assertEquals(27, b.getPersonaje(0).calcularPuntosDeAtaque());
		Assert.assertEquals(27, b.getPersonaje(1).calcularPuntosDeAtaque());
		
		///////
		Enemigo e1=new Goblin();
		Enemigo e2=new Goblin();
		Enemigo e3=new Goblin();
		
		BatallonEnemigos be=new BatallonEnemigos();
		be.agregar(e1);
		be.agregar(e2);
		be.agregar(e3);
		
		Combate com=new Combate();
		
		com.combatir(b, be);
		
		if (be.getTamBatallon() == 0) {
			
			
			List <Integer> almas = be.getAlmas();
			
			for (int i = 0; i < b.getTamBatallon(); i++) {
				
				for (int j = 0; j < almas.size(); j++) {
					
					Personaje aux= RegistroDeAlmas.asignarAlma(almas.get(j), b.getPersonaje(i));
					b.setPersonajeDecorado(i,aux);
				}
			}	
			
		}
		
		Assert.assertEquals(57, b.getPersonaje(0).calcularPuntosDeAtaque());
		Assert.assertEquals(57, b.getPersonaje(1).calcularPuntosDeAtaque());
	}
}
