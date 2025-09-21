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
import umu.tds.dominio.Usuario;

public class TestUsuario {
	
	Usuario u;
	
	@Before
	public void inicializa() {
		u = new Usuario("David", "Valera", "d.valeralopez1@um.es", "dac", "123", LocalDate.now(), new DescuentoNull(), new HashSet<Playlist>(), new LinkedList<Cancion>());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Resultado prueba 1", "David", u.getNombre());
	}

	@Test
	public void testIsPremium() {
		assertEquals("Resultado prueba 2", false, u.isPremium());
	}

}
