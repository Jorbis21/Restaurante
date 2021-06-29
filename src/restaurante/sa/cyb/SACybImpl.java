package restaurante.sa.cyb;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.cyb.DAOCyb;
import restaurante.model.ComidaYBebida;

public class SACybImpl implements SACyb {

	@Override
	public boolean aniadirCyb(ComidaYBebida a) throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		return x.aniadirCyb(a);
	}

	@Override
	public boolean modificarCyb(ComidaYBebida a, int x) throws FileNotFoundException {
		DAOCyb z = AbstractFactoryDAO.getInstance().createDAOCyb();
		z.modificarCyb(a,x);
		return true;
	}

	@Override
	public ComidaYBebida buscarCyb(ComidaYBebida a) throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		ComidaYBebida y;
		y = x.buscarCyb(a);
		return y;
	}

	@Override
	public boolean eliminarCyb(ComidaYBebida a, int x) throws FileNotFoundException {
		DAOCyb z = AbstractFactoryDAO.getInstance().createDAOCyb();
		if(buscarCyb(a) != null){
			z.eliminarCyb(x);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<ComidaYBebida> lista() throws FileNotFoundException {
		DAOCyb x = AbstractFactoryDAO.getInstance().createDAOCyb();
		return x.lista();
	}

}
