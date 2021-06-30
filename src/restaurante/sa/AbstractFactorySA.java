package restaurante.sa;

import restaurante.sa.almacen.SAAlmacen;
import restaurante.sa.cliente.SACliente;
import restaurante.sa.cocinero.SACocinero;
import restaurante.sa.cyb.SACyb;
import restaurante.sa.encargado.SAEncargado;

public abstract class AbstractFactorySA {
	//---------------------
	//Atributos
	//---------------------
	private static AbstractFactorySA instance;
	/**
	 * Constructor
	 * @return
	 */
	public static AbstractFactorySA getInstance() {
		if(instance == null) {
			instance = new FactorySA();
		}
		return instance;
	}
	/**
	 * Crea la interfaz del SA de almacen
	 * @return
	 */
	public abstract SAAlmacen createSAAlm();
	/**
	 * Crea la interfaz del SA de cliente
	 * @return
	 */
	public abstract SACliente createSACli();
	/**
	 * Crea la interfaz del SA de comidaybebida
	 * @return
	 */
	public abstract SACyb createSACyb();
	/**
	 * Crea la interfaz del SA de cocinero
	 * @return
	 */
	public abstract SACocinero createSACoci();
	/**
	 * Crea la interfaz del SA de encargado
	 * @return
	 */
	public abstract SAEncargado createSAEnc();
}
