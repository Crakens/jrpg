package personaje;

import java.util.HashMap;
import java.util.Map;

public class Hechicero extends Especialidad{
	
private Map<String, Hechizo> hechizos = new HashMap<String, Hechizo>();


	public Hechicero(){
		
		this.hechizos=new HashMap<String, Hechizo>();
		this.magiaTot = 50;
		this.magia= magiaTot;
		this.energia=-6;
		this.salud=0;
		this.agilidad=-3;
		
		this.nombre="Hechicero";
		this.descripcion="\nLos hechiceros son lanzadores de conjuros arcanos que manipulan la energía mágica con imaginación y talento, más que con una disciplina de estudio. "
				+ 		 "\nNo tienen libros ni mentores ni teorías: sólo un poder en bruto que dirigen a voluntad."
				+        "\nYa que los hechiceros ganan sus poderes sin pasar por los años de estudio riguroso, tienen más tiempo para aprender habilidades de lucha y son competentes con las armas sencillas";
	}
	
	
	public void agregarHechizo(String conjuro, Hechizo hechizo) {
		
		this.hechizos.put(conjuro, hechizo);
	}
	
	public int getCantidadDeHechizos() {
		
		return this.hechizos.size();
	}

	public boolean hechizar(String conjuro, Atacable e) {
		
		int aux=this.hechizos.get(conjuro).getGastaEnergia();
		
		if(aux<=magia)
		{
			this.hechizos.get(conjuro).afectar(e);
			magia-=aux;//aca se restaria el valor de magia que gasta el conjuro a la magia//
		}
		else
		{
			System.out.println("No se posee la magia necesaria para realizar el hechizo");
			return false;
		}
		
		return true;
	}
	
	@Override
	public int calcularPuntosDeAtaque() {
		
		return 10;
	}

	@Override
	public int obtenerPuntosDeDefensa() {
		
		return 6;
	}
}
