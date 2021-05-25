package Restaurante.view.Empleado;

public class EmpleadosTable {
	String Nombre, id, Salario,FechaPago;
	
	public EmpleadosTable(String n, String i, String s, String f) {
		Nombre = n;
		id = i;
		Salario = s;
		FechaPago = f;
	}
	public EmpleadosTable() {
		Nombre = "";
		id = "";
		Salario = "";
		FechaPago = "";
	}
	public void setNombre(String n) {
		Nombre = n;
	}
	public void setId(String i) {
		id = i;
	}
	public void setSalario(String s) {
		Salario = s;
	}
	public void setFechaPago(String f) {
		FechaPago = f;
	}
	public String getNombre() {
		return Nombre;
	}
	public String getId() {
		return id;
	}
	public String getSalario() {
		return Salario;
	}
	public String getFechaPago() {
		return FechaPago;
	}
}
