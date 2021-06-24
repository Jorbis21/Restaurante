/**
 * Clase restaurante en esta clase se inicializan todas las listas y se gestionan
 *esta clase es equivalente al controller
 */
package Restaurante.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import Restaurante.Factories.AlmacenBuilder;
import Restaurante.Factories.Builder;
import Restaurante.Factories.BuilderBasedFactory;
import Restaurante.Factories.CYBBuilder;
import Restaurante.Factories.ClienteBuilder;
import Restaurante.Factories.CocineroBuilder;
import Restaurante.Factories.EmpleadoBuilder;
import Restaurante.Factories.EncargadoBuilder;
import Restaurante.Factories.Factory;
import Restaurante.model.Almacen;
import Restaurante.model.Cliente;
import Restaurante.model.Cocinero;
import Restaurante.model.ComidaYBebida;
import Restaurante.model.Empleado;
import Restaurante.model.Encargado;
import Restaurante.model.ResObserver;

public class Restaurante {
	// ---------------------------------------
	// Atributos
	// ---------------------------------------
	private ArrayList<Empleado> ListEmpleado;
	private List<ResObserver> ResObsL;
	private static ArrayList<Cliente> ListCliente;
	private static ArrayList<ComidaYBebida> ListCYB;
	private static ArrayList<Cocinero> ListCocinero;
	private static ArrayList<Encargado> ListEncargado;
	private static ArrayList<Almacen> ListAlmacen;
	private static Factory<Cliente> factoryCli;
	private static Factory<Almacen> factoryAlm;
	private static Factory<ComidaYBebida> factoryCYB;
	private static Factory<Cocinero> factoryCoci;
	private static Factory<Empleado> factoryEmpl;
	private static Factory<Encargado> factoryEnc;

	// ---------------------------------------
	// Metodos
	// ---------------------------------------

	public Restaurante() {
		init();
	}

	/**
	 * Inicializa los atributos
	 */
	private void init() {
		ResObsL = new ArrayList<ResObserver>();
		ListCliente = new ArrayList<Cliente>();
		ListCYB = new ArrayList<ComidaYBebida>();
		ListAlmacen = new ArrayList<Almacen>();
		ListCocinero = new ArrayList<Cocinero>();
		ListEncargado = new ArrayList<Encargado>();
		ListEmpleado = new ArrayList<Empleado>();
		ArrayList<Builder<Cliente>> ClienteBuilder = new ArrayList<>();
		ClienteBuilder.add(new ClienteBuilder());
		factoryCli = new BuilderBasedFactory<Cliente>(ClienteBuilder);

		ArrayList<Builder<Almacen>> AlmacenBuilder = new ArrayList<>();
		AlmacenBuilder.add(new AlmacenBuilder());
		factoryAlm = new BuilderBasedFactory<Almacen>(AlmacenBuilder);

		ArrayList<Builder<ComidaYBebida>> CYBBuilder = new ArrayList<>();
		CYBBuilder.add(new CYBBuilder());
		factoryCYB = new BuilderBasedFactory<ComidaYBebida>(CYBBuilder);

		ArrayList<Builder<Cocinero>> CociBuilder = new ArrayList<>();
		CociBuilder.add(new CocineroBuilder());
		factoryCoci = new BuilderBasedFactory<Cocinero>(CociBuilder);

		ArrayList<Builder<Empleado>> EmplBuilder = new ArrayList<>();
		EmplBuilder.add(new EmpleadoBuilder());
		factoryEmpl = new BuilderBasedFactory<Empleado>(EmplBuilder);

		ArrayList<Builder<Encargado>> EncBuilder = new ArrayList<>();
		EncBuilder.add(new EncargadoBuilder());
		factoryEnc = new BuilderBasedFactory<Encargado>(EncBuilder);
	}

	// -----------------------
	// Metodos Comida Y Bebida
	// -----------------------
	/**
	 * Guarda los datos de la lista en un archivo
	 * @param a
	 * @throws FileNotFoundException 
	 */
	public static void closeCYB() throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Carta.json"));
		PrintStream p = new PrintStream(os);
		p.println(chargeCYB());
	}
	/**
	 * Limpia y actualiza la lista de ComidaYBebida
	 */
	public void resetCYB() {
		ListCYB = new ArrayList<ComidaYBebida>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Carga los datos del almacen desde el fichero
	 * @param in
	 */
	public void loadCYB(InputStream in) {
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray cyb = jsonInput.getJSONArray("CYB");
			for (int i = 0; i < cyb.length(); i++) {
				ComidaYBebida c = factoryCYB.createInstance(cyb.getJSONObject(i));
				ListCYB.add(c);
			}
		}
	}
	/**
	 * Carga los datos del objeto para guardarlo
	 * en un fichero
	 * @return
	 */
	public static JSONObject chargeCYB() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for (ComidaYBebida b : ListCYB) {
			x.put("type", "Plato");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("CYB", array);
		return j;
	}
	/**
	 * Actualiza el valor de la lista ComidaYBebida
	 * @param info
	 */
	public void setCYB(JSONArray info) {
		ArrayList<ComidaYBebida> x = new ArrayList<ComidaYBebida>();
		for (Object obj : info) {
			ComidaYBebida cl = factoryCYB.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListCYB = new ArrayList<ComidaYBebida>();
		ListCYB = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Da valor a la lista ComidaYBebida
	 * @param c
	 */
	public void setListCYB(ArrayList<ComidaYBebida> c) {
		ListCYB = c;
	}
	/**
	 * Devuelve la lista de ComidaYBebida
	 * @return
	 */
	public ArrayList<ComidaYBebida> getListCYB() {
		return ListCYB;
	}
	// -----------------------
	// Metodos Cliente
	// -----------------------
	/**
	 * Guarda los datos en un fichero
	 * @param a
	 * @throws FileNotFoundException 
	 */
	public static void closeCli() throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Clientes.json"));
		PrintStream p = new PrintStream(os);
		p.println(chargeClientes());
	}
	/**
	 * Limpia y actualiza la lista de clientes
	 */
	public void resetCli() {
		ListCliente = new ArrayList<Cliente>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Carga clientes desde fichero
	 * @param in
	 */
	public void loadClientes(InputStream in) {
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray cli = jsonInput.getJSONArray("Cliente");
			for (int i = 0; i < cli.length(); i++) {
				Cliente c = factoryCli.createInstance(cli.getJSONObject(i));
				ListCliente.add(c);
			}
		}
	}
	/**
	 * Carga los objetos para guardarlos en un archivo
	 * @return
	 */
	public static JSONObject chargeClientes() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for (Cliente b : ListCliente) {
			x.put("type", "Cliente");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Cliente", array);
		return j;
	}
	/**
	 * Actualiza la lista de clientes y la tabla
	 * @param info
	 */
	public void setClientes(JSONArray info) {
		ArrayList<Cliente> x = new ArrayList<Cliente>();
		for (Object obj : info) {
			Cliente cl = factoryCli.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListCliente = new ArrayList<Cliente>();
		ListCliente = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Da valor a la lista clientes
	 * @param c
	 */
	public void setListCliente(ArrayList<Cliente> c) {
		ListCliente = c;
	}
	/**
	 * Devuelve el valor de la lista clientes
	 * @return
	 */
	public ArrayList<Cliente> getListCliente() {
		return ListCliente;
	}
	// -----------------------
	// Metodos Almacen
	// -----------------------
	/**
	 * Guarda los datos del almacen
	 * en el archivo
	 * @param a
	 * @throws FileNotFoundException 
	 */
	public static void closeAlm() throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Almacen.json"));
		PrintStream p = new PrintStream(os);
		p.println(chargeAlmacen());
	}
	/**
	 * Limpia la lista de almacen y la actualiza
	 */
	public void resetAlm() {
		ListAlmacen = new ArrayList<Almacen>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Carga los datos del almacen desde el archivo
	 * @param in
	 */
	public void loadAlmacen(InputStream in) {
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray alm = jsonInput.getJSONArray("Almacen");
			for (int i = 0; i < alm.length(); i++) {
				Almacen a = factoryAlm.createInstance(alm.getJSONObject(i));
				ListAlmacen.add(a);
			}
		}
	}
	/**
	 * Carga los datos de los objetos
	 * para guardarlos en el archivo
	 * @return
	 */
	public static JSONObject chargeAlmacen() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for (Almacen b : ListAlmacen) {
			x.put("type", "Almacen");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Almacen", array);
		return j;
	}
	/**
	 * Actualiza la lista de almacenes y la tabla
	 * @param info
	 */
	public void setAlm(JSONArray info) {
		ArrayList<Almacen> x = new ArrayList<Almacen>();
		for (Object obj : info) {
			Almacen cl = factoryAlm.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListAlmacen = new ArrayList<Almacen>();
		ListAlmacen = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Devuelve el valor de la lista de almacen
	 * @return
	 */
	public ArrayList<Almacen> getListAlmacen() {
		return ListAlmacen;
	}
	/**
	 * Actualiza la lista del almacen
	 * @param a
	 */
	public void setListAlmacen(ArrayList<Almacen> a) {
		ListAlmacen = a;
	}
	// -----------------------
	// Metodos Encargado
	// -----------------------
	/**
	 * Pone la lista de empleados del encargado
	 * en la tabla
	 * @param x
	 */
	public void tablaEnc(ArrayList<Empleado> x) {
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, x, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Limpia y actualiza la lista de encargados
	 */
	public void resetEnc() {
		ListEncargado = new ArrayList<Encargado>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Carga los datos desde un archivo
	 * @param in
	 */
	public void loadEnc(InputStream in) {
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray enc = jsonInput.getJSONArray("Encargado");
			for (int i = 0; i < enc.length(); i++) {
				Encargado e = factoryEnc.createInstance(enc.getJSONObject(i));
				ListEncargado.add(e);
				ListEmpleado.add(e);
				for (Empleado x : ListEncargado.get(i).getLista()) {
					ListEmpleado.add(x);
				}
			}
		}
	}
	/**
	 * Carga los datos de los objetos para
	 * guardarlos en un archivo
	 * @return
	 */
	public static JSONObject chargeEnc() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for (Encargado b : ListEncargado) {
			x.put("type", "Encargado");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Encargado", array);
		return j;
	}
	/**
	 * Da valor a la lista de empleados
	 * del encargado
	 * @param info
	 * @param i
	 */
	public void setEnc(JSONArray info, int i) {
		ArrayList<Empleado> x = new ArrayList<Empleado>();
		for (Object obj : info) {
			Empleado cl = factoryEmpl.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListEncargado.get(i).setLista(x);
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Guarda los datos de los encargados
	 * en un archivo
	 * @param a
	 * @throws FileNotFoundException 
	 */
	public static void closeEnc() throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Encargados.json"));
		PrintStream p = new PrintStream(os);
		p.println(chargeEnc());
	}
	public boolean existeEnc(String d, int id) {
		int i = 0; boolean found = false;
		while(i < ListEncargado.size() && !found) {
			if(ListEncargado.get(i).getDni().equals(d) && ListEncargado.get(i).getIdEncargado() == id)
				found = true;
			i++;
		}
		return found;
	}
	public boolean existeEmp(String d, int id) {
		int i = 0; boolean found = false;
		while(i < ListEmpleado.size() && !found) {
			if(ListEmpleado.get(i).getDni().equals(d) && ListEmpleado.get(i).getid() == id)
				found = true;
			i++;
		}
		return found;
	}
	/**
	 * Devuelve el valor de la lista de encargados
	 * @return
	 */
	public ArrayList<Encargado> getListEncargado() {
		return ListEncargado;
	}
	/**
	 * Devuelve el valor de la lista de empleados
	 * @return
	 */
	public ArrayList<Empleado> getListEmpleado() {
		return ListEmpleado;
	}
	/**
	 * Da valor a la lista de empleados
	 * @param e
	 */
	public void setListEmpleado(ArrayList<Empleado> e) {
		ListEmpleado = e;
	}

	// -----------------------
	// Metodos Cocinero
	// -----------------------
	/**
	 * Carga los datos desde un archivo
	 * @param in
	 */
	public void loadCoci(InputStream in) {
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray coc = jsonInput.getJSONArray("Cocinero");
			for (int i = 0; i < coc.length(); i++) {
				Cocinero c = factoryCoci.createInstance(coc.getJSONObject(i));
				ListCocinero.add(c);
			}
		}
	}
	/**
	 * Limpia y actualiza los datos de la lista
	 * de cocineros
	 */

	public void resetCoci() {
		ListCocinero = new ArrayList<Cocinero>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Carga los datos de los objetos para
	 * guardarlos en un archivo
	 * @return
	 */
	public static JSONObject chargeCoci() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for (Cocinero b : ListCocinero) {
			x.put("type", "Cocinero");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Cocinero", array);
		return j;
	}
	/**
	 * actualiza el array de cocineros
	 * @param info
	 */
	public void setCoci(JSONArray info) {
		ArrayList<Cocinero> x = new ArrayList<Cocinero>();
		for (Object obj : info) {
			Cocinero cl = factoryCoci.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListCocinero = new ArrayList<Cocinero>();
		ListCocinero = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
		}
	}
	/**
	 * Guarda los datos de los cocineros
	 * @param a
	 * @throws FileNotFoundException 
	 */
	public static void closeCoci() throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Cocineros.json"));
		PrintStream p = new PrintStream(os);
		p.println(chargeCoci());
	}
	/**
	 * Aniade un observador al resturante
	 * @param o
	 */
	public void addObserver(ResObserver o) {
		if (ResObsL.contains(o)) {
			throw new IllegalArgumentException();
		}
		ResObsL.add(o);
		o.onRegister(ListAlmacen, ListCliente, ListEmpleado, ListEncargado, ListCYB, ListCocinero);
	}

	
	
}
