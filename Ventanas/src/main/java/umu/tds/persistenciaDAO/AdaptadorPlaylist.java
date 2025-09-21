package umu.tds.persistenciaDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.Playlist;
import umu.tds.dominio.RepositorioCanciones;

public class AdaptadorPlaylist implements IAdaptadorPlaylistDAO{
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorPlaylist unicaInstancia = null;
	
	private static final String PLAYLIST = "Playlist";
	private static final String NOMBRE = "nombre";
	private static final String CANCIONES = "canciones";
	

	public static AdaptadorPlaylist getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorPlaylist();
		else
			return unicaInstancia;
	}

	private AdaptadorPlaylist() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	// Dado un string de canciones se devuelve un Set con las canciones obtenidas.
	// Se utiliza porque en la base de datos se guardan Strings.
	private Set<Cancion> stringToCanciones(String canciones) {
		
		HashSet<Cancion> listaCanciones = new HashSet<Cancion>();
		Scanner scanner = new Scanner(canciones);
		scanner.useDelimiter(" ");
		
		// Recorro un string que contiene los identificadores de la canciones separados por un espacio.
		// Obtengo la cancion asociada a dicho identificador.
		while (scanner.hasNextInt()) {
			listaCanciones.add(RepositorioCanciones.INSTANCE.findCancion(scanner.nextInt()));
		}
		return listaCanciones;
	}
	
	private Playlist entidadToPlaylist(Entidad ePlaylist) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(ePlaylist, NOMBRE);
		String canciones = servPersistencia.recuperarPropiedadEntidad(ePlaylist, CANCIONES);
		

		
		Playlist playlist = new Playlist(nombre);		
		
		stringToCanciones(canciones).stream()
								.forEach(c -> playlist.addCancion(c));
		playlist.setId(ePlaylist.getId());
		
		return playlist;
	}
	
	// Dada una lista de canciones se devuelve un String con los identificadores de las canciones concatenados.
	// Se utiliza porque en la base de datos se guardan Strings.
	private String cancionesToString(LinkedList<Cancion> canciones) {
		// Recorro la lista, obtengo el identificador de cada cancion y 
		// los concateno en un string.
		return canciones.stream()
                .map(Cancion::getId)
                .map(Object::toString)
                .collect(Collectors.joining(" "))
                .trim();
		
		
	}
	
	private Entidad playlistToEntidad(Playlist playlist) {
		
		Entidad ePlaylist = new Entidad();
		ePlaylist.setNombre(PLAYLIST);
		ePlaylist.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE, playlist.getNombre()), 
				new Propiedad(CANCIONES, cancionesToString(playlist.getCanciones())
				))));
		
		return ePlaylist;
	}

	@Override
	public void create(Playlist playlist) {
		Entidad ePlaylist = playlistToEntidad(playlist);
		ePlaylist = servPersistencia.registrarEntidad(ePlaylist);
		playlist.setId(ePlaylist.getId());
	}

	@Override
	public boolean delete(Playlist playlist) {
		Entidad ePlaylist = servPersistencia.recuperarEntidad(playlist.getId());
		return servPersistencia.borrarEntidad(ePlaylist);
	}

	@Override
	public void update(Playlist playlist) {
		Entidad ePlaylist = servPersistencia.recuperarEntidad(playlist.getId());
		
		for (Propiedad prop : ePlaylist.getPropiedades()) {
			if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(playlist.getNombre());
			} else if (prop.getNombre().equals(CANCIONES)) {
				prop.setValor(cancionesToString(playlist.getCanciones()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Playlist get(int id) {
		Entidad ePlaylist = servPersistencia.recuperarEntidad(id);
		return entidadToPlaylist(ePlaylist);
	}

	@Override
	public HashSet<Playlist> getAll() {
		return (HashSet<Playlist>) servPersistencia.recuperarEntidades(PLAYLIST).stream()
				.map(Entidad::getId)
				.map(this::get)
				.collect(Collectors.toSet());
	}

}
