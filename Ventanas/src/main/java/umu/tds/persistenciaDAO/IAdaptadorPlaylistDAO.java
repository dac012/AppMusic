package umu.tds.persistenciaDAO;

import java.util.HashSet;

import umu.tds.dominio.Playlist;

public interface IAdaptadorPlaylistDAO {
	
	void create(Playlist playlist);
	boolean delete(Playlist playlist);
	void update(Playlist playlist);
	Playlist get(int id);
	HashSet<Playlist> getAll();
	
}
