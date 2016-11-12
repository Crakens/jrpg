package graficos;

public final class Sprite {
	
	private final int lado; //el tamaño del sprite//no se utiliza el nombre tamaño pra evitar el uso de la ñ y futuras complicaciones//
	private  int x;
	private  int y;
	
	public int [] pixeles; //se define public xq se utilizaran con mucha frecuencia en lapsos de tiempo muy reducido//es mas rapido accder directamente al atributo y no con geter//
	private HojaSprites hoja; //este atributo lo pongo pra no hacer un get de pixeles a cada rato//
	
	//coleccion de Sprites//
	public static final Sprite VACIO= new Sprite(32, 0);
	public static final Sprite LLANURA=new Sprite(32, 0, 0, HojaSprites.hoja1); //al estar definida hoja1 como static puedo llamarlo directamente por su nombre//
	
	//fin de la coleccion//
	
	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja){
		
		this.lado=lado; //el tamaño de cada sprite se asume que es un cuadrado//
		
		pixeles=new int[lado*lado]; //inicio un array con tamaño de lado*lado//
		
		this.x= columna*lado; //la columna a la que pertenece el sprite en la hoja//
		this.y= fila*lado;	//la fila a la que pertenece el sprite en la hoja//
		this.hoja=hoja; //hoja sera igual a la hoja de donde quiero sacar el sprite//
		
		
		for(int i=0; i< lado ; i++){
			
			for(int j=0; j< lado ; j++){
				
				pixeles[j+(i*this.lado)] = this.hoja.pixeles[(j+this.x) + (i+this.y)*this.hoja.getAncho()]; //con esta operacion tomo todo el cuadrado del sprite correspondiente//es un sustituto a el array bidimensional que seria mas ineficiente//
			}
		}
	}
	
	public Sprite(final int lado, final int color){
		
		this.lado=lado;
		pixeles =new int[lado*lado];
		
		for(int i=0; i< pixeles.length; i++){
			
			pixeles[i] = color;
		}
	}
	
	public int getLado() {
		return lado;
	}
}
