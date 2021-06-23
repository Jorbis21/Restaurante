/*
 * Clase de la tabla ComidaYBebida
 */
package Restaurante.view.ComidaYBebida.control;

public class CYBTable {
	//-------------------------------
	//Atributos
	//-------------------------------
	String Nombre, Cantidad, Comida, Desc;
	//-------------------------------
	//Metodos
	//-------------------------------
	/**
	 * Constructor
	 * @param n
	 * @param c
	 * @param _c
	 * @param d
	 */
	public CYBTable(String n, String c, String _c, String d) {
		Nombre = n;
		Cantidad = c;
		Comida = _c;
		Desc = d;
	}
	/**
	 * Constructor vacio
	 */
	public CYBTable() {
		Nombre = "";
		Cantidad = "";
		Comida = "";
		Desc = "";
	}
	/**
	 * Da valor al nombre
	 * @param n
	 */
	public void setNombre(String n) {
		Nombre = n;
	}
	/**
	 * Da valor a la cantidad
	 * @param c
	 */
	public void setCantidad(String c) {
		Cantidad = c;
	}
	/**
	 * Da valor a la comida
	 * @param _c
	 */
	public void setComida(String _c) {
		Comida = _c;
	}
	/**
	 * Da valor a la descripcion
	 * @param d
	 */
	public void setDesc(String d) {
		Desc = d;
	}
	/**
	 * Devuelve el nombre
	 * @return
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Devuelve la cantidad
	 * @return
	 */
	public String getCantidad() {
		return Cantidad;
	}
	/**
	 * Deuelve la comida
	 * @return
	 */
	public String getComida() {
		return Comida;
	}
	/**
	 * Devuelve la descripcion 
	 * @return
	 */
	public String getDesc() {
		return Desc;
	}
}
