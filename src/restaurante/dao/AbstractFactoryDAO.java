package restaurante.dao;

import restaurante.dao.almacen.DAOAlmacen;
import restaurante.dao.cliente.DAOCliente;
import restaurante.dao.cocinero.DAOCoci;
import restaurante.dao.cyb.DAOCyb;
import restaurante.dao.encargado.DAOEncargado;

public abstract class AbstractFactoryDAO {
	private static AbstractFactoryDAO instance;
	public static AbstractFactoryDAO getInstance() {
		if(instance == null) {
			instance = new FactoryDAO();
		}
		return instance;
	}
	public abstract DAOAlmacen createDAOAlm();
	public abstract DAOCliente createDAOCli();
	public abstract DAOCyb createDAOCyb();
	public abstract DAOCoci createDAOCoci();
	public abstract DAOEncargado createDAOEnc();
}
