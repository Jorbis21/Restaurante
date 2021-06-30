package restaurante.dao;

import restaurante.dao.almacen.DAOAlmacen;
import restaurante.dao.cliente.DAOCliente;
import restaurante.dao.cocinero.DAOCoci;
import restaurante.dao.cyb.DAOCyb;
import restaurante.dao.encargado.DAOEncargado;

public abstract class AbstractFactoryDAO {
	//------------------------------------
	//Atributos
	//------------------------------------
	private static AbstractFactoryDAO instance;
	/**
	 * Constructor
	 * @return
	 */
	public static AbstractFactoryDAO getInstance() {
		if(instance == null) {
			instance = new FactoryDAO();
		}
		return instance;
	}
	/**
	 * Crea la interfaz del DAO de Almacen
	 * @return
	 */
	public abstract DAOAlmacen createDAOAlm();
	/**
	 * Crea la interfaz del DAO de Cliente
	 * @return
	 */
	public abstract DAOCliente createDAOCli();
	/**
	 * Crea la interfaz del DAO de ComidaYBebida
	 * @return
	 */
	public abstract DAOCyb createDAOCyb();
	/**
	 * Crea la interfaz del DAO de Cocinero
	 * @return
	 */
	public abstract DAOCoci createDAOCoci();
	/**
	 * Crea la interfaz del DAO de Encargado
	 * @return
	 */
	public abstract DAOEncargado createDAOEnc();
}
