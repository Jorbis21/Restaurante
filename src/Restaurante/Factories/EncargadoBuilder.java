package Restaurante.Factories;

import java.util.ArrayList;

import org.json.JSONObject;

import Restaurante.model.Empleado;
import Restaurante.model.Encargado;

public class EncargadoBuilder extends Builder<Encargado>{
	private Factory<Empleado> liste ;
	public EncargadoBuilder() {
		super("Encargado", "Datos del Encargado");
		ArrayList<Builder<Empleado>> EmplBuilder = new ArrayList<>();
		EmplBuilder.add(new EmpleadoBuilder());
		liste = new BuilderBasedFactory<Empleado>(EmplBuilder);
	}

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
		return new Encargado(n,i,s,f,ie,x);
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
