/**
 * Constructor de encargado desde archivos JSON
 */
package restaurante.factories;

import java.util.ArrayList;

import org.json.JSONObject;

import restaurante.model.Empleado;
import restaurante.model.Encargado;

public class EncargadoBuilder extends Builder<Encargado>{
	//----------------------
	//Atributod
	//----------------------
	private Factory<Empleado> liste ;
	//----------------------
	//Metodos
	//----------------------
	/**
	 * Constructor
	 */
	public EncargadoBuilder() {
		super("Encargado", "Datos del Encargado");
		ArrayList<Builder<Empleado>> EmplBuilder = new ArrayList<>();
		EmplBuilder.add(new EmpleadoBuilder());
		liste = new BuilderBasedFactory<Empleado>(EmplBuilder);
	}
	/**
	 * Da valor al objeto
	 */
	@Override
	protected Encargado createTheInstance(JSONObject data) {
		String n = data.getString("Name");
		int i = data.getInt("Id");
		double s = data.getDouble("Salary");
		String f = data.getString("Date");
		int ie = data.getInt("IdE");
		ArrayList<Empleado> x = new ArrayList<Empleado>();
		for(Object e: data.getJSONArray("ListE")) {
			x.add(liste.createInstance((JSONObject) e));
		}
		String d = data.getString("Dni");
		return new Encargado(n,i,s,f,ie,x,d);
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
