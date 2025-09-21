package umu.tds.persistenciaDAO;

import java.util.List;
import umu.tds.dominio.Usuario;

public interface IAdaptadorUsuarioDAO {
	
	void create(Usuario usuario);
	boolean delete(Usuario usuario);
	void update(Usuario usuario);
	Usuario get(int id);
	List<Usuario> getAll();
}
