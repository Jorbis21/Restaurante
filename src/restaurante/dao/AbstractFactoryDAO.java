package restaurante.dao;

import restaurante.dao.almacen.DAOAlmacen;
import restaurante.factories.FactoryDAO;

public abstract class AbstractFactoryDAO {
	private static AbstractFactoryDAO instance;
	public static AbstractFactoryDAO getInstance() {
		if(instance == null) {
			instance = new FactoryDAO();
		}
		return instance;
	}
	public abstract DAOAlmacen createDAOAlm();
		
}
