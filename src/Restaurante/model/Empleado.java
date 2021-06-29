/**
 * Clase de Empleado
 */
package restaurante.model;

import org.json.JSONObject;

public class Empleado {
	//-----------------------------------
	//Atributos
	//-----------------------------------
	private String Nombre;
	private int id;
	private double Salario;
	private String FechaPago;
	private String dni;
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
	public Empleado(String n, int _id, double s, String f, String d) {
		Nombre = n;
		id = _id;
		Salario =s;
		FechaPago = f;
		dni = d;
	}
	/**
	 * Devuelve el nombre
	 * @return
	 */
	public String getNombre(){
		return Nombre;
	}
	/**
	 * Devuelve el id
	 * @return
	 */
	public int getid(){
		return id;
	}
	/**
	 * Devuelve el salario
	 * @return
	 */
	public double getSalario(){
		return Salario;
	}
	/**
	 * Devulve la fecha de pago
	 * @return
	 */
	public String getFechaPago(){
		return FechaPago;
	}
	public String getDni(){
		return dni;
	}
	/**
	 * Da valor al nombre
	 * @param n
	 */
	public void setNombre(String n){
		Nombre = n;
	}
	/**
	 * Da valor al id
	 * @param _id
	 */
	public void setid(int _id){
		id = _id;
	}
	/**
	 * Da valor al salario
	 * @param s
	 */
	public void setSalario(double s){
		Salario = s;
	}
	/**
	 * Da valor a la fecha de pago
	 * @param f
	 */
	public void setFechaPago(String f){
		FechaPago = f;
	}
	public void setDni(String d){
		dni = d;
	}
	/**
	 * Crea un JSONObject con los datos de la clase
	 * @return
	 */
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Id", id);
		data.put("Salary", Salario);
		data.put("Date", FechaPago);
		data.put("Dni", dni);
		return data;
	}
	public boolean equals(Empleado a) {
		if(Nombre.equals(a.getNombre()) && id == a.getid() && Salario == a.getSalario()&& FechaPago.equals(a.getFechaPago()) && dni.equals(a.getDni())) {
			return true;
		}
		return false;
		
	}
	public String toString() {
		return getData().toString();
	}
	
}
