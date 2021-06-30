package restaurante.sa.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cliente;

public interface SACliente {
	/**
	 * Genera una interfaz del dao cliente y llama 
	 * a la funcion añadir del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirCli(Cliente a) throws FileNotFoundException;

	/**
	 * Genera una interfaz del dao cliente y llama 
	 * a la funcion modificar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarCli(Cliente a, int x) throws FileNotFoundException;

	/**
	 * Genera una interfaz del dao cliente y llama 
	 * a la funcion buscar del dao
	 * @param a
	 * @return
	 * @throws FileNotFoundException
	 */
	Cliente buscarCli(Cliente a) throws FileNotFoundException;

	/**
	 * Genera una interfaz del dao cliente y llama
	 * a la funcion lista del dao
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Cliente> lista() throws FileNotFoundException;

	/**
	 * Genera una interfaz del dao cliente y llama 
	 * a la funcion eliminar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarCli(Cliente a, int x) throws FileNotFoundException;
}
