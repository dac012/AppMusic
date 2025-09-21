package umu.tds.dominio;

public class Cancion {
	
	private String titulo;
	private String interprete;
	private String estilo;
	private String rutaFichero;
	private int numReproducciones;
	private int id; // Relacionado con la base de datos
	
	public Cancion(String titulo, String interprete, String rutaFichero, String estilo) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
		numReproducciones = 0;
	}
	
	public Cancion(String titulo, String interprete, String rutaFichero, String estilo, int numReproducciones) {
		this.titulo = titulo;
		this.interprete = interprete;
		this.estilo = estilo;
		this.rutaFichero = rutaFichero;
		this.numReproducciones = numReproducciones;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getInterprete() {
		return interprete;
	}
	
	public String getEstiloMusical() {
		return estilo;
	}
	
	public String getRutaFichero() {
		return rutaFichero;
	}
	
	public int getNumReproducciones() {
		return numReproducciones;
	}
	
	public void addReproduccion() {
		numReproducciones++;
	}
	
}
