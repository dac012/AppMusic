package umu.pruebas;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import umu.tds.descuentos.DescuentoNull;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.Playlist;
import umu.tds.dominio.RepositorioUsuarios;
import umu.tds.dominio.Usuario;

public class TestRepositorioUsuarios {
	
	Usuario usuarioEsperado;
	
	@Before
	public void inicializar() {
		usuarioEsperado = new Usuario("David", "Valera", "d.valeralopez1@um.es", "dac", "123", LocalDate.now(), new DescuentoNull(), new HashSet<Playlist>(), new LinkedList<Cancion>());
		RepositorioUsuarios.INSTANCE.addUsuario(usuarioEsperado);
	}
	@Test
	public void testFindUsuarioString() {
		assertEquals("Resultado prueba 1", usuarioEsperado.getNombre(), RepositorioUsuarios.INSTANCE.findUsuario("dac").getNombre());
		assertEquals("Resultado prueba 2", usuarioEsperado.getApellidos(), RepositorioUsuarios.INSTANCE.findUsuario("dac").getApellidos());
	}

}
