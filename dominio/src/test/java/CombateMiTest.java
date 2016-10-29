package test;

import java.util.Scanner;

import combates.Combate;
import enemigos.Dragon;
import enemigos.Enemigo;
import enemigos.Goblin;
import item.RegistroDeAlmas;
import personaje.Elfo;
import personaje.Especialidad;
import personaje.Hechicero;
import personaje.Humano;
import personaje.Personaje;

public class CombateMiTest {

	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		int opc;
		Personaje java= new Elfo();
		java.setNombrePersonaje("Java");
		Especialidad c= new Hechicero(); 
		java.setCasta(c);
		java.bonificacionDeCasta();
		java=RegistroDeAlmas.asignarAlma(3, java); //me equipo AlmaHeroica//
		java=RegistroDeAlmas.asignarAlma(2, java); //me equipo AlmaOscura//
		
		System.out.println(java.toString() + "\n");
		
		do{
			System.out.println("Ingrese el combate a realizar:  \n 1_combateContraDragon 2_combateContraGoblin 3_contraRata\n");
			opc=sc.nextInt();
			
		}while(opc < 1 && opc >3);
		
		switch (opc) {
		
		case 1:
			java=combateContraDragon(java);break;
			
		case 2:
			java=combateContraGoblin(java);break;
			
		}
		
		System.out.println("Observar los cambios en la fuerza y la salud.Si pierde un item el personaje reestablece su salud al maximo\n");
		
		System.out.println(java.toString());
	}

	private static Personaje combateContraGoblin(Personaje java) {
		
		Enemigo e= new Goblin();
		
		Combate com= new Combate();
		
		com.combatir(java, e);
		
		if(!java.estaVivo()){
			System.out.println("Te han vencido y pierdes un item");
			java.perderItemMasValioso(java);
		}
		
		if(!e.estaVivo()){
			System.out.println("Has vencido al enemigo y obtienes un item y experiencia");
			java.ganarExperiencia(e.devolverExperiencia());
			java.serEnergizadoTotalmente();
			java=RegistroDeAlmas.asignarAlma(e.devolverItem(), java);
		}
		
		return java;
	}

	private static Personaje combateContraDragon(Personaje java) {
		
		Enemigo e= new Dragon();
		
		Combate com= new Combate();
		
		com.combatir(java, e);
		
		if(!java.estaVivo()){
			System.out.println("Te han vencido y pierdes un item\n");
			java.perderItemMasValioso(java);
		}
		
		if(!e.estaVivo()){
			System.out.println("Has vencido al enemigo y obtienes un item y experiencia\n");
			java.ganarExperiencia(e.devolverExperiencia());
			java.serEnergizadoTotalmente();
			java=RegistroDeAlmas.asignarAlma(e.devolverItem(), java);
		}
		
		return java;
	}
	
	
	
}
