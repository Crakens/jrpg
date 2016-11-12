package test;

import org.junit.Assert;
import org.junit.Test;
import item.RegistroDeAlmas;
import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Orco;
import personaje.Personaje;

public class MematanYPierdoItemTest {
	
	
//### Historia de usuario 10 ###//
@Test
public void pierdeMejorItem() {
		
		boolean vive=true;
		Personaje p=new Humano();
		p.setNombrePersonaje("Java");
		Especialidad c=new Guerrero();
		p.setCasta(c);
		p.bonificacionDeCasta();
		
		Assert.assertEquals(27,p.calcularPuntosDeAtaque());
		
		p=RegistroDeAlmas.asignarAlma(3, p);
		p=RegistroDeAlmas.asignarAlma(4, p); //le asigno tambien el item almaOscura//
		p=RegistroDeAlmas.asignarAlma(2, p); //le asigno tambien el item almaOscura//
		
		Personaje e=new Orco();			//creo al personaje que me va a matar//
		e.setNombrePersonaje("Carlitos");
		c=new Guerrero();
		e.setCasta(c);
		e.bonificacionDeCasta();
		
		p.serAtacado(30); //me redusco la salud para que no se canse el orco antes que me muera//
		
		Assert.assertEquals(64,p.calcularPuntosDeAtaque());
		
		Assert.assertEquals(30,e.calcularPuntosDeAtaque());
		
		while(vive){
			
			e.atacar(p); //me ataca carlitos//
			vive=p.estaVivo(); //pregunto si estoy vivo//
			
			if(!vive){
				
				e.ganarExperiencia(p.devolverExperiencia()); //es amodo de idea xq no tiene esxperiencia mi personaje si lo acabo de crear//
				int almaRobada=p.perderItemMasValioso(p);
				
				if(almaRobada != 0) //si no tenia alma que robar no hay q llamar al metodo//
					e=RegistroDeAlmas.asignarAlma(almaRobada, e); //carlitos recive el idAlma de la mejor que poseo y la busca en el registro//La mejor que poseo es el almaDelCoraje//
			}
			
		}
		
		Assert.assertEquals(27,p.calcularPuntosDeAtaque());
		Assert.assertEquals(68,e.calcularPuntosDeAtaque()); //Hay que ver que el orco deje de ontar los ataques al finalizar la batalla//
		
	}

}
