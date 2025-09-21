package umu.tds.descuentos;

public class DescuentoJOVEN implements Descuento{
	
	// Precio aleatorio
	private final static double PRECIO_PREMIUM = 0.7;
	@Override
	public double calcDescuento(double precioPremium) {
		return precioPremium * PRECIO_PREMIUM;		
	}

}
