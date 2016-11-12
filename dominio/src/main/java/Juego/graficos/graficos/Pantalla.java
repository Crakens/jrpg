package graficos;

import mapa.cuadro.Cuadro;

public final class Pantalla {
	
	private final int ancho;
	private final int alto;
	
	private int diferenciaX;
	private int diferenciaY;
	
	
	public final int [] pixeles;
	
	//temporal//
	
//	private final static int LADO_SPRITE=32; //constante y solo sirve pra la prueba xeso no uso un geter//
//	private final static int MASCARA_SPRITE = LADO_SPRITE - 1; //
	//Fin temporal//
	
	public Pantalla(final int ancho,final int alto){
		
		this.ancho=ancho;
		this.alto=alto;
		
		pixeles=new int[ancho*alto];
	}
	
	public void limpiar(){ //limpia la pantalla en cada actualizacion//
		
		for(int i=0; i<pixeles.length;i++){
			pixeles[i]=0; //pinto la pantalla de negro ya que el 0 es el negro//
		}
	}
	
	//TEMPORAL//
//	public void mostrar(final int compensacionX,final int compensacionY){ //compensacionX y  compensacionY son al posicion del personaje en el mapa//
//		
//		for(int i=0;i<alto;i++){
//			
//			int posicionY= i + compensacionY; //se calcula desde donde se debe mostrar en y//
//			
//			if(posicionY<0 || posicionY>=alto){ //si la posicionY se sale del rango del mapa//
//				continue; //se saltea el incremento de i++ y se dirige directamente al for del j//
//			}
//			
//			for(int j=0;j<ancho;j++){
//				
//				int posicionX= j + compensacionX; //se calcula desde donde se debe mostrar en x//
//			
//				if(posicionX<0 || posicionX>=ancho){ //si la posicionX se sale del rango del mapa//
//					continue; //se saltea el incremento de j++//
//				}
//				
//				//codigo temporal solo pra probar su funcionamiento//
//				pixeles[posicionX + posicionY*ancho]= Sprite.LLANURA.pixeles[(j & MASCARA_SPRITE) + (i & MASCARA_SPRITE)*LADO_SPRITE]; //el operador & operado con MASCARA_SPRITE chequea q tanto j como i no sobre pase el valor del Sprite...de ocurrir esto i o j vuelve a 0// 
//				
//			}
//		}
//		
//	}
	
	//FIN TEMPORAL//
	
	public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro){
		
		compensacionX -= diferenciaX;
		compensacionY -= diferenciaY;
		
		
		for(int y=0; y < cuadro.sprite.getLado(); y++){
			
			int posicionY = y + compensacionY;
			
			for(int x=0; x < cuadro.sprite.getLado(); x++){
				
				int posicionX = x + compensacionX;
				
				if(posicionX < -cuadro.sprite.getLado() || posicionX >= ancho || posicionY < 0 || posicionY >= alto)
					break; //solo dibujo la porcion que ocupa la pantalla para no sobre cargar la memoria al cuete//
				
				if(posicionX < 0 )
					posicionX=0;
				
				pixeles[posicionX + posicionY * ancho] = cuadro.sprite.pixeles[ x + y * cuadro.sprite.getLado()];
			}
		}
	}
	
	public void estableceDiferencia(final int diferenciaX, final int diferenciaY){
		
		this.diferenciaX = diferenciaX;
		this.diferenciaY = diferenciaY;	
	}
	
	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}
}
