package umu.tds.ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import umu.tds.controlador.Controlador;
import umu.tds.descuentos.Descuento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaDescuento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	/**
	 * Create the frame.
	 */
	public VentanaDescuento() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{15, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{15, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblIconoPagar = new JLabel("");
		lblIconoPagar.setIcon(new ImageIcon(VentanaDescuento.class.getResource("/umu/tds/images/metodo-de-pago.png")));
		GridBagConstraints gbc_lblIconoPagar = new GridBagConstraints();
		gbc_lblIconoPagar.gridwidth = 2;
		gbc_lblIconoPagar.insets = new Insets(0, 0, 5, 5);
		gbc_lblIconoPagar.gridx = 3;
		gbc_lblIconoPagar.gridy = 1;
		contentPane.add(lblIconoPagar, gbc_lblIconoPagar);
		
		JLabel lblTextoPrecio = new JLabel("Precio sin descuento:");
		lblTextoPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblTextoPrecio = new GridBagConstraints();
		gbc_lblTextoPrecio.anchor = GridBagConstraints.EAST;
		gbc_lblTextoPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblTextoPrecio.gridx = 3;
		gbc_lblTextoPrecio.gridy = 3;
		contentPane.add(lblTextoPrecio, gbc_lblTextoPrecio);
		
		String precio = String.format("%.2f€", Controlador.INSTANCE.getPrecioPremiumSinDescuento());
		JLabel lblPrecio = new JLabel(precio);
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.anchor = GridBagConstraints.WEST;
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 4;
		gbc_lblPrecio.gridy = 3;
		contentPane.add(lblPrecio, gbc_lblPrecio);
		
		JLabel lblIntroduceCodDescuento = new JLabel("Codigo de descuento:");
		GridBagConstraints gbc_lblIntroduceCodDescuento = new GridBagConstraints();
		gbc_lblIntroduceCodDescuento.anchor = GridBagConstraints.EAST;
		gbc_lblIntroduceCodDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblIntroduceCodDescuento.gridx = 3;
		gbc_lblIntroduceCodDescuento.gridy = 4;
		contentPane.add(lblIntroduceCodDescuento, gbc_lblIntroduceCodDescuento);
		
		JTextField fieldDescuento = new JTextField();
		GridBagConstraints gbc_fieldDescuento = new GridBagConstraints();
		gbc_fieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_fieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldDescuento.gridx = 4;
		gbc_fieldDescuento.gridy = 4;
		contentPane.add(fieldDescuento, gbc_fieldDescuento);
		fieldDescuento.setColumns(10);
		
		JButton btnPagar = new JButton("Pagar");
		GridBagConstraints gbc_btnPagar = new GridBagConstraints();
		gbc_btnPagar.gridwidth = 2;
		gbc_btnPagar.insets = new Insets(0, 0, 0, 5);
		gbc_btnPagar.gridx = 3;
		gbc_btnPagar.gridy = 5;
		contentPane.add(btnPagar, gbc_btnPagar);
		
		addManejadorBotonPagar(btnPagar, fieldDescuento);
	}
	
	public void mostrarVentana() {
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void addManejadorBotonPagar(JButton btnPagar, JTextField fieldDescuento) {
		btnPagar.addActionListener(e -> {
			Descuento descuento = Controlador.INSTANCE.getDescuento(fieldDescuento.getText());
			double precioApagar = Controlador.INSTANCE.getPrecioPremium(descuento);
			String precioPago = "¿Deseas pagar " + String.format("%.2f€", precioApagar) + "?";
			int respuesta = JOptionPane.showConfirmDialog(null, precioPago, "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
			if (respuesta == JOptionPane.YES_OPTION) {
				Controlador.INSTANCE.setPremiumUser(descuento);
				JOptionPane.showMessageDialog(null, "Ahora eres PREMIUM", "Premium", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		});
	}
}





















