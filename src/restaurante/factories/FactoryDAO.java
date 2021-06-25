package restaurante.factories;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.almacen.DAOAlmacen;
import restaurante.dao.almacen.DAOAlmacenImpl;

public class FactoryDAO extends AbstractFactoryDAO {
	public DAOAlmacen createDAOAlm() {
		return new DAOAlmacenImpl();
	}

}
