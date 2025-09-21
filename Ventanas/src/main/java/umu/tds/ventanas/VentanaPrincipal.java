package umu.tds.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;

import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;

import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import umu.tds.controlador.Controlador;
import umu.tds.dominio.Cancion;
import umu.tds.dominio.Playlist;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.util.EventObject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Vector;

import pulsador.IEncendidoListener;
import pulsador.Luz;


import javax.swing.UIManager;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.JList;

public class VentanaPrincipal extends JFrame{
	

	private JPanel contentPane;
	private JTextField txtInterprete;
	private JTextField txtTitulo;
	private JFrame frameVentanaPrincipal;
	private JPanel cardLayout;
	private JPanel Botonera;
	private JToggleButton iconoBuscar;
	private JToggleButton iconoGestionPlaylists;
	private JToggleButton iconoRecientes;
	private JToggleButton iconoMisPlaylists;
	private JPanel Principal;
	private JButton btnLogout;
	private JButton btnPremium;
	private JPanel panelBuscar;
	private JLabel lblUsuarioActual;
	private JPanel panelInteriorInferiorBuscar;
	private JPanel panelReproduccion;
	private JButton btnAnadirLista;
	private JTable tablaBuscar;
	
	private static final String XML_EXTENSION = ".xml";
	private static final String TITULO = "Titulo";
	private static final String INTERPRETE = "Interprete";
	private static final String  ESTILO = "Estilo";
	private static final String NUM_REPRODUCCIONES = "Reproducciones";
	private JPanel panelNorteGestionPlaylist;
	private JTextField textFieldTituloG;
	private JButton btnCrearPlaylist;
	private JButton btnEliminarPlaylist;
	private Luz luz;
	private JPanel panelReproduccionGP;
	private JTable tableGestionPlaylist;
	private JButton btnEliminarLista;
	private JPanel panelBotonesCancionesR;

	private JTable tableRecientes;
	private JButton btnMasReproducidas;
	private JButton btnGenerarPDF;
	private JPanel panelMisListas;
	private JList<String> misListas;
	private String estiloABuscar = "NINGUNO";
	private DefaultTableModel modeloTabla;
	private String[] columnas = {TITULO, INTERPRETE, ESTILO};
	private DefaultListModel<String> modeloMisListas;
	private Vector<String> estilosMusicales;
	private JComboBox<String> comboBoxEstilos;
	private JButton btnSkipBuscar;
	private JButton btnPlayBuscar;
	private JButton btnPauseBuscar;
	private JButton btnBackBuscar;
	private JCheckBox checkBoxFav;
	private JButton btnEliminarCancionGP;
	DefaultTableModel modeloTablaGP;
	JComboBox<String> comboPlaylists;
	private String playlistAbiertaGP;
	private JPanel panelReproduccionMP;
	private JTable tableMisPlaylists;
	private JButton btnBackMP;
	private JButton btnPauseMP;
	private JButton btnPlayMP;
	private JButton btnSkipMP;
	private DefaultTableModel modeloTablaR;
	private JPanel panelMasReproducidas;
	private JPanel panelReproduccionMasReproducidas;
	private JScrollPane scrollMasReproducidas;
	private DefaultTableModel modeloTablaMasReproducidas;
	private JTable tablaMasReproducidas;
	private JButton btnPauseMasReproducidas;
	private JButton btnPlayMasReproducidas;
	private JButton btnPauseRecientes;
	private JButton btnPlayRecientes;

	

	public VentanaPrincipal() {
		frameVentanaPrincipal = new JFrame();
		initialize();
	}
	
	public void mostrarVentana() {
		frameVentanaPrincipal.setLocationRelativeTo(null);
		frameVentanaPrincipal.setVisible(true);
	}
	public void initialize() {
		frameVentanaPrincipal.setTitle("AppMusic");
		frameVentanaPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/umu/tds/images/IconoApp.png")));
		frameVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVentanaPrincipal.setBounds(100, 100, 553, 447);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frameVentanaPrincipal.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		crearPanelBotonera();
		crearPanelPrincipal();
		
		
		JPanel panelGestionPlaylists = new JPanel();
		cardLayout.add(panelGestionPlaylists, "panelGestionPlaylists");
		panelGestionPlaylists.setLayout(new BorderLayout(0, 0));
		
		panelNorteGestionPlaylist = new JPanel();
		panelGestionPlaylists.add(panelNorteGestionPlaylist, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorteGestionPlaylist = new GridBagLayout();
		gbl_panelNorteGestionPlaylist.columnWidths = new int[]{20, 0, 0, 0};
		gbl_panelNorteGestionPlaylist.rowHeights = new int[]{20, 0, 0, 20, 0};
		gbl_panelNorteGestionPlaylist.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNorteGestionPlaylist.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelNorteGestionPlaylist.setLayout(gbl_panelNorteGestionPlaylist);
		
		textFieldTituloG = new JTextField("Titulo");
		GridBagConstraints gbc_textFieldTituloG = new GridBagConstraints();
		gbc_textFieldTituloG.gridwidth = 2;
		gbc_textFieldTituloG.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldTituloG.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTituloG.gridx = 1;
		gbc_textFieldTituloG.gridy = 1;
		panelNorteGestionPlaylist.add(textFieldTituloG, gbc_textFieldTituloG);
		textFieldTituloG.setColumns(10);
		
		addManejadorTituloG();
		
		btnCrearPlaylist = new JButton("Crear");
		
		addManejadorBotonCrearPlaylist();
		
		GridBagConstraints gbc_btnCrearPlaylist = new GridBagConstraints();
		gbc_btnCrearPlaylist.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrearPlaylist.gridx = 1;
		gbc_btnCrearPlaylist.gridy = 2;
		panelNorteGestionPlaylist.add(btnCrearPlaylist, gbc_btnCrearPlaylist);
		
		btnEliminarPlaylist = new JButton("Eliminar");
		
		addManejadorBotonEliminarPlaylist();
		
		GridBagConstraints gbc_btnEliminarPlaylist = new GridBagConstraints();
		gbc_btnEliminarPlaylist.insets = new Insets(0, 0, 5, 0);
		gbc_btnEliminarPlaylist.gridx = 2;
		gbc_btnEliminarPlaylist.gridy = 2;
		panelNorteGestionPlaylist.add(btnEliminarPlaylist, gbc_btnEliminarPlaylist);
		
		panelReproduccionGP = new JPanel();
		panelGestionPlaylists.add(panelReproduccionGP, BorderLayout.SOUTH);
		
		btnEliminarCancionGP = new JButton("Eliminar Cancion");
		panelReproduccionGP.add(btnEliminarCancionGP);
		
		btnEliminarLista = new JButton("Eliminar Lista");
		
		addManejadorEliminarCancion();
		
		modeloTablaGP = new DefaultTableModel(columnas, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                // Devolver false para que las celdas no sean editables
                return false;
            }
		};
		tableGestionPlaylist = new JTable(modeloTablaGP);
		tableGestionPlaylist.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JScrollPane scrollGestionPlaylist = new JScrollPane(tableGestionPlaylist);
		panelGestionPlaylists.add(scrollGestionPlaylist, BorderLayout.CENTER);
		
		
		JPanel panelRecientes = new JPanel();
		cardLayout.add(panelRecientes, "panelRecientes");
		panelRecientes.setLayout(new BorderLayout(0, 0));
		
		panelBotonesCancionesR = new JPanel();
		panelRecientes.add(panelBotonesCancionesR, BorderLayout.SOUTH);
		
		btnPauseRecientes = new JButton("");
		btnPauseRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/barras-verticales.png")));
		panelBotonesCancionesR.add(btnPauseRecientes);
		
		btnPlayRecientes = new JButton("");
		btnPlayRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/punta-de-flecha-del-boton-de-reproduccion.png")));
		panelBotonesCancionesR.add(btnPlayRecientes);
		
		
		
		modeloTablaR = new DefaultTableModel(columnas, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                // Devolver false para que las celdas no sean editables
                return false;
            }
		};
		tableRecientes = new JTable(modeloTablaR);
		tableRecientes.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JScrollPane scrollRecientes = new JScrollPane(tableRecientes);
		panelRecientes.add(scrollRecientes, BorderLayout.CENTER);
		
		addManejadorTablaRecientes(tableRecientes);
		crearManejadorBotonPlayCancionRecientes(tableRecientes, btnPlayRecientes);
		crearManejadorBotonPauseCancion(tableRecientes, btnPlayRecientes);
		
		JPanel panelMisPlaylists = new JPanel();
		cardLayout.add(panelMisPlaylists, "panelMisPlaylists");
		panelMisPlaylists.setLayout(new BorderLayout(0, 0));
		
		panelReproduccionMP = new JPanel();
		panelMisPlaylists.add(panelReproduccionMP, BorderLayout.SOUTH);
		
		btnBackMP = new JButton("");
		btnBackMP.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/musica.png")));
		panelReproduccionMP.add(btnBackMP);
		
		btnPauseMP = new JButton("");
		btnPauseMP.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/barras-verticales.png")));
		panelReproduccionMP.add(btnPauseMP);
		
		btnPlayMP = new JButton("");
		btnPlayMP.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/punta-de-flecha-del-boton-de-reproduccion.png")));
		panelReproduccionMP.add(btnPlayMP);
		
		btnSkipMP = new JButton("");
		btnSkipMP.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/proximo.png")));
		panelReproduccionMP.add(btnSkipMP);
		
		
		
		tableMisPlaylists = new JTable(modeloTablaGP);
		tableMisPlaylists.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JScrollPane scrollMisPlaylists = new JScrollPane(tableMisPlaylists);
		panelMisPlaylists.add(scrollMisPlaylists, BorderLayout.CENTER);
		
		crearManejadorBotonPlayCancion(tableMisPlaylists, btnPlayMP);
		crearManejadorBotonPauseCancion(tableMisPlaylists, btnPauseMP);
		crearManejadorBotonSkipCancion(tableMisPlaylists, btnSkipMP);
		crearManejadorBotonBackCancion(tableMisPlaylists, btnBackMP);
		
		addManejadorTabla(tableMisPlaylists);
		
		panelMasReproducidas = new JPanel();
		cardLayout.add(panelMasReproducidas, "panelMasReproducidas");
		panelMasReproducidas.setLayout(new BorderLayout(0, 0));
		
		panelReproduccionMasReproducidas = new JPanel();
		panelMasReproducidas.add(panelReproduccionMasReproducidas, BorderLayout.SOUTH);
		
		btnPauseMasReproducidas = new JButton("");
		btnPauseMasReproducidas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/barras-verticales.png")));
		panelReproduccionMasReproducidas.add(btnPauseMasReproducidas);
		
		btnPlayMasReproducidas = new JButton("");
		btnPlayMasReproducidas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/punta-de-flecha-del-boton-de-reproduccion.png")));
		panelReproduccionMasReproducidas.add(btnPlayMasReproducidas);
		
		
		
		String[] columnasMasReproducidas = {TITULO, INTERPRETE, ESTILO, NUM_REPRODUCCIONES};
		
		modeloTablaMasReproducidas = new DefaultTableModel(columnasMasReproducidas, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                // Devolver false para que las celdas no sean editables
                return false;
            }
		};
		
		tablaMasReproducidas = new JTable(modeloTablaMasReproducidas);
		scrollMasReproducidas = new JScrollPane(tablaMasReproducidas);
		panelMasReproducidas.add(scrollMasReproducidas, BorderLayout.CENTER);
		
		addManejadorTablaMasReproducidas(tablaMasReproducidas);
		crearManejadorBotonPlayCancionMasReproducidas(tablaMasReproducidas, btnPlayMasReproducidas);
		crearManejadorBotonPauseCancion(tablaMasReproducidas, btnPauseMasReproducidas);
		
	}
	
	private void addManejadorBotonCrearPlaylist() {
		btnCrearPlaylist.addActionListener(e -> {
			String tituloLista = textFieldTituloG.getText();
			if (!tituloLista.equals("")) {
				Playlist playlist = Controlador.INSTANCE.getPlaylist(tituloLista);
				if (playlist != null) {
					playlistAbiertaGP = tituloLista;
					modeloTablaGP.setRowCount(0);
					playlist.getCanciones().stream().forEach(c -> {
						Vector<String> fila = new Vector<>();
						fila.add(c.getTitulo());
						fila.add(c.getInterprete());
						fila.add(c.getEstiloMusical());
						modeloTablaGP.addRow(fila);
					});
				} else {
					String mensajeConfirmacion = "¿Deseas crear la playlist: " + tituloLista + "?";
					int respuesta = JOptionPane.showConfirmDialog(null, mensajeConfirmacion, "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						Controlador.INSTANCE.crearPlaylistVacia(tituloLista);
						modeloMisListas.addElement(tituloLista);
						panelMisListas.revalidate();
					}
				}
			}
			else {
				JOptionPane.showConfirmDialog(null, "Indique un titulo.", "", JOptionPane.WARNING_MESSAGE);
			}
			
		});
	}
	
	private void addManejadorBotonEliminarPlaylist() {
		btnEliminarPlaylist.addActionListener(e -> {
			String tituloLista = textFieldTituloG.getText();
			if(!tituloLista.equals("")) {
				Playlist playlist = Controlador.INSTANCE.getPlaylist(tituloLista);
				if(playlist != null) {
					String mensajeConfirmacion = "¿Deseas eliminar la playlist: " + tituloLista + "?";
					int respuesta = JOptionPane.showConfirmDialog(null, mensajeConfirmacion, "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
					if(respuesta == JOptionPane.YES_OPTION) {
						modeloMisListas.removeElement(tituloLista);
						Controlador.INSTANCE.eliminarPlaylist(playlist);
					}
				} else {
					String mensajeError = "La playlist " + tituloLista + " no existe.";
					JOptionPane.showMessageDialog(null, mensajeError);
				}
			} else {
				JOptionPane.showConfirmDialog(null, "Indique un titulo.", "", JOptionPane.WARNING_MESSAGE);
			}
		});
		
	}
	
	private void addManejadorTituloG() {
		textFieldTituloG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				textFieldTituloG.setText("");
			}
			
		});
	}
	
	private void crearPanelBotonera() {
		Botonera = new JPanel();
		Botonera.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		Botonera.setForeground(new Color(0, 0, 0));
		contentPane.add(Botonera, BorderLayout.WEST);
		GridBagLayout gbl_Botonera = new GridBagLayout();
		gbl_Botonera.columnWidths = new int[]{79, 0};
		gbl_Botonera.rowHeights = new int[]{20, 30, 25, 0, 0, 20, 0, 0, 10, 0, 0};
		gbl_Botonera.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_Botonera.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		Botonera.setLayout(gbl_Botonera);
		
		crearBotonesBotonera();
		crearManejadorBotonBuscarPanel();
		crearManejadorBotonGestionPlaylists();
		crearManejadorBotonRecientes();
		crearManejadorBotonMisPlaylists();
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(iconoBuscar);
		buttonGroup.add(iconoGestionPlaylists);
		buttonGroup.add(iconoRecientes);
		buttonGroup.add(iconoMisPlaylists);
		
		
		panelMisListas = new JPanel();
		panelMisListas.setPreferredSize(new Dimension(1, 1));
		panelMisListas.setVisible(false);
		
		panelMisListas.setBorder(new TitledBorder(null, "Mis listas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelMisListas = new GridBagConstraints();
		gbc_panelMisListas.fill = GridBagConstraints.BOTH;
		gbc_panelMisListas.gridx = 0;
		gbc_panelMisListas.gridy = 9;
		Botonera.add(panelMisListas, gbc_panelMisListas);
		
		modeloMisListas = new DefaultListModel<>();
		Controlador.INSTANCE.getPlaylistsUsuario().stream().forEach(p -> {
			modeloMisListas.addElement(p.getNombre());
		});
		panelMisListas.setLayout(new BorderLayout(0, 0));
		misListas = new JList<String>(modeloMisListas);
		JScrollPane scrollMisListas = new JScrollPane(misListas);
		panelMisListas.add(scrollMisListas);
		
		addManejadorMisListas();
	}
	
	private void crearManejadorBotonReproducidas() {
		btnMasReproducidas.addActionListener(e -> {
			panelMisListas.setVisible(false);
			if (Controlador.INSTANCE.esUsuarioPremium()) {
			
				modeloTablaMasReproducidas.setRowCount(0);
				CardLayout card = (CardLayout) cardLayout.getLayout();
				card.show(cardLayout, "panelMasReproducidas");
				LinkedList<Cancion> cancionesMasReproducidas = Controlador.INSTANCE.getCancionesMasReproducidas();

				cancionesMasReproducidas.forEach(c -> {
					Vector<String> fila = new Vector<>();
					fila.add(c.getTitulo());
					fila.add(c.getInterprete());
					fila.add(c.getEstiloMusical());
					fila.add(String.valueOf(c.getNumReproducciones()));
					modeloTablaMasReproducidas.addRow(fila);
				});
				
				
			}
			else {
				JOptionPane.showMessageDialog(null, "Necesitas ser Premium", "Mas Reproducidas", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	private void crearManejadorBotonGenerarPDF() {
		btnGenerarPDF.addActionListener(e -> {
			panelMisListas.setVisible(false);
			if (Controlador.INSTANCE.esUsuarioPremium()) {
				JFileChooser ch = new JFileChooser();
				JFrame frame = new JFrame("Seleccionar directorio");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = ch.showOpenDialog(frame);
            	if(res == JFileChooser.APPROVE_OPTION) {
                    String dSeleccionado = ch.getSelectedFile().getAbsolutePath();
                    Controlador.INSTANCE.crearPDF(dSeleccionado);
    				JOptionPane.showMessageDialog(null, "PDF Creado", "Generar PDF", JOptionPane.INFORMATION_MESSAGE);
                }
			}
			else {
				JOptionPane.showMessageDialog(null, "Necesitas ser Premium", "Generar PDF", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
	
	private void crearBotonesParaPremium() {
		btnMasReproducidas = new JButton("Mas Reproducidas");
		GridBagConstraints gbc_btnMasReproducidas = new GridBagConstraints();
		gbc_btnMasReproducidas.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMasReproducidas.insets = new Insets(0, 0, 5, 0);
		gbc_btnMasReproducidas.gridx = 0;
		gbc_btnMasReproducidas.gridy = 6;
		Botonera.add(btnMasReproducidas, gbc_btnMasReproducidas);
	
		
		btnGenerarPDF = new JButton("Generar PDF");
		GridBagConstraints gbc_btnGenerarPDF = new GridBagConstraints();
		gbc_btnGenerarPDF.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGenerarPDF.insets = new Insets(0, 0, 5, 0);
		gbc_btnGenerarPDF.gridx = 0;
		gbc_btnGenerarPDF.gridy = 7;
		Botonera.add(btnGenerarPDF, gbc_btnGenerarPDF);
		
		crearManejadorBotonReproducidas();
		crearManejadorBotonGenerarPDF();
	}
	
	private void crearBotonesBotonera() {
		
		// CARGADOR DE CANCIONES
		luz = new Luz();
		luz.setBackground(UIManager.getColor("Button.disabledForeground"));
		luz.setColor(UIManager.getColor("Button.disabledForeground"));
		GridBagConstraints gbc_luz = new GridBagConstraints();
		gbc_luz.insets = new Insets(0, 0, 5, 0);
		gbc_luz.gridx = 0;
		gbc_luz.gridy = 0;
		Botonera.add(luz, gbc_luz);
		luz.addEncendidoListener(new IEncendidoListener(){
            @Override
            public void enteradoCambioEncendido(EventObject arg0) {

            	JFileChooser ch = new JFileChooser();
                int res = ch.showOpenDialog(luz);
            	File archivoActual = ch.getSelectedFile();
            	if(res == JFileChooser.APPROVE_OPTION) {
                    try {
                    	if(archivoActual.getName().endsWith(XML_EXTENSION)) {
                    		Controlador.INSTANCE.cargarCanciones(archivoActual);
                    		// Actualizar combo box estilos
                    		estilosMusicales = Controlador.INSTANCE.getEstilosMusicales();
                    		comboBoxEstilos.setModel(new DefaultComboBoxModel<String>(estilosMusicales));
                    	} else {
                    		JOptionPane.showMessageDialog(null, "El archivo seleccionado no es XML", "ERROR", JOptionPane.ERROR_MESSAGE);
                    	}
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
		
		// BUSCAR
		iconoBuscar = new JToggleButton("Buscar");
		iconoBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/lupa.png")));
		GridBagConstraints gbc_iconoBuscar = new GridBagConstraints();
		gbc_iconoBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_iconoBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_iconoBuscar.gridx = 0;
		gbc_iconoBuscar.gridy = 1;
		Botonera.add(iconoBuscar, gbc_iconoBuscar);
		
		// GESTION PLAYLISTS
		iconoGestionPlaylists = new JToggleButton("Gestión Playlists");
		iconoGestionPlaylists.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/lista-de-reproduccion.png")));
		GridBagConstraints gbc_iconoGestionPlaylists = new GridBagConstraints();
		gbc_iconoGestionPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_iconoGestionPlaylists.insets = new Insets(0, 0, 5, 0);
		gbc_iconoGestionPlaylists.gridx = 0;
		gbc_iconoGestionPlaylists.gridy = 2;
		Botonera.add(iconoGestionPlaylists, gbc_iconoGestionPlaylists);
		
		// RECIENTES
		iconoRecientes = new JToggleButton("Recientes");
		iconoRecientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/reciente.png")));
		GridBagConstraints gbc_iconoRecientes = new GridBagConstraints();
		gbc_iconoRecientes.fill = GridBagConstraints.HORIZONTAL;
		gbc_iconoRecientes.insets = new Insets(0, 0, 5, 0);
		gbc_iconoRecientes.gridx = 0;
		gbc_iconoRecientes.gridy = 3;
		Botonera.add(iconoRecientes, gbc_iconoRecientes);
		
		// MIS PLAYLISTS
		iconoMisPlaylists = new JToggleButton("Mis Playlists");
		iconoMisPlaylists.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/album-de-musica.png")));
		GridBagConstraints gbc_iconoMisPlaylists = new GridBagConstraints();
		gbc_iconoMisPlaylists.fill = GridBagConstraints.HORIZONTAL;
		gbc_iconoMisPlaylists.insets = new Insets(0, 0, 5, 0);
		gbc_iconoMisPlaylists.gridx = 0;
		gbc_iconoMisPlaylists.gridy = 4;
		Botonera.add(iconoMisPlaylists, gbc_iconoMisPlaylists);
		
		// BOTONES PREMIUM
		crearBotonesParaPremium();
		
	}
	
	private void crearManejadorBotonBuscarPanel() {
		iconoBuscar.addActionListener(e -> {
			panelMisListas.setVisible(false);
			btnAnadirLista.setVisible(true);
			comboPlaylists.setVisible(false);
			CardLayout card = (CardLayout) cardLayout.getLayout();
			card.show(cardLayout, "panelBuscar");
		});
	}
	
	private void crearManejadorBotonGestionPlaylists() {
		iconoGestionPlaylists.addActionListener(e -> {
			panelMisListas.setVisible(false);
			CardLayout card = (CardLayout) cardLayout.getLayout();
			card.show(cardLayout, "panelGestionPlaylists");
			panelMisListas.setVisible(true);
		});
	}
	
	private void crearManejadorBotonRecientes() {
		iconoRecientes.addActionListener(e -> {
			panelMisListas.setVisible(false);
			CardLayout card = (CardLayout) cardLayout.getLayout();
			card.show(cardLayout, "panelRecientes");
		
			
			modeloTablaR.setRowCount(0);
			LinkedList<Cancion> cancionesRecientes = Controlador.INSTANCE.getCancionesRecientes();
			cancionesRecientes.descendingIterator().forEachRemaining(c -> {
				Vector<String> fila = new Vector<>();
				fila.add(c.getTitulo());
				fila.add(c.getInterprete());
				fila.add(c.getEstiloMusical());
				modeloTablaR.addRow(fila);
			});
			
			
		});
	}
	
	private void crearManejadorBotonMisPlaylists() {
		iconoMisPlaylists.addActionListener(e -> {
			CardLayout card = (CardLayout) cardLayout.getLayout();
			card.show(cardLayout, "panelMisPlaylists");
			panelMisListas.setVisible(true);
		});
	}
	
	private void crearPanelPrincipal() {
		
		crearPanelNorte();
		crearPanelCentral();
		
		
	}
	
	private void crearPanelNorte() {
		Principal = new JPanel();
		contentPane.add(Principal, BorderLayout.CENTER);
		Principal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		FlowLayout fl_panelNorte = (FlowLayout) panelNorte.getLayout();
		fl_panelNorte.setAlignment(FlowLayout.RIGHT);
		Principal.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Bienvenido, ");
		panelNorte.add(lblNewLabel);
		
		lblUsuarioActual = new JLabel(Controlador.INSTANCE.getUsuarioActual().getLogin());
		panelNorte.add(lblUsuarioActual);
		
		
		btnPremium = new JButton("Premium");
		panelNorte.add(btnPremium);
		
		btnLogout = new JButton("Logout");
		crearManejadorBotonPremium();
		crearManejadorBotonLogout();
		
		panelNorte.add(btnLogout);
	}
	
	private void crearManejadorBotonLogout() {
		btnLogout.addActionListener(e -> {
			Controlador.INSTANCE.logout();
			VentanaLogin login = new VentanaLogin();
			login.mostrarVentana();
			frameVentanaPrincipal.setVisible(false);
		});
	}
	
	
	
	private void crearManejadorBotonPremium() {
		btnPremium.addActionListener(e -> {
			if (!Controlador.INSTANCE.esUsuarioPremium()) {
				VentanaDescuento vDescuento = new VentanaDescuento();
				vDescuento.mostrarVentana();
			}
			else {
				int respuesta = JOptionPane.showConfirmDialog(null, "Ya eres PREMIUM. ¿Deseas cancelar la suscripción?", "Premium", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					Controlador.INSTANCE.cancelarPremium();
				}
			}
		});
	}
	
	private void crearPanelCentral() {
		cardLayout = new JPanel();
		Principal.add(cardLayout, BorderLayout.CENTER);
		cardLayout.setLayout(new CardLayout(0, 0));
		
		crearPanelesDelCardLayout();
	}
	
	private void crearPanelesDelCardLayout() {
		
		// BUSCAR
		panelBuscar = new JPanel();
		panelBuscar.setBorder(null);
		cardLayout.add(panelBuscar, "panelBuscar");
		GridBagLayout gbl_panelBuscar = new GridBagLayout();
		gbl_panelBuscar.columnWidths = new int[]{10, 0, 0};
		gbl_panelBuscar.rowHeights = new int[]{20, 0, 150, 0};
		gbl_panelBuscar.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelBuscar.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelBuscar.setLayout(gbl_panelBuscar);
		
		crearSubpanelBuscar();
		
	}
	
	private void crearSubpanelBuscar() {
		
		JPanel panelInteriorSuperiorBuscar = new JPanel();
		panelInteriorSuperiorBuscar.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3), "Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelInteriorSuperiorBuscar = new GridBagConstraints();
		gbc_panelInteriorSuperiorBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_panelInteriorSuperiorBuscar.fill = GridBagConstraints.BOTH;
		gbc_panelInteriorSuperiorBuscar.gridx = 1;
		gbc_panelInteriorSuperiorBuscar.gridy = 1;
		panelBuscar.add(panelInteriorSuperiorBuscar, gbc_panelInteriorSuperiorBuscar);
		
		GridBagLayout gbl_panelInteriorSuperiorBuscar = new GridBagLayout();
		gbl_panelInteriorSuperiorBuscar.columnWidths = new int[]{40, 0, 0, 0, 0, 0};
		gbl_panelInteriorSuperiorBuscar.rowHeights = new int[]{15, 0, 0, 0, 0};
		gbl_panelInteriorSuperiorBuscar.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelInteriorSuperiorBuscar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelInteriorSuperiorBuscar.setLayout(gbl_panelInteriorSuperiorBuscar);
		
		crearIconosEtiquetasSubpanelBuscar(panelInteriorSuperiorBuscar);
		crearEtiquetasSubpanelBuscar(panelInteriorSuperiorBuscar);
		crearManejadorLabelInterprete();
		crearManejadorLabelTitulo();
		crearBotonesSubpanelBuscar(panelInteriorSuperiorBuscar);
		
		panelInteriorInferiorBuscar = new JPanel();
		GridBagConstraints gbc_panelInteriorInferiorBuscar = new GridBagConstraints();
		gbc_panelInteriorInferiorBuscar.fill = GridBagConstraints.BOTH;
		gbc_panelInteriorInferiorBuscar.gridx = 1;
		gbc_panelInteriorInferiorBuscar.gridy = 2;
		panelBuscar.add(panelInteriorInferiorBuscar, gbc_panelInteriorInferiorBuscar);
		panelInteriorInferiorBuscar.setLayout(new BorderLayout(0, 0));
		
		panelReproduccion = new JPanel();
		panelInteriorInferiorBuscar.add(panelReproduccion, BorderLayout.SOUTH);
		
		btnBackBuscar = new JButton("");
		btnBackBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/musica.png")));
		panelReproduccion.add(btnBackBuscar);
	
		
		btnPauseBuscar = new JButton("");
		btnPauseBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/barras-verticales.png")));
		panelReproduccion.add(btnPauseBuscar);
		
		
		btnPlayBuscar = new JButton("");
		btnPlayBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/punta-de-flecha-del-boton-de-reproduccion.png")));
		panelReproduccion.add(btnPlayBuscar);
	
		
		btnSkipBuscar = new JButton("");
		btnSkipBuscar.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/proximo.png")));
		panelReproduccion.add(btnSkipBuscar);
	
		
		btnAnadirLista = new JButton("Añadir Lista");
		panelReproduccion.add(btnAnadirLista);
		
		comboPlaylists = new JComboBox<String>();
		panelReproduccion.add(comboPlaylists);
		comboPlaylists.setVisible(false);
		
		
		addManejadorAnadirLista();
		
		
		
		modeloTabla = new DefaultTableModel(columnas, 0) {
			@Override
            public boolean isCellEditable(int row, int column) {
                // Devolver false para que las celdas no sean editables
                return false;
            }
		};
		tablaBuscar = new JTable(modeloTabla);
		tablaBuscar.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		JScrollPane scrollPane = new JScrollPane(tablaBuscar);
		
		addManejadorTabla(tablaBuscar);
		
		panelInteriorInferiorBuscar.add(scrollPane, BorderLayout.CENTER);
		
		crearManejadorBotonPlayCancion(tablaBuscar, btnPlayBuscar);
		crearManejadorBotonPauseCancion(tablaBuscar, btnPauseBuscar);
		crearManejadorBotonSkipCancion(tablaBuscar, btnSkipBuscar);
		crearManejadorBotonBackCancion(tablaBuscar, btnBackBuscar);
	}
	
	private void crearIconosEtiquetasSubpanelBuscar(JPanel panelInteriorBuscar) {
		
		// INTERPRETE
		JLabel lblLupaInterprete = new JLabel("");
		lblLupaInterprete.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/cantante.png")));
		GridBagConstraints gbc_lblLupaInterprete = new GridBagConstraints();
		gbc_lblLupaInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_lblLupaInterprete.anchor = GridBagConstraints.EAST;
		gbc_lblLupaInterprete.gridx = 1;
		gbc_lblLupaInterprete.gridy = 1;
		panelInteriorBuscar.add(lblLupaInterprete, gbc_lblLupaInterprete);
		
		// LUPA
		JLabel lblLupaTitulo = new JLabel("");
		lblLupaTitulo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/umu/tds/images/lupa.png")));
		GridBagConstraints gbc_lblLupaTitulo = new GridBagConstraints();
		gbc_lblLupaTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblLupaTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblLupaTitulo.gridx = 3;
		gbc_lblLupaTitulo.gridy = 1;
		panelInteriorBuscar.add(lblLupaTitulo, gbc_lblLupaTitulo);
		
	}
	
	private void crearEtiquetasSubpanelBuscar(JPanel panelInteriorBuscar) {
		
		// INTERPRETE
		txtInterprete = new JTextField();
		txtInterprete.setHorizontalAlignment(SwingConstants.LEFT);
		txtInterprete.setText("Interprete");
		GridBagConstraints gbc_txtInterprete = new GridBagConstraints();
		gbc_txtInterprete.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterprete.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterprete.gridx = 2;
		gbc_txtInterprete.gridy = 1;
		panelInteriorBuscar.add(txtInterprete, gbc_txtInterprete);
		txtInterprete.setColumns(10);
		
		// TITULO
		txtTitulo = new JTextField();
		txtTitulo.setText("Titulo");
		txtTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_txtTitulo = new GridBagConstraints();
		gbc_txtTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_txtTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitulo.gridx = 4;
		gbc_txtTitulo.gridy = 1;
		panelInteriorBuscar.add(txtTitulo, gbc_txtTitulo);
		txtTitulo.setColumns(10);
		
		// FAVORITAS
		checkBoxFav = new JCheckBox("Favoritas");
		checkBoxFav.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_checkBoxFav = new GridBagConstraints();
		gbc_checkBoxFav.anchor = GridBagConstraints.WEST;
		gbc_checkBoxFav.insets = new Insets(0, 0, 5, 5);
		gbc_checkBoxFav.gridx = 2;
		gbc_checkBoxFav.gridy = 2;
		panelInteriorBuscar.add(checkBoxFav, gbc_checkBoxFav);
		
		// ESTILOS
		
		estilosMusicales = Controlador.INSTANCE.getEstilosMusicales();
		comboBoxEstilos = new JComboBox<String>();
		comboBoxEstilos.setModel(new DefaultComboBoxModel<String>(estilosMusicales));
		
		comboBoxEstilos.addActionListener(e -> {
			estiloABuscar = (String) comboBoxEstilos.getSelectedItem();
		});
		
		
		
		GridBagConstraints gbc_comboBoxEstilos = new GridBagConstraints();
		gbc_comboBoxEstilos.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxEstilos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEstilos.gridx = 4;
		gbc_comboBoxEstilos.gridy = 2;
		panelInteriorBuscar.add(comboBoxEstilos, gbc_comboBoxEstilos);
	}
	
	private void crearBotonesSubpanelBuscar(JPanel panelInteriorBuscar) {
		JButton btnBuscarCancion = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscarCancion = new GridBagConstraints();
		gbc_btnBuscarCancion.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscarCancion.gridx = 4;
		gbc_btnBuscarCancion.gridy = 3;
		panelInteriorBuscar.add(btnBuscarCancion, gbc_btnBuscarCancion);
		
		crearManejadorBotonBuscarCancion(btnBuscarCancion);
	}
	
	private void crearManejadorLabelInterprete() {
		txtInterprete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				txtInterprete.setText("");
			}
			
		});
	}
	
	private void crearManejadorLabelTitulo() {
		txtTitulo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtTitulo.setText("");
			}
		});
	}
	
	private void crearManejadorBotonBuscarCancion(JButton btnBuscarCancion) {
		btnBuscarCancion.addActionListener(e -> {
			modeloTabla.setRowCount(0);
			HashSet<Cancion> cancionesFiltradas = Controlador.INSTANCE.buscarCanciones(txtTitulo.getText(), 
																						txtInterprete.getText(), 
																						estiloABuscar, 
																						checkBoxFav.isSelected());
			cancionesFiltradas.forEach(c -> {
				Vector<String> fila = new Vector<>();
				fila.add(c.getTitulo());
				fila.add(c.getInterprete());
				fila.add(c.getEstiloMusical());
				modeloTabla.addRow(fila);
			});
		});
	}
	
	private void addManejadorTabla(JTable tabla) {
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
				}
			}
		});
	}
	
	private void crearManejadorBotonPlayCancion(JTable tabla, JButton boton) {
		boton.addActionListener(e -> {
			if(tabla.getSelectedRow() != -1) {
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
			}
		});
	}
	
	private void crearManejadorBotonPlayCancionMasReproducidas(JTable tabla, JButton boton) {
		boton.addActionListener(e -> {
			int filaSeleccionada = tabla.getSelectedRow();
			if(filaSeleccionada != -1) {
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(filaSeleccionada, 0));
				modeloTablaMasReproducidas.setRowCount(0);
				
				LinkedList<Cancion> cancionesMasReproducidas = Controlador.INSTANCE.getCancionesMasReproducidas();

				cancionesMasReproducidas.forEach(c -> {
					Vector<String> fila = new Vector<>();
					fila.add(c.getTitulo());
					fila.add(c.getInterprete());
					fila.add(c.getEstiloMusical());
					fila.add(String.valueOf(c.getNumReproducciones()));
					modeloTablaMasReproducidas.addRow(fila);
				});
			}
		});
	}
	
	private void crearManejadorBotonPlayCancionRecientes(JTable tabla, JButton boton) {
		boton.addActionListener(e -> {
			int filaSeleccionada = tabla.getSelectedRow();
			if(filaSeleccionada != -1) {
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(filaSeleccionada, 0));
				modeloTablaR.setRowCount(0);	
				LinkedList<Cancion> cancionesMasReproducidas = Controlador.INSTANCE.getCancionesRecientes();
				cancionesMasReproducidas.descendingIterator().forEachRemaining(c -> {
					Vector<String> fila = new Vector<>();
					fila.add(c.getTitulo());
					fila.add(c.getInterprete());
					fila.add(c.getEstiloMusical());
					fila.add(String.valueOf(c.getNumReproducciones()));
					modeloTablaR.addRow(fila);
				});
			}
		});
	}
	
	private void crearManejadorBotonPauseCancion(JTable tabla, JButton boton) {
		boton.addActionListener(e -> {
//			if(tabla.getSelectedRow() != -1) {
				Controlador.INSTANCE.pausarCancion();
//			}
		});
	}
	
	private void crearManejadorBotonSkipCancion(JTable tabla, JButton boton) {
		boton.addActionListener(e -> {
			if(tabla.getSelectedRow() != -1 && tabla.getSelectedRow() != tabla.getRowCount()-1) {
				tabla.getSelectionModel().setSelectionInterval(tabla.getSelectedRow()+1, tabla.getSelectedRow()+1);
				Controlador.INSTANCE.pausarCancion();
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
			} else if (tabla.getSelectedRow() != -1 && tabla.getSelectedRow() == tabla.getRowCount()-1) {
				tabla.setRowSelectionInterval(0, 0);
				Controlador.INSTANCE.pausarCancion();
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
			}
		});
	}
	
	private void crearManejadorBotonBackCancion(JTable tabla, JButton boton) {
		boton.addActionListener(e -> {
			if(tabla.getSelectedRow() != -1 && tabla.getSelectedRow() != 0) {
				tabla.getSelectionModel().setSelectionInterval(tabla.getSelectedRow()-1, tabla.getSelectedRow()-1);
				Controlador.INSTANCE.pausarCancion();
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
			} else if(tabla.getSelectedRow() != -1 && tabla.getSelectedRow() == 0) {
				tabla.setRowSelectionInterval(tabla.getRowCount()-1, tabla.getRowCount()-1);
				Controlador.INSTANCE.pausarCancion();
				Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
			}
		});
	}
	
	private void addManejadorMisListas () {
		misListas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                	modeloTablaGP.setRowCount(0);
                    int index = misListas.locationToIndex(e.getPoint());
                    String playlist = misListas.getModel().getElementAt(index);
                    playlistAbiertaGP = playlist;
                    LinkedList<Cancion> canciones = Controlador.INSTANCE.getCancionesFromPlaylist(playlist);
                    canciones.stream().forEach(c -> {
	        				Vector<String> fila = new Vector<>();
	        				fila.add(c.getTitulo());
	        				fila.add(c.getInterprete());
	        				fila.add(c.getEstiloMusical());
	        				modeloTablaGP.addRow(fila);
        			});
                }
            }
        });
	}
	
	private void addManejadorAnadirLista() {
		btnAnadirLista.addActionListener(e -> {
			
			// Si el usuario no tiene playlist -> Ventana que muestre que necesita tener playlists
			if (Controlador.INSTANCE.getNumPlaylistsUsuario() == 0) {
				JOptionPane.showConfirmDialog(null, "No hay playlists.", "", JOptionPane.WARNING_MESSAGE);
			} else if (tablaBuscar.getSelectedRow() != -1){
				btnAnadirLista.setVisible(false);
				
				comboPlaylists.setModel(new DefaultComboBoxModel<String>(Controlador.INSTANCE.getNombrePlaylistsUsuario()));
				comboPlaylists.setVisible(true);
				
				comboPlaylists.addActionListener(e2 -> {
					String playlist = (String) comboPlaylists.getSelectedItem();
					String cancion = (String) tablaBuscar.getValueAt(tablaBuscar.getSelectedRow(), 0);
					Controlador.INSTANCE.addCancionToPlaylist(cancion, playlist);
					comboPlaylists.setVisible(false);
					btnAnadirLista.setVisible(true);
				});
				
				
			}
		});
	}
	
	private void addManejadorEliminarCancion() {
		btnEliminarCancionGP.addActionListener(e -> {
			if (tableGestionPlaylist.getSelectedRow() != -1) {
				String cancion = (String) tableGestionPlaylist.getValueAt(tableGestionPlaylist.getSelectedRow(), 0);
				Controlador.INSTANCE.eliminarCancionFromPlaylist(playlistAbiertaGP, cancion);
				LinkedList<Cancion> canciones = Controlador.INSTANCE.getCancionesFromPlaylist(playlistAbiertaGP);
				modeloTablaGP.setRowCount(0);
                canciones.stream().forEach(c -> {
        				Vector<String> fila = new Vector<>();
        				fila.add(c.getTitulo());
        				fila.add(c.getInterprete());
        				fila.add(c.getEstiloMusical());
        				modeloTablaGP.addRow(fila);
    			});
			}
		});
	}
	
	private void addManejadorTablaRecientes(JTable tabla) {
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
					modeloTablaR.setRowCount(0);
					LinkedList<Cancion> cancionesRecientes = Controlador.INSTANCE.getCancionesRecientes();
					cancionesRecientes.descendingIterator().forEachRemaining(c -> {
						Vector<String> fila = new Vector<>();
						fila.add(c.getTitulo());
						fila.add(c.getInterprete());
						fila.add(c.getEstiloMusical());
						modeloTablaR.addRow(fila);
					});
				}
			}
		});
		
	}
	
	private void addManejadorTablaMasReproducidas(JTable tabla) {
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Controlador.INSTANCE.reproducirCancion((String)tabla.getValueAt(tabla.getSelectedRow(), 0));
					modeloTablaMasReproducidas.setRowCount(0);
					LinkedList<Cancion> cancionesRecientes = Controlador.INSTANCE.getCancionesMasReproducidas();
					cancionesRecientes.forEach(c -> {
						Vector<String> fila = new Vector<>();
						fila.add(c.getTitulo());
						fila.add(c.getInterprete());
						fila.add(c.getEstiloMusical());
						fila.add(String.valueOf(c.getNumReproducciones()));
						modeloTablaMasReproducidas.addRow(fila);
					});
				}
			}
		});
	}
}
