package umu.tds.dominio;

import java.util.LinkedList;

public class Playlist {
	
	private String nombre;
	private LinkedList<Cancion> canciones;
	
	private int id; // Relacionado con la base de datos
	
	public Playlist(String nombre) {
		this.nombre = nombre;
		canciones = new LinkedList<Cancion>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public LinkedList<Cancion> getCanciones(){
		return canciones;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean addCancion(Cancion c) {
		if (!canciones.contains(c)) { 	// Para que no pueda estar la misma canción varias veces en una playlist
										// comprobamos si está antes de añadirla.
			canciones.add(c);
			return true;
		}
		return false;
		
	}
	
	public void removeCancion(Cancion c) {
		canciones.remove(c);
	}
	
}
