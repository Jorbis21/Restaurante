package restaurante.factories;

import restaurante.sa.AbstractFactorySA;
import restaurante.sa.almacen.ISAAlmacen;
import restaurante.sa.almacen.SAAlmacenImpl;

public class FactorySA extends AbstractFactorySA {


	public ISAAlmacen createSAAlm() {
		return new SAAlmacenImpl();
	}

}
