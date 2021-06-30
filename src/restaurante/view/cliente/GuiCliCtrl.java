package restaurante.view.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cliente;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class GuiCliCtrl {
	//---------------------------
	//Atributos
	//---------------------------
	private ResObserver ObsCli;
	/**
	 * Ejecuta la funcion correspondiente y actualiza la tabla
	 * @param a
	 * @param e
	 * @param z
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean eventoCli(Cliente a, int e, int z) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACli().aniadirCli(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACli().eliminarCli(a,z);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACli().modificarCli(a,z);
		}
		break;
		}
		ArrayList<Cliente> ListCliente = new ArrayList<Cliente>();
		ListCliente = AbstractFactorySA.getInstance().createSACli().lista();
		ObsCli.ObsCli(ListCliente);
		return x;
	}
	/**
	 * Inicia el observer
	 * @param a
	 * @throws FileNotFoundException
	 */
	public void iniciarObs(ResObserver c) throws FileNotFoundException {
		ArrayList<Cliente> ListCliente = AbstractFactorySA.getInstance().createSACli().lista();
		ObsCli = c;
		ObsCli.ObsCli(ListCliente);
	}
}
