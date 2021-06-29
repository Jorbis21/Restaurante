package restaurante.sa.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
	public boolean modificarCoci(Cocinero a, int x) throws FileNotFoundException {
		DAOCoci z = AbstractFactoryDAO.getInstance().createDAOCoci();
		z.modificarCoci(a, x);
		return true;
	}

	@Override
	public Cocinero buscarCoci(Cocinero a) throws FileNotFoundException {
		DAOCoci x = AbstractFactoryDAO.getInstance().createDAOCoci();
		Cocinero y = x.buscarCoci(a);
		return y;
	}

	@Override
	public boolean eliminarCoci(Cocinero a, int x) throws FileNotFoundException {
		DAOCoci z = AbstractFactoryDAO.getInstance().createDAOCoci();
		if(buscarCoci(a) == null){
			z.eliminarCoci(x);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Cocinero> lista() throws FileNotFoundException {
		DAOCoci x = AbstractFactoryDAO.getInstance().createDAOCoci();
		return x.lista();
	}
}
