/**
 * Clase del cliente 
 */
package Restaurante.model;

import org.json.JSONObject;

public class Cliente {
	//-----------------
	//Atributos
	//-----------------
	private String Nombre;
	private int Cuenta;
	private boolean MetodoPago;//true para tarjeta credito
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param n
	 * @param c
	 * @param m
	 */
	public Cliente(String n, int c, boolean m) {
		Nombre = n;
		Cuenta = c;
		MetodoPago = m;
	}
	/**
	 * Devuelve el nombre 
	 * @return
	 */
	public String getNombre(){
		return Nombre;
	}
	/**
	 * Devuelve la cuenta
	 * @return
	 */
	public int getCuenta(){
		return Cuenta;
	}
	/**
	 * Devuelve el metodo de pago
	 * @return
	 */
	public boolean getMetodoPago(){
		return MetodoPago;
	}
	/**
	 * Da valos al nombre
	 * @param n
	 */
	public void setNombre(String n){
		Nombre = n;
	}
	/**
	 * Da valor a la cuenta
	 * @param c
	 */
	public void setCuenta(int c){
		Cuenta = c;
	}
	/**
	 * Da valort al metodo de pago
	 * @param m
	 */
	public void setMetodoPago(boolean m){
		MetodoPago = m;
	}
	/**
	 * Crea un JSONObject con los datos de la clase
	 * @return
	 */
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Bill", Cuenta);
		if(MetodoPago) 
			data.put("Pay", "Tarjeta");
		else
			data.put("Pay", "Metalico");
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
