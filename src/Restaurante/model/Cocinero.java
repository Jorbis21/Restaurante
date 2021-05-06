package Restaurante.model;


import java.util.Date;

public class Cocinero extends Empleado{
	private String Tipo, Especialidad;
	public Cocinero(String n, int _id, double s, Date f,String t, String e) {
		super(n, _id, s, f);
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
