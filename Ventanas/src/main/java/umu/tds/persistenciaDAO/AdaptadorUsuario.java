package umu.tds.persistenciaDAO;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.descuentos.Descuento;
import umu.tds.descuentos.FactoriaDescuento;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.Playlist;
import umu.tds.dominio.Usuario;

public class AdaptadorUsuario implements IAdaptadorUsuarioDAO{
	
	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String EMAIL = "email";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String PREMIUM = "premium";
	private static final String DESCUENTO = "descuento";
	private static final String PLAYLISTS = "playlists";
	private static final String RECIENTES = "recientes";
	
	private ServicioPersistencia servPersistencia;
	private static AdaptadorUsuario unicaInstancia = null;
	private DateTimeFormatter dateFormat;
	
	public static AdaptadorUsuario getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuario();
		else
			return unicaInstancia;
	}

	private AdaptadorUsuario() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = DateTimeFormatter.ofPattern("dd-MM-y");
	}
	
	// Dado un String con los identificadores de las playlists concatenados
	// Se devuelve un HashSet con las playlists contenidas
	// Se utiliza porque en la base de datos se guardan Strings.
	private HashSet<Playlist> stringToPlaylists(String playlists){
		
		HashSet<Playlist> misPlaylists = new HashSet<Playlist>();
		IAdaptadorPlaylistDAO playlistDAO = AdaptadorPlaylist.getUnicaInstancia();
		
		Scanner scanner = new Scanner(playlists);
		scanner.useDelimiter(" ");
		
		while (scanner.hasNextInt()) {
			misPlaylists.add(playlistDAO.get(scanner.nextInt()));
		}
		
		return misPlaylists;
	}
	
	// Dado una String con los identificadores de las canciones recientes
	// Se devuelve una Lista con las canciones recientes.
	// Se utiliza porque en la base de datos se guardan Strings.
	private LinkedList<Cancion> stringToLinkedList (String lista){
		LinkedList<Cancion> recientes = new LinkedList<>();
		IAdaptadorCancionDAO cancionDAO = AdaptadorCancion.getUnicaInstancia();
		
		Scanner scanner = new Scanner(lista);
		scanner.useDelimiter(" ");
		
		while(scanner.hasNextInt()) {
			recientes.add(cancionDAO.get(scanner.nextInt()));
		}
		return recientes;
	}
	private Usuario entidadToUsuario(Entidad eUsuario) {
		
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		String login = servPersistencia.recuperarPropiedadEntidad(eUsuario, LOGIN);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		String descuento = servPersistencia.recuperarPropiedadEntidad(eUsuario, DESCUENTO);
		String playlists = servPersistencia.recuperarPropiedadEntidad(eUsuario, PLAYLISTS);
		String recientes = servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES);
		LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento,dateFormat);
		
		HashSet<Playlist> misPlaylists = stringToPlaylists(playlists);
		LinkedList<Cancion> misRecientes = stringToLinkedList(recientes);
		
		Descuento desc = FactoriaDescuento.INSTANCE.getDescuento(descuento, Period.between(fechaNacimientoLocalDate, LocalDate.now()).getYears());
		
		Usuario usuario = new Usuario(nombre, 
										apellidos, 
										email, 
										login, 
										password, 
										fechaNacimientoLocalDate, 
										desc, 
										misPlaylists,
										misRecientes);
		
		if (premium.equals("true"))
			usuario.setPremium(true);
		
		usuario.setId(eUsuario.getId());

		return usuario;
	}
	// Dado un Set con las playlists
	// Se devuelve un String con los identificadores de las playlists concatenados.
	// Se utiliza porque en la base de datos se guardan Strings.
	private String playlistsToString(Set<Playlist> playlists) {		
		return playlists.stream()
                .map(Playlist::getId)
                .map(Object::toString)
                .collect(Collectors.joining(" "))
                .trim();
	}
	// Dado una lista con las canciones recientes
	// Se devuelve un String con los identificadores de las canciones concatenados.
	// Se utiliza porque en la base de datos se guardan Strings.
	private String recientesToString(LinkedList<Cancion> recientes) {
		return recientes.stream()
                .map(Cancion::getId)
                .map(Object::toString)
                .collect(Collectors.joining(" "))
                .trim();
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		
		String misPlaylists = playlistsToString(usuario.getMisPlaylists());
		String recientes = recientesToString(usuario.getCancionesRecientes());

		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(NOMBRE, usuario.getNombre()),
				new Propiedad(APELLIDOS, usuario.getApellidos()), new Propiedad(EMAIL, usuario.getEmail()),
				new Propiedad(LOGIN, usuario.getLogin()), new Propiedad(PASSWORD, usuario.getPassword()),
				new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento().format(dateFormat)),
				new Propiedad(PREMIUM, Boolean.toString(usuario.isPremium())),
				new Propiedad(DESCUENTO, usuario.getDescuento().getClass().getName()),
				new Propiedad(PLAYLISTS, misPlaylists),
				new Propiedad(RECIENTES, recientes)
				)));
		return eUsuario;
	}

	@Override
	public void create(Usuario usuario) {
		Entidad eUsuario = usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	@Override
	public boolean delete(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}

	@Override
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getPassword());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(usuario.getEmail());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(usuario.getApellidos());
			} else if (prop.getNombre().equals(LOGIN)) {
				prop.setValor(usuario.getLogin());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(usuario.getFechaNacimiento().format(dateFormat));
			} else if(prop.getNombre().equals(PREMIUM)) {
				prop.setValor(Boolean.toString(usuario.isPremium()));
			} else if(prop.getNombre().equals(DESCUENTO)) {
				prop.setValor(usuario.getDescuento().getClass().getName());
			} else if (prop.getNombre().equals(PLAYLISTS)) {
				prop.setValor(playlistsToString(usuario.getMisPlaylists()));
			} else if (prop.getNombre().equals(RECIENTES)) {
				prop.setValor(recientesToString(usuario.getCancionesRecientes()));
			} 
			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	@Override
	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);
		return entidadToUsuario(eUsuario);
	}

	@Override
	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
		
		return entidades.stream()
				.map(Entidad::getId)
				.map(this::get)
				.collect(Collectors.toList());
	}

}
