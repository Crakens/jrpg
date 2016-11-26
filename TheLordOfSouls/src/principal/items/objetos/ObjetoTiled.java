package principal.items.objetos;

import java.awt.Point;

public class ObjetoTiled {

	private Point posicion;
	private Objeto objeto; //objeto la clase creada no la de java, un objeto que se encuentra en el mapa//
	
	public ObjetoTiled(Point posicion, Objeto objeto){
		this.posicion = posicion;
		this.objeto = objeto;
	}
	
	public Point obtenerPosicion(){
		return posicion;
	}
	
	public Objeto obtenerObjeto(){
		return objeto;
	}
}
