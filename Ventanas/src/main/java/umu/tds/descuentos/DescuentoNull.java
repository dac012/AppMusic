package umu.tds.descuentos;

public class DescuentoNull implements Descuento{
	// No existe ningún descuento
	@Override
	public double calcDescuento(double precioPremium) {
		return precioPremium;
	}

}
