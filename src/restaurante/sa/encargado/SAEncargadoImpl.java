package restaurante.sa.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.encargado.DAOEncargado;
import restaurante.model.Empleado;

public class SAEncargadoImpl implements SAEncargado {

	@Override
	public boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.aniadirEnc(a,x);
	}

	@Override
	public boolean modificarEnc(Empleado a, int n, int x) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		y.modificarEnc(a,n,x);
		return true;
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
		if(buscarEnc(a,x) != null){
			y.eliminarEnc(n,x);
			return true;
		}
		return false;
	}
	
	public int buscEnc(String dni, int ide) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.buscEnc(dni, ide);
		
	}
	public boolean buscEmp(String dni, int id) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.buscEmp(dni, id);
	}

	@Override
	public ArrayList<Empleado> lista(int status, int enc) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.lista(status, enc);
	}

	@Override
	public boolean buscDni(String dni) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.buscDni(dni);
	}

	@Override
	public boolean buscId(int id) throws FileNotFoundException {
		DAOEncargado y = AbstractFactoryDAO.getInstance().createDAOEnc();
		return y.buscId(id);
	}

}
