package restaurante.sa.cocinero;

import java.io.FileNotFoundException;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.cocinero.DAOCoci;
import restaurante.model.Cocinero;

public class SACocineroImpl implements SACocinero {

	@Override
	public boolean aniadirCoci(Cocinero a) throws FileNotFoundException {
		DAOCoci x = AbstractFactoryDAO.getInstance().createDAOCoci();
		if(buscarCoci(a) == null){
			x.aniadirCoci(a);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarCoci(Cocinero a) throws FileNotFoundException {
		DAOCoci x = AbstractFactoryDAO.getInstance().createDAOCoci();
		if(buscarCoci(a) == null){
			x.modificarCoci(a);
			return true;
		}
		return false;
	}

	@Override
	public Cocinero buscarCoci(Cocinero a) throws FileNotFoundException {
		DAOCoci x = AbstractFactoryDAO.getInstance().createDAOCoci();
		Cocinero y = x.buscarCoci(a);
		return y;
	}

	@Override
	public boolean eliminarCoci(Cocinero a) throws FileNotFoundException {
		DAOCoci x = AbstractFactoryDAO.getInstance().createDAOCoci();
		if(buscarCoci(a) == null){
			x.eliminarCoci(a);
			return true;
		}
		return false;
	}
}
