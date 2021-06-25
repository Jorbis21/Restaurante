/**
 * Clase de la tabla Almacen
 */
package restaurante.view.almacen.control;

public class AlmTable {
	//--------------------------------
	//Atributos
	//--------------------------------
	private String Nombre, Tipo, Cantidad;
	//--------------------------------
	//Metodos
	//--------------------------------
	/**
	 * Constructor
	 * @param n
	 * @param t
	 * @param c
	 */
	public AlmTable(String n, String t, String c) {
		Nombre = n;
		Tipo = t;
		Cantidad = c;
	}
	/**
	 * Constructor vacio
	 */
	public AlmTable() {
		Nombre = "";
		Tipo = "";
		Cantidad = "";
	}
	/**
	 * Devuelve el nombre
	 * @return
	 */
	public String getNombre(){
		return Nombre;
	}
	/**
	 * Devuelve la cantidad
	 * @return
	 */
	public String getCantidad(){
		return Cantidad;
	}
	/**
	 * Devuelve el tipo de bibere
	 * @return
	 */
	public String getTipo(){
		return Tipo;
	}
	/**
	 * Da valor al nombre
	 * @param n
	 */
	public void setNombre(String n){
		Nombre = n;
	}
	/**
	 * Da valor a la cantidad
	 * @param c
	 */
	public void setCantidad(String c){
		Cantidad = c;
	}
	/**
	 * Da valor al tipo
	 * @param t
	 */
	public void setTipo(String t){
		Tipo = t;
	}
}
