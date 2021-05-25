/*
 * Clase de ComidaYBebida
 */
package Restaurante.model;

import org.json.JSONObject;

public class ComidaYBebida {
	//-------------------------------
	//Atributos
	//-------------------------------
	private String Nombre, Desc;
	private int Cantidad;
	private boolean Comida;//true para bebida
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
	public ComidaYBebida(String n, int c, boolean _c, String d) {
		Nombre = n;
		Cantidad = c;
		Comida = _c;
		Desc = d;
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
	 * Deuelve la comida
	 * @return
	 */
	public boolean getComida(){
		return Comida;
	}
	/**
	 * Devuelve la descripcion 
	 * @return
	 */
	public String getDesc() {
		return Desc;
	}
	/**
	 * Da valor a la descripcion
	 * @param d
	 */
	public void setDesc(String d) {
		Desc = d;
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
	 * Da valor a la comida
	 * @param _c
	 */
	public void setComida(boolean _c){
		Comida = _c;
	}
	/**
	 * Crea un JSONObject con los datos de la clase
	 * @return
	 */
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Amount", Cantidad);
		if(Comida)
			data.put("Food", "Bebida");
		else
			data.put("Food", "Comida");
		data.put("Desc", Desc);
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
