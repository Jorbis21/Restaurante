package Restaurante.model;

import java.util.Date;

public class Empleado {
	private String Nombre;
	private int id;
	private double Salario;
	private Date FechaPago;
	public Empleado(String n, int _id, double s, Date f) {
		Nombre = n;
		id = _id;
		Salario =s;
		FechaPago = f;
	}
	public String getNombre(){
		return Nombre;
	}
	public int getid(){
		return id;
	}
	public double getSalario(){
		return Salario;
	}
	public Date getFechaPago(){
		return FechaPago;
	}
	public void setNombre(String n){
		Nombre = n;
	}
	public void setid(int _id){
		id = _id;
	}
	public void setSalario(double s){
		Salario = s;
	}
	public void setFechaPago(Date f){
		FechaPago = f;
	}
}
