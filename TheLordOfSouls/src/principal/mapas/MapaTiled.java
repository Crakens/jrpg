package principal.mapas;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.interfaz_usuario.Combate;
import principal.interfaz_usuario.LevelUp;
import principal.control.GestorControles;
import principal.entes.enemigos.Enemigo;
import principal.entes.enemigos.RegistroEnemigos;
import principal.herramientas.DibujoDebug;
import principal.items.objetos.Objeto;
import principal.items.objetos.ObjetoTiled;
import principal.items.objetos.RegistroObjetos;
import principal.sprites.HojaSprites;
import principal.sprites.Sprite;
import principal.items.equipados.RegistroDeAlmas;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class MapaTiled {

	private int anchoMapaEnTiles;
	private int altoMapaEnTiles;
	private Point puntoInicial;
	private ArrayList<CapaSprites> capasSprites;
	private ArrayList<CapaColisiones> capasColisiones;

	private ArrayList<Rectangle> areasColisionesOriginales;
	public ArrayList<Rectangle> areasColisionPorActualizacion;
	private Sprite [] paletaSprites;
	
	private ArrayList <ObjetoTiled> objetosMapa;
	private ArrayList <Enemigo> enemigosMapa;
	private Rectangle reestauracion;
	
	public MapaTiled(final String ruta){
		
		try {
			
				FileReader archivo=new FileReader(ruta);
				BufferedReader br=new BufferedReader(archivo);
				String contenido = null,line; // asignar el contenido del archivo json a esta variable//
				line=br.readLine();
				contenido=line;
				line=br.readLine();
				while ( line != null) {
					
					contenido+=line;
					line=br.readLine();
					
				}
				
				//ANCHO, ALTO//
				
				JSONObject globalJSON= obtenerObjetoJSON(contenido);
				
				anchoMapaEnTiles = obtenerIntDesdeJSON(globalJSON, "width");
				altoMapaEnTiles = obtenerIntDesdeJSON(globalJSON, "height");
				
				//PUNTO DE REESTAURACION//
				JSONObject reestauracion = obtenerObjetoJSON(globalJSON.get("punto reestauracion").toString());
				
				this.reestauracion= new Rectangle(obtenerIntDesdeJSON(reestauracion, "x"), obtenerIntDesdeJSON(reestauracion, "y"), obtenerIntDesdeJSON(reestauracion, "width"), obtenerIntDesdeJSON(reestauracion, "height"));
				
				//PUNTO INICIAL//
				JSONObject puntoInicial = obtenerObjetoJSON(globalJSON.get("start").toString());
				
				this.puntoInicial = new Point(obtenerIntDesdeJSON(puntoInicial, "x"),obtenerIntDesdeJSON(puntoInicial, "y"));
				
				//CAPAS//
				JSONArray capas= obtenerArrayJSON(globalJSON.get("layers").toString());
				
				this.capasSprites= new ArrayList<CapaSprites>();
				this.capasColisiones= new ArrayList<CapaColisiones>();
				
				//INICIAR CAPAS//
				
				for (int i = 0; i < capas.size(); i++) {
					JSONObject datosCapa= obtenerObjetoJSON(capas.get(i).toString());
					int anchoCapa= obtenerIntDesdeJSON(datosCapa, "width");
					int altoCapa= obtenerIntDesdeJSON(datosCapa, "height");
					int xCapa= obtenerIntDesdeJSON(datosCapa, "x");
					int yCapa= obtenerIntDesdeJSON(datosCapa, "y");
					String tipo= datosCapa.get("type").toString();
					
					
					switch (tipo) {
					case "tilelayer":
						JSONArray sprites= obtenerArrayJSON(datosCapa.get("data").toString());
						int[] spritesCapa = new int[sprites.size()];
						for (int j = 0; j < sprites.size(); j++) {
							int codigoSprite = Integer.parseInt(sprites.get(j).toString());
							spritesCapa[j] = codigoSprite -1;
						}
						this.capasSprites.add(new CapaSprites(anchoCapa, altoCapa, xCapa, yCapa, spritesCapa));
						break;
					case "objectgroup":
						JSONArray rectangulos = obtenerArrayJSON(datosCapa.get("objects").toString());
						Rectangle [] rectangulosCapa = new Rectangle[rectangulos.size()];
						for (int j = 0; j < rectangulos.size(); j++) {
							JSONObject datosRectangulo= obtenerObjetoJSON(rectangulos.get(j).toString());
							
							int x= obtenerIntDesdeJSON(datosRectangulo, "x");
							int y= obtenerIntDesdeJSON(datosRectangulo, "y");
							int ancho= obtenerIntDesdeJSON(datosRectangulo, "width");
							int alto= obtenerIntDesdeJSON(datosRectangulo, "height");
							
							if (x == 0) {
								x=1;
							}
							
							if (y == 0 ) {
								y=1;
							}
							
							if (ancho == 0 ) {
								ancho=1;
							}
							
							if (alto == 0 ) {
								alto=1;
							}
							
							Rectangle rectangulo = new Rectangle(x, y, ancho, alto);
							rectangulosCapa[j] = rectangulo;
							
						}
						this.capasColisiones.add(new CapaColisiones(anchoCapa, altoCapa, xCapa, yCapa, rectangulosCapa));
						
						break;
						
					}
					br.close();
				}
				
				
				//CONVINAR COLISIONES EN UN SOLO ARRAYLIST POR EFICIENCIA//
				areasColisionesOriginales = new ArrayList<Rectangle>();
				for (int i = 0; i < capasColisiones.size(); i++) {
					Rectangle [] rectangulos = capasColisiones.get(i).obtenerColisionables();
					
					for (int j = 0; j < rectangulos.length; j++) {
						
						areasColisionesOriginales.add(rectangulos[j]);
					}
				}
				
				//AVERIGUAR TOTAL SPRITES EXISTENTES EN TODAS LAS CAPAS
				JSONArray coleccionesSprites = obtenerArrayJSON(globalJSON.get("tilesets").toString());
				int totalSprites = 0;
				
				for (int i = 0; i < coleccionesSprites.size(); i++) {
					JSONObject datosGrupo = obtenerObjetoJSON(coleccionesSprites.get(i).toString());
					totalSprites += obtenerIntDesdeJSON(datosGrupo, "tilecount");
				}
				
				paletaSprites = new Sprite[totalSprites];
				
				//ASIGNAR SPRITES NECESARIOS A LA PALETA A PARTIR DE LAS CAPAS//
				
				for (int i = 0; i < coleccionesSprites.size(); i++) {
					JSONObject datosGrupo = obtenerObjetoJSON(coleccionesSprites.get(i).toString());
					String nombreImagen = datosGrupo.get("image").toString();
					int anchoTiles = obtenerIntDesdeJSON(datosGrupo, "tilewidth");
					int altoTiles = obtenerIntDesdeJSON(datosGrupo, "tileheight");
					HojaSprites hoja = new HojaSprites("/imagenes/hojasTexturas/" + nombreImagen, anchoTiles, altoTiles, false);
					
					int primerSpriteColeccion = obtenerIntDesdeJSON(datosGrupo, "firstgid") - 1;
					int ultimoSpriteColeccion = primerSpriteColeccion + obtenerIntDesdeJSON(datosGrupo, "tilecount") - 1;
					
					for (int j = 0; j < this.capasSprites.size(); j++) {
						
						CapaSprites capaActual = this.capasSprites.get(j);
						int[] spritesCapa = capaActual.obtenerArraySprites();
						
						for (int k = 0; k < spritesCapa.length; k++) {
							int idSpriteActual = spritesCapa[k];
							if (idSpriteActual >= primerSpriteColeccion && idSpriteActual <= ultimoSpriteColeccion) {
								if (paletaSprites[idSpriteActual] == null) { //cada sprite se guarda una unica ves//
									paletaSprites[idSpriteActual] = hoja.obtenerSprite(idSpriteActual - primerSpriteColeccion);
								}
							}
						}
					}
				}
				
				//OBTENER OBJETOS//
				objetosMapa= new ArrayList<ObjetoTiled>();
				JSONArray coleccionObjetos = obtenerArrayJSON(globalJSON.get("objetos").toString());
				for (int i = 0; i < coleccionObjetos.size(); i++) {
					JSONObject datosObjecto = obtenerObjetoJSON(coleccionObjetos.get(i).toString());
					
					int idObjeto = obtenerIntDesdeJSON(datosObjecto, "id");
					int xObjeto = obtenerIntDesdeJSON(datosObjecto, "x");
					int yObjeto = obtenerIntDesdeJSON(datosObjecto, "y");
					
					Point posicionObjeto = new Point(xObjeto, yObjeto);
					Objeto objeto = RegistroObjetos.obtenerObjeto(idObjeto); 
					ObjetoTiled objetoUnico = new ObjetoTiled(posicionObjeto, objeto);
					objetosMapa.add(objetoUnico);
				}
				
				//OBTENER ENEMIGOS//
				enemigosMapa= new ArrayList<>();
				JSONArray coleccionEnemigos = obtenerArrayJSON(globalJSON.get("enemigos").toString());
				for (int i = 0; i < coleccionEnemigos.size(); i++) {
					JSONObject datosEnemigo = obtenerObjetoJSON(coleccionEnemigos.get(i).toString());
					
					int idEnemigo = obtenerIntDesdeJSON(datosEnemigo, "id");
					int xEnemigo = obtenerIntDesdeJSON(datosEnemigo, "x");
					int yEnemigo = obtenerIntDesdeJSON(datosEnemigo, "y");
					
					Point posicionEnemigo = new Point(xEnemigo, yEnemigo);
					Enemigo enemigo = RegistroEnemigos.obtenerEnemigo(idEnemigo); 
				
					enemigo.establecerPosicion(posicionEnemigo.x, posicionEnemigo.y);

					enemigosMapa.add(enemigo);
				}
				
				areasColisionPorActualizacion = new ArrayList<Rectangle>();
				
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "No se encontro el archivo del mapa","Error durante MapaTiled", JOptionPane. ERROR_MESSAGE);
		}
		
	}
	
	public void actualizar(){
		
		puntoRestauracion();
		actualizarAreasColisiones();
		actualizarRecogidaObjetos();
		actualizarEnemigos();
	}
	
	
	private void actualizarAreasColisiones() {
		if (!areasColisionPorActualizacion.isEmpty()) {
			areasColisionPorActualizacion.clear();
		}
		
		for (int i = 0; i < areasColisionesOriginales.size(); i++) {
			Rectangle rInicial = areasColisionesOriginales.get(i);
			
			int puntoX = rInicial.x - (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X;
			int puntoY = rInicial.y - (int) ElementosPrincipales.jugador.obtenerPosicionY() + Constantes.MARGEN_Y;
			
			final Rectangle rFinal = new Rectangle(puntoX, puntoY, rInicial.width, rInicial.height);
			
			areasColisionPorActualizacion.add(rFinal);
		}
	}
	
	private void actualizarRecogidaObjetos() {
		if (!objetosMapa.isEmpty()) {
            final Rectangle areaJugador = new Rectangle(ElementosPrincipales.jugador.obtenerPosicionXInt(),
                    ElementosPrincipales.jugador.obtenerPosicionYInt(), Constantes.ANCHO_SPRITE, Constantes.ALTO_SPRITE);

            for (int i = 0; i < objetosMapa.size(); i++) {
                final ObjetoTiled objetoActual = objetosMapa.get(i);

                final Rectangle posicionObjetoActual = new Rectangle(
                        objetoActual.obtenerPosicion().x,
                        objetoActual.obtenerPosicion().y, Constantes.ANCHO_SPRITE,
                        Constantes.ALTO_SPRITE);

                if (areaJugador.intersects(posicionObjetoActual)) {
                    switch (objetoActual.obtenerObjeto().obtenerId()) {
					case 0: //si la id del objeto es 0 eso significa que es un CuraSalud
					{
						ElementosPrincipales.jugador.p.serCurado(+20);
						//se accede al metodo serCurado del personaje del Jugador//
					}break;
					case 1: //si la id del objeto es 1 eso significa que es un RecuperaMagia
					{
						if (ElementosPrincipales.jugador.p.getCasta().getNombre() == "Hechicero") {
							ElementosPrincipales.jugador.p.getCasta().getHechicero().restaurarMagia(+20);
						}
						
						//se accede al metodo recuperaMagia del personaje//
					}break;
					
					}
                    objetosMapa.remove(i);
                }
            }
        }
	}
	
	private void actualizarEnemigos() {
		if (!enemigosMapa.isEmpty()) {
            final Rectangle areaJugador = new Rectangle(ElementosPrincipales.jugador.obtenerPosicionXInt(),
                    ElementosPrincipales.jugador.obtenerPosicionYInt(), Constantes.ANCHO_SPRITE, Constantes.ALTO_SPRITE);

            for (int i = 0; i < enemigosMapa.size(); i++) {
                final Enemigo enemigoActual = enemigosMapa.get(i);

                final Rectangle posicionObjetoActual = new Rectangle((int)enemigoActual.obtenerPosicionX(),
                        (int)enemigoActual.obtenerPosicionY(), Constantes.ANCHO_SPRITE,
                        Constantes.ALTO_SPRITE);
                
                if (areaJugador.intersects(posicionObjetoActual)){
                	Combate combate=new Combate(ElementosPrincipales.jugador.p,enemigoActual);
                	combate.definirTurnos();
                	combate.setVisible(true);
                	GestorControles.teclado.liberarTeclas();
                	while (combate.isCombatiendo()) {
                		System.out.println(""); //si sacan este syso se rompe todo!!! no es joda//		
					}
                	GestorControles.teclado.liberarTeclas();
                	if (combate.getHuir() == true || (!ElementosPrincipales.jugador.p.estaVivo())) {
                		ElementosPrincipales.jugador.p.perderItemMasValioso(ElementosPrincipales.jugador.p);
                		ElementosPrincipales.jugador.p.serCuradoTotalmente();
                		combate.setVisible(false);
                    	combate.dispose();
                		if (ElementosPrincipales.jugador.p.getCasta().getNombre() == "Hechicero") {
                			ElementosPrincipales.jugador.p.getCasta().getHechicero().restaurarMagia();
						}
                		ElementosPrincipales.jugador.establecerPosicionY(puntoInicial.y);
                		ElementosPrincipales.jugador.establecerPosicionX(puntoInicial.x);
					}
                	else
                	{
                		ElementosPrincipales.jugador.p=RegistroDeAlmas.asignarAlma(enemigoActual.devolverItem(), ElementosPrincipales.jugador.p);
                		combate.setVisible(false);
                    	combate.dispose();
                    	int nivelAnt=ElementosPrincipales.jugador.p.getNivel();
                		ElementosPrincipales.jugador.p.ganarExperiencia(enemigoActual.devolverExperiencia());
                		enemigosMapa.remove(i);
                		
                		if (nivelAnt < ElementosPrincipales.jugador.p.getNivel()) {
							LevelUp subirNivel= new LevelUp(ElementosPrincipales.jugador.p);
							subirNivel.setVisible(true);
							GestorControles.teclado.liberarTeclas();
							
							while (!subirNivel.puntosDistribuidos()) {
								System.out.println("");
							}
							subirNivel.setVisible(false);
	                    	subirNivel.dispose();
							GestorControles.teclado.liberarTeclas();
						}
                	}
                	
                	ElementosPrincipales.jugador.p.serEnergizadoTotalmente();
                	
                }
                	
            }

		}
	}
	
	public void puntoRestauracion(){
		
		if (ElementosPrincipales.jugador.p.getSalud() < ElementosPrincipales.jugador.p.getSaludTotal() || ElementosPrincipales.jugador.p.getCasta().getMagia() < ElementosPrincipales.jugador.p.getCasta().getMagiaTot()){
			final Rectangle areaJugador = new Rectangle(ElementosPrincipales.jugador.obtenerPosicionXInt(),
	                 ElementosPrincipales.jugador.obtenerPosicionYInt(), Constantes.ANCHO_SPRITE, Constantes.ALTO_SPRITE);
			 
			if (areaJugador.intersects(this.reestauracion)) {
				GestorControles.teclado.liberarTeclas();
				ElementosPrincipales.jugador.p.serCuradoTotalmente();
				JOptionPane.showMessageDialog(null, "Se ha reestaurado la salud y la magia de tu personaje");
				if (ElementosPrincipales.jugador.p.getCasta().getNombre() == "Hechicero") {
					ElementosPrincipales.jugador.p.getCasta().getHechicero().restaurarMagia();
				}
				
			}
		}
		 
	}
	
	public void dibujar(Graphics g) {
		for (int i = 0; i < capasSprites.size(); i++) {
			int[] spritesCapa = capasSprites.get(i).obtenerArraySprites();
			
			for (int y = 0; y < altoMapaEnTiles; y++) {
				for (int x = 0; x < anchoMapaEnTiles; x++) {
					int idSpriteActual = spritesCapa[x + y * anchoMapaEnTiles];
					
					if (idSpriteActual != -1) {
						int puntoX = x * Constantes.ANCHO_SPRITE
								- (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X;
						int puntoY = y * Constantes.ALTO_SPRITE
								- (int) ElementosPrincipales.jugador.obtenerPosicionY()+ Constantes.MARGEN_Y;
						
						if (puntoX < 0 - Constantes.ANCHO_SPRITE ||
							puntoX > Constantes.ANCHO_JUEGO ||
							puntoY < 0 - Constantes.ALTO_SPRITE ||
							puntoY > Constantes.ALTO_JUEGO - 65) { //si alguna de estas condiciones se cumple no dibuja//
							continue;
						}
						
						DibujoDebug.dibujarImagen(g, paletaSprites[idSpriteActual].getImagen(),
								puntoX, puntoY);
					}
				}
			}
		}
		
		for (int i = 0; i < objetosMapa.size(); i++) {
			ObjetoTiled objetoActual = objetosMapa.get(i);
			
			int puntoX = objetoActual.obtenerPosicion().x
					- (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X;
			int puntoY = objetoActual.obtenerPosicion().y
					- (int) ElementosPrincipales.jugador.obtenerPosicionY() + Constantes.MARGEN_Y;
			
			if (puntoX < 0 - Constantes.ANCHO_SPRITE ||
					puntoX > Constantes.ANCHO_JUEGO ||
					puntoY < 0 - Constantes.ALTO_SPRITE ||
					puntoY > Constantes.ALTO_JUEGO - 65) {
					continue;
				}
			
			DibujoDebug.dibujarImagen(g, objetoActual.obtenerObjeto().obtenerSprite().getImagen(),
					puntoX, puntoY);
		}
		
		for (int i = 0; i < enemigosMapa.size(); i++) {
			Enemigo enemigo = enemigosMapa.get(i);
			int puntoX = (int) enemigo.obtenerPosicionX()
					- (int) ElementosPrincipales.jugador.obtenerPosicionX() + Constantes.MARGEN_X;
			int puntoY = (int) enemigo.obtenerPosicionY()
					- (int) ElementosPrincipales.jugador.obtenerPosicionY() + Constantes.MARGEN_Y;
			
			if (puntoX < 0 - Constantes.ANCHO_SPRITE ||
					puntoX > Constantes.ANCHO_JUEGO ||
					puntoY < 0 - Constantes.ALTO_SPRITE ||
					puntoY > Constantes.ALTO_JUEGO - 65) {
					continue;
				}
			
			enemigo.dibujar(g, puntoX, puntoY);
		}
	}
 
	
	private JSONObject obtenerObjetoJSON(final String codigoJSON){
		
		JSONParser lector=new JSONParser();
		JSONObject objetoJSON= null;
		
		try{
			
			Object recuperado= lector.parse(codigoJSON);
			objetoJSON = (JSONObject) recuperado;
		}
		catch(ParseException e){
			JOptionPane.showMessageDialog(null, "Error en la Posicion: + e.getPosition() del archivo JSON","Error en obtener objetoJSON de MapaTiled", JOptionPane. ERROR_MESSAGE);
		}
		
		return objetoJSON;
	}
	
	private JSONArray obtenerArrayJSON(final String codigoJSON){
		
		JSONParser lector=new JSONParser();
		JSONArray arrayJSON= null;
		
		try{
			
			Object recuperado= lector.parse(codigoJSON);
			arrayJSON = (JSONArray) recuperado;
		}
		catch(ParseException e){
			JOptionPane.showMessageDialog(null, "Error en la Posicion: + e.getPosition() del archivo JSON","Error en obtener ArrayJSON de MapaTiled", JOptionPane. ERROR_MESSAGE);
		}
		
		return arrayJSON;
	}
	
	private int obtenerIntDesdeJSON(final JSONObject objetoJSON, final String clave){
		String aux=objetoJSON.get(clave).toString();
		double otroAux=Double.parseDouble(aux);
		
		return (int) otroAux;
	}

	public Point obtenerPosicionInicial() {
		
		return puntoInicial;
	}

	public Rectangle obtenerBordes(final int posicionX,final int posicionY) {
		int x = Constantes.MARGEN_X - posicionX + ElementosPrincipales.jugador.obtenerAncho();
		int y = Constantes.MARGEN_Y - posicionY + ElementosPrincipales.jugador.obtenerAlto();
		
		int ancho = this.anchoMapaEnTiles*Constantes.ANCHO_SPRITE - ElementosPrincipales.jugador.obtenerAncho();
		int alto = this.altoMapaEnTiles*Constantes.ALTO_SPRITE - ElementosPrincipales.jugador.obtenerAlto();
		
		return new Rectangle(x, y, ancho - Constantes.ANCHO_SPRITE/2, alto - Constantes.ANCHO_SPRITE/2);
	}
}

