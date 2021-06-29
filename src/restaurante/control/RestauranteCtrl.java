package restaurante.control;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import restaurante.model.Almacen;
import restaurante.model.Cliente;
import restaurante.model.Cocinero;
import restaurante.model.ComidaYBebida;
import restaurante.model.Empleado;
import restaurante.model.Encargado;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class RestauranteCtrl {
	
	private ArrayList<Encargado> ListEncargado;
	
	private ArrayList<Empleado> ListEmpleado;
	public RestauranteCtrl() throws FileNotFoundException {
		init();
	}
	private void init() throws FileNotFoundException {
		
		
		ListEncargado = AbstractFactorySA.getInstance().createSAEnc().lista();
		
		ListEmpleado = new ArrayList<Empleado>();
		for(Encargado e: ListEncargado) {
			ListEmpleado.add(e);
			for(Empleado a: e.getLista()) {
				ListEmpleado.add(a);
			}
		}
		
	}
	
	
	
	
	
	public boolean eventoEnc(Empleado a, int e, int n, int z) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSAEnc().aniadirEnc(a, n);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSAEnc().eliminarEnc(a, n, z);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSAEnc().modificarEnc(a, n, z);
		}
		break;
		}
		return x;
	}
	
}
