/**
 * Clase de la tabla Empleado
 */
package restaurante.view.empleado;

public class EmpleadosTable {
	//-----------------------------------
	//Atributos
	//-----------------------------------
	protected String Nombre;
	protected String id;
	protected String Salario;
	protected String FechaPago;
	protected String dni;
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
	public EmpleadosTable(String n, String i, String s, String f,String d) {
		Nombre = n;
		id = i;
		Salario = s;
		FechaPago = f;
		dni = d;
	}
	/**
	 * Constructor vacio
	 */
	public EmpleadosTable() {
		Nombre = "";
		id = "";
		Salario = "";
		FechaPago = "";
		dni = "";
	}
	/**
	 * Da valor al nombre
	 * @param n
	 */
	public void setNombre(String n) {
		Nombre = n;
	}
	public void setDni(String d) {
		dni = d;
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
	public String getDni() {
		return dni;
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
