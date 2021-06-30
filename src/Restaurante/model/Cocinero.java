/**
 * Clase del Cocinero
 */
package restaurante.model;

import org.json.JSONObject;

public class Cocinero extends Empleado{
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
	public Cocinero(String n, int _id, double s, String f,String t, String e, String d) {
		super(n, _id, s, f,d);
		Tipo = t;
		Especialidad = e;
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
	/**
	 * Crea un JSONObject con los datos de la clase
	 */
	public JSONObject getData() {
		JSONObject data = super.getData();
		data.put("Type", Tipo);
		data.put("Specialty", Especialidad);
		return data;
	}
	/**
	 * Objeto a String
	 */
	public String toString() {
		return getData().toString();
	}
	/**
	 * Conmprueba si son iguales
	 * @param a
	 * @return
	 */
	public boolean equals(Cocinero a) {
		if(super.equals(a) && Especialidad.equals(a.getEspecialidad()) && Tipo.equals(a.getTipo())) {
			return true;
		}
		return false;
		
	}
}
