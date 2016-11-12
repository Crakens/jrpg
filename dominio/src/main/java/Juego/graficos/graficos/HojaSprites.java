package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites { //esta clase levanta la hoja de sprites que contiene todos los graficos a utilizar en el juego//
	
	final int ancho;
	final int alto;
	public final int [] pixeles; 
	
	//coleccion de Hojas de Sprites //va a haber una x cada nivel//
	
	public static HojaSprites hoja1=new HojaSprites("/texturas/HojaSpritesConSprite.png", 320, 320);
	
	//fin de la coleccion//
	
	public HojaSprites(final String ruta, final int ancho, final int alto){ //con el final el constructor se ejecuta mas rapido// 
		
		this.ancho=ancho;
		this.alto=alto;
		this.pixeles=new int [ancho*alto];
		
		BufferedImage imagen;
		try {
			
			imagen = ImageIO.read(HojaSprites.class.getResource(ruta)); //se intenta crear un objeto imagen utilizando la ruta del archivo//
			imagen.getRGB(0, 0, this.ancho, this.alto, this.pixeles, 0, this.ancho); //escanea la primer linea de la imagen y la guarda en pixeles//
																					//se le indica que comienze en x=0, y=0 sin desplazamiento offset=0 y que lea hasta llegar al final osea ancho;
		} catch (IOException e) {
			e.printStackTrace(); //como la imagen puede no existir se rodea en un try catch//
		} 
		
	}

	public int getAncho() {
		return ancho;
	}
	
}
