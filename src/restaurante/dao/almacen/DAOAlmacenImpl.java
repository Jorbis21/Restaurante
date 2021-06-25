package restaurante.dao.almacen;

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

import restaurante.factories.AlmacenBuilder;
import restaurante.factories.Builder;
import restaurante.factories.BuilderBasedFactory;
import restaurante.factories.Factory;
import restaurante.model.Almacen;

public class DAOAlmacenImpl implements DAOAlmacen {
	
	@Override
	public boolean eliminarAlm(Almacen a) throws FileNotFoundException {
		ArrayList<Almacen> ListAlmacen = iniList();
		ListAlmacen.remove(a);
		guardar(ListAlmacen);
		return true;
	}
	
	/**
	 * Guarda los datos del almacen
	 * en el archivo
	 * @param a
	 * @throws FileNotFoundException 
	 */
	@Override
	public boolean aniadirAlm(Almacen a) throws FileNotFoundException {
		
		ArrayList<Almacen> ListAlmacen = iniList();
		ListAlmacen.add(a);
		guardar(ListAlmacen);
		return true;
		
	}

	@Override
	public boolean modificarAlm(Almacen a) throws FileNotFoundException {
		eliminarAlm(a);
		aniadirAlm(a);
		return true;
	}

	@Override
	public Almacen buscarAlm(Almacen a) throws FileNotFoundException {
		ArrayList<Almacen> ListAlmacen = iniList();
		for(Almacen x: ListAlmacen) {
			if(x==a) {
				return x;
			}
		}
		return null;
	}
	private ArrayList<Almacen> iniList() throws FileNotFoundException {
		Factory<Almacen> factoryAlm;
		ArrayList<Builder<Almacen>> AlmacenBuilder = new ArrayList<>();
		AlmacenBuilder.add(new AlmacenBuilder());
		factoryAlm = new BuilderBasedFactory<Almacen>(AlmacenBuilder);
		return loadAlmacen(new FileInputStream(new File("resources/Almacen.json")),factoryAlm);
	}
	/**
	 * Carga los datos del almacen desde el archivo
	 * @param in
	 */
	private ArrayList<Almacen> loadAlmacen(InputStream in, Factory<Almacen> factoryAlm) {
		ArrayList<Almacen> ListAlmacen = new ArrayList<Almacen>();
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray alm = jsonInput.getJSONArray("Almacen");
			for (int i = 0; i < alm.length(); i++) {
				Almacen a = factoryAlm.createInstance(alm.getJSONObject(i));
				ListAlmacen.add(a);
			}
		}
		return ListAlmacen;
	}
	private void guardar(ArrayList<Almacen> ListAlmacen) throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Almacen.json"));
		@SuppressWarnings("resource")
		PrintStream p = new PrintStream(os);
		p.println(chargeAlmacen(ListAlmacen));
	}
	private JSONObject chargeAlmacen(ArrayList<Almacen> ListAlmacen) {
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

}
