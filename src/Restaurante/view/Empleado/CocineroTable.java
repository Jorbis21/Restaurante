package Restaurante.view.Empleado;

public class CocineroTable extends EmpleadosTable{
	private String Tipo, Especialidad;
	public CocineroTable(String n, String i, String s, String f, String t, String e) {
		super(n, i, s, f);
		Tipo = t;
		Especialidad = e;
	}
	public String getTipo() {
		return Tipo;
	}
	public String getEspecialidad() {
		return Especialidad;
	}
	public void setTipo(String t) {
		Tipo = t;
	}
	public void setEspecialidad(String e) {
		Especialidad = e;
	}

}
