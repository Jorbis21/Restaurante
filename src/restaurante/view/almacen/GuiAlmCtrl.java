package restaurante.view.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Almacen;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class GuiAlmCtrl {
	//---------------------------
	//Atributos
	//---------------------------
	private ResObserver ObsAlm;
	/**
	 * Ejecuta la funcion correspondiente y actualiza la tabla
	 * @param a
	 * @param e
	 * @param z
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean eventoAlm(Almacen a, int e, int z) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSAAlm().aniadirAlm(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSAAlm().eliminarAlm(a,z);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSAAlm().modificarAlm(a,z);
		}
		break;
		}
		ArrayList<Almacen> ListAlmacen = new ArrayList<Almacen>();
		ListAlmacen = AbstractFactorySA.getInstance().createSAAlm().lista();
		ObsAlm.ObsAlm(ListAlmacen);
		return x;
	}
	/**
	 * Inicia el observer
	 * @param a
	 * @throws FileNotFoundException
	 */
	public void iniciarObs(ResObserver a) throws FileNotFoundException {
		ArrayList<Almacen> ListAlmacen = AbstractFactorySA.getInstance().createSAAlm().lista();
		ObsAlm = a;
		ObsAlm.ObsAlm(ListAlmacen);
	}
	
}
