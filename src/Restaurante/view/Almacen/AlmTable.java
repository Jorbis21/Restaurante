package Restaurante.view.Almacen;

public class AlmTable {
	private String Nombre, Tipo, Cantidad;
	public AlmTable(String n, String t, String c) {
		Nombre = n;
		Tipo = t;
		Cantidad = c;
	}
	public AlmTable() {
		Nombre = "";
		Tipo = "";
		Cantidad = "";
	}
	public String getNombre(){
		return Nombre;
	}
	public String getCantidad(){
		return Cantidad;
	}
	public String getTipo(){
		return Tipo;
	}
	public void setNombre(String n){
		Nombre = n;
	}
	public void setCantidad(String c){
		Cantidad = c;
	}
	public void setTipo(String t){
		Tipo = t;
	}
}
