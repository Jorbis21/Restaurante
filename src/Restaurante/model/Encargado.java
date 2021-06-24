/**
 * Clase del Encargado
 */
package Restaurante.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Restaurante.control.Restaurante;

public class Encargado extends Empleado{
	//------------------------
	//Atributos
	//------------------------
	private ArrayList<Empleado> PersonasACargo;
	Restaurante res;
	private int IdEncargado;
	//------------------------
	//Metodos
	//------------------------
	/**
	 * Constructor
	 * @param n
	 * @param _id
	 * @param s
	 * @param f
	 * @param idE
	 * @param l
	 */
	public Encargado(String n, int _id, double s, String f, int idE, ArrayList<Empleado> l, String d) {
		super(n, _id, s, f, d);
		IdEncargado = idE;
		PersonasACargo = l;
	}
	/**
	 * Devuelve la lista de empleados
	 * @return
	 */
	public ArrayList<Empleado> getLista(){
		return PersonasACargo;
	}
	/**
	 * Devuelve el Id del encargado
	 * @return
	 */
	public int getIdEncargado() {
		return IdEncargado;
	}
	/**
	 * Da valor a la lista
	 * @param l
	 */
	public void setLista(ArrayList<Empleado> l) {
		PersonasACargo = l;
	}
	/**
	 * Da valor al id
	 * @param idE
	 */
	public void setIdEncargado(int idE) {
		IdEncargado = idE;
	}
	/**
	 * Crea un JSONObject con los datos de la clase
	 * @return
	 */
	public JSONArray EJSONArray() {
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for(Empleado b : PersonasACargo) {
			x.put("type", "Empleado");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		return array;	
		
	}
	/**
	 * Crea un JSONObject con los datos de la clase
	 * @return
	 */
	public JSONObject getData() {
		JSONObject data = super.getData();
		data.put("IdE", IdEncargado);
		data.put("ListE", EJSONArray());
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
