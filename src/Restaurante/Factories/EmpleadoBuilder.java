package Restaurante.Factories;

import org.json.JSONObject;

import Restaurante.model.Empleado;

public class EmpleadoBuilder extends Builder<Empleado>{
	public EmpleadoBuilder() {
		super("Empleado", "Datos del Empleado");
	}

	@Override
	protected Empleado createTheInstance(JSONObject data) {
		String n = data.getString("Name");
		int i = data.getInt("Id");
		double s = data.getDouble("Salary");
		String f = data.getString("Date");
		return new Empleado(n,i,s,f);
	}
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("Name", "Nombre del empleado");
		data.put("Id", "Id del empleado");
		data.put("Salary", "Salario del empleado");
		data.put("Date","Fecha cobro del salario");
		return data;
	}
}
