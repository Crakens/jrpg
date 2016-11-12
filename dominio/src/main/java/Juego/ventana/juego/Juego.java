package juego;

import graficos.Pantalla;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import mapa.Mapa;
import mapa.MapaGenerado;
import control.Teclado;

public class Juego extends Canvas implements Runnable{ //interface Runnable para usar varios procesos//
	
	private static final long serialVersionUID = 1L; //identificador para saber si la clase fue modificada//
	
	private static final int ANCHO=800;
	private static final int ALTO=600;
	
	private static volatile boolean enFuncionamiento = false; //indica si el jeugo esta funcioanndo o no//volatile sirve para que la variable no se pueda tocar mientras este siendo usada en otro metodo//
	
	private static final String NOMBRE = "The Lord Of Souls"; //NOMBRE DEL JUEGO//
	private static String CONTADOR_FPS = "";
	private static String CONTADOR_APS = "";	
	
	private static int aps=0;
	private static int fps=0;
	
	private static int x=0;
	private static int y=0;
	
	
	private static JFrame ventana;
	private static Thread thread; //proceso en paralelo//
	private static Teclado teclado;
	private static Pantalla pantalla;
	
	private static Mapa mapa;
	
	//para manipular los pixeles del juego//
	private static BufferedImage imagen =new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);//crea un objeto imagen con ancho*alto de dimension y con los colores que utiliza el monitor(RGB)//
	private static int [] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData(); //DataBufferInt = casteo a array de int//getRaster=transforma la imagen en un array//getDataBuffer=obtiene el tipo de dato q esta en el buffer//getData=enlaza el array de pixeles con el de la imagen...cualquier cambio que haga en pixeles afecta a la imagen//
	private static final ImageIcon icono=new ImageIcon(Juego.class.getResource("/icono/icono.png"));
	
	private Juego(){
		
		setPreferredSize(new Dimension(ANCHO, ALTO));
		
		pantalla=new Pantalla(ANCHO,ALTO);
		
		mapa =new MapaGenerado(128, 128); //le pasa por parametro la cantidad de cuadros//
		
		teclado=new Teclado();
		addKeyListener(teclado); //le indica a la maquina virtual que detecte todas las teclas pulsadas durante la clase Juego//
		
		ventana=new JFrame(NOMBRE); //creo una instancia de la ventana y le pengo que aparezca el nombre del juego arriba//
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Esto hace que al darle a la cruz se cierre la ventanita//
		ventana.setResizable(false); //el usuario no me modifica el tamaño de la ventana//
		ventana.setLayout(new BorderLayout()); //La organizacion interna de la ventana//
		ventana.add(this, BorderLayout.CENTER); //permite centrar la imagen del juego a la ventana//
		
		ventana.setUndecorated(true); //elimina los bordes de la ventana//
		
		ventana.pack(); //se pone x si acaso...para que la ventana se ajuste al ancho y alto establecido//
		ventana.setLocationRelativeTo(null);//posicion de la ventana en el escritorio//
		ventana.setVisible(true); //que la ventana se pueda ver//
		ventana.setIconImage(icono.getImage());
	}
	
	public static void main(String [] args){
		Juego j=new Juego();
		j.iniciar();
	}
	
	private synchronized void iniciar(){ //synchronized hace lo mismo que volatiles//evita el conflicto de la utilizacion de la misma variable en varios metodos(enFuncionamiento)//
		
		enFuncionamiento = true; //importante ponerlo antes de thread.start ya que al ir a run ejecuta el while//
		thread = new Thread(this, "Graficos"); //crea el proceso graficos//
		thread.start();	//Comienza a ejecutarse lo que se encuentre en el metodo run//
	}
	
	private synchronized void detener(){
		
		enFuncionamiento = false;
		
		try {
			
			thread.join(); //intenta finalizar el proceso//espera a que termine con lo que este haciendo//
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void actualizar(){ //actualiza las variables del juego//
		
		teclado.actualizar(); //actualiza el teclado//
		
		if(teclado.arriba){
			
			y--;
		}
		
		if(teclado.abajo){
			
			y++;
		}
		
		if(teclado.izquierda){
			
			x--;
		}
		
		if(teclado.derecha){
			
			x++;
		}
		
		if(teclado.salir){
			
			System.exit(0);
		}
		
		aps++;
	}
	
	private void mostrar(){ //los metodos necesarios para re dibujar los graficos//
		
		BufferStrategy estrategia= getBufferStrategy(); //defino un buffer//buffer=espacio en memoria donde se efectuaran los calculos para mostrar correctamente la imagen//
		
		if(estrategia == null){ //si todavai no cree ningun buffer//
			
			createBufferStrategy(3);//entonces creo 3//Al crear 3 buffer estaria haciendo como un tipo cadena de montaje de imagenes(cola de imagenes) lo que agilisa mucho el trabajo y evita errores de imagen//
			return;
		}
		
		pantalla.limpiar(); 
		//pantalla.mostrar(x, y);
		mapa.mostrar(x, y, pantalla);
		
		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
		
//		for(int i=0;i<pixeles.length;i++){ // copia el array pro de forma menos eficiente//
			
//			pixeles[i]= pantalla.pixeles[i];
//		}
		
		Graphics g= estrategia.getDrawGraphics(); //Graphics=clase que permite dibujar tomando dichos graficos desde el buffer//
		
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null); //dibuja la imagen indicandoles desde que posicion pro pra ello necesita el alto y ancho de dicha imagen//
		g.setColor(Color.RED);
		g.fillRect(ANCHO/2, ALTO/2, 32, 32);
		g.drawString(CONTADOR_APS, 10, 20);
		g.drawString(CONTADOR_FPS, 10, 35);	
		g.dispose(); //libera la memoria que g estaba utilizando//
		
		estrategia.show();
		
		fps++;
	}
	
	public void run() {
		
		final int NS_POR_SEGUNDOS = 1000000000; //nanos por segundo//
		final byte APS_OBJETIVO = 60; //actualizaciones x segundo//cuanto mas bajo el numero es mejor//
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDOS/APS_OBJETIVO; //cuantos nano lleva cada actualizacion//
		
		long referenciaActulizacion= System.nanoTime(); //toma el tiempo actual en nano segundos//
		long referenciaContador= System.nanoTime();
		
		double tiempoTranscurrido;
		double delta = 0; //su nombre es por convencion//La cantidad de tiempo que transcurre hasta que halla una actualizacion//
		
		requestFocus(); //mantiene el foco en la ventana del juego//Asi no hay q hacer click en al ventana para q tenga el foco a cada rato//
		
		while(enFuncionamiento){
			
			final long inicioBucle= System.nanoTime(); //toma el tiempo actual que es distinto del de referenciaActulizacion//inicia el cronometro//
			
			tiempoTranscurrido= inicioBucle - referenciaActulizacion; //guarda la diferencia desde que inicio el cronometro//
			referenciaActulizacion= inicioBucle;
			
			delta+=tiempoTranscurrido/ NS_POR_ACTUALIZACION; //incrementa delta en base al tiempo transcurrido en nanos//
			
			while(delta>= 1){
				
				actualizar(); //actualizar se estaria ejecutando casi exactamente 60 veces por segundo//
				delta--;
			}
			
			mostrar();
			
			if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDOS){
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "APS: " + fps;
				
				fps=0;
				aps=0;
				referenciaContador= System.nanoTime();
			}
		}
	}
	

}
