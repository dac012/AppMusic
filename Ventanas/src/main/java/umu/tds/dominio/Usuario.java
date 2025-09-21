package umu.tds.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import umu.tds.descuentos.Descuento;

public class Usuario {
	
	
	private String nombre;
	private String apellidos;
	private String email;
	private String login;
	private String password;
	private LocalDate fechaNacimiento;
	private boolean premium;
	private Descuento descuento;
	private Set<Playlist> misPlaylists;
	private LinkedList<Cancion> cancionesRecientes; // Lista en la que se almacenan las últimas canciones escuchadas.
	
	private int id; // Relacionado con la base de datos
	
	public Usuario(String nombre, String apellidos, String email, String login, String password, LocalDate fechaNacimiento, Descuento descuento, Set<Playlist> misPlaylists, LinkedList<Cancion> cancionesRecientes) {
		this.id = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.login = login;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.premium = false;
		this.descuento = descuento;
		this.misPlaylists = misPlaylists;
		this.cancionesRecientes = cancionesRecientes;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return Period.between(fechaNacimiento, LocalDate.now()).getYears();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	

	public Descuento getDescuento() {
		return descuento;
	}

	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}

	public HashSet<Playlist> getMisPlaylists() {
		return new HashSet<>(misPlaylists);
	}
	
	public void addPlaylist(Playlist playlist) {
		misPlaylists.add(playlist);
	}
	
	
	public void removePlaylist(String nombre) {
		misPlaylists.remove(misPlaylists.stream()
										.filter(p -> p.getNombre().equals(nombre))
										.findFirst()
										.orElse(null));
	}
	
	public Playlist getPlaylist(String playlist) {
		return misPlaylists.stream()
                .filter(p -> p.getNombre().equals(playlist))
                .findFirst()
                .orElse(null);
	}
	
	public LinkedList<Cancion> getCancionesRecientes() {
		return new LinkedList<>(cancionesRecientes);
	}
	
	public void setCancionesRecientes(LinkedList<Cancion> recientes) {
		cancionesRecientes = recientes;
	}
	
}
