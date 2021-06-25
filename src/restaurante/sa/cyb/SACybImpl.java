package restaurante.sa.cyb;

import java.io.FileNotFoundException;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.cyb.DAOCyb;
import restaurante.model.ComidaYBebida;

public class SACybImpl implements SACyb {

	@Override
	public boolean aniadirCyb(ComidaYBebida a) throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		if(buscarCyb(a) == null){
			x.aniadirCyb(a);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarCyb(ComidaYBebida a) throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		if(!(buscarCyb(a) == null)){
			x.modificarCyb(a);
			return true;
		}
		return false;
	}

	@Override
	public ComidaYBebida buscarCyb(ComidaYBebida a) throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		ComidaYBebida y;
		y = x.buscarCyb(a);
		return y;
	}

	@Override
	public boolean eliminarCyb(ComidaYBebida a) throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		if(buscarCyb(a) == null){
			x.eliminarCyb(a);
			return true;
		}
		return false;
	}

}
