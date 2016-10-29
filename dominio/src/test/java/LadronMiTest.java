package test;

import personaje.Elfo;
import personaje.Humano;
import personaje.Orco;
import personaje.Especialidad;
import personaje.Ladron;
import personaje.Personaje;

public class LadronMiTest {

	public static void main(String[] args) {
		
		Personaje personaje=new Elfo(); //puede ser un Orco, un Humano o un Elfo//
		Especialidad casta=new Ladron();
		personaje.setCasta(casta);
		personaje.bonificacionDeCasta(); //genero mi ladron//
		
		System.out.println("El ataque en posicion normal del ladron es :" + personaje.calcularPuntosDeAtaque());
		System.out.println("La defensa en posicion normal del ladron es :" + personaje.obtenerPuntosDeDefensa());
		
		
		//daño critico// aumenta mucho el ataque segun un random pero a costo de dejar la defensa a 0//
		
		System.out.println("\nEl ladron utiliza dañoCritico");
		
		personaje.getCasta().getLadron().dañoCritico(personaje.getDefensa()); //accedo desde la casta a la habilidad daño critico//
		
		System.out.println("\nEl ataque del personaje cambia a " + personaje.calcularPuntosDeAtaque());
		System.out.println("\nLa defensa del personaje cambia a " + personaje.obtenerPuntosDeDefensa());
		
		personaje.getCasta().getLadron().salirDañoCritico(); //puede volver a su estado normal//
		
		System.out.println("\n***HABILIDAD EVASION***"); //la habilidad de evasion aumenta la defensa en cada turno segun un random//
		
		Personaje enemigo=new Humano();
		casta=new Ladron();
		enemigo.setCasta(casta);
		enemigo.bonificacionDeCasta(); //creo enemigo para atacar a mi ladron//
		
		System.out.println("\nLa salud inicial del personaje es: " + personaje.getSalud());
		
		System.out.println("\nEl enemigo ataca con un daño de " + enemigo.calcularPuntosDeAtaque());
		System.out.println("\nLa defensa del personaje sin evacion es " + (personaje.getDefensa()-6) + " y el daño que reciviria seria de " + (enemigo.calcularPuntosDeAtaque() - (personaje.getDefensa()-6))); //El -6 es porque al legir como casta ladron se le resta 6 a la defesa//
		System.out.println("\nY la salud final del personaje seria: " + (personaje.getSalud() - (enemigo.calcularPuntosDeAtaque() - (personaje.getDefensa()-6))) );
		
		enemigo.atacar(personaje);
		
		System.out.println("\nPero gracias a su habilidad de evasion, su salud real es de: " + personaje.getSalud() + ". Si el daño supuesto es igual al real es porque no pudo evadir el golpe ya que usa random");
	}
	
}
