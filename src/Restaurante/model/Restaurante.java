package Restaurante.model;

import java.util.ArrayList;

public class Restaurante {
	private int NumMesas, NumEmpleados, AforoMax;
	private ArrayList<Cliente> ListCliente;
	private ArrayList<ComidaYBebida> ListCYB;
	private ArrayList<Empleado> ListEmpleado;
	private ArrayList<Almacen> ListAlmacen;
	public Restaurante(int m,int e, int af) {
		NumMesas = m;
		NumEmpleados = e;
		AforoMax = af;
		ListCliente = new ArrayList<Cliente>();
		ListCYB = new ArrayList<ComidaYBebida>();
		ListEmpleado = new ArrayList<Empleado>();
		ListAlmacen = new ArrayList<Almacen>();
	}
	public int getNumMesas() {
		return NumMesas;
	}
	public int getNumEmpleados() {
		return NumEmpleados;
	}
	public int getAforoMax() {
		return AforoMax;
	}
	public ArrayList<Cliente> getListCliente(){
		return ListCliente;
	}
	public ArrayList<ComidaYBebida> getListCYB(){
		return ListCYB;
	}
	public ArrayList<Empleado> getListEmpleado(){
		return ListEmpleado;
	}
	public ArrayList<Almacen> getListAlmacen(){
		return ListAlmacen;
	}
	public void setNumMesas(int m) {
		NumMesas = m;
	}
	public void setNumEmpleados(int e) {
		NumEmpleados = e;
	}
	public void setAforoMax(int af) {
		AforoMax = af;
	}
	public void setListCliente(ArrayList<Cliente> c) {
		ListCliente = c;
	}
	public void setListCYB(ArrayList<ComidaYBebida> c) {
		ListCYB = c;
	}
	public void setListEmpleado(ArrayList<Empleado> e) {
		ListEmpleado = e;
	}
	public void setListAlmacen(ArrayList<Almacen> a) {
		ListAlmacen = a;
	}
}

