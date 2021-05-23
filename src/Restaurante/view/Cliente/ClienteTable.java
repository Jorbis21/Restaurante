package Restaurante.view.Cliente;

public class ClienteTable {
	String Nombre, Cuenta, MetodoPago;
	
	public ClienteTable(String n, String c, String m) {
		Nombre = n;
		Cuenta = c;
		MetodoPago = m;
	}
	public void setNombre(String n) {
		Nombre = n;
	}
	public void setCuenta(String c) {
		Cuenta = c;
	}
	public void setMetodoPago(String m) {
		MetodoPago = m;
	}
	public String getNombre() {
		return Nombre;
	}
	public String getCuenta() {
		return Cuenta;
	}
	public String getMetodoPago() {
		return MetodoPago;
	}
}
