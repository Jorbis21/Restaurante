package restaurante.sa.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cocinero;

public interface SACocinero {
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion añadir del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirCoci(Cocinero a) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion modificar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarCoci(Cocinero a, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion buscar del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	Cocinero buscarCoci(Cocinero a) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion lista del dao
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Cocinero> lista() throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion eliminar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarCoci(Cocinero a, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion buscar dni del dao
	 * @param dni
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean buscDni(String dni) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion buscar id del dao
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean buscId(int id) throws FileNotFoundException;
		
}
