package Restaurante.model;

import org.json.JSONObject;

public class ComidaYBebida {
	private String Nombre, Desc;
	private int Cantidad;
	private boolean Comida;//true para bebida
	public ComidaYBebida(String n, int c, boolean _c, String d) {
		Nombre = n;
		Cantidad = c;
		Comida = _c;
		Desc = d;
	}
	public String getNombre(){
		return Nombre;
	}
	public int getCantidad(){
		return Cantidad;
	}
	public boolean getComida(){
		return Comida;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String d) {
		Desc = d;
	}
	public void setNombre(String n){
		Nombre = n;
	}
	public void setCantidad(int c){
		Cantidad = c;
	}
	public void setComida(boolean _c){
		Comida = _c;
	}
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Amount", Cantidad);
		if(Comida)
			data.put("Food", "Bebida");
		else
			data.put("Food", "Comida");
		data.put("Desc", Desc);
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
