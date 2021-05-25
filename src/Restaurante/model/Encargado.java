package Restaurante.model;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import Restaurante.control.Restaurante;

public class Encargado extends Empleado{
	private ArrayList<Empleado> PersonasACargo;
	Restaurante res;
	private int IdEncargado;
	public Encargado(String n, int _id, double s, String f, int idE, ArrayList<Empleado> l) {
		super(n, _id, s, f);
		IdEncargado = idE;
		PersonasACargo = l;
	}
	
	public ArrayList<Empleado> getLista(){
		return PersonasACargo;
	}
	public int getIdEncargado() {
		return IdEncargado;
	}
	public void setLista(ArrayList<Empleado> l) {
		PersonasACargo = l;
	}
	public void setIdEncargado(int idE) {
		IdEncargado = idE;
	}
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
