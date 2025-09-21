package umu.tds.dominio;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.stream.Collectors;

import umu.tds.controlador.Controlador;
import umu.tds.persistenciaDAO.DAOException;
import umu.tds.persistenciaDAO.FactoriaDAO;
import umu.tds.persistenciaDAO.IAdaptadorCancionDAO;

public enum RepositorioCanciones {
	INSTANCE;
	
	private FactoriaDAO factoria;
	private HashMap<String, Cancion> cancionesPorTitulo;
	private HashMap<Integer, Cancion> cancionesPorId;
	
	private RepositorioCanciones() {
		cancionesPorTitulo = new HashMap<String,Cancion>();
		cancionesPorId = new HashMap<Integer,Cancion>();
		try {
			factoria = FactoriaDAO.getInstancia();
			
			List<Cancion> listacanciones = factoria.getCancionDAO().getAll();
			for (Cancion cancion : listacanciones) {
				cancionesPorId.put(cancion.getId(), cancion);
				cancionesPorTitulo.put(cancion.getTitulo(), cancion);
			}
		} catch (DAOException eDAO) {
			eDAO.printStackTrace();
		}
	}
	
	public Set<Cancion> findCanciones(){
		return new HashSet<Cancion>(cancionesPorTitulo.values());
	}
	
	public Cancion findCancion(String titulo) {
		return cancionesPorTitulo.get(titulo);
	}
	
	public Cancion findCancion(int id) {
		return cancionesPorId.get(id);
	}
	
	public void addCancion(Cancion cancion) {
		if (findCancion(cancion.getTitulo()) == null) {
			cancionesPorId.put(cancion.getId(), cancion);
			cancionesPorTitulo.put(cancion.getTitulo(), cancion);
		}
	}
	
	public void removeCancion(Cancion cancion) {
		cancionesPorId.remove(cancion.getId());
		cancionesPorTitulo.remove(cancion.getTitulo());
		// Eliminar canción en la base de datos
		IAdaptadorCancionDAO cancionDAO = factoria.getCancionDAO();
		cancionDAO.delete(cancion);
	}
	// Muestra por terminal las canciones que hay en el servidor
	// (Por curiosidad)
	public void printCanciones() {
		for (String c : cancionesPorTitulo.keySet()) {
			System.out.println("Titulo: " + cancionesPorTitulo.get(c).getTitulo() + "\n" + 
					"Estilo: " + cancionesPorTitulo.get(c).getEstiloMusical() + "\n" + 
					"Interprete: " + cancionesPorTitulo.get(c).getInterprete() + "\n" +
					"id: " + cancionesPorTitulo.get(c).getId() + "\n\n");
			
		}
		System.out.println("Cantidad de canciones: " + cancionesPorTitulo.size());
	}
	
	// El parametro fav contiene si se ha pulsado el checkBox de favoritas o no.
	public Set<Cancion> getCancionesConFiltro(String titulo, String interprete, String estilo, boolean fav) {
		HashSet<Cancion> cancionesConFiltro = new HashSet<Cancion>(cancionesPorTitulo.values());

		// Se pasa titulo e interprete a minuscula para que no sea sensitivo.
		// Estilo se comprueba con "NINGUNO" para que si no se especifica nada en dicho campo, no filtre nada respecto al estilo.
		cancionesConFiltro = (HashSet<Cancion>) cancionesConFiltro.stream()
													.filter(c -> c.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
													.filter(c -> c.getInterprete().toLowerCase().contains(interprete.toLowerCase()))
													.filter(c -> c.getEstiloMusical().equals(estilo) || estilo.equals("NINGUNO"))
													.collect(Collectors.toSet());
		
		if (fav) {
			// Como cancion favorita == cancion que este en alguna playlist -> Obtenemos todas las canciones que esten en playlists
			// y comprobamos si estan o no.
			LinkedList<Cancion> cancionesFavoritas = Controlador.INSTANCE.getAllCancionesFromPlaylist();
			cancionesConFiltro = (HashSet<Cancion>) cancionesConFiltro.stream().filter(c -> cancionesFavoritas.contains(c))
										.collect(Collectors.toSet());
		}
		
		return cancionesConFiltro;
	}
	public Vector<String> getEstilos(){
		Set<String> estilos = cancionesPorTitulo.values().stream()
								.map(Cancion::getEstiloMusical)
								.distinct()
								.collect(Collectors.toSet());
		Vector<String> listaEstilos = new Vector<String>(estilos);
		// Se añade a la primera posicion el valor NINGUNO para indicar que existe la posibilidad de no filtrar por estilo.
		// Esto se utilara en el comboBox de estilos en la ventana principal -> ventana de busqueda.
		listaEstilos.add(0, "NINGUNO");
		return listaEstilos;
	}
	
	// Obtener las "max" canciones mas reproducidas en el servidor
	// El numero de canciones mostradas viene determinado por "max" y 
	// puede ser modificador por el administrador de la aplicacion.
	public LinkedList<Cancion> getCancionesMasReproducidas(int max){
		return  cancionesPorTitulo.values().stream()
				.filter(c -> c.getNumReproducciones() != 0)
				.sorted(Comparator.comparingInt(Cancion::getNumReproducciones).reversed())
                .limit(max)
                .collect(Collectors.toCollection(LinkedList::new));
	}
	
}
