package umu.tds.descuentos;

public class DescuentoFIJO implements Descuento {
	// Precio aleatorio (Se puede establecer a cualquier valor)
	private final static double PRECIO_FIJO = 0.9;
	@Override
	public double calcDescuento(double precioPremium) {
		return precioPremium * PRECIO_FIJO;	
	}

}
