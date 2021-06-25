package restaurante.sa;

import restaurante.sa.almacen.SAAlmacen;
import restaurante.sa.almacen.SAAlmacenImpl;
import restaurante.sa.cliente.SACliente;
import restaurante.sa.cliente.SAClienteImpl;
import restaurante.sa.cocinero.SACocinero;
import restaurante.sa.cocinero.SACocineroImpl;
import restaurante.sa.cyb.SACyb;
import restaurante.sa.cyb.SACybImpl;
import restaurante.sa.encargado.SAEncargado;
import restaurante.sa.encargado.SAEncargadoImpl;

public class FactorySA extends AbstractFactorySA {


	public SAAlmacen createSAAlm() {
		return new SAAlmacenImpl();
	}

	@Override
	public SACliente createSACli() {
		return new SAClienteImpl();
	}

	@Override
	public SACyb createSACyb() {
		return new SACybImpl();
	}

	@Override
	public SACocinero createSACoci() {
		return new SACocineroImpl();
	}

	@Override
	public SAEncargado createSAEnc() {
		return new SAEncargadoImpl();
	}

}
