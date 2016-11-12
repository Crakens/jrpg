package test;

import item.RegistroDeAlmas;

import enemigos.Dragon;
import enemigos.Enemigo;
import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Humano;
import personaje.Personaje;

public class PersonajeMataEnemigoMiTest {
	
	public static void main(String[] args) {
		
		boolean vive=true; //si el enemigo aun vive//
		Personaje p=new Humano(); //creo mi personaje//
		p.setNombrePersonaje("Java"); //con su nombre//
		Especialidad c=new Guerrero(); //especialidad//
		p.setCasta(c); 
		p.bonificacionDeCasta(); //le calculo los beneficios//
		
		Enemigo e=new Dragon(); //creo un enemigo//
		
		System.out.println(p.toString()+ "\n"); //muestra mi personaje antes de obtener el alma 
		
		while(vive){
			
			p.atacar(e); //ataco al enemigo//
			vive=e.estaVivo(); //pregunto si el enemigo aun vive//
			
			if(!vive){ //si el dragon picho//
				
				p.ganarExperiencia(e.devolverExperiencia()); //el dragon tiene 300 de exp, sube seguro de nivel//
				p=RegistroDeAlmas.asignarAlma(e.devolverItem(), p); //busco en el registro el alma del dragon y se la equipo//el alma que tiene el dragon es AlmaDelCoraje// *2 a la fuerza//
			}
			
		}

		System.out.println(p.toString()); //se muestra el personaje con los cambios de tomar el alma del dragon mas los puntos asministrados x haber subido de nivel//
	}

}
