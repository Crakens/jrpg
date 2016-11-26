package principal.entes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.entes.personajes.Engorgio;
import principal.entes.personajes.Especialidad;
import principal.entes.personajes.Guerrero;
import principal.entes.personajes.Hechicero;
import principal.entes.personajes.Ladron;
import principal.entes.personajes.Orco;
import principal.entes.personajes.Personaje;
import principal.herramientas.DibujoDebug;
import principal.sprites.HojaSprites;

public class Jugador {

	private double posicionX;
	private double posicionY;
	public Personaje p; //un jugador tendra un personaje//
	private int direccion;

	private double velocidad = 1;

	private boolean enMovimiento;

	private HojaSprites hs;

	private BufferedImage imagenActual;

	private final int ANCHO_JUGADOR = 16;
	private final int ALTO_JUGADOR = 16;

	private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
	private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);

	private int animacion;
	private int estado;

	public Jugador(Personaje per) {
		
		p=per;
		posicionX = ElementosPrincipales.mapa.obtenerPosicionInicial().getX();
		posicionY = ElementosPrincipales.mapa.obtenerPosicionInicial().getY();

		direccion = 1;

		enMovimiento = false;
//		p= new Orco("Mujer"); //solo necesario para la prueba estos valores se inicializan solo en la interfaz de crearPersonaje
//		Especialidad c=new Hechicero();
//		p.setCasta(c);
//		p.setNombrePersonaje("Carlota");
//		p.getCasta().getHechicero().agregarHechizo("Engorgio", new Engorgio());
		
		hs = new HojaSprites(Constantes.RUTA_PERSONAJE + p.getCasta().getNombre() + "/" + p.getRaza() + ".png", Constantes.ANCHO_SPRITE, Constantes.ALTO_SPRITE, false);

		imagenActual = hs.obtenerSprite(0).getImagen();

		animacion = 0;
		estado = 0;
	}

	public void actualizar() {
		cambiarAnimacionEstado();
		enMovimiento = false;
		determinarDireccion();
		animar();
	}

	private void cambiarAnimacionEstado() {
		if (animacion < 40) {
			animacion++;
		} else {
			animacion = 0;
		}
		
		if (animacion < 10)
			estado = 0;
		
		if (animacion < 20 && animacion > 10)
			estado = 1;
		
		if (animacion < 30 && animacion > 20 )
			estado = 2;
		
		if (animacion < 40 && animacion > 30)
			estado = 1;
		
			
	}

	private void determinarDireccion() { //nosotros nos vamos a mover solo con el mouse
		final int velocidadX = evaluarVelocidadX();
		final int velocidadY = evaluarVelocidadY();

		if (velocidadX == 0 && velocidadY == 0) {
			
			return;
		}

		if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
			
			mover(velocidadX, velocidadY);
		} else {
			// izquierda y arriba
			if (velocidadX == -1 && velocidadY == -1) {
				if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.arriba
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// izquierda y abajo
			if (velocidadX == -1 && velocidadY == 1) {
				if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.abajo
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// derecha y arriba
			if (velocidadX == 1 && velocidadY == -1) {
				if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.arriba
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// derecha y abajo
			if (velocidadX == 1 && velocidadY == 1) {
				if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.abajo
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
		}
	}

	private int evaluarVelocidadX() {
		int velocidadX = 0;
		
		if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
			velocidadX = -1;
		} else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
			velocidadX = 1;
		}

		return velocidadX;
	}

	private int evaluarVelocidadY() {
		int velocidadY = 0;

		if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
			velocidadY = -1;
		} else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
			velocidadY = 1;
		}

		return velocidadY;
	}

	private void mover(final int velocidadX, final int velocidadY) {
		enMovimiento = true;

		cambiarDireccion(velocidadX, velocidadY);

		if (!fueraMapa(velocidadX, velocidadY)) {
			if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
				posicionX += velocidadX * velocidad;
			}
			if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
				posicionX += velocidadX * velocidad;
			}
			if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
				posicionY += velocidadY * velocidad;
			}
			if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
				posicionY += velocidadY * velocidad;
			}
		}
	}

	private boolean enColisionArriba(int velocidadY) {
		
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);
			
			int origenX = area.x;
			int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_ARRIBA.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean enColisionAbajo(int velocidadY) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x;
			int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_ABAJO.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean enColisionIzquierda(int velocidadX) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean enColisionDerecha(int velocidadX) {
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_DERECHA.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean fueraMapa(final int velocidadX, final int velocidadY) {

		int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
		int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

		final Rectangle bordesMapa = ElementosPrincipales.mapa.obtenerBordes(posicionFuturaX, posicionFuturaY);

		final boolean fuera;

		if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa)
				|| LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)) {
			fuera = false;
		} else {
			fuera = true;
		}

		return fuera;
	}

	private void cambiarDireccion(final int velocidadX, final int velocidadY) {
		if (velocidadX == -1) {
			direccion = 1;
		} else if (velocidadX == 1) {
			direccion = 2;
		}

		if (velocidadY == -1) {
			direccion = 3;
		} else if (velocidadY == 1) {
			direccion = 0;
		}
	}

	private void animar() {
		if (!enMovimiento) {
			estado = 0;
			animacion = 0;
		}

		imagenActual = hs.obtenerSprite(estado,direccion).getImagen();
	}

	public void dibujar(Graphics g) {
		final int centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.ANCHO_SPRITE / 2;
		final int centroY = Constantes.ALTO_JUEGO / 2 - Constantes.ALTO_SPRITE / 2;

		DibujoDebug.dibujarImagen(g, imagenActual, centroX, centroY);
        
        DibujoDebug.dibujarString(g, p.getNombrePersonaje(),  centroX-10, centroY);
	}

	public void establecerPosicionX(double posicionX) {
		this.posicionX = posicionX;
	}

	public void establecerPosicionY(double posicionY) {
		this.posicionY = posicionY;
	}

	public double obtenerPosicionX() {
		return posicionX;
	}

	public double obtenerPosicionY() {
		return posicionY;
	}

	public int obtenerPosicionXInt() {
		return (int) posicionX;
	}

	public int obtenerPosicionYInt() {
		return (int) posicionY;
	}

	public int obtenerAncho() {
		return ANCHO_JUGADOR;
	}

	public int obtenerAlto() {
		return ALTO_JUGADOR;
	}

	public Rectangle obtener_LIMITE_ARRIBA() {
		return LIMITE_ARRIBA;
	}

    public int obtenerDireccion() {
        return direccion;
    }
    
    public Point obtenerPosicion() {
    	return new Point((int)posicionX, (int)posicionY);
    }
}
