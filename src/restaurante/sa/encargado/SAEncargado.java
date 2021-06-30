package restaurante.sa.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Empleado;

public interface SAEncargado {
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion añadir del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion modificar del dao
	 * @param a
	 * @param n
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarEnc(Empleado a,int n, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion buscar del dao
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion lista del dao
	 * @param status
	 * @param enc
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Empleado> lista(int status, int enc) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion eliminar del dao
	 * @param a
	 * @param n
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarEnc(Empleado a,int n, int x) throws FileNotFoundException;
	/**
	 * Genera una interfaz del dao cocinero y llama 
	 * a la funcion buscar encargado del dao
	 * @param dni
	 * @param ide
	 * @return
	 * @throws FileNotFoundException
	 */
	int buscEnc(String dni, int ide) throws FileNotFoundException;
	/**
	 *  Genera una interfaz del dao cocinero y llama 
	 * a la funcion buscar empleado del dao
	 * @param dni
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean buscEmp(String dni, int id) throws FileNotFoundException;
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
