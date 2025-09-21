package umu.tds.descuentos;

import java.lang.reflect.Constructor;

public enum FactoriaDescuento {
	INSTANCE;	
	
	public Descuento getDescuento(String tipo, int edad) {
		Descuento desc;
		try {
			// Utilizamos introspeccion para crear una clase Descuento.
			
			Class<?> descuentoClass = Class.forName("umu.tds.descuentos.Descuento" + tipo);
			Constructor<?> constructor = descuentoClass.getConstructor();
			desc = (Descuento) constructor.newInstance();
			
			// No se puede aplicar el descuento JOVEN si no eres menor de edad.
			if (edad >= 18 && desc.getClass().getName().equals("umu.tds.descuentos." + "DescuentoJOVEN")) {
				desc = new DescuentoNull();
			}
		}  catch (NoClassDefFoundError e) {
			// No hay descuento
            desc = new DescuentoNull();
        } catch (Exception e) {
        	// Manejar otras excepciones
            desc = new DescuentoNull(); 
        }
		return desc;
	}
	
	public Descuento getDescuentoNulo() {
		return new DescuentoNull(); 
	}
}
