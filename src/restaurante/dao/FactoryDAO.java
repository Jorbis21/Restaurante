package restaurante.dao;

import restaurante.dao.almacen.DAOAlmacen;
import restaurante.dao.almacen.DAOAlmacenImpl;
import restaurante.dao.cliente.DAOCliente;
import restaurante.dao.cliente.DAOClienteImpl;
import restaurante.dao.cocinero.DAOCoci;
import restaurante.dao.cocinero.DAOCociImpl;
import restaurante.dao.cyb.DAOCyb;
import restaurante.dao.cyb.DAOCybImpl;
import restaurante.dao.encargado.DAOEncargado;
import restaurante.dao.encargado.DAOEncargadoImpl;

public class FactoryDAO extends AbstractFactoryDAO {
	public DAOAlmacen createDAOAlm() {
		return new DAOAlmacenImpl();
	}
	public DAOCliente createDAOCli() {
		return new DAOClienteImpl();
	}
	@Override
	public DAOCyb createDAOCyb() {
		return new DAOCybImpl();
	}
	@Override
	public DAOCoci createDAOCoci() {
		return new DAOCociImpl();
	}
	@Override
	public DAOEncargado createDAOEnc() {
		return new DAOEncargadoImpl();
	}

}
