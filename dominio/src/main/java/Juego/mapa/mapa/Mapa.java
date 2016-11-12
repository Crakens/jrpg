package mapa;

import mapa.cuadro.Cuadro;
import graficos.Pantalla;

public abstract class Mapa {

	protected int ancho;
	protected int alto;
	
	protected int [] cuadros; //tile en ingles
	
	public Mapa(int ancho, int alto){ //Para crear un mapa de forma aleatoria//
		
		this.ancho=ancho;
		this.alto=alto;
		
		cuadros=new int[ancho*alto];
		generarMapa();
	}
	
	public Mapa(String ruta){
		
		cargarMapa(ruta);
	}

	protected void generarMapa() {
		
		
	}
	
	private void cargarMapa(String ruta) {
		
		
	}
	
	public void actualizar(){
		
		
	}
	
	public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla){
		//oeste,este,norte,sur//
		
		pantalla.estableceDiferencia(compensacionX, compensacionY);
		
		int o = compensacionX >> 5; //equivalente a dividir por 32 con shifting porque es mas rapido//divido por 32 que es tamaño del cuadro asi tiene un pixel//
		int e = (compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5;
		int n = compensacionY >> 5;
		int s = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5;
		
		for(int y = n; y < s; y++){
			
			for(int x = o; x < e; x++){
				
				obtenCuadro(x, y).mostrar(x, y, pantalla);
			}
		}
	}
	
	public Cuadro obtenCuadro(final int x, final int y){
		
		if(x < 0 || y < 0 || x >= ancho || y >= alto){
			return Cuadro.VACIO;
		}
		
		switch(cuadros[x + y * ancho]){
		
		case 0:
				return Cuadro.LLANURA;
		
		default:
				return Cuadro.VACIO;
			
		}	
	}
}
