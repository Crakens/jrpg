package test;

import org.junit.Assert;
import org.junit.Test;

import batallones.Batallon;
import combates.Combate;
import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Ladron;
import personaje.Orco;
import personaje.Personaje;

public class BatallonMasAgilTest {
	
	//### Historia de usuario 20 ###//
	@Test
	public void asignarTurnosALosBatallones() {
		
		Personaje personaje1=new Orco(); 
		Especialidad casta1=new Ladron();
		personaje1.setNombrePersonaje("Picaro"); // el ladron es el que mas agiliad tiene//
		personaje1.setCasta(casta1);
		personaje1.bonificacionDeCasta(); //1er Personaje batallon 1//
		
		Personaje personaje2=new Orco(); 
		Especialidad casta2=new Guerrero();
		personaje2.setCasta(casta2);
		personaje2.bonificacionDeCasta(); //2do Personaje batallon 1//
		
		Personaje personaje3=new Orco(); 
		Especialidad casta3=new Guerrero();
		personaje3.setCasta(casta3);
		personaje3.bonificacionDeCasta(); //3er Personaje batallon 1//
		
		Batallon b1=new Batallon();
		
		b1.agregar(personaje3);
		b1.agregar(personaje1);
		b1.agregar(personaje2);
		
		//////////////////
		Personaje personaje4=new Humano(); 
		Especialidad casta4=new Guerrero();
		personaje4.setCasta(casta4);
		personaje4.bonificacionDeCasta(); //4to Personaje batallon 2//
		
		Personaje personaje5=new Humano(); 
		Especialidad casta5=new Guerrero();
		personaje5.setCasta(casta5);
		personaje5.bonificacionDeCasta(); //5to Personaje batallon 2//
		
		Batallon b2=new Batallon();
		
		b2.agregar(personaje4);
		b2.agregar(personaje5);
		/////////////////
		
		Combate com= new Combate();
		
		com.definirTurnos(b1, b2);
		
		Assert.assertEquals(true, com.getTurno1());
		Assert.assertEquals(false, com.getTurno2());
	}
	
	
	//### Historia de usuario 21 ###//
	@Test
	public void ordenarPorMayorAgilidadBatallones() {
		
		Personaje personaje1=new Orco(); 
		Especialidad casta1=new Ladron();
		personaje1.setNombrePersonaje("Picaro"); // el ladron es el que mas agiliad tiene//
		personaje1.setCasta(casta1);
		personaje1.bonificacionDeCasta(); //1er Personaje batallon 1//
		
		Personaje personaje2=new Orco(); 
		Especialidad casta2=new Guerrero();
		personaje2.setCasta(casta2);
		personaje2.bonificacionDeCasta(); //2do Personaje batallon 1//
		
		Personaje personaje3=new Orco(); 
		Especialidad casta3=new Guerrero();
		personaje3.setCasta(casta3);
		personaje3.bonificacionDeCasta(); //3er Personaje batallon 1//
		
		Batallon b=new Batallon();
		
		b.agregar(personaje3);
		b.agregar(personaje1);
		b.agregar(personaje2);
		
		b.ordeNarBatallonPorMayorAgilidad();
		
		Assert.assertEquals(9, b.getPersonaje(0).getAgilidad());
		Assert.assertEquals(4, b.getPersonaje(1).getAgilidad());
		Assert.assertEquals(4, b.getPersonaje(2).getAgilidad());
	} 

}
