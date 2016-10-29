package combates;

import item.RegistroDeAlmas;

import java.util.Scanner;

import enemigos.Enemigo;
import personaje.Atacable;
import personaje.Personaje;

public class Combate {
	
	boolean turno1;
	boolean turno2;
	boolean combatiendo;
	boolean huir;
	int opc;
	Scanner sc=new Scanner(System.in);
	
	public void definirTurnos(Personaje p, Atacable e){
		
		if (p.getAgilidad() > e.getAgilidad()){
			turno1= true;
			turno2= false;
		}
		else{
			turno2=true;
			turno1=false;
		}
	}
	
	public void combatir(Personaje p, Enemigo e){ // personaje contra enemigo //
		
		this.combatiendo = true;
		this.huir= false;
		
		definirTurnos(p,e);
		
		while(combatiendo && !huir){ //mientras ambos esten vivos el combate perdura//
			
			if(this.turno1){
				
				do{
					System.out.println("Ingrese opcion:");
					System.out.println("1_Ataque normal || 2_Hablidad de casta || 3_Huir");
					opc=sc.nextInt();	
					
				}while(opc < 1 && opc > 3);
						
				switch (opc) {
				
				case 1:
				{
					combatiendo=p.atacar(e);break;
				}
				
				case 2:
				{	
					combatiendo=habilidadesDeCasta(p,e);break;
				}
				
				case 3:
				{	
					System.out.println("Por cobarde pierdes un item\n");
					p.perderItemMasValioso(p);
					huir = true; //si el personaje decide huir//
					break;
				}
					
				}
				
				e.serEnergizado();
				this.turno2 = true;
				
			}
			
			if(e.estaVivo()){
				if(this.turno2){
					
					combatiendo=e.atacar(p); //si el enemigo tiene varios ataques tiras un random y un switch//
					p.serEnergizado();
					this.turno1 = true;
				}
			}
			else
				combatiendo=false;
		}
		
	}
	
	public void combatir(Personaje p, Personaje e){ //personaje contra otro personaje //
		
		this.combatiendo = true;
		this.huir= false;
		
		definirTurnos(p,e);
		
		while(combatiendo && !huir){ //mientras ambos esten vivos el combate perdura//
			
			
			if(this.turno1){
				
				do{
					System.out.println("Ingrese opcion:");
					System.out.println("1_Ataque normal || 2_Hablidad de casta || 3_Huir");
					opc=sc.nextInt();	
					
				}while(opc < 1 && opc > 3);
						
				switch (opc) {
				
				case 1:
				{
					combatiendo=p.atacar(e);break;
				}
				
				case 2:
				{	
					combatiendo=habilidadesDeCasta(p,e);break;
				}
				
				case 3:
				{	
					p.perderItemMasValioso(p);
					huir = true; //si el personaje decide huir//
					break;
				}
					
				}
				
				e.serEnergizado();
				this.turno2 = true;
				
			}
			
			if(this.turno2){
				
				do{
					System.out.println("Ingrese opcion:");
					System.out.println("1_Ataque normal || 2_Hablidad de casta || 3_Huir");
					opc=sc.nextInt();	
					
				}while(opc < 1 && opc > 3);
						
				switch (opc) {
				
				case 1:
				{
					combatiendo=e.atacar(p);break;
				}
				
				case 2:
				{	
					combatiendo=habilidadesDeCasta(e,p);break;
				}
				
				case 3:
				{	
					e.perderItemMasValioso(e);
					huir = true; //si el personaje decide huir//
					break;
				}
					
				}
				
				p.serEnergizado();
				this.turno2 = true;
			}
		}

	}
	
	private boolean habilidadesDeCasta(Personaje p, Atacable e) {
		
		System.out.println("Las habilidades disponibles son:");
		
		switch (p.getCasta().getNombre()) {
		
		case "Hechicero":
		{	
			do{
				System.out.println("1_Hechizar");
				opc=sc.nextInt();
				
			}while(opc != 1);
			
			if(p.getCasta().getHechicero().getCantidadDeHechizos() != 0)
				p.getCasta().getHechicero().hechizar("Engorgio", e); //Por ahora solo sabe engorgio//
			else
				System.out.println("Sos mago pro no sabes ni un hechizo u.u");
			
			return true;
		}
				
		case "Guerrero":
		{
			do{
				System.out.println("1_Posicion Agresiva || 2_Posicion Defensiva || 0_Posicion Normal");
				opc=sc.nextInt();
				
			}while(opc< 0 && opc> 2);
			
			switch(opc){
			
				case 0:
				{
					p.getCasta().getGuerrero().posicionNormal();break;
				}
					
				case 1:
				{
					p.getCasta().getGuerrero().posicionAgresiva();break;
				}
			
				case 2:
				{
					p.getCasta().getGuerrero().posicionDefensiva();break;
				}
			
			}
			
			return p.atacar(e);
		}
		
		case "Ladron":
		{
			do{
				System.out.println("1_Daño Critico || 0_Ataque Normal");
				opc=sc.nextInt();
				
			}while(opc != 1 && opc != 0);
			
			if(opc == 1)
				p.getCasta().getLadron().dañoCritico(p.getDefensa()); //Por ahora solo tiene daño critico//
			
			return p.atacar(e);
			
		}
		
		}
		
		return true;
	}
	
}
	
