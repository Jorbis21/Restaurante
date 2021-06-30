package restaurante.sa.cyb;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.ComidaYBebida;

public interface SACyb {
	/**
	 * Genera una interfaz del dao comidaybebida y llama 
	 * a la funcion añadir del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirCyb(ComidaYBebida a) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao comidaybebida y llama 
	 * a la funcion modificar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarCyb(ComidaYBebida a, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao comidaybebida y llama 
	 * a la funcion buscar del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	ComidaYBebida buscarCyb(ComidaYBebida a) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao comidaybebida y llama 
	 * a la funcion lista del dao
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<ComidaYBebida> lista() throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao comidaybebida y llama 
	 * a la funcion eliminar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarCyb(ComidaYBebida a, int x) throws FileNotFoundException;
}
