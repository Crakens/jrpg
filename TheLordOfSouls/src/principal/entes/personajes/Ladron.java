package principal.entes.personajes;

public class Ladron extends Especialidad{
	
	protected int evasion=5;
	protected int danioCritico=0;
	private int anularDefensa=0;
	
	public Ladron(){
		
		this.habilidades.add("Danio Critico");
		this.habilidades.add("Salir Danio Critico");
		
		this.energia=3;
		this.salud=3;
		this.agilidad=3;
		
		this.nombre="Ladron";
		
		this.descripcion="\nAgilidad y riesgo son ambos excelentes rasgos para el ladron, y su confluencia puede crear espectaculares proezas de acrobacia."
				+ "\nCuando pilla a su oponente en un momento en que sea incapaz de defenderse eficazmente de su ataque, el ladron puede alcanzar un punto vital para infligir mayor danio."
				+ "\nAdemas, el ladron puede echar a rodar para apartarse de un golpe mortal y hacer que éste le inflija menor danio";
	}
	
	public void danioCritico(int anularDefensa){
		
		this.danioCritico=20;
		this.anularDefensa=anularDefensa;
	}
	
	public void salirDanioCritico(){
		
		this.danioCritico=0;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		
		int aux = ((int)(Math.random()*danioCritico));
		
		return (15 + aux);
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		
		if(this.danioCritico == 0)
			return (-3 + ((int)(Math.random()*evasion))); //la idea es que evasion se incremente con cada subida de nivel//
		else
			return (-this.anularDefensa); //le deja la defensa en cero por estar en modo danioCritico//
	}
	
}

