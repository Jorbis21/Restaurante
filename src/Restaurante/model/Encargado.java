package Restaurante.model;

import java.util.ArrayList;
import java.util.Date;

public class Encargado extends Empleado{
	private ArrayList<Empleado> ListPersonasACargo;
	private int IdEncargado;
	public Encargado(String n, int _id, double s, Date f, int idE, ArrayList<Empleado> l) {
		super(n, _id, s, f);
		IdEncargado = idE;
		ListPersonasACargo = l;
	}
	public ArrayList<Empleado> getLista(){
		return ListPersonasACargo;
	}
	public int getIdEncargado() {
		return IdEncargado;
	}
	public void setLista(ArrayList<Empleado> l) {
		ListPersonasACargo = l;
	}
	public void setIdEncargado(int idE) {
		IdEncargado = idE;
	}
	
}
