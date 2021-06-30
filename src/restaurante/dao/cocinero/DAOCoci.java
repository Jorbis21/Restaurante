package restaurante.dao.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cocinero;

public interface DAOCoci {
	/**
	 * Añade el cocinero que se pasa por parametro a la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirCoci(Cocinero a) throws FileNotFoundException;
	/**
	 * Modifica el cocinero del indice x por el almacen a
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarCoci(Cocinero a, int x) throws FileNotFoundException;
	/**
	 * Busca el cocinero a en la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	Cocinero buscarCoci(Cocinero a) throws FileNotFoundException;
	/**
	 * Devuelve la lista de cocineros para actualizar la tabla a traves de los observers
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Cocinero> lista() throws FileNotFoundException;
	/**
	 * Elimina el cocinero que esta en el indice a
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarCoci(int a) throws FileNotFoundException;
	/**
	 * Busca si hay algun cocinero con el mismo dni
	 * @param dni
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean buscDni(String dni) throws FileNotFoundException;
	/**
	 * Busca si hay algun cocinero con el mismo id
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean buscId(int id) throws FileNotFoundException;
}
