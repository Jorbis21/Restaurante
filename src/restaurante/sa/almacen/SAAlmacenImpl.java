package restaurante.sa.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.almacen.DAOAlmacen;
import restaurante.model.Almacen;

public class SAAlmacenImpl implements SAAlmacen {
	@Override
	public boolean eliminarAlm(Almacen a, int x) throws FileNotFoundException {
		DAOAlmacen b = AbstractFactoryDAO.getInstance().createDAOAlm();
		if(buscarAlm(a) == null) {
			b.eliminarAlm(x);
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
	public boolean modificarAlm(Almacen a, int x) throws FileNotFoundException {
		DAOAlmacen b = AbstractFactoryDAO.getInstance().createDAOAlm();
		b.modificarAlm(a,x);
		return true;
		
	}

	@Override
	public Almacen buscarAlm(Almacen a) throws FileNotFoundException {
		DAOAlmacen x = AbstractFactoryDAO.getInstance().createDAOAlm();
		Almacen y;
		y = x.buscarAlm(a);
		return y;		
	}

	@Override
	public ArrayList<Almacen> lista() throws FileNotFoundException {
		DAOAlmacen x = AbstractFactoryDAO.getInstance().createDAOAlm();
		return x.lista();
	}
}
