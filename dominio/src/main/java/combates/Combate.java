package combates;

import java.util.Scanner;

import batallones.Batallon;
import batallones.BatallonEnemigos;
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
		}
		else{
			turno2=true;
		}
	}
	
	public void definirTurnos(Batallon b1, Batallon b2) {
		
		int aux1=sumarAgilidadBatallon(b1);
		int aux2=sumarAgilidadBatallon(b2);
		
		if(aux1 > aux2)
			this.turno1 =true;
		else
			this.turno2 =true;
	}
	
	private int sumarAgilidadBatallon(Batallon b) {
		
		Atacable p;
		int suma=0;
		
		for(int i=0; i < b.getTamBatallon(); i++){
			
			p=b.getPersonaje(i);
			suma+=p.getAgilidad();
		}
		
		return suma;
	}

	
	private void definirTurnos(Batallon b1, BatallonEnemigos b2) {
		
		int aux1=sumarAgilidadBatallon(b1);
		int aux2=sumarAgilidadBatallon(b2);
		
		if(aux1 > aux2)
			this.turno1 =true;
		else
			this.turno2 =true;
	}
	
	
	private int sumarAgilidadBatallon(BatallonEnemigos b) {
		
		Atacable p;
		int suma=0;
		
		for(int i=0; i < b.getTamBatallon(); i++){
			
			p=b.getPersonaje(i);
			suma+=p.getAgilidad();
		}
		
		return suma;
	}

	//personaje contra enemigo
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
				this.turno1 = false;
				
			}
			
			if(e.estaVivo() && huir!=true){
				if(this.turno2){
					
					combatiendo=e.atacar(p); //si el enemigo tiene varios ataques tiras un random y un switch//
					p.serEnergizado();
					this.turno1 = true;
					this.turno2 = false;
				}
			}
			else
				combatiendo=false;
		}
		
	}
	
	
	//Personaje contra Personaje
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
				this.turno1 = false;
				
			}
			
			if(e.estaVivo() && huir!=true){
				
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
					this.turno1 = false;
				}
			}
			else
				combatiendo = false;
		}

	}
	
	
	//batallon contra batallon
	public void combatir(Batallon b1, Batallon b2){ //personaje contra otro personaje //
		
		int contHuir1 = 0;
		int contHuir2 = 0;
		this.combatiendo = true;
		this.huir= false;
		
		definirTurnos(b1,b2);
	    
		b1.ordeNarBatallonPorMayorAgilidad();
		b2.ordeNarBatallonPorMayorAgilidad();
		
		while(combatiendo && !huir){ //mientras ambos esten vivos el combate perdura//
			
			
			if(this.turno1){
				
				for(int i=0; i < b1.getTamBatallon() ; i++){
				
					do{
						System.out.println("Ingrese opcion:");
						System.out.println("1_Ataque normal || 2_Hablidad de casta || 3_Huir");
						opc=sc.nextInt();	
					
					}while(opc < 1 && opc > 3);
						
					switch (opc) {
				
					case 1:
					{
						contHuir1=0;
						int j=0;
						
						do{
							
							System.out.println("Ingrese el numero del miembro que decea atacar:(entre 1 y" + b2.getTamBatallon() + "):");
							j=sc.nextInt();
						}while(j<1 && j>b2.getTamBatallon());
						
						combatiendo= b1.getPersonaje(i).atacar(b2.obtenerProximaVictima(j-1));break;
					}
				
					case 2:
					{	
						contHuir1=0;
						int j=0;
						
						do{
							
							System.out.println("Ingrese el numero del miembro que decea afectar:(entre 1 y" + b2.getTamBatallon() + "):");
							j=sc.nextInt();
						}while(j<1 && j>b2.getTamBatallon());
						
						combatiendo=habilidadesDeCasta(b1.getPersonaje(i),b2.obtenerProximaVictima(j-1));break;
					}
				
					case 3:
					{	
						contHuir1++;
						if(contHuir1 == b1.getTamBatallon())
							huir=true;
						break;
					}
					
					}
				
					for (int j2 = 0; j2 < b2.getTamBatallon(); j2++) {
						b2.getPersonaje(j2).serEnergizado();
					}
					
					this.turno2 = true;
					this.turno1 = false;
					b2.depurarBatallon();
				}
				
			}
			else
				combatiendo = false; //todos en el batallon 1 estan muertos
			
			if(b2.getTamBatallon()!=0 && !huir){ //si el batallon enemigo tiene tamaño 0 estan todos muertos 
				
				if(this.turno2){
				
					for (int i = 0; i < b2.getTamBatallon(); i++) {
						
						do{
							System.out.println("Ingrese opcion:");
							System.out.println("1_Ataque normal || 2_Hablidad de casta || 3_Huir");
							opc=sc.nextInt();	
						
						}while(opc < 1 && opc > 3);
							
						switch (opc) {
					
						case 1:
						{
							contHuir2=0;
							
							int j=0;
							
							do{
								
								System.out.println("Ingrese el numero del miembro que decea atacar:(entre 1 y" + b1.getTamBatallon() + "):");
								j=sc.nextInt();
							}while(j<1 && j>b1.getTamBatallon());
							
							combatiendo= b2.getPersonaje(i).atacar(b1.obtenerProximaVictima(j-1));break;
						}
					
						case 2:
						{	
							contHuir2=0;
							int j=0;
							
							do{
								
								System.out.println("Ingrese el numero del miembro que decea afectar:(entre 1 y" + b1.getTamBatallon() + "):");
								j=sc.nextInt();
							}while(j<1 && j>b1.getTamBatallon());
							
							combatiendo=habilidadesDeCasta(b2.getPersonaje(i),b1.obtenerProximaVictima(j-1));break;
						}
					
						case 3:
						{	
							contHuir2++;
							if(contHuir2 == b2.getTamBatallon())
								huir=true;
							break;
						}
						
						}
					
						for (int j1 = 0; j1 < b1.getTamBatallon(); j1++) {
							b1.getPersonaje(j1).serEnergizado();
						}
						
						this.turno1 = true;
						this.turno2 = false;
						b1.depurarBatallon();
					}
				}
				else
					combatiendo = false; //todos en el batallon 2 estan muertos
			}
					
		}

	}
	
	
	//batallon contra batallonEnemigos
	public void combatir(Batallon b1, BatallonEnemigos b2) {
		
		int contHuir = 0;
		this.combatiendo = true;
		this.huir= false;
		
		definirTurnos(b1,b2);
	    
		b1.ordeNarBatallonPorMayorAgilidad();
		b2.ordeNarBatallonPorMayorAgilidad();
		
		while(combatiendo && !huir){ //mientras ambos esten vivos el combate perdura//
			
			
			if(this.turno1){
				
				for(int i=0; i < b1.getTamBatallon() ; i++){
				
					do{
						System.out.println("Ingrese opcion:");
						System.out.println("1_Ataque normal || 2_Hablidad de casta || 3_Huir");
						opc=sc.nextInt();	
					
					}while(opc < 1 && opc > 3);
						
					switch (opc) {
				
					case 1:
					{
						contHuir=0;
						int j=0;
						
						do{
							
							System.out.println("Ingrese el numero del miembro que decea atacar:(entre 1 y " + b2.getTamBatallon() + "):");
							j=sc.nextInt();
						}while(j<1 && j>b2.getTamBatallon());
						
						combatiendo= b1.getPersonaje(i).atacar(b2.obtenerProximaVictima(j-1));break;
					}
				
					case 2:
					{	
						contHuir=0;
						int j=0;
						
						do{
							
							System.out.println("Ingrese el numero del miembro que decea afectar:(entre 1 y " + b2.getTamBatallon() + "):");
							j=sc.nextInt();
						}while(j<1 && j>b2.getTamBatallon());
						
						combatiendo=habilidadesDeCasta(b1.getPersonaje(i),b2.obtenerProximaVictima(j-1));break;
					}
				
					case 3:
					{	
						contHuir++;
						if(contHuir == b1.getTamBatallon())
							huir=true;
						break;
					}
					
					}
					
					for (int j2 = 0; j2 < b2.getTamBatallon(); j2++) {
						b2.getPersonaje(j2).serEnergizado();
					}
					
					this.turno2 = true;
					this.turno1 = false;
					b2.depurarBatallon();
				}
				
			}
			else
				combatiendo = false; //todos en el batallon 1 estan muertos

			if(b2.getTamBatallon()!=0 && !huir){
				
				if(this.turno2){
					
					for (int i = 0; i < b2.getTamBatallon() ; i++) {
						
						int j= (int) (Math.random()*b1.getTamBatallon()); //el batallon de enemigos ataca aleatoriamente a uno del batallon de personajes
						
						combatiendo=b2.getPersonaje(i).atacar(b1.obtenerProximaVictima(j)); //si el enemigo tiene varios ataques tiras un random y un switch//
						
						for (int j1 = 0; j1 < b1.getTamBatallon(); j1++) {
							b1.getPersonaje(j1).serEnergizado();
						}
						
						this.turno1 = true;
						this.turno2 = false;
						b1.depurarBatallon();
					}
				}
			}
			else
				combatiendo=false;
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

	public boolean getHuir() {
		
		return this.huir;
	}

	public boolean getTurno1() {
		
		return this.turno1;
	}
	
	public boolean getTurno2() {
		
		return this.turno2;
	}
}
	
