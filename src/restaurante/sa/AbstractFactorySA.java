package restaurante.sa;

import restaurante.factories.FactorySA;
import restaurante.sa.almacen.ISAAlmacen;

public abstract class AbstractFactorySA {
	private static AbstractFactorySA instance;
	public static AbstractFactorySA getInstance() {
		if(instance == null) {
			instance = new FactorySA();
		}
		return instance;
	}
	public abstract ISAAlmacen createSAAlm();

}
