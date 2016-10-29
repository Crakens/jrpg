package personaje;

public abstract class Especialidad {
	
	protected String nombre;
	protected String descripcion;
	protected int magia=0;	
	protected int energia;
	protected int salud;
	protected int agilidad;
	protected int defensa;
	protected int magiaTot=0;
	
	public abstract int calcularPuntosDeAtaque();
	public abstract int obtenerPuntosDeDefensa();
	
	public Hechicero getHechicero(){
		
		if(this.nombre=="Hechicero")
			return (Hechicero)this; //devuelve la misma casta pero casteada//
		
		return null;
	}
	
	public Ladron getLadron(){
		
		if(this.nombre=="Ladron")
			return (Ladron)this; //devuelve la misma casta pero casteada//
		
		return null;
	}
	
	public Guerrero getGuerrero(){
		
		if(this.nombre=="Guerrero")
			return (Guerrero)this; //devuelve la misma casta pero casteada//
		
		return null;
	}
	
	public String getNombre() {
		
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public int getMagia() {
		return magia;
	}
}
