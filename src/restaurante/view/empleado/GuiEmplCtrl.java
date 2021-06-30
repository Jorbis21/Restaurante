package restaurante.view.empleado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Empleado;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;
import restaurante.sa.encargado.SAEncargado;

public class GuiEmplCtrl {
	//---------------------------
	//Atributos
	//---------------------------
	private ResObserver ObsEnc;
	private int enc, status;
	/**
	 * Constructor
	 * @param s
	 */
	public GuiEmplCtrl(int s) {
		status = s;
	}
	/**
	 * Ejecuta la funcion correspondiente y actualiza la tabla
	 * @param a
	 * @param e
	 * @param z
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean eventoEnc(Empleado a, int e, int z) throws FileNotFoundException {
		boolean x = false;
		SAEncargado y = AbstractFactorySA.getInstance().createSAEnc();
		switch(e) {
		case 0:{
			x = y.aniadirEnc(a, enc);
		}
		break;
		case 1:{
			x = y.eliminarEnc(a, enc, z);
		}
		break;
		case 2:{
			x = y.modificarEnc(a, enc, z);
		}
		break;
		}
		ArrayList<Empleado> ListEncargado = new ArrayList<Empleado>();
		ListEncargado = y.lista(status, enc);
		ObsEnc.ObsEmp(ListEncargado);
		return x;
	}
	/**
	 * Busca dni para ver si no hay repetido
	 * @param dni
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean buscDni(String dni) throws FileNotFoundException{
		SAEncargado y = AbstractFactorySA.getInstance().createSAEnc();
		return y.buscDni(dni);
	}
	/**
	 * Busca id para ver si no hay repetido
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean buscId(int id) throws FileNotFoundException{
		SAEncargado y = AbstractFactorySA.getInstance().createSAEnc();
		return y.buscId(id);
	}
	/**
	 * Busca encargado para inicio sesion
	 * @param dni
	 * @param ide
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean buscEnc(String dni, int ide) throws FileNotFoundException {
		enc = AbstractFactorySA.getInstance().createSAEnc().buscEnc(dni, ide);
		if(enc > -1) {
			status = 0;
			return true;
		}
		return false;
	}
	/**
	 * Busca empleado para inicio sesion
	 * @param dni
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean buscEmp(String dni, int id) throws FileNotFoundException {
		status = 1;
		return AbstractFactorySA.getInstance().createSAEnc().buscEmp(dni, id);
	}
	/**
	 * Inicia el observer
	 * @param e
	 * @throws FileNotFoundException
	 */
	public void iniciarObs(ResObserver e) throws FileNotFoundException {
		ArrayList<Empleado> ListEncargado = AbstractFactorySA.getInstance().createSAEnc().lista(status, enc);
		ObsEnc = e;
		ObsEnc.ObsEmp(ListEncargado);
	}

}
