package test;

import item.RegistroDeAlmas;

import org.junit.Assert;
import org.junit.Test;

import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Personaje;

public class EquipoItemsSinRestriccionTest {
	
//### Historia de usuario 13 ###//## Historia de usuario 13.1 ###//
@Test

	public void equipoItemsSinRestriccion(){
	Personaje perso=new Humano();
	Especialidad c=new Guerrero();
	
	perso.setCasta(c);
	perso.bonificacionDeCasta();
	
	Assert.assertEquals(27, perso.calcularPuntosDeAtaque());
	
	perso=RegistroDeAlmas.asignarAlma(3, perso);//37
	perso=RegistroDeAlmas.asignarAlma(2, perso);//27
	perso=RegistroDeAlmas.asignarAlma(3, perso);//37
	perso=RegistroDeAlmas.asignarAlma(4, perso);//74
	perso=RegistroDeAlmas.asignarAlma(2, perso);//64
	perso=RegistroDeAlmas.asignarAlma(3, perso);//74
	perso=RegistroDeAlmas.asignarAlma(2, perso);//64
	perso=RegistroDeAlmas.asignarAlma(4, perso);//128
	

	Assert.assertEquals(128, perso.calcularPuntosDeAtaque());
	
}
}
