package umu.tds.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.time.Instant;
import java.util.Date;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;

import umu.tds.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;

public class VentanaRegistro extends JDialog {

	private JPanel contentPane;
	private JTextField nameField;
	private JLabel lblUsuario;
	private JLabel lblFechaNacimiento;
	private JDateChooser dateChooser;
	private JTextField userField;
	private JButton btnRegistro;
	private JLabel lblIcono;
	private JButton btnAtras;
	private JLabel lblNombreError;
	private JLabel lblUsuarioError;
	private JLabel lblApellidos;
	private JTextField apellidosField;
	private JLabel lblApellidosError;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel lblPasswordAgain;
	private JPasswordField passwordAgainField;
	private JLabel lblPasswordError;
	private JLabel lblFechaError;
	private JLabel lblEmail;
	private JTextField emailField;
	private JLabel lblEmailError;

	/**
	 * Create the frame.
	 */
	public VentanaRegistro(JFrame owner) {
		super(owner, "AppMusic", true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/umu/tds/images/IconoApp.png")));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 597, 529);
		crearPanelRegistro();
	}
	
	private void crearPanelRegistro() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 0, 170, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 0, 20, 0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Icono
		lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/umu/tds/images/editar.png")));
		GridBagConstraints gbc_lblIcono = new GridBagConstraints();
		gbc_lblIcono.gridwidth = 4;
		gbc_lblIcono.insets = new Insets(0, 0, 5, 0);
		gbc_lblIcono.gridx = 1;
		gbc_lblIcono.gridy = 2;
		contentPane.add(lblIcono, gbc_lblIcono);
				
		crearBotones();
		crearEtiquetasCuestionario();
		crearCamposDeRespuesta();
		crearEtiquetasError();
		
		crearManejadorBotonAtras(btnAtras);
		crearManejadorBotonRegistro(btnRegistro);
		ocultarErrores();
	}
	
	private void crearBotones() {
		
		// ATRAS
		btnAtras = new JButton("Atrás");
		btnAtras.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/umu/tds/images/hacia-atras.png")));		
		GridBagConstraints gbc_btnAtras = new GridBagConstraints();
		gbc_btnAtras.insets = new Insets(0, 0, 5, 0);
		gbc_btnAtras.gridx = 4;
		gbc_btnAtras.gridy = 1;
		contentPane.add(btnAtras, gbc_btnAtras);
		
		// REGISTRO
		btnRegistro = new JButton("Registro");
		btnRegistro.setIcon(new ImageIcon(VentanaRegistro.class.getResource("/umu/tds/images/registro.png")));
		GridBagConstraints gbc_btnRegistro = new GridBagConstraints();
		gbc_btnRegistro.gridwidth = 4;
		gbc_btnRegistro.gridx = 1;
		gbc_btnRegistro.gridy = 16;
		contentPane.add(btnRegistro, gbc_btnRegistro);
	}
	
	private void crearEtiquetasCuestionario() {
		
		// NOMBRE
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 4;
		contentPane.add(lblNombre, gbc_lblNombre);
		
		// APELLIDOS
		lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.gridx = 1;
		gbc_lblApellidos.gridy = 6;
		contentPane.add(lblApellidos, gbc_lblApellidos);
		
		// EMAIL
		lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 8;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		// USUARIO
		lblUsuario = new JLabel("Usuario");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 1;
		gbc_lblUsuario.gridy = 10;
		contentPane.add(lblUsuario, gbc_lblUsuario);
		
		// PASSWORD
		lblPassword = new JLabel("Contraseña");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 12;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		// PASSWORD AGAIN
		lblPasswordAgain = new JLabel("Otra vez");
		lblPasswordAgain.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPasswordAgain = new GridBagConstraints();
		gbc_lblPasswordAgain.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordAgain.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordAgain.gridx = 3;
		gbc_lblPasswordAgain.gridy = 12;
		contentPane.add(lblPasswordAgain, gbc_lblPasswordAgain);
		
		// FECHA NACIMIENTO
		lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 14;
		contentPane.add(lblFechaNacimiento, gbc_lblFechaNacimiento);
	}
	
	private void crearCamposDeRespuesta() {
		
		// NOMBRE
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridwidth = 3;
		gbc_nameField.insets = new Insets(0, 0, 5, 0);
		gbc_nameField.gridx = 2;
		gbc_nameField.gridy = 4;
		contentPane.add(nameField, gbc_nameField);
		nameField.setColumns(20);
		
		// APELLIDOS
		apellidosField = new JTextField();
		GridBagConstraints gbc_apellidosField = new GridBagConstraints();
		gbc_apellidosField.gridwidth = 3;
		gbc_apellidosField.insets = new Insets(0, 0, 5, 0);
		gbc_apellidosField.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellidosField.gridx = 2;
		gbc_apellidosField.gridy = 6;
		contentPane.add(apellidosField, gbc_apellidosField);
		apellidosField.setColumns(10);
		
		// EMAIL
		emailField = new JTextField();
		GridBagConstraints gbc_emailField = new GridBagConstraints();
		gbc_emailField.gridwidth = 3;
		gbc_emailField.insets = new Insets(0, 0, 5, 0);
		gbc_emailField.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailField.gridx = 2;
		gbc_emailField.gridy = 8;
		contentPane.add(emailField, gbc_emailField);
		emailField.setColumns(10);
		
		// USUARIO
		userField = new JTextField();
		GridBagConstraints gbc_userField = new GridBagConstraints();
		gbc_userField.gridwidth = 3;
		gbc_userField.insets = new Insets(0, 0, 5, 0);
		gbc_userField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userField.gridx = 2;
		gbc_userField.gridy = 10;
		contentPane.add(userField, gbc_userField);
		userField.setColumns(10);
		
		// PASSWORD
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 12;
		contentPane.add(passwordField, gbc_passwordField);
		
		// PASSWORD AGAIN
		passwordAgainField = new JPasswordField();
		GridBagConstraints gbc_passwordAgainField = new GridBagConstraints();
		gbc_passwordAgainField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordAgainField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordAgainField.gridx = 4;
		gbc_passwordAgainField.gridy = 12;
		contentPane.add(passwordAgainField, gbc_passwordAgainField);
		
		// FECHA DE NACIMIENTO
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-y");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 14;
		contentPane.add(dateChooser, gbc_dateChooser);
		
	}
	
	private void crearEtiquetasError() {
		
		// NOMBRE
		lblNombreError = new JLabel("El nombre es obligatorio");
		lblNombreError.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombreError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblNombreError = new GridBagConstraints();
		gbc_lblNombreError.gridwidth = 3;
		gbc_lblNombreError.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombreError.gridx = 2;
		gbc_lblNombreError.gridy = 5;
		contentPane.add(lblNombreError, gbc_lblNombreError);
		
		// APELLIDOS
		lblApellidosError = new JLabel("Los apellidos son obligatorios");
		lblApellidosError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblApellidosError = new GridBagConstraints();
		gbc_lblApellidosError.gridwidth = 3;
		gbc_lblApellidosError.insets = new Insets(0, 0, 5, 0);
		gbc_lblApellidosError.gridx = 2;
		gbc_lblApellidosError.gridy = 7;
		contentPane.add(lblApellidosError, gbc_lblApellidosError);
		
		// EMAIL
		lblEmailError = new JLabel("El email es obligatorio");
		lblEmailError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblEmailError = new GridBagConstraints();
		gbc_lblEmailError.gridwidth = 3;
		gbc_lblEmailError.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailError.gridx = 2;
		gbc_lblEmailError.gridy = 9;
		contentPane.add(lblEmailError, gbc_lblEmailError);
		
		// USUARIO
		lblUsuarioError = new JLabel("El usuario es obligatorio");
		lblUsuarioError.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarioError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblUsuarioError = new GridBagConstraints();
		gbc_lblUsuarioError.gridwidth = 3;
		gbc_lblUsuarioError.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsuarioError.gridx = 2;
		gbc_lblUsuarioError.gridy = 11;
		contentPane.add(lblUsuarioError, gbc_lblUsuarioError);
		
		// PASSWORD & PASSWORD AGAIN
		lblPasswordError = new JLabel("Error al introducir las contraseñas");
		lblPasswordError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblPasswordError = new GridBagConstraints();
		gbc_lblPasswordError.gridwidth = 3;
		gbc_lblPasswordError.insets = new Insets(0, 0, 5, 0);
		gbc_lblPasswordError.gridx = 2;
		gbc_lblPasswordError.gridy = 13;
		contentPane.add(lblPasswordError, gbc_lblPasswordError);
		
		// FECHA NACIMIENTO
		lblFechaError = new JLabel("Introduce la fecha de nacimiento");
		lblFechaError.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblFechaError = new GridBagConstraints();
		gbc_lblFechaError.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaError.gridx = 2;
		gbc_lblFechaError.gridy = 15;
		contentPane.add(lblFechaError, gbc_lblFechaError);
		
	}

	private void ocultarErrores(){
		lblNombreError.setVisible(false);
		lblApellidosError.setVisible(false);
		lblFechaError.setVisible(false);
		lblEmailError.setVisible(false);
		lblUsuarioError.setVisible(false);
		lblPasswordError.setVisible(false);		
	}
	private boolean checkFields() {
		boolean salida = true;
		
		ocultarErrores();
		
		if (nameField.getText().trim().isEmpty()) {
			lblNombreError.setVisible(true);
			salida = false;
		}
		if (apellidosField.getText().trim().isEmpty()) {
			lblApellidosError.setVisible(true);
			salida = false;
		}
		if (emailField.getText().trim().isEmpty()) {
			lblEmailError.setVisible(true);
			salida = false;
		}
		if (userField.getText().trim().isEmpty()) {
			lblUsuarioError.setVisible(true);
			salida = false;
		}
		if (dateChooser.getDate() == null) {
			lblFechaError.setVisible(true);
			salida = false;
		} else if (!dateChooser.getDate().before(Date.from(Instant.now().minusSeconds(4)))) {
			lblFechaError.setText("La fecha debe ser anterior a la de hoy.");
			lblFechaError.setVisible(true);
			salida = false;
		}
		
		String password = new String(passwordField.getPassword());
		String password2 = new String(passwordAgainField.getPassword());
		
		if (password.isEmpty() || password2.isEmpty()) {
			lblPasswordError.setVisible(true);
			salida = false;
		}
		if (!password.equals(password2)) {
			lblPasswordError.setText("La contraseña no coincide");
			lblPasswordError.setVisible(true);
			salida = false;
		}
		
		if(!lblUsuarioError.getText().isEmpty() && Controlador.INSTANCE.esUsuarioRegistrado(userField.getText())) {
			lblUsuarioError.setText("Ya existe ese usuario");
			lblUsuarioError.setVisible(true);
			salida = false;
		}
		this.revalidate();
//		this.pack();
		return salida;
		
	}
	
	private void crearManejadorBotonAtras(JButton btnAtras) {
		btnAtras.addActionListener(e -> {
			VentanaLogin login = new VentanaLogin();
			login.mostrarVentana();
			VentanaRegistro.this.dispose();
		});
	}
	
	private void crearManejadorBotonRegistro(JButton btnRegistro) {
		
		btnRegistro.addActionListener(e -> {
			boolean ok = false;
			ok = checkFields();
			
			if (ok) {
				boolean registrado = false;
				registrado = Controlador.INSTANCE.registrarUsuario(nameField.getText(), 
																		apellidosField.getText(), 
																		emailField.getText(), 
																		userField.getText(),
																		new String(passwordField.getPassword()), 
																		dateChooser.getDate());
				if (registrado) {
					JOptionPane.showMessageDialog(VentanaRegistro.this, "Usuario registrado correctamente.", "Registro", JOptionPane.INFORMATION_MESSAGE);
					VentanaLogin login = new VentanaLogin();
					login.mostrarVentana();
					VentanaRegistro.this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(VentanaRegistro.this, "No se ha podido llevar a vabo el registro.\n", "Registro", JOptionPane.ERROR_MESSAGE);
					VentanaRegistro.this.setTitle("Login Gestor Eventos");
				}
														
			}
		});
		
	}
}







