/**
 * Constructor de cocinero desde archivos JSON
 */
package restaurante.factories;

import org.json.JSONObject;

import restaurante.model.Cocinero;


public class CocineroBuilder extends Builder<Cocinero>{
	//----------------------
	//Metodos
	//----------------------
	/**
	 * Constructor
	 */
	public CocineroBuilder() {
		super("Cocinero", "Datos del Cocinero");
	}
	/**
	 * Da valor al objeto
	 */
	@Override
	protected Cocinero createTheInstance(JSONObject data) {
		String n = data.getString("Name");
		int i = data.getInt("Id");
		double s = data.getDouble("Salary");
		String f = data.getString("Date");
		String t = data.getString("Type");
		String e = data.getString("Specialty");
		String d = data.getString("Dni");
		return new Cocinero(n,i,s,f,t,e,d);
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
		data.put("Type", "Tipo de cocinero");
		data.put("Specialty", "Especialidad del cocinero");
		return data;
	}
}
