package restaurante.control;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import restaurante.model.Almacen;
import restaurante.model.Cliente;
import restaurante.model.Cocinero;
import restaurante.model.ComidaYBebida;
import restaurante.model.Empleado;
import restaurante.model.Encargado;
import restaurante.model.ResObserver;
import restaurante.sa.AbstractFactorySA;

public class RestauranteCtrl {
	private List<ResObserver> ResObsL;
	private ArrayList<Cliente> ListCliente;
	private ArrayList<ComidaYBebida> ListCYB;
	private ArrayList<Cocinero> ListCocinero;
	private ArrayList<Encargado> ListEncargado;
	private ArrayList<Almacen> ListAlmacen;
	private ArrayList<Empleado> ListEmpleado;
	public RestauranteCtrl() throws FileNotFoundException {
		init();
	}
	private void init() throws FileNotFoundException {
		ResObsL = new ArrayList<ResObserver>();
		ListCliente = AbstractFactorySA.getInstance().createSACli().lista();
		ListCYB = AbstractFactorySA.getInstance().createSACyb().lista();
		ListCocinero = AbstractFactorySA.getInstance().createSACoci().lista();
		ListEncargado = AbstractFactorySA.getInstance().createSAEnc().lista();
		ListAlmacen = AbstractFactorySA.getInstance().createSAAlm().lista();
		ListEmpleado = new ArrayList<Empleado>();
		for(Encargado e: ListEncargado) {
			ListEmpleado.add(e);
			for(Empleado a: e.getLista()) {
				ListEmpleado.add(a);
			}
		}
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	
	public boolean eventoAlm(Almacen a, int e, int z) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSAAlm().aniadirAlm(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSAAlm().eliminarAlm(a,z);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSAAlm().modificarAlm(a,z);
		}
		break;
		}
		ListAlmacen = new ArrayList<Almacen>();
		ListAlmacen = AbstractFactorySA.getInstance().createSAAlm().lista();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
		return x;
	}
	public boolean eventoCoci(Cocinero a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACoci().aniadirCoci(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACoci().eliminarCoci(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACoci().modificarCoci(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoCli(Cliente a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACli().aniadirCli(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACli().eliminarCli(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACli().modificarCli(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoCyb(ComidaYBebida a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACyb().aniadirCyb(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACyb().eliminarCyb(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACyb().modificarCyb(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoEnc(Empleado a, int e, int n) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSAEnc().aniadirEnc(a, n);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSAEnc().eliminarEnc(a, n);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSAEnc().modificarEnc(a, n);
		}
		break;
		}
		return x;
	}
	/**
	 * Aniade un observador al resturante
	 * @param o
	 * @throws FileNotFoundException 
	 */
	public void addObserver(ResObserver o) {
		
		if (ResObsL.contains(o)) {
			throw new IllegalArgumentException();
		}
		ResObsL.add(o);
		o.onRegister(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
	}
}
