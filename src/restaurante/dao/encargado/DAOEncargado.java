package restaurante.dao.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Empleado;

public interface DAOEncargado {
	/**
	 * Añade el empleado que se pasa por parametro a la BBDD
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException;
	/**
	 * Modifica el empleado del indice x por el almacen a
	 * @param a
	 * @param n
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean modificarEnc(Empleado a,int n ,int x) throws FileNotFoundException;
	/**
	 * Busca el Empleado a en la BBDD
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException;
	/**
	 * Devuelve la lista de empleados para actualizar la tabla a traves de los observers
	 * @param status
	 * @param enc
	 * @return
	 * @throws FileNotFoundException
	 */
	ArrayList<Empleado> lista(int status, int enc) throws FileNotFoundException;
	/**
	 * Elimina el empleado que esta en el indice a
	 * @param a
	 * @param x
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean eliminarEnc(int a, int x) throws FileNotFoundException;
	/**
	 * Busca el empleado para iniciar sesion
	 * @param dni
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean buscEmp(String dni, int id) throws FileNotFoundException;
	/**
	 * Busca el encargado para iniciar sesion
	 * @param dni
	 * @param ide
	 * @return
	 * @throws FileNotFoundException
	 */
	int buscEnc(String dni, int ide) throws FileNotFoundException;
	/**
	 * Busca si hay algun empleado con el mismo dni
	 * @param dni
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean buscDni(String dni) throws FileNotFoundException;
	/**
	 * Busca si hay algun empleado con el mismo id
	 * @param id
	 * @return
	 * @throws FileNotFoundException
	 */
	boolean buscId(int id) throws FileNotFoundException;
}
