/**
 * Clase de la tabla Empleado
 */
package Restaurante.view.Empleado;

public class EmpleadosTable {
	//-----------------------------------
	//Atributos
	//-----------------------------------
	String Nombre, id, Salario,FechaPago;
	//-----------------------------------
	//Metodos
	//-----------------------------------
	/**
	 * Constructor
	 * @param n
	 * @param _id
	 * @param s
	 * @param f
	 */
	public EmpleadosTable(String n, String i, String s, String f) {
		Nombre = n;
		id = i;
		Salario = s;
		FechaPago = f;
	}
	/**
	 * Constructor vacio
	 */
	public EmpleadosTable() {
		Nombre = "";
		id = "";
		Salario = "";
		FechaPago = "";
	}
	/**
	 * Da valor al nombre
	 * @param n
	 */
	public void setNombre(String n) {
		Nombre = n;
	}
	/**
	 * Da valor al id
	 * @param _id
	 */
	public void setId(String i) {
		id = i;
	}
	/**
	 * Da valor al salario
	 * @param s
	 */
	public void setSalario(String s) {
		Salario = s;
	}
	/**
	 * Da valor a la fecha de pago
	 * @param f
	 */
	public void setFechaPago(String f) {
		FechaPago = f;
	}
	/**
	 * Devuelve el nombre
	 * @return
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Devuelve el id
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * Devuelve el salario
	 * @return
	 */
	public String getSalario() {
		return Salario;
	}
	/**
	 * Devulve la fecha de pago
	 * @return
	 */
	public String getFechaPago() {
		return FechaPago;
	}
}
