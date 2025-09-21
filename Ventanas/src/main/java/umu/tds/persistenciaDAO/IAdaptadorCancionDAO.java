package umu.tds.persistenciaDAO;

import java.util.List;

import umu.tds.dominio.Cancion;

public interface IAdaptadorCancionDAO {
	
	void create(Cancion cancion);
	boolean delete(Cancion cancion);
	void update(Cancion cancion);
	Cancion get(int id);
	List<Cancion> getAll();
	
}
