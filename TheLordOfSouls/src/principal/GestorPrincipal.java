package principal;

import java.io.FileNotFoundException;

import javax.swing.JFrame;

import principal.cs.Cliente;
import principal.graficos.SuperFicieDibujo;
import principal.graficos.Ventana;
import principal.mapas.MapaTiled;
import principal.maquinaestado.GestorEstados;
import principal.interfaz_usuario.*;

public class GestorPrincipal extends JFrame {

	private boolean enFuncionamiento = false;
	private String titulo;
	private int ancho;
	private int alto;
	private Cliente cliente;
	
	private SuperFicieDibujo sd;
	private Ventana ventana;
	private GestorEstados ge;
	
	public GestorPrincipal(final String titulo, final int ancho, final int alto, Cliente c){
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
		this.cliente = c;
	}
	
	
	public void iniciarJuego() {
		enFuncionamiento=true;
		inicializar();
		
	}
	
	
	public void inicializar() {
		sd=new SuperFicieDibujo(ancho,alto);
		ventana= new Ventana(titulo, sd, this.cliente);
		ge= new GestorEstados();
		
	}

	public void iniciarBuclePrincipal() {
		
		int aps=0;
		int fps=0;
		
		final int NS_POR_SEGUNDOS = 1000000000; //nanos por segundo//
		final int APS_OBJETIVO = 60; //actualizaciones x segundo//cuanto mas bajo el numero es mejor//
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDOS/APS_OBJETIVO; //cuantos nano lleva cada actualizacion//
		
		long referenciaActulizacion= System.nanoTime(); //toma el tiempo actual en nano segundos//
		long referenciaContador= System.nanoTime();
		
		double tiempoTranscurrido;
		double delta = 0; //su nombre es por convencion//La cantidad de tiempo que transcurre hasta que halla una actualizacion//
		
		while(enFuncionamiento){
			
			final long inicioBucle= System.nanoTime(); //toma el tiempo actual que es distinto del de referenciaActulizacion//inicia el cronometro//
			
			tiempoTranscurrido= inicioBucle - referenciaActulizacion; //guarda la diferencia desde que inicio el cronometro//
			referenciaActulizacion= inicioBucle;
			
			delta+=tiempoTranscurrido/ NS_POR_ACTUALIZACION; //incrementa delta en base al tiempo transcurrido en nanos//
			
			while(delta>= 1){
				
				actualizar(); //actualizar se estaria ejecutando casi exactamente 60 veces por segundo//
				aps++;
				delta--;
			}
			
			dibujar();
			fps++;
			
			if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDOS){
				
//				System.out.println("FPS: " + fps + " APS: " + aps);
				
				fps=0;
				aps=0;
				referenciaContador= System.nanoTime();
			}
		}
		
	}
	
	private void actualizar(){
		sd.actualizar(); //se actualiza el teclado//
		ge.actualizar(); //se actualizan los estados//
		
	}
	
	private void dibujar(){
		sd.dibujar(ge); 
	}
/* ACA FUNCIONA
	public static void main(String[] args) throws FileNotFoundException {
		
		GestorPrincipal gp=new GestorPrincipal("TheLordOfSouls", Constantes.ANCHO_JUEGO, Constantes.ALTO_JUEGO);
		
		//iniciar aqui el servidor y las interfaces iniciales//Como login, crearPersonaje, elegirMapa//
		//gp.iniciarLogin();
		gp.iniciarJuego();
		gp.iniciarBuclePrincipal();
	}*/

}
