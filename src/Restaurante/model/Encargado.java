package Restaurante.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Encargado extends Empleado{
	private ArrayList<Empleado> ListPersonasACargo;
	private int IdEncargado;
	public Encargado(String n, int _id, double s, String f, int idE, ArrayList<Empleado> l) {
		super(n, _id, s, f);
		IdEncargado = idE;
		ListPersonasACargo = l;
	}
	public ArrayList<Empleado> getLista(){
		return ListPersonasACargo;
	}
	public int getIdEncargado() {
		return IdEncargado;
	}
	public void setLista(ArrayList<Empleado> l) {
		ListPersonasACargo = l;
	}
	public void setIdEncargado(int idE) {
		IdEncargado = idE;
	}
	public JSONArray EJSONArray() {
		JSONArray x = new JSONArray();
		for(Empleado e: ListPersonasACargo) {
			x.put(e);
		}
		return x;
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
