/**
 * Clase del almacen
 */
package restaurante.model;

import org.json.JSONObject;

public class Almacen {
	//-----------------------
	//Atributos
	//-----------------------
	private String Tipo;//verdura, carne, pescado
	private String Nombre;
	private int Cantidad;
	//-----------------------
	//Metodos
	//-----------------------
	/**
	 * Constructor
	 * @param n
	 * @param t
	 * @param c
	 */
	public Almacen(String n, String t, int c) {
		Nombre = n;
		Tipo = t;
		Cantidad = c;
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
	public int getCantidad(){
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
	public void setCantidad(int c){
		Cantidad = c;
	}
	/**
	 * Da valor al tipo
	 * @param t
	 */
	public void setTipo(String t){
		Tipo = t;
	}
	/**
	 * Crea un JSONObject con los valores de la clase
	 * @return
	 */
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Type", Tipo);
		data.put("Amount", Cantidad);
		return data;
	}
	public boolean equals(Almacen a) {
		if(Nombre.equals(a.getNombre()) && Tipo.equals(a.getTipo()) && Cantidad == a.getCantidad()) {
			return true;
		}
		return false;
		
	}
	public String toString() {
		return getData().toString();
	}
}
