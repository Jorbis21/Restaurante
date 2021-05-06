package Restaurante.model;

public class ComidaYBebida {
	private String Nombre;
	private int Cantidad;
	private boolean Comida;//true para bebida
	public ComidaYBebida(String n, int c, boolean _c) {
		Nombre = n;
		Cantidad = c;
		Comida = _c;
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
	public void setNombre(String n){
		Nombre = n;
	}
	public void setCantidad(int c){
		Cantidad = c;
	}
	public void setComida(boolean _c){
		Comida = _c;
	}
}
