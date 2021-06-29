package restaurante.dao.encargado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import restaurante.factories.Builder;
import restaurante.factories.BuilderBasedFactory;
import restaurante.factories.EncargadoBuilder;
import restaurante.factories.Factory;
import restaurante.model.Empleado;
import restaurante.model.Encargado;

public class DAOEncargadoImpl implements DAOEncargado {

	@Override
	public boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		ListEncargado.get(x).getLista().add(a);
		guardar(ListEncargado);
		return true;
	}

	@Override
	public boolean modificarEnc(Empleado a, int n,int x) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		ListEncargado.get(n).getLista().get(x).setNombre(a.getNombre());
		ListEncargado.get(n).getLista().get(x).setDni(a.getDni());
		ListEncargado.get(n).getLista().get(x).setFechaPago(a.getFechaPago());
		ListEncargado.get(n).getLista().get(x).setSalario(a.getSalario());
		ListEncargado.get(n).getLista().get(x).setid(a.getid());
		return true;
	}
	// hay que cambiarlo
	@Override
	public Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		for (Encargado e: ListEncargado) {
			for(Empleado y: e.getLista()) {
				if(y.equals(a)) {
					return y;
				}
			}
		}
		
		return null;
	}
	public int buscEnc(String dni, int ide) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		int i = 0;
		while (i < ListEncargado.size()) {
			Encargado e = ListEncargado.get(i);
			if (e.getDni().equals(dni) && e.getIdEncargado() == ide) {
				return i;
			}
			i++;
		}
		return -1;
		
	}
	public boolean buscDni(String dni) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		for (Encargado e: ListEncargado) 
			for(Empleado y: e.getLista()) 
				if(e.getDni().equals(dni) || y.getDni().equals(dni))
					return true;
		return false;
	}
	public boolean buscId(int id) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		for (Encargado e: ListEncargado) 
			for(Empleado y: e.getLista()) 
				if(e.getid() == id || y.getid() == id)
					return true;
		return false;
	}
	public boolean buscEmp(String dni, int id) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		for (Encargado e: ListEncargado) 
			for(Empleado em: e.getLista()) 
				if (em.getDni().equals(dni) && em.getid() == id) 
					return true;
		return false;
	}
	@Override
	public boolean eliminarEnc(int a, int x) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		ListEncargado.get(a).getLista().remove(x);
		guardar(ListEncargado);
		return true;
	}
	private ArrayList<Encargado> iniList() throws FileNotFoundException{
		Factory<Encargado> factoryEnc;
		ArrayList<Builder<Encargado>> EncBuilder = new ArrayList<>();
		EncBuilder.add(new EncargadoBuilder());
		factoryEnc = new BuilderBasedFactory<Encargado>(EncBuilder);
		return loadEnc(new FileInputStream(new File("resources/Encargados.json")),factoryEnc);
		
	}
	/**
	 * Guarda los datos de los encargados
	 * en un archivo
	 * @param a
	 * @throws FileNotFoundException 
	 */
	private void guardar(ArrayList<Encargado> ListEncargado) throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Encargados.json"));
		@SuppressWarnings("resource")
		PrintStream p = new PrintStream(os);
		p.println(chargeEnc(ListEncargado));
	}
	/**
	 * Carga los datos de los objetos para
	 * guardarlos en un archivo
	 * @return
	 */
	private JSONObject chargeEnc(ArrayList<Encargado> ListEncargado) {
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
	 * Carga los datos desde un archivo
	 * @param in
	 * @return 
	 */
	private ArrayList<Encargado> loadEnc(InputStream in,Factory<Encargado> factoryEnc) {
		ArrayList<Encargado> ListEncargado = new ArrayList<Encargado>();
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray enc = jsonInput.getJSONArray("Encargado");
			for (int i = 0; i < enc.length(); i++) {
				Encargado e = factoryEnc.createInstance(enc.getJSONObject(i));
				ListEncargado.add(e);
			}
		}
		return ListEncargado;
	}

	@Override
	public ArrayList<Empleado> lista(int status, int enc) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		ArrayList<Empleado> x = new ArrayList<Empleado>();
		if(status == 0) {
			return ListEncargado.get(enc).getLista();
		}
		else if(status == 1) {
			for (Encargado e: ListEncargado) {
				x.add(e);
				for(Empleado em: e.getLista()) {
					x.add(em);
				}
			}
		}
		return x;
	}

}
