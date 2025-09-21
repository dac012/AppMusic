package umu.tds.ventanas;


import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import umu.tds.controlador.Controlador;
import umu.tds.github.LoginGitHub;

import java.awt.GridBagConstraints;
import javax.swing.JPasswordField;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;

public class VentanaLogin {

	private JFrame frameLogin;
	private JTextField UserField;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public VentanaLogin() {
		initialize();
	}
	
	public void mostrarVentana() {
		frameLogin.setLocationRelativeTo(null);
		frameLogin.setVisible(true);
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogin = new JFrame("AppMusic");
		frameLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/umu/tds/images/IconoApp.png")));
		frameLogin.setBounds(100, 100, 480, 334);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{60, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 40, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frameLogin.getContentPane().setLayout(gridBagLayout);
		
		// Nombre central de la aplicacion
		
		createTitulo();
		
		// Logo central de la aplicacion
		
		createLogo();
		
		// Cuestionario de la aplicacion
		
		createCuestionario();
		
	}
	
	private void createTitulo() {
		JLabel Title = new JLabel("AppMusic");
		Title.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_Title = new GridBagConstraints();
		gbc_Title.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_Title.insets = new Insets(0, 0, 5, 5);
		gbc_Title.gridx = 2;
		gbc_Title.gridy = 1;
		frameLogin.getContentPane().add(Title, gbc_Title);
	}
	
	private void createLogo() {
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/images/logoMusicaByN.png")));
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.insets = new Insets(0, 0, 5, 5);
		gbc_logo.gridx = 2;
		gbc_logo.gridy = 2;
		frameLogin.getContentPane().add(logo, gbc_logo);
	}
	
	private void createCuestionario() {
		
		// Etiqueta Usuario
		JLabel User = new JLabel("Usuario:");
		GridBagConstraints gbc_User = new GridBagConstraints();
		gbc_User.anchor = GridBagConstraints.EAST;
		gbc_User.insets = new Insets(0, 0, 5, 5);
		gbc_User.gridx = 1;
		gbc_User.gridy = 3;
		frameLogin.getContentPane().add(User, gbc_User);
		
		// Campo de escritura de usuario
		UserField = new JTextField();
		GridBagConstraints gbc_UserField = new GridBagConstraints();
		gbc_UserField.fill = GridBagConstraints.HORIZONTAL;
		gbc_UserField.insets = new Insets(0, 0, 5, 5);
		gbc_UserField.gridx = 2;
		gbc_UserField.gridy = 3;
		frameLogin.getContentPane().add(UserField, gbc_UserField);
		UserField.setColumns(20);
		
		// Etiqueta Contraseña 
		JLabel Password = new JLabel("Contraseña:");
		GridBagConstraints gbc_Password = new GridBagConstraints();
		gbc_Password.insets = new Insets(0, 0, 5, 5);
		gbc_Password.anchor = GridBagConstraints.EAST;
		gbc_Password.gridx = 1;
		gbc_Password.gridy = 4;
		frameLogin.getContentPane().add(Password, gbc_Password);
		
		// Campo de escritura de la Contraseña
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 4;
		frameLogin.getContentPane().add(passwordField, gbc_passwordField);
		
		// Panel con los botones Login y Registro
		JPanel panelLoginRegistrarse = new JPanel();
		GridBagConstraints gbc_panelLoginRegistrarse = new GridBagConstraints();
		gbc_panelLoginRegistrarse.insets = new Insets(0, 0, 5, 5);
		gbc_panelLoginRegistrarse.fill = GridBagConstraints.BOTH;
		gbc_panelLoginRegistrarse.gridx = 2;
		gbc_panelLoginRegistrarse.gridy = 5;
		frameLogin.getContentPane().add(panelLoginRegistrarse, gbc_panelLoginRegistrarse);
		
		// Boton de login
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/images/login.png")));
		panelLoginRegistrarse.add(btnLogin);
		
		// Boton de registro
		JButton btnReistrarse = new JButton("Registrarse");
		btnReistrarse.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/images/registro.png")));
		panelLoginRegistrarse.add(btnReistrarse);
		
		// Boton de login con github
		JButton btnLoginGitHub = new JButton("Login con GitHub");
		btnLoginGitHub.setIcon(new ImageIcon(VentanaLogin.class.getResource("/umu/tds/images/github.png")));
		GridBagConstraints gbc_btnLoginGitHub = new GridBagConstraints();
		gbc_btnLoginGitHub.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoginGitHub.gridx = 2;
		gbc_btnLoginGitHub.gridy = 6;
		frameLogin.getContentPane().add(btnLoginGitHub, gbc_btnLoginGitHub);
		
		addManejadorBotonRegistrarse(btnReistrarse);
		addManejadorBotonLogin(btnLogin);
		addManejadorBotonLoginGitHub(btnLoginGitHub);
	}
	
	private void addManejadorBotonRegistrarse(JButton btnRegistrarse) {
		btnRegistrarse.addActionListener(e -> {
			VentanaRegistro reg = new VentanaRegistro(frameLogin);
			reg.setLocationRelativeTo(frameLogin);
			reg.setVisible(true);
			frameLogin.dispose();
		});
	}
	
	private void addManejadorBotonLogin(JButton btnLogin) {
			btnLogin.addActionListener(e -> {
			boolean login = Controlador.INSTANCE.loginUsuario(UserField.getText(), new String(passwordField.getPassword()));
			if (login) {
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.mostrarVentana();
				frameLogin.dispose();
			} else
				JOptionPane.showMessageDialog(frameLogin, "Nombre de usuario o contraseña no valido",
						"Error", JOptionPane.ERROR_MESSAGE);
		});
	}
	
	private void addManejadorBotonLoginGitHub(JButton btnLoginGitHub) {
		btnLoginGitHub.addActionListener(e -> {
			if (UserField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(frameLogin, "Es necesario indicar un nombre de usuario.",
						"Error", JOptionPane.ERROR_MESSAGE);
			} else {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.addChoosableFileFilter(new FileFilter() {
					public String getDescription() {
						return "GitHub Properties File (*.properties)";
					}

					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							return f.getName().toLowerCase().endsWith(".properties");
						}
					}
				});
				fileChooser.setAcceptAllFileFilterUsed(false);
				File workingDirectory = new File(System.getProperty("user.dir"));
				fileChooser.setCurrentDirectory(workingDirectory);
				int result = fileChooser.showOpenDialog(frameLogin);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());

					if (LoginGitHub.INSTANCE.verificar(UserField.getText(), selectedFile.getAbsolutePath())) {
						JOptionPane.showMessageDialog(frameLogin, "Login Correcto", "Login",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frameLogin, "Login Fallido", "Login", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			
		});
	}

}
