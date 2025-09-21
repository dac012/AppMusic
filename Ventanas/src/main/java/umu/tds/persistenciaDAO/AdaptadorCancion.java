package umu.tds.persistenciaDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.dominio.Cancion;

public class AdaptadorCancion implements IAdaptadorCancionDAO{
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorCancion unicaInstancia = null;
	
	private static final String CANCION = "cancion";
	private static final String TITULO = "titulo";
	private static final String INTERPRETE = "interprete";
	private static final String ESTILO = "estilo";
	private static final String RUTA_FICHERO = "rutaFichero"; 
	private static final String NUM_REPRODUCCIONES = "numReproducciones";

	public static AdaptadorCancion getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorCancion();
		else
			return unicaInstancia;
	}
	
	private AdaptadorCancion() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	private Cancion entidadToCancion(Entidad eCancion) {
		String titulo = servPersistencia.recuperarPropiedadEntidad(eCancion, TITULO);
		String interprete = servPersistencia.recuperarPropiedadEntidad(eCancion, INTERPRETE);
		String estilo = servPersistencia.recuperarPropiedadEntidad(eCancion, ESTILO);
		String rutaFichero = servPersistencia.recuperarPropiedadEntidad(eCancion, RUTA_FICHERO);
		String numReproducciones = servPersistencia.recuperarPropiedadEntidad(eCancion, NUM_REPRODUCCIONES);
		
		Cancion cancion = new Cancion(titulo, interprete, rutaFichero, estilo, Integer.valueOf(numReproducciones));
		cancion.setId(eCancion.getId());
		
		return cancion;
	}
	
	private Entidad cancionToEntidad(Cancion cancion) {
		Entidad eCancion = new Entidad();
		eCancion.setNombre(CANCION);
		
		eCancion.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad(TITULO, cancion.getTitulo()),
				new Propiedad(INTERPRETE, cancion.getInterprete()), new Propiedad(ESTILO, cancion.getEstiloMusical()),
				new Propiedad(RUTA_FICHERO, cancion.getRutaFichero()),
				new Propiedad(NUM_REPRODUCCIONES, String.valueOf(cancion.getNumReproducciones()))
				)));
		return eCancion;
	}
	
	@Override
	public void create(Cancion cancion) {
		Entidad eCancion = cancionToEntidad(cancion);
		eCancion = servPersistencia.registrarEntidad(eCancion);
		cancion.setId(eCancion.getId());
	}
	
	@Override
	public boolean delete(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		return servPersistencia.borrarEntidad(eCancion);
	}

	@Override
	public void update(Cancion cancion) {
		Entidad eCancion = servPersistencia.recuperarEntidad(cancion.getId());
		
		for (Propiedad prop : eCancion.getPropiedades()) {
			if(prop.getNombre().equals(TITULO)) {
				prop.setValor(cancion.getTitulo());
			} else if(prop.getNombre().equals(INTERPRETE)) {
				prop.setValor(cancion.getInterprete());
			} else if(prop.getNombre().equals(ESTILO)) {
				prop.setValor(cancion.getEstiloMusical());
			} else if(prop.getNombre().equals(RUTA_FICHERO)) {
				prop.setValor(cancion.getRutaFichero());
			} else if(prop.getNombre().equals(NUM_REPRODUCCIONES)) {
				prop.setValor(String.valueOf(cancion.getNumReproducciones()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	@Override
	public Cancion get(int id) {
		Entidad eCancion = servPersistencia.recuperarEntidad(id);
		return entidadToCancion(eCancion);
	}

	@Override
	public List<Cancion> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(CANCION);
		
		List<Cancion> canciones = new LinkedList<Cancion>();
		
		for(Entidad eCancion : entidades) {
			canciones.add(get(eCancion.getId()));
		}
		return canciones;
	}
}
