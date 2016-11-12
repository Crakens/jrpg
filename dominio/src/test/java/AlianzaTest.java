package test;

import item.RegistroDeAlmas;

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

public class AlianzaTest {

	//### Historia de usuario 9 ###//
//	@Test
//	public void aliarseParaDuplicarExperiencia(){
//		
//		Personaje perso1=new Humano();
//		Especialidad c1=new Guerrero();
//		
//		perso1.setCasta(c1);
//		perso1.bonificacionDeCasta();
//		
//		Personaje perso2=new Humano();
//		Especialidad c2=new Guerrero();
//		
//		perso2.setCasta(c2);
//		perso2.bonificacionDeCasta();
//		
//		Batallon b=new Batallon();
//		
//		b.agregar(perso1);
//		b.agregar(perso2);
//		
//		Assert.assertEquals(0, b.getPersonaje(0).getExperienciaAcum());
//		Assert.assertEquals(0, b.getPersonaje(1).getExperienciaAcum());
//		////////////////////////////////////////
//		Enemigo e=new Goblin();
//		
//		BatallonEnemigos be=new BatallonEnemigos();
//		
//		be.agregar(e);
//		
//		Combate com=new Combate();
//		
//		com.combatir(b, be);
//		
//		if (be.getTamBatallon() == 0) { 
//			
//			for (int i = 0; i < b.getTamBatallon() ; i++) {
//				
//				b.getPersonaje(i).ganarExperiencia(be.getExperienciaTot()*2); //dijimos que al vencer enemigos estando aliadoz multiplica//
//			}
//		}
//		
//		Assert.assertEquals(20, b.getPersonaje(0).getExperienciaAcum());
//		Assert.assertEquals(20, b.getPersonaje(1).getExperienciaAcum());
//	}
	
	
	//### Historia de usuario 24 ###//### Historia de usuario 25 ###//
	@Test
	public void todosElegimosHuir(){
		
		Personaje perso1=new Humano();
		Especialidad c1=new Guerrero();
		
		perso1.setCasta(c1);
		perso1.bonificacionDeCasta();
		perso1= RegistroDeAlmas.asignarAlma(4, perso1); //27*2
		perso1= RegistroDeAlmas.asignarAlma(3, perso1);//27*2 +10
		
		Personaje perso2=new Humano();
		Especialidad c2=new Guerrero();
		
		perso2.setCasta(c2);
		perso2.bonificacionDeCasta();
		perso2= RegistroDeAlmas.asignarAlma(4, perso2); //27*2
		perso2= RegistroDeAlmas.asignarAlma(2, perso2); //27*2 - 10
		
		Assert.assertEquals(64, perso1.calcularPuntosDeAtaque());
		Assert.assertEquals(44, perso2.calcularPuntosDeAtaque());
		
		Batallon b=new Batallon();
		
		b.agregar(perso1);
		b.agregar(perso2);
		
		Enemigo e=new Goblin();
		
		BatallonEnemigos be=new BatallonEnemigos();
		
		be.agregar(e);
		
		Combate com=new Combate();
		
		com.combatir(b, be);
		
		if (com.getHuir() == true && b.getTamBatallon() != 0) { //si huyeron y el batallon todavia tiene integrantes// 
			
			for (int i = 0; i < b.getTamBatallon(); i++) {
				
				b.getPersonaje(i).perderItemMasValioso(b.getPersonaje(i));
			}
		}
		
		Assert.assertEquals(true, com.getHuir());
		Assert.assertEquals(37, b.getPersonaje(0).calcularPuntosDeAtaque());
		Assert.assertEquals(17, b.getPersonaje(1).calcularPuntosDeAtaque());

	}
}
