package Restaurante.model;

import org.json.JSONObject;

public class Cliente {
	private String Nombre;
	private int Cuenta;
	private boolean MetodoPago;//true para tarjeta credito
	public Cliente(String n, int c, boolean m) {
		Nombre = n;
		Cuenta = c;
		MetodoPago = m;
	}
	public String getNombre(){
		return Nombre;
	}
	public int getCuenta(){
		return Cuenta;
	}
	public boolean getMetodoPago(){
		return MetodoPago;
	}
	public void setNombre(String n){
		Nombre = n;
	}
	public void setCuenta(int c){
		Cuenta = c;
	}
	public void setMetodoPago(boolean m){
		MetodoPago = m;
	}
	
	public JSONObject getData() {
		JSONObject data = new JSONObject();
		data.put("Name", Nombre);
		data.put("Bill", Cuenta);
		if(MetodoPago) 
			data.put("Pay", "Tarjeta");
		else
			data.put("Pay", "Metalico");
		return data;
	}
	public String toString() {
		return getData().toString();
	}
}
