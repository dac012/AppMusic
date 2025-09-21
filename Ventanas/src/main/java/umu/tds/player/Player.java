package umu.tds.player;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;

public class Player {
	// canciones almacenadas en src/main/resources
	private umu.tds.dominio.Cancion cancionActual = null;
	private MediaPlayer mediaPlayer;
	
	public Player(){
		//existen otras formas de lanzar JavaFX desde Swing
		try {
			com.sun.javafx.application.PlatformImpl.startup(()->{});			
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}
	public void play(String boton, Cancion cancion){
		switch (boton) { 
		case "play":
			// Si aun no hay ninguna cancion -> se reproduce sin problemas
			if (mediaPlayer == null){
				try {
					setCancionActual(cancion);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Controlador.INSTANCE.addReproduccionCancion(cancion);
			// Si la cancion esta sonando o esta pausada y se va a ejecutar otra cancion distinta -> 
			// paramos mediaPlayer (para que no suenen varias canciones a la vez)
			// y ejecutamos la nueva cancion.
			} else if ((mediaPlayer.getStatus() == Status.PLAYING) || (mediaPlayer.getStatus() == Status.PAUSED && cancion != cancionActual)) {
				try {
					mediaPlayer.stop();
					setCancionActual(cancion);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Controlador.INSTANCE.addReproduccionCancion(cancion);
			
			// Si la cancion esta parada (que no pausada) y ejecutamos otra cancion -> se establece la nueva cancion
			} else if (mediaPlayer.getStatus() == Status.STOPPED && cancion != cancionActual) {
				try {
					setCancionActual(cancion);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Controlador.INSTANCE.addReproduccionCancion(cancion);
			
			// Si la cancion esta parada y ejecutamos la misma cancion -> solo añadimos 1 reproduccion.
			} else if(mediaPlayer.getStatus() == Status.STOPPED) {
				Controlador.INSTANCE.addReproduccionCancion(cancion);
			}
			// Comportamiento por defecto de cada cancion al acabar
			// En nuestro caso, se repite la cancion infinitamente.
			mediaPlayer.setOnEndOfMedia(() -> {
				this.play("play", cancion);
			});
			
			// Escuchar la cancion
			mediaPlayer.play();
			break;
		case "stop": 
			if(cancionActual != null) {
				mediaPlayer.stop();
			}
			break;
		case "pause": 
			if(cancionActual != null) {
				mediaPlayer.pause();
			}
			break;
		}
	}
	private void setCancionActual(Cancion cancion) throws FileNotFoundException {
		if (cancionActual != cancion){
			cancionActual = cancion;
			String rutaCancion = cancion.getRutaFichero();
		    System.out.println(rutaCancion);
			URL resourceURL = null;
			try {
				resourceURL = new URL(rutaCancion);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			if (resourceURL == null) {
			    throw new FileNotFoundException("Resource not found: " + rutaCancion);
			}

			Media hit = new Media(resourceURL.toExternalForm());
			mediaPlayer = new MediaPlayer(hit);
		    
		}
		
		
	}

}