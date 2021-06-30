package restaurante.dao.cyb;

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
import restaurante.factories.CYBBuilder;
import restaurante.factories.Factory;
import restaurante.model.ComidaYBebida;

public class DAOCybImpl implements DAOCyb {
	@Override
	public boolean aniadirCyb(ComidaYBebida a) throws FileNotFoundException {
		ArrayList<ComidaYBebida> ListCYB = iniList();
		ListCYB.add(a);
		guardar(ListCYB);
		return true;
	}
	@Override
	public boolean modificarCyb(ComidaYBebida a, int x) throws FileNotFoundException {
		ArrayList<ComidaYBebida> ListCYB = iniList();
		ListCYB.get(x).setNombre(a.getNombre());
		ListCYB.get(x).setCantidad(a.getCantidad());
		ListCYB.get(x).setComida(a.getComida());
		ListCYB.get(x).setDesc(a.getDesc());
		ListCYB.get(x).setPrecio(a.getPrecio());
		guardar(ListCYB);
		return true;
	}
	@Override
	public ComidaYBebida buscarCyb(ComidaYBebida a) throws FileNotFoundException {
		ArrayList<ComidaYBebida> ListCYB = iniList();
		for(ComidaYBebida x: ListCYB) 
			if(x.equals(a)) 
				return x;
		return null;
	}
	@Override
	public boolean eliminarCyb(int a) throws FileNotFoundException {
		ArrayList<ComidaYBebida> ListCYB = iniList();
		ListCYB.remove(a);
		guardar(ListCYB);
		return true;
	}
	@Override
	public ArrayList<ComidaYBebida> lista() throws FileNotFoundException {
		return iniList();
	}

	//--------------------
	//Metodos auxiliares
	//--------------------
	
	/**
	 * Transforma lista de ComidaYBebida en un JSONArray para guardarlo en la BBDD
	 * @param ListCYB
	 * @return
	 */
	private JSONObject chargeCYB(ArrayList<ComidaYBebida> ListCYB) {
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
	 * Devuelve una lista con los ComidaYBebida cargados desde la BBDD
	 * @return
	 * @throws FileNotFoundException
	 */
	private ArrayList<ComidaYBebida> iniList() throws FileNotFoundException{
		Factory<ComidaYBebida> factoryCYB;
		ArrayList<Builder<ComidaYBebida>> CYBBuilder = new ArrayList<>();
		CYBBuilder.add(new CYBBuilder());
		factoryCYB = new BuilderBasedFactory<ComidaYBebida>(CYBBuilder);
		return loadCYB(new FileInputStream(new File("resources/Carta.json")),factoryCYB);
	}
	/**
	 * Carga los datos del ComidaYBebida desde el archivo
	 * @param in
	 * @param factoryCYB
	 * @return
	 */
	private ArrayList<ComidaYBebida> loadCYB(InputStream in,Factory<ComidaYBebida> factoryCYB) {
		ArrayList<ComidaYBebida> ListCYB = new ArrayList<ComidaYBebida>();
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray cyb = jsonInput.getJSONArray("CYB");
			for (int i = 0; i < cyb.length(); i++) {
				ComidaYBebida c = factoryCYB.createInstance(cyb.getJSONObject(i));
				ListCYB.add(c);
			}
		}
		return ListCYB;
	}
	/**
	 * Guarda la lista de ComidaYBebida modificada en la BBDD
	 * @param ListCYB
	 * @throws FileNotFoundException
	 */
	private void guardar(ArrayList<ComidaYBebida> ListCYB) throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Carta.json"));
		@SuppressWarnings("resource")
		PrintStream p = new PrintStream(os);
		p.println(chargeCYB(ListCYB));
	}

	
}
