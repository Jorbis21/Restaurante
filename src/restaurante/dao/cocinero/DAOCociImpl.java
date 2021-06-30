package restaurante.dao.cocinero;

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
import restaurante.factories.CocineroBuilder;
import restaurante.factories.Factory;
import restaurante.model.Cocinero;

public class DAOCociImpl implements DAOCoci {
	@Override
	public boolean aniadirCoci(Cocinero a) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = iniList();
		ListCocinero.add(a);
		guardar(ListCocinero);
		return true;
	}
	@Override
	public boolean modificarCoci(Cocinero a, int x) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = iniList();
		ListCocinero.get(x).setNombre(a.getNombre());
		ListCocinero.get(x).setSalario(a.getSalario());
		ListCocinero.get(x).setDni(a.getDni());
		ListCocinero.get(x).setEspecialidad(a.getEspecialidad());
		ListCocinero.get(x).setFechaPago(a.getFechaPago());
		ListCocinero.get(x).setTipo(a.getTipo());
		ListCocinero.get(x).setid(a.getid());
		guardar(ListCocinero);
		return true;
	}
	@Override
	public Cocinero buscarCoci(Cocinero a) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = iniList();
		for(Cocinero x: ListCocinero) 
			if(x.equals(a)) 
				return x;
		return null;
	}
	@Override
	public boolean buscDni(String dni) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = iniList();
		for(Cocinero c: ListCocinero) 
			if(c.getDni().equals(dni))
				return true;
		return false;
		
	}
	@Override
	public boolean buscId(int id) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = iniList();
		for(Cocinero c: ListCocinero) 
			if(c.getid() == id)
				return true;
		return false;
	}
	@Override
	public boolean eliminarCoci(int a) throws FileNotFoundException {
		ArrayList<Cocinero> ListCocinero = iniList();
		ListCocinero.remove(a);
		guardar(ListCocinero);
		return true;
	}
	@Override
	public ArrayList<Cocinero> lista() throws FileNotFoundException {
		return iniList();
	}
	
	//--------------------
	//Metodos auxiliares
	//--------------------
	
	/**
	 * Devuelve una lista con los cocineros cargados desde la BBDD
	 * @return
	 * @throws FileNotFoundException
	 */
	private ArrayList<Cocinero> iniList() throws FileNotFoundException{
		Factory<Cocinero> factoryCoci;
		ArrayList<Builder<Cocinero>> CociBuilder = new ArrayList<>();
		CociBuilder.add(new CocineroBuilder());
		factoryCoci = new BuilderBasedFactory<Cocinero>(CociBuilder);
		return loadCoci(new FileInputStream(new File("resources/Cocineros.json")),factoryCoci);
	}
	/**
	 * Guarda la lista de cocineros modificada en la BBDD
	 * @param ListCocinero
	 * @throws FileNotFoundException
	 */
	private void guardar(ArrayList<Cocinero> ListCocinero) throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Cocineros.json"));
		@SuppressWarnings("resource")
		PrintStream p = new PrintStream(os);
		p.println(chargeCoci(ListCocinero));
	}
	/**
	 * Transforma lista de cocineros en un JSONArray para guardarlo en la BBDD
	 * @param ListCocinero
	 * @return
	 */
	private JSONObject chargeCoci(ArrayList<Cocinero> ListCocinero) {
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
	 * Carga los datos del cocinero desde el archivo
	 * @param in
	 * @param factoryCoci
	 * @return
	 */
	private ArrayList<Cocinero> loadCoci(InputStream in, Factory<Cocinero> factoryCoci) {
		ArrayList<Cocinero> ListCocinero = new ArrayList<Cocinero>();
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray coc = jsonInput.getJSONArray("Cocinero");
			for (int i = 0; i < coc.length(); i++) {
				Cocinero c = factoryCoci.createInstance(coc.getJSONObject(i));
				ListCocinero.add(c);
			}
		}
		return ListCocinero;
	}
}
