package restaurante.sa;

import restaurante.sa.almacen.SAAlmacen;

public abstract class AbstractFactorySA {
	private static AbstractFactorySA instance;
	public static AbstractFactorySA getInstance() {
		if(instance == null) {
			instance = new FactorySA();
		}
		return instance;
	}
	public abstract SAAlmacen createSAAlm();

}
