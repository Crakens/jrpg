package principal.entes.enemigos;

import java.awt.Graphics;

import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;

public class Goblin extends Enemigo{
	
	public Goblin(){
	
	this.nombre= "Goblin";
    this.energiaTot = 20;
	this.energia= energiaTot;
	this.salud= 40;
	this.experiencia= 10;
	this.idAlma= 3;
	this.agilidad = 5;
	this.fatiga = 10;
	this.recuperacion = 5;
	this.nombreAlma= "Alma Heroica";
	
	 if (hojaSprites == null) {
         hojaSprites = new HojaSprites(Constantes.RUTA_ENEMIGOS + nombre + ".png",
                 Constantes.ANCHO_SPRITE, false); //ancho o lado es igual son los dos de 32//
     }
	
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return 18;
	}

	@Override
	public void serAtacado(int dano) {
		super.serAtacado(dano);
	}
	
	public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        DibujoDebug.dibujarImagen(g, hojaSprites.obtenerSprite(0).getImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
}
