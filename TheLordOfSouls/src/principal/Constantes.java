package principal;

import java.awt.Font;

import principal.herramientas.CargadorRecursos;

public class Constantes {

	public static final int ANCHO_SPRITE = 32;
	public static final int ALTO_SPRITE = 32;
	
	public static int ANCHO_JUEGO = 640;
	public static int ALTO_JUEGO = 360;

	// public static int ANCHO_PANTALLA_COMPLETA = 1920;
	// public static int ALTO_PANTALLA_COMPLETA = 1080;

	public static int ANCHO_PANTALLA_COMPLETA = 1280;
	public static int ALTO_PANTALLA_COMPLETA = 720;

	// 4:3

	public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO;
	public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO;

	public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2;
	public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2;

	public static int MARGEN_X = ANCHO_JUEGO / 2 - ANCHO_SPRITE / 2;
	public static int MARGEN_Y = ALTO_JUEGO / 2 - ALTO_SPRITE / 2;

	public static String RUTA_MAPA = ".\\recursos\\mapas\\mapa1.csv";
	public static String RUTA_ICONO_RATON = "/imagenes/iconos/iconoCursor.png";
	public static String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/";
	public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/iconoVentana.png";
	public static String RUTA_LOGOTIPO = "/imagenes/iconos/logotipo.png";
	public static String RUTA_OBJETOS = "/imagenes/hojasObjetos/Objetos.png";
	public static String RUTA_ENEMIGOS = "/imagenes/hojasEnemigos/";
	public static String RUTA_MENU_LOGIN = "/imagenes/hojasMenus/The Lord of Souls 3.png";
	public static String RUTA_SELECCION_MAPA = "/imagenes/hojasMenus/mapa1.png";
	public static String RUTA_SELECCION_PERSONAJE = "/imagenes/hojasMenus/pergamino.png";
	public static String RUTA_IMAGEN_DE_COMBATE = "/imagenes/hojasEnemigos/";
	public static String RUTA_ARCHIVO_IP = ".\\recursos\\IP.ini";
	
	public boolean EMPEZAR_JUEGO=false;

//	public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/px10.ttf");
}

