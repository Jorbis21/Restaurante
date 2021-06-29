package restaurante.sa.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.encargado.DAOEncargado;
import restaurante.model.Empleado;
import restaurante.model.Encargado;

public class SAEncargadoImpl implements SAEncargado {

	@Override
	public boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		if(buscarEnc(a,x) == null){
			y.aniadirEnc(a,x);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarEnc(Empleado a, int n, int x) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		if(!(buscarEnc(a,x) == null)){
			y.modificarEnc(a,x);
			return true;
		}
		return false;
	}

	@Override
	public Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		Empleado c = y.buscarEnc(a, x);
		return c;
	}

	@Override
	public boolean eliminarEnc(Empleado a,int n, int x) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		if(buscarEnc(a,x) == null){
			y.eliminarEnc(a,x);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Encargado> lista() throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.lista();
	}

}
