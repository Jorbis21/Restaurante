package Restaurante.model;

import org.json.JSONObject;

public class Empleado {
	private String Nombre;
	private int id;
	private double Salario;
	private String FechaPago;
	public Empleado(String n, int _id, double s, String f) {
		Nombre = n;
		id = _id;
		Salario =s;
		FechaPago = f;
	}
	public String getNombre(){
		return Nombre;
	}
	public int getid(){
		return id;
	}
	public double getSalario(){
		return Salario;
	}
	public String getFechaPago(){
		return FechaPago;
	}
	public void setNombre(String n){
		Nombre = n;
	}
	public void setid(int _id){
		id = _id;
	}
	public void setSalario(double s){
		Salario = s;
	}
	public void setFechaPago(String f){
		FechaPago = f;
	}
	
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Id", id);
		data.put("Salary", Salario);
		data.put("Date", FechaPago);
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
