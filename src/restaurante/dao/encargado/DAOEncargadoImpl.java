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
		return false;
	}

	@Override
	public boolean modificarEnc(Empleado a, int x) throws FileNotFoundException {
		eliminarEnc(a,x);
		aniadirEnc(a,x);
		return false;
	}

	@Override
	public Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		for(Empleado y: ListEncargado.get(x).getLista()) {
			if(y == a) {
				return y;
			}
		}
		return null;
	}

	@Override
	public boolean eliminarEnc(Empleado a, int x) throws FileNotFoundException {
		ArrayList<Encargado> ListEncargado = iniList();
		ListEncargado.get(x).getLista().remove(a);
		return false;
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
		ArrayList<Empleado> ListEmpleado = new ArrayList<Empleado>();
		ArrayList<Encargado> ListEncargado = new ArrayList<Encargado>();
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
		return ListEncargado;
	}

}
