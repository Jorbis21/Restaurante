package restaurante.sa;

import restaurante.sa.almacen.SAAlmacen;
import restaurante.sa.almacen.SAAlmacenImpl;

public class FactorySA extends AbstractFactorySA {


	public SAAlmacen createSAAlm() {
		return new SAAlmacenImpl();
	}

}
