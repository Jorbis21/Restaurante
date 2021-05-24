package Restaurante.model;


import org.json.JSONObject;

public class Cocinero extends Empleado{
	private String Tipo, Especialidad;
	public Cocinero(String n, int _id, double s, String f,String t, String e) {
		super(n, _id, s, f);
		Tipo = t;
		Especialidad = e;
	}
	public String getTipo() {
		return Tipo;
	}
	public String getEspecialidad() {
		return Especialidad;
	}
	public void setTipo(String t) {
		Tipo = t;
	}
	public void setEspecialidad(String e) {
		Especialidad = e;
	}
	public JSONObject getData() {
		JSONObject data = super.getData();
		data.put("Type", Tipo);
		data.put("Specialty", Especialidad);
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
