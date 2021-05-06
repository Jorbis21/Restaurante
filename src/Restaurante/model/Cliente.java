package Restaurante.model;

public class Cliente {
	private String Nombre;
	private int Cuenta;
	private boolean MetodoPago;//true para tarjeta credito
	public Cliente(String n, int c) {
		Nombre = n;
		Cuenta = c;
		MetodoPago = false;
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
}
