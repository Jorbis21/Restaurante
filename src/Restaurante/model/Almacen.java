package Restaurante.model;

public class Almacen {
	private String Tipo;//verdura, carne, pescado
	private String Nombre;
	private int Cantidad;
	public Almacen(String n, String t, int c) {
		Nombre = n;
		Tipo = t;
		Cantidad = c;
	}
	public String getNombre(){
		return Nombre;
	}
	public int getCantidad(){
		return Cantidad;
	}
	public String getTipo(){
		return Tipo;
	}
	public void setNombre(String n){
		Nombre = n;
	}
	public void setCantidad(int c){
		Cantidad = c;
	}
	public void setTipo(String t){
		Tipo = t;
	}
}
