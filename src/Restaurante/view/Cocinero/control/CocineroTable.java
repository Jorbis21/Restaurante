/**
 * Clase de la tabla Cocinero
 */
package Restaurante.view.Cocinero.control;

import Restaurante.view.Empleado.control.EmpleadosTable;

public class CocineroTable extends EmpleadosTable{
	//----------------------
	//Atributos
	//----------------------
	private String Tipo, Especialidad;
	//----------------------
	//Metodos
	//----------------------
	/**
	 * Constructor
	 * @param n
	 * @param _id
	 * @param s
	 * @param f
	 * @param t
	 * @param e
	 */
	public CocineroTable(String n, String i, String s, String f, String t, String e, String d) {
		super(n, i, s, f,d);
		Tipo = t;
		Especialidad = e;
	}
	/**
	 * Constructor vacio
	 */
	public CocineroTable() {
		super();
		Tipo = "";
		Especialidad = "";
	}
	/**
	 * Devuelve el tipo
	 * @return
	 */
	public String getTipo() {
		return Tipo;
	}
	/**
	 * Devuelve la especialidad
	 * @return
	 */
	public String getEspecialidad() {
		return Especialidad;
	}
	/**
	 * Da valor al tipo
	 * @param t
	 */
	public void setTipo(String t) {
		Tipo = t;
	}
	/**
	 * Da valor a la especialidad
	 * @param e
	 */
	public void setEspecialidad(String e) {
		Especialidad = e;
	}

}
