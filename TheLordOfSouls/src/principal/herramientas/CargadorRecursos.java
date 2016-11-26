package principal.herramientas;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class CargadorRecursos {

	public static BufferedImage cargarImagenCompatibleOpaca(final String ruta){
		Image imagen = null;
		
		try{
				imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
			
		}catch (IOException e){
			JOptionPane.showMessageDialog(null, "no se encontro el archivo","Error al cargar imagen opaca en CargadorRecursos", JOptionPane. ERROR_MESSAGE);
		}
		
		GraphicsConfiguration gc= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null), Transparency.OPAQUE);
		
		Graphics g= imagenAcelerada.getGraphics();
		g.drawImage(imagen, 0, 0, null);
		g.dispose();
		
		return imagenAcelerada;
	}
	
	public static BufferedImage cargarImagenCompatibleTranslucida(final String ruta){
		Image imagen = null;
		
		try{
				imagen = ImageIO.read(ClassLoader.class.getResource(ruta));
			
		}catch (IOException e){
			JOptionPane.showMessageDialog(null, "no se encontro el archivo","Error al cargar imagen translucida en CargadorRecursos", JOptionPane. ERROR_MESSAGE);
		}
		
		GraphicsConfiguration gc= GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		BufferedImage imagenAcelerada = gc.createCompatibleImage(imagen.getWidth(null), imagen.getHeight(null),Transparency.TRANSLUCENT);
		
		Graphics g= imagenAcelerada.getGraphics();
		g.drawImage(imagen, 0, 0, null);
		g.dispose();
		
		return imagenAcelerada;
	}
}
