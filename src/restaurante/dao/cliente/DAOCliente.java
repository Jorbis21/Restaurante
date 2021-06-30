package restaurante.dao.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cliente;

public interface DAOCliente {
	/**
	 * Añade el cliente que se pasa por parametro a la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirCli(Cliente a) throws FileNotFoundException;
	/**
	 * Modifica el cliente del indice x por el cliente a
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarCli(Cliente a, int x) throws FileNotFoundException;
	/**
	 * Busca el cliente a en la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	Cliente buscarCli(Cliente a) throws FileNotFoundException;
	/**
	 * Devuelve la lista de clientes para actualizar la tabla a traves de los observers
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Cliente> lista() throws FileNotFoundException;
	/**
	 * Elimina el cliente que esta en el indice a
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarCli(int a) throws FileNotFoundException;
}
