package restaurante.sa;

import restaurante.sa.almacen.SAAlmacen;
import restaurante.sa.cliente.SACliente;
import restaurante.sa.cocinero.SACocinero;
import restaurante.sa.cyb.SACyb;
import restaurante.sa.encargado.SAEncargado;

public abstract class AbstractFactorySA {
	private static AbstractFactorySA instance;
	public static AbstractFactorySA getInstance() {
		if(instance == null) {
			instance = new FactorySA();
		}
		return instance;
	}
	public abstract SAAlmacen createSAAlm();
	public abstract SACliente createSACli();
	public abstract SACyb createSACyb();
	public abstract SACocinero createSACoci();
	public abstract SAEncargado createSAEnc();
}
