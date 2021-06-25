package restaurante.sa.almacen;

import java.io.FileNotFoundException;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.almacen.DAOAlmacen;
import restaurante.model.Almacen;

public class SAAlmacenImpl implements SAAlmacen {
	@Override
	public boolean eliminarAlm(Almacen a) throws FileNotFoundException {
		DAOAlmacen x = AbstractFactoryDAO.getInstance().createDAOAlm();
		if(buscarAlm(a) == null) {
			x.eliminarAlm(a);
			return true;
		}
		return false;
	}

	@Override
	public boolean aniadirAlm(Almacen a) throws FileNotFoundException {
		DAOAlmacen x = AbstractFactoryDAO.getInstance().createDAOAlm();
		if(buscarAlm(a) == null) {
			x.aniadirAlm(a);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarAlm(Almacen a) throws FileNotFoundException {
		DAOAlmacen x = AbstractFactoryDAO.getInstance().createDAOAlm();
		if(!(buscarAlm(a) == null)) {
			x.modificarAlm(a);
			return true;
		}
		return false;
	}

	@Override
	public Almacen buscarAlm(Almacen a) throws FileNotFoundException {
		DAOAlmacen x = AbstractFactoryDAO.getInstance().createDAOAlm();
		Almacen y;
		y = x.buscarAlm(a);
		return y;		
	}

	

}
