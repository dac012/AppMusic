package umu.tds.creadorPDF;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Set;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.Playlist;

public class CreadorPDF {
	
	public CreadorPDF() {
	}
	
	// Método que crea un documento PDF con las playlists y sus respectivas canciones.
	public void playlistsPDF(String path) {
		Set<Playlist> playlists = Controlador.INSTANCE.getPlaylistsUsuario();
		
		FileOutputStream archivo = null;
		Document documento;
		Font fuenteTitulo = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
		Font fuenteTipo = new Font(Font.FontFamily.UNDEFINED, 13, Font.BOLD);
		try {
			archivo = new FileOutputStream(path + "/" + Controlador.INSTANCE.getUsuarioActual().getLogin() + ".pdf");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		documento = new Document();
		try {
			PdfWriter.getInstance(documento, archivo);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		documento.open();
		try {
			for (Playlist p : playlists) {
				documento.add(new Paragraph(p.getNombre(), fuenteTitulo));
				for (Cancion c : p.getCanciones()) {
					documento.add(new Paragraph("  - Nombre: ", fuenteTipo));
					documento.add(new Paragraph("    " + c.getTitulo() + "\n"));
					documento.add(new Paragraph("  - Estilo: ", fuenteTipo));
					documento.add(new Paragraph("    " + c.getEstiloMusical() + "\n"));
					documento.add(new Paragraph("  - Intérprete: ", fuenteTipo));
					documento.add(new Paragraph("    " + c.getInterprete() + "\n\n"));
				}
			}
			documento.add(new Paragraph("\n¡Gracias por usar AppMusic!"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		documento.close();
	}
}
