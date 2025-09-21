package umu.tds.controlador;


import java.io.File;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

import tds.CargadorCanciones.CancionEvent;
import tds.CargadorCanciones.CancionListener;
import tds.CargadorCanciones.Canciones;
import tds.CargadorCanciones.CargadorCanciones;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.Playlist;
import umu.tds.dominio.RepositorioCanciones;
import umu.tds.dominio.RepositorioUsuarios;
import umu.tds.dominio.Usuario;
import umu.tds.persistenciaDAO.DAOException;
import umu.tds.persistenciaDAO.FactoriaDAO;
import umu.tds.persistenciaDAO.IAdaptadorCancionDAO;
import umu.tds.persistenciaDAO.IAdaptadorPlaylistDAO;
import umu.tds.persistenciaDAO.IAdaptadorUsuarioDAO;
import umu.tds.player.Player;
import umu.tds.creadorPDF.CreadorPDF;
import umu.tds.descuentos.*;

// Patron SINGLETON
public enum Controlador implements CancionListener {
	INSTANCE;
	
	private static final int MAX_CANCIONES_REPRODUCIDAS = 10; 	// Numero maximo de canciones que van a mostrarse en mas reproducidas
	private static final int MAX_CANCIONES_RECIENTES = 10; 		// Numero maximo de canciones que van a mostrarse en recientes
	private Usuario usuarioActual;
	private double precioPremium;
	private Player player;
	private CreadorPDF cPDF;
	
	private IAdaptadorUsuarioDAO usuarioDAO;
	private IAdaptadorCancionDAO cancionDAO;
	private IAdaptadorPlaylistDAO playlistDAO;
	
	public static final String PLAY = "play";
	public static final String STOP = "stop";
	public static final String PAUSE = "pause";
	
	private Controlador() {
		
		usuarioActual = null;
		Random rand = new Random();
		precioPremium = rand.nextDouble() * 10 + 10;
		inicializarAdaptadores();
		CargadorCanciones.INSTANCE.addCargadorListener(this);
		player = new Player();
		cPDF = new CreadorPDF();
	}
	
	// Cargar las canciones a partir de un archivo xml
	public void cargarCanciones(File archivo) throws URISyntaxException {
		CargadorCanciones.INSTANCE.setArchivoCanciones(archivo.toString());
	}
	
	// Añadir las nuevas canciones a la aplicación
	// Si una canción ya estaba añadida, no se vuelve a añadir
	public void anadirNuevasCanciones(CancionEvent e) {
		Canciones canciones = e.getNuevasCanciones();
		for (tds.CargadorCanciones.Cancion cancion : canciones.getCancion()) {
			String titulo = cancion.getTitulo();
			String interprete = cancion.getInterprete().toUpperCase();
			String estilo = cancion.getEstilo().toUpperCase();
			String rutaFichero = cancion.getURL();
			umu.tds.dominio.Cancion c = new Cancion(titulo, interprete, rutaFichero, estilo);
			RepositorioCanciones.INSTANCE.addCancion(c);
			cancionDAO.create(c);
		}
	}
	// Inicializo los adaptadores para poder actualizar el servicio de persistencia
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		usuarioDAO = factoria.getUsuarioDAO();
		cancionDAO = factoria.getCancionDAO();
		playlistDAO = factoria.getPlaylistDAO();
		
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
	
	public boolean esUsuarioRegistrado(String login) {
		return RepositorioUsuarios.INSTANCE.findUsuario(login) != null;
	}
	
	public boolean loginUsuario(String nombre, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(nombre);
		if(usuario != null && usuario.getPassword().equals(password)) {
			this.usuarioActual = usuario;
			System.out.println(usuarioActual.toString());
			return true;
		}
		return false;
	}
	
	public boolean registrarUsuario(String nombre, String apellidos, String email, String login, String password, Date fechaNacimiento) {
		
		if (esUsuarioRegistrado(login))
			return false;
		
		Usuario usuario = new Usuario(nombre, 
										apellidos, 
										email, 
										login, 
										password, 
										fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
										FactoriaDescuento.INSTANCE.getDescuentoNulo(), 
										new HashSet<Playlist>(),
										new LinkedList<Cancion>()
										);
		
		RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		
		return true;
	}
	
	public void logout() {
		player.play(STOP, null); // Si hay una canción sonando, se para. Así no suena cuando ya no estamos dentro de nuestra cuenta.
		usuarioActual = null;
	}
	
	public boolean esUsuarioPremium() {
		return usuarioActual.isPremium();
	}
	
	public void setPremiumUser(Descuento descuento) {
		usuarioActual.setPremium(true);
		usuarioActual.setDescuento(descuento);
		usuarioDAO.update(usuarioActual);
	}
	
	public void cancelarPremium() {
		usuarioActual.setPremium(false);
		Descuento descuento = new DescuentoNull(); // Se le establece el descuento nulo. Equivalente a no tener descuento.
		usuarioActual.setDescuento(descuento);
		usuarioDAO.update(usuarioActual);
	}
	
	public Descuento getDescuento(String tipo) {
		return FactoriaDescuento.INSTANCE.getDescuento(tipo, usuarioActual.getEdad());
	}
	
	public double getPrecioPremiumSinDescuento() {
		return precioPremium;
	}
	
	public double getPrecioPremium(Descuento descuento) {
		return descuento.calcDescuento(precioPremium);
	}
	
	public Vector<String> getEstilosMusicales(){
		return RepositorioCanciones.INSTANCE.getEstilos();
	}
	
	public HashSet<Cancion> buscarCanciones(String titulo, String interprete, String estilo, boolean fav) {
		return (HashSet<Cancion>) RepositorioCanciones.INSTANCE.getCancionesConFiltro(titulo, interprete, estilo, fav);
	}
	
	public void reproducirCancion(String titulo) {
		Cancion c = RepositorioCanciones.INSTANCE.findCancion(titulo);
		player.play(PLAY, c);
	}
	
	public void pausarCancion() {
		player.play(PAUSE, null);
	}
	
	public Playlist getPlaylist(String nombre) {
		return usuarioActual.getPlaylist(nombre);
	}
	
	public void crearPlaylistVacia(String nombre) {
		Playlist playlist = new Playlist(nombre);
		playlistDAO.create(playlist);
		usuarioActual.addPlaylist(playlist);
		usuarioDAO.update(usuarioActual);
	}
	
	public void eliminarPlaylist(Playlist playlist) {
		usuarioActual.removePlaylist(playlist.getNombre());
		usuarioDAO.update(usuarioActual);
		playlistDAO.delete(playlist);
	}
	
	public HashSet<Playlist> getPlaylistsUsuario(){
		return usuarioActual.getMisPlaylists();
	}
	
	public void crearPDF(String path) {
		cPDF.playlistsPDF(path);
	}
	
	public LinkedList<Cancion> getCancionesFromPlaylist(String playlist){
		return usuarioActual.getPlaylist(playlist).getCanciones();
	}
	
	public LinkedList<Cancion> getAllCancionesFromPlaylist(){
		return usuarioActual.getMisPlaylists().stream()
												.flatMap(p -> p.getCanciones().stream())
												.collect(Collectors.toCollection(LinkedList::new));
	}
	
	public int getNumPlaylistsUsuario() {
		return this.getPlaylistsUsuario().size();
	}
	
	public Vector<String> getNombrePlaylistsUsuario(){
		return new Vector<String>(getPlaylistsUsuario().stream()
															.map(Playlist::getNombre)
															.collect(Collectors.toCollection(LinkedList::new)));
	}
	
	public void addCancionToPlaylist(String cancion, String playlist) {
		Playlist p = usuarioActual.getPlaylist(playlist);
		Cancion c = RepositorioCanciones.INSTANCE.findCancion(cancion);
		if(p.addCancion(c))
			playlistDAO.update(p);
	}
	
	public void eliminarCancionFromPlaylist(String playlist, String cancion) {
		Playlist p = usuarioActual.getPlaylist(playlist);
		Cancion c = RepositorioCanciones.INSTANCE.findCancion(cancion);
		p.removeCancion(c);
		playlistDAO.update(p);
	}
	
	public void addReproduccionCancion(Cancion cancion) {
		cancion.addReproduccion();
		System.out.println(cancion.getNumReproducciones());
		cancionDAO.update(cancion);
		
		// Recientes
		
		addCancionReciente(cancion);
		usuarioDAO.update(usuarioActual);
	}
	
	private void addCancionReciente(Cancion c) {
		LinkedList<Cancion> canciones = usuarioActual.getCancionesRecientes();
		if (canciones.contains(c)) { 	// Si la lista contiene la canción, estamos seguros de que no superará el tamaño máximo.
										// Además, lo único que hay que hacer es eliminarla de la lista y volver a añadirla
										// para colocarla como la última canción escuchada
			canciones.remove(c);
			canciones.add(c);
		} else if(canciones.size() < MAX_CANCIONES_RECIENTES) {	// Si aun no hemos llegado al límite, no hay problema en añadir la canción.
			canciones.add(c);
		} else {	// Si la lista está llena, se elimina la más antigua (primera en la lista) y se añade la canción por el final.
			canciones.removeFirst();
			canciones.add(c);
		}
		usuarioActual.setCancionesRecientes(canciones);
	}
	
	public LinkedList<Cancion> getCancionesRecientes() {
		return usuarioActual.getCancionesRecientes();
	}
	
	public LinkedList<Cancion> getCancionesMasReproducidas(){
		return RepositorioCanciones.INSTANCE.getCancionesMasReproducidas(MAX_CANCIONES_REPRODUCIDAS);
	}
}



