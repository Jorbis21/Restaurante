/**
 * Clase de la tabla cliente 
 */
package Restaurante.view.Cliente;

public class ClienteTable {
	//-----------------
	//Atributos
	//-----------------
	String Nombre, Cuenta, MetodoPago;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param n
	 * @param c
	 * @param m
	 */
	public ClienteTable(String n, String c, String m) {
		Nombre = n;
		Cuenta = c;
		MetodoPago = m;
	}
	/**
	 * Constructor vacio
	 */
	public ClienteTable() {
		Nombre = "";
		Cuenta = "";
		MetodoPago = "";
	}
	/**
	 * Da valos al nombre
	 * @param n
	 */
	public void setNombre(String n) {
		Nombre = n;
	}
	/**
	 * Da valor a la cuenta
	 * @param c
	 */
	public void setCuenta(String c) {
		Cuenta = c;
	}
	/**
	 * Da valort al metodo de pago
	 * @param m
	 */
	public void setMetodoPago(String m) {
		MetodoPago = m;
	}
	/**
	 * Devuelve el nombre 
	 * @return
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * Devuelve la cuenta
	 * @return
	 */
	public String getCuenta() {
		return Cuenta;
	}
	/**
	 * Devuelve el metodo de pago
	 * @return
	 */
	public String getMetodoPago() {
		return MetodoPago;
	}
}
