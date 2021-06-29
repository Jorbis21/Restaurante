package restaurante.view.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Almacen;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class GuiAlmCtrl {
	private ResObserver ObsAlm;
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
	public void iniciarObs(ResObserver a) throws FileNotFoundException {
		ArrayList<Almacen> ListAlmacen = AbstractFactorySA.getInstance().createSAAlm().lista();
		ObsAlm = a;
		ObsAlm.ObsAlm(ListAlmacen);
	}
	
}
