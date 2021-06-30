package restaurante.view.comidaybebida;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.ComidaYBebida;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class GuiCybCtrl {
	//---------------------------
	//Atributos
	//---------------------------
	private ResObserver ObsCyb;
	/**
	 * Ejecuta la funcion correspondiente y actualiza la tabla
	 * @param a
	 * @param e
	 * @param z
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean eventoCyb(ComidaYBebida a, int e, int z) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			x = AbstractFactorySA.getInstance().createSACyb().aniadirCyb(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACyb().eliminarCyb(a,z);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACyb().modificarCyb(a,z);
		}
		break;
		}
		ArrayList<ComidaYBebida> ListCYB = new ArrayList<ComidaYBebida>();
		ListCYB = AbstractFactorySA.getInstance().createSACyb().lista();
		ObsCyb.ObsCyb(ListCYB);
		return x;

	}
	/**
	 * Inicia el observer
	 * @param a
	 * @throws FileNotFoundException
	 */
	public void iniciarObs(ResObserver c) throws FileNotFoundException {
		ArrayList<ComidaYBebida> ListCYB = AbstractFactorySA.getInstance().createSACyb().lista();
		ObsCyb = c;
		ObsCyb.ObsCyb(ListCYB);
	}
}
