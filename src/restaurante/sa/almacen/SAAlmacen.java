package restaurante.sa.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Almacen;

public interface SAAlmacen {
	/**
	 * Genera una interfaz del dao almacen y llama 
	 * a la funcion añadir del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirAlm(Almacen a) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao almacen y llama 
	 * a la funcion modificar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarAlm(Almacen a, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao almacen y llama 
	 * a la funcion buscar del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	Almacen buscarAlm(Almacen a) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao almacen y llama 
	 * a la funcion lista del dao
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Almacen> lista() throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao almacen y llama 
	 * a la funcion eliminar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarAlm(Almacen a,int x) throws FileNotFoundException;
}
