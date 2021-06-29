package restaurante.view.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cocinero;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class GuiCociCtrl {
	private ResObserver ObsCoci;
	public boolean eventoCoci(Cocinero a, int e, int z) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACoci().aniadirCoci(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACoci().eliminarCoci(a,z);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACoci().modificarCoci(a,z);
		}
		break;
		}
		ArrayList<Cocinero> ListCocinero = new ArrayList<Cocinero>();
		ListCocinero = AbstractFactorySA.getInstance().createSACoci().lista();
		ObsCoci.ObsCoci(ListCocinero);
		return x;
	}
	public void iniciarObs(ResObserver c) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = AbstractFactorySA.getInstance().createSACoci().lista();
		ObsCoci = c;
		ObsCoci.ObsCoci(ListCocinero);;
	}
}
