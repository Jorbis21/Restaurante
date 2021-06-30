package restaurante.dao.cyb;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import restaurante.model.ComidaYBebida;

public interface DAOCyb {
	/**
	 * Añade el ComidaYBebida que se pasa por parametro a la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirCyb(ComidaYBebida a) throws FileNotFoundException;
	/**
	 * Modifica el ComidaYBebida del indice x por el ComidaYBebida a
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarCyb(ComidaYBebida a, int x) throws FileNotFoundException;
	/**
	 * Busca el ComidaYBebida a en la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	ComidaYBebida buscarCyb(ComidaYBebida a) throws FileNotFoundException;
	/**
	 * Devuelve la lista de ComidaYBebida para actualizar la tabla a traves de los observers
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<ComidaYBebida> lista() throws FileNotFoundException;
	/**
	 * Elimina el ComidaYBebida que esta en el indice a
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarCyb(int a) throws FileNotFoundException;
}
