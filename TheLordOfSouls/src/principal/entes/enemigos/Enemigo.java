package principal.entes.enemigos;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.entes.personajes.Atacable;
import principal.entes.personajes.Personaje;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;


public abstract class Enemigo implements Atacable, Comparable < Enemigo >{

	protected String nombre;
	protected int energiaTot;
	protected int energia;
	protected int salud;
	protected int experiencia;
	protected int idAlma;
	protected int agilidad;
	protected int fatiga;
	protected int recuperacion;
	protected int altura=1;
	private double posicionX;
    private double posicionY;
    protected HojaSprites hojaSprites;
    protected String nombreAlma; 
	
	public final boolean atacar(Atacable atacado) {
		
		if(atacado.estaVivo()){
			if(puedeAtacar()){
				atacado.serAtacado(calcularPuntosDeAtaque()); //modificado//
				energia-= fatiga ; //sugiero metodo aparte para calcular la energia que gasta//
			}
			
			return true;
		}
		
		return false;
	}

	public boolean estaVivo(){
		
		if(this.salud<=0){
			return false;
		}
		
		return true;
	}
	
	public boolean puedeAtacar() {
		return (energia >= fatiga && this.estaVivo());
	}
	
	public abstract int calcularPuntosDeAtaque();
	
	
	@Override
	public void serAtacado(int dano) {
		this.salud -= dano;
	}
	
	public void serAgrandado(){
		
		this.altura *= 2;
	}
	
	public int devolverItem(){
		
		return idAlma;
	}
	
	public int devolverExperiencia(){
		
		return experiencia;
	}
	
	public int getAgilidad() {
		return agilidad;
	}

	public void serEnergizado() {
		
		if(this.energia + this.recuperacion > this.energiaTot)
			this.energia = energiaTot;
		else
			this.energia+= recuperacion;
	}
	
	public int compareTo(Enemigo e) { 
		
		if(this.getAgilidad() > e.getAgilidad())
				return -1;
		
		if(this.getAgilidad() < e.getAgilidad())
				return 1;
			
		return 0;
	}
	
	public double obtenerPosicionX() {
        return posicionX;
    }

    public double obtenerPosicionY() {
        return posicionY;
    }
    
    public String getNombre(){
    	return nombre;
    }
    
    public void establecerPosicion(final double posicionX, final double posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public void dibujar(final Graphics g, final int puntoX, final int puntoY) {
    	
        if (salud<= 0) {
            return;
        }

        DibujoDebug.dibujarRectanguloContorno(g, obtenerArea());
//        dibujarDistancia(g, puntoX, puntoY);
    }

//	private void dibujarDistancia(final Graphics g, final int puntoX, final int puntoY) {
//       
//    	Point puntoJugador = new Point(
//                (int) ElementosPrincipales.jugador.obtenerPosicionX(),
//                (int) ElementosPrincipales.jugador.obtenerPosicionY()
//        );
//	
//        Point puntoEnemigo = new Point((int) posicionX, (int) posicionY);
//        
//        Double distancia = CalculadoraDistancia.obtenerDistanciaEntrePuntos(puntoJugador, puntoEnemigo);
//
//        DibujoDebug.dibujarString(g, String.format("%.2f" ,distancia), puntoX, puntoY - 8);
//    }


    public Rectangle obtenerArea() {
        final int puntoX = (int) posicionX * Constantes.ANCHO_SPRITE
                - (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X;
        final int puntoY = (int) posicionY * Constantes.ALTO_SPRITE
                - (int) ElementosPrincipales.jugador.obtenerPosicionY() + Constantes.MARGEN_Y;

        return new Rectangle(puntoX, puntoY, Constantes.ANCHO_SPRITE, Constantes.ALTO_SPRITE);
    }
    
    public Rectangle obtenerAreaPosicional() {
    	return new Rectangle((int) posicionX, (int) posicionY, Constantes.ANCHO_SPRITE, Constantes.ALTO_SPRITE);
    }

	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	public String getNombreAlma() {
		return nombreAlma;
	}
}
