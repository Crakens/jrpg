package test;

import item.ConGranAlmaCorrupta;
import item.ConGranAlmaDelCoraje;
import item.ConGranAlmaOscura;

import org.junit.Assert;
import org.junit.Test;

import personaje.Especialidad;
import personaje.Hechicero;
import personaje.Humano;
import personaje.Personaje;

public class ItemTest {
   
	//### Historia de usuario 6 ###//
    @Test
    public void quePuedoAgregarItem() {
 
        Personaje sigmund = new Humano();
        Especialidad casta= new Hechicero();
        sigmund.setCasta(casta);
        sigmund.bonificacionDeCasta();
        
        Assert.assertEquals(17, sigmund.calcularPuntosDeAtaque());
 
        // agrego item AlmaCorrupta // reduce el ataque a la mitad// 
        sigmund = new ConGranAlmaCorrupta(sigmund);
        Assert.assertEquals(8, sigmund.calcularPuntosDeAtaque());
        
    }
 
   
   @Test
    public void quePuedoAgregarAmbosItems() {
 
	    Personaje sigmund = new Humano();
        Especialidad casta= new Hechicero();
        sigmund.setCasta(casta);
        sigmund.bonificacionDeCasta();
        
        Assert.assertEquals(17, sigmund.calcularPuntosDeAtaque());
 
        // agrego item ConGranAlmaCorrupta//
        sigmund = new ConGranAlmaCorrupta(sigmund);
        
        
        Assert.assertEquals(8, sigmund.calcularPuntosDeAtaque());
        
        //agrego item ConGranAlmaOscura// Le resta 10 al ataque//
        sigmund = new ConGranAlmaOscura(sigmund);
        
        Assert.assertEquals(-2, sigmund.calcularPuntosDeAtaque());
        // sigo perdiendo ataque// O.O
    
    }
    
    @Test
    public void quePuedoAgregarDosTiposDeItem() {
   	
    	Personaje sigmund = new Humano();
        Especialidad casta= new Hechicero();
        sigmund.setCasta(casta);
        sigmund.bonificacionDeCasta();
        
        Assert.assertEquals(17, sigmund.calcularPuntosDeAtaque());
 
        // agrego item AlmaCorrupta
        sigmund = new ConGranAlmaCorrupta(sigmund);
        Assert.assertEquals(8, sigmund.calcularPuntosDeAtaque());
 
        // agrego ConGranAlmaDelCoraje multiplicador (x2)
        sigmund = new ConGranAlmaDelCoraje(sigmund);
        Assert.assertEquals(8 * 2, sigmund.calcularPuntosDeAtaque());
    }

}
