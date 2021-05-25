package Restaurante.control;

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
	private int NumMesas, NumEmpleados, AforoMax;
	private static ArrayList<Cliente> ListCliente;
	private static ArrayList<ComidaYBebida> ListCYB;
	private static ArrayList<Cocinero> ListCocinero;
	private static ArrayList<Encargado> ListEncargado;
	private static ArrayList<Almacen> ListAlmacen;
	private ArrayList<Empleado> ListEmpleado;
	private List<ResObserver> ResObsL;
	private static Factory<Cliente> factoryCli;
	private static Factory<Almacen> factoryAlm;
	private static Factory<ComidaYBebida> factoryCYB;
	private static Factory<Cocinero> factoryCoci;
	private static Factory<Empleado> factoryEmpl;
	private static Factory<Encargado> factoryEnc;
	public Restaurante(int m,int e, int af) {
		NumMesas = m;
		NumEmpleados = e;
		AforoMax = af;
		init();
	}
	private void init() {
		ResObsL = new ArrayList<ResObserver>();
		ListCliente = new ArrayList<Cliente>();
		ListCYB = new ArrayList<ComidaYBebida>();
		ListAlmacen = new ArrayList<Almacen>();
		ListCocinero = new ArrayList<Cocinero>();
		ListEncargado = new ArrayList<Encargado>();
		ListEncargado.add(new Encargado("Admin", -21, 0, "nunca", 2001, null));
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
	public static void closeCYB(FileOutputStream a){
		OutputStream os =a;
		PrintStream p = new PrintStream(os);
		p.println(chargeCYB());
	}
	public static void closeCli(FileOutputStream a) throws FileNotFoundException {
		OutputStream os = a;
		PrintStream p = new PrintStream(os);
		p.println(chargeClientes());
	}
	public static void closeAlm(FileOutputStream a) {
		OutputStream os = a;
		PrintStream p = new PrintStream(os);
		p.println(chargeAlmacen());
	}
	public void resetCli() {
		ListCliente = new ArrayList<Cliente>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void loadClientes(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray cli = jsonInput.getJSONArray("Cliente");
		for (int i = 0; i < cli.length(); i++) {
			Cliente c = factoryCli.createInstance(cli.getJSONObject(i));
			ListCliente.add(c);
		}
	}
	public static JSONObject chargeClientes() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for(Cliente b : ListCliente) {
			x.put("type", "Cliente");
			x.put("data",b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Cliente", array);
		return j;	
	}
	public void tablaEnc(ArrayList<Empleado> x) {
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,x,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void resetAlm() {
		ListAlmacen = new ArrayList<Almacen>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void loadAlmacen(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray alm = jsonInput.getJSONArray("Almacen");
		for (int i = 0; i < alm.length(); i++) {
			Almacen a = factoryAlm.createInstance(alm.getJSONObject(i));
			ListAlmacen.add(a);
		}
	}
	public static JSONObject chargeAlmacen() {	
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for(Almacen b : ListAlmacen) {
			x.put("type", "Almacen");
			x.put("data",b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Almacen", array);
		return j;
	}
	public void resetCYB() {
		ListCYB = new ArrayList<ComidaYBebida>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void loadCYB(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray cyb = jsonInput.getJSONArray("CYB");
		for (int i = 0; i < cyb.length(); i++) {
			ComidaYBebida c = factoryCYB.createInstance(cyb.getJSONObject(i));
			ListCYB.add(c);
		}
	}
	
	
	
	public void loadCoci(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray coc = jsonInput.getJSONArray("Cocinero");
		for (int i = 0; i < coc.length(); i++) {
			Cocinero c = factoryCoci.createInstance(coc.getJSONObject(i));
			ListCocinero.add(c);
		}
	}
	public void resetCoci() {
		ListCocinero = new ArrayList<Cocinero>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void resetEnc() {
		ListEncargado = new ArrayList<Encargado>();
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void loadEnc(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray enc = jsonInput.getJSONArray("Encargado");
		for (int i = 0; i < enc.length(); i++) {
			Encargado e = factoryEnc.createInstance(enc.getJSONObject(i));
			ListEncargado.add(e);
			ListEmpleado.add(e);
			for(Empleado x:ListEncargado.get(i).getLista()) {
				ListEmpleado.add(x);
			}
		}
			
	}
	public static JSONObject chargeCYB() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for(ComidaYBebida b : ListCYB) {
			x.put("type", "Plato");
			x.put("data",b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("CYB", array);
		return j;
	}
	public static JSONObject chargeCoci() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for(Cocinero b : ListCocinero) {
			x.put("type", "Cocinero");
			x.put("data",b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Cocinero", array);
		return j;	
	}
	public static JSONObject chargeEnc() {
		JSONObject j = new JSONObject();
		JSONObject x = new JSONObject();
		JSONArray array = new JSONArray();
		for(Encargado b : ListEncargado) {
			x.put("type", "Encargado");
			x.put("data", b.getData());
			array.put(x);
			x = new JSONObject();
		}
		j.put("Encargado", array);
		return j;	
	}
	public void loadRes(InputStream in) {
		JSONObject res = new JSONObject(new JSONTokener(in));
		NumMesas = res.getInt("Mesas");
		NumEmpleados = res.getInt("Empleados");
		AforoMax = res.getInt("Aforo");
	}
	public JSONObject chargeRes() {
		JSONObject j = new JSONObject();
		j.put("Mesas", NumMesas);
		j.put("Empleados", NumEmpleados);
		j.put("Aforo", AforoMax);
		return j;	
	}
	public void setClientes(JSONArray info) {
		ArrayList<Cliente> x = new ArrayList<Cliente>();
		for(Object obj : info) {
			Cliente cl = factoryCli.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListCliente = new ArrayList<Cliente>();
		ListCliente = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void setCYB(JSONArray info) {
		ArrayList<ComidaYBebida> x = new ArrayList<ComidaYBebida>();
		for(Object obj : info) {
			ComidaYBebida cl = factoryCYB.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListCYB = new ArrayList<ComidaYBebida>();
		ListCYB = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void setEnc(JSONArray info,int i) {
		ArrayList<Empleado> x = new ArrayList<Empleado>();
		for(Object obj : info) {
			Empleado cl = factoryEmpl.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListEncargado.get(i).setLista(x);
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void setCoci(JSONArray info) {
		ArrayList<Cocinero> x = new ArrayList<Cocinero>();
		for(Object obj : info) {
			Cocinero cl = factoryCoci.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListCocinero= new ArrayList<Cocinero>();
		ListCocinero = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
	}
	public void setAlm(JSONArray info) {
		ArrayList<Almacen> x = new ArrayList<Almacen>();
		for(Object obj : info) {
			Almacen cl = factoryAlm.createInstance((JSONObject) obj);
			if (cl == null) {
				throw new IllegalArgumentException();
			}
			x.add(cl);
		}
		ListAlmacen = new ArrayList<Almacen>();
		ListAlmacen = x;
		for (ResObserver s : ResObsL) {
			s.onAdd(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
		}
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
	public ArrayList<Encargado> getListEncargado(){
		return ListEncargado;
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
	public void addObserver(ResObserver o){
		if(ResObsL.contains(o)){
			throw new IllegalArgumentException();
		}
		ResObsL.add(o);
		o.onRegister(ListAlmacen,ListCliente,ListEmpleado,ListEncargado,ListCYB,ListCocinero);
	}
	public static void closeEnc(FileOutputStream a) {
		OutputStream os =a;
		PrintStream p = new PrintStream(os);
		p.println(chargeEnc());
	}
	public static void closeCoci(FileOutputStream a) {
		OutputStream os =a;
		PrintStream p = new PrintStream(os);
		p.println(chargeCoci());
	}
}

