package restaurante.dao.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Almacen;

public interface DAOAlmacen {
	/**
	 * Elimina el almacen que esta en el indice a
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarAlm(int a) throws FileNotFoundException;
	/**
	 * Añade el almacen que se pasa por parametro a la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirAlm(Almacen a) throws FileNotFoundException;
	/**
	 * Modifica el almacen del indice x por el almacen a
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarAlm(Almacen a,int x) throws FileNotFoundException;
	/**
	 * Devuelve la lista de almacenes para actualizar la tabla a traves de los observers
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Almacen> lista() throws FileNotFoundException;
	/**
	 * Busca el almacen a en la BBDD
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	Almacen buscarAlm(Almacen a) throws FileNotFoundException;
}
