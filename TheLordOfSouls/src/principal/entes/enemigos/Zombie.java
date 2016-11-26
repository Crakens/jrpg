package principal.entes.enemigos;

import java.awt.Graphics;

import principal.Constantes;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;

public class Zombie extends Enemigo {

int ataquesRecibidos;
	
	public Zombie(){
	
	this.nombre= "Zombie1";
	this.energiaTot= 20;
	this.energia= energiaTot;
	this.salud= 100;
	this.experiencia= 20;
	this.idAlma= 2;
	this.agilidad = 3;
	this.fatiga = 15;
	this.recuperacion = 5;
	this.nombreAlma= "Alma Oscura";
	
		if (hojaSprites == null) {
			hojaSprites = new HojaSprites(Constantes.RUTA_ENEMIGOS + nombre + ".png",
					Constantes.ANCHO_SPRITE, false); //ancho o lado es igual son los dos de 32//
		}
	}

	@Override
	public int calcularPuntosDeAtaque() {
		return 30 + ataquesRecibidos;
	}

	@Override
	public void serAtacado(int dano) {
		super.serAtacado(dano);
		this.ataquesRecibidos++;
	}

	public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
        DibujoDebug.dibujarImagen(g, hojaSprites.obtenerSprite(0).getImagen(), puntoX, puntoY);
        super.dibujar(g, puntoX, puntoY);
    }
}
