package Restaurante.view.ComidaYBebida;

public class CYBTable {
	String Nombre, Cantidad, Comida, Desc;
	
	public CYBTable(String n, String c, String _c, String d) {
		Nombre = n;
		Cantidad = c;
		Comida = _c;
		Desc = d;
	}
	public CYBTable() {
		Nombre = "";
		Cantidad = "";
		Comida = "";
		Desc = "";
	}
	public void setNombre(String n) {
		Nombre = n;
	}
	public void setCantidad(String c) {
		Cantidad = c;
	}
	public void setComida(String _c) {
		Comida = _c;
	}
	public void setDesc(String d) {
		Desc = d;
	}
	public String getNombre() {
		return Nombre;
	}
	public String getCantidad() {
		return Cantidad;
	}
	public String getComida() {
		return Comida;
	}
	public String getDesc() {
		return Desc;
	}
}
