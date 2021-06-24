/**
 * Constructor de empleado desde archivos JSON
 */
package Restaurante.Factories;

import org.json.JSONObject;

import Restaurante.model.Empleado;

public class EmpleadoBuilder extends Builder<Empleado>{
	//----------------------
	//Metodos
	//----------------------
	/**
	 * Constructor
	 */
	public EmpleadoBuilder() {
		super("Empleado", "Datos del Empleado");
	}
	/**
	 * Da valor al objeto
	 */
	@Override
	protected Empleado createTheInstance(JSONObject data) {
		String n = data.getString("Name");
		int i = data.getInt("Id");
		double s = data.getDouble("Salary");
		String f = data.getString("Date");
		String d = data.getString("Dni");
		return new Empleado(n,i,s,f,d);
	}
	/**
	 * Crea los datos
	 */
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("Name", "Nombre del empleado");
		data.put("Id", "Id del empleado");
		data.put("Salary", "Salario del empleado");
		data.put("Date","Fecha cobro del salario");
		return data;
	}
}
