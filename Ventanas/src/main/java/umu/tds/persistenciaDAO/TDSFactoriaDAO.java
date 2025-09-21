package umu.tds.persistenciaDAO;

public class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {}
	
	@Override
	public IAdaptadorCancionDAO getCancionDAO() {
		return AdaptadorCancion.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorPlaylistDAO getPlaylistDAO() {
		return AdaptadorPlaylist.getUnicaInstancia();
	}
	
	@Override
	public IAdaptadorUsuarioDAO getUsuarioDAO() {
		return AdaptadorUsuario.getUnicaInstancia();
	}

}
