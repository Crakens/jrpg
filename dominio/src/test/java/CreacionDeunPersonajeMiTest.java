package test;

import java.util.Scanner;

import personaje.Elfo;
import personaje.Especialidad;
import personaje.Guerrero;
import personaje.Hechicero;
import personaje.Humano;
import personaje.Ladron;
import personaje.Orco;
import personaje.Personaje;

public class CreacionDeunPersonajeMiTest {
	
	public static void main(String[] args) {
		
		Personaje p = null;
		Especialidad c= null;
		Scanner sc = new Scanner(System.in); //Se crea el lector//
		System.out.println("\nIngrese el nombre de su personaje");
		String nombre = sc.nextLine();
		String cadena;
		boolean caprichoDelWhile= true; //esta variable es por que el DoWhile se me encapricho y no salia nunk del bucle//
		
		do{
			System.out.println("Razas disponibles: [Orco, Humano, Elfo]");
			System.out.println("\nIngrese una de las 3 razas disponibles que desea que tenga su personaje:");
			cadena = sc.nextLine();
			
			switch (cadena) {
			
			case "Orco":
				{
					p=new Orco();
					caprichoDelWhile=false;
					break;
				}
				
			case "Humano":
				{
					p=new Humano();
					caprichoDelWhile=false;
					break;
				}
				
			case "Elfo":
				{
					p=new Elfo();
					caprichoDelWhile=false;
					break;
				}
				
			default:
				{
					System.out.println("La raza ingresada no exite elija una de las posibles\n");break;
				}
			}
			
		}while(caprichoDelWhile);
		
		p.setNombrePersonaje(nombre); //le pone el nombre que eligio al personaje//
		
		caprichoDelWhile=true;
		
		do{
			System.out.println("Castas disponibles: [Hechicero, Guerrero, Ladron]");
			System.out.println("\nIngrese una de las 3 castas disponibles que desea que tenga su personaje:");
			cadena = sc.nextLine();
			
			switch (cadena) {
			
			case "Hechicero":
				{
					c=new Hechicero();
					caprichoDelWhile=false;
					break;
				}
				
			case "Ladron":
				{
					c=new Ladron();
					caprichoDelWhile=false;
					break;
				}
				
			case "Guerrero":
				{
					c=new Guerrero();
					caprichoDelWhile=false;
					break;
				}
				
			default:
				{
					System.out.println("La raza ingresada no exite elija una de las posibles\n");break;
				}
			}
			
				
		}while(caprichoDelWhile);
		
		p.setCasta(c);
		
		System.out.println("\nSe muestran las caracteristicas del personaje formado sin los efectos de la casta\n");
		System.out.println(p.toString()); 
		System.out.println("\n\nAhora se muestran las caracteristicas del personaje formado con los efectos de la casta\n");
		p.bonificacionDeCasta();
		System.out.println(p.toString()); 
		
	}
	
}
