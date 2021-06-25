/**
 * Constructor de ComidaYBebida desde archivos JSON
 */
package restaurante.factories;

import org.json.JSONObject;

import restaurante.model.ComidaYBebida;

public class CYBBuilder extends Builder<ComidaYBebida>{
	//----------------------
	//Metodos
	//----------------------
	/**
	 * Constructor
	 */
	public CYBBuilder() {
		super("Plato", "Descripcion del plato");
	}
	/**
	 * Da valor al objeto
	 */
	@Override
	protected ComidaYBebida createTheInstance(JSONObject data) {
		String n = data.getString("Name");
		int c = data.getInt("Amount");
		boolean _c;
		if(data.getString("Food").equals("Bebida"))
			_c = true;
		else
			_c = false;
		String d = data.getString("Desc");
		double p = data.getDouble("Price");
		return new ComidaYBebida(n,c,_c,d,p);
	}
	/**
	 * Crea los datos
	 */
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("Name", "Nombre del plato");
		data.put("Amount", "Cantidad de platos disponibles");
		data.put("Food", "Comida/Bebida");
		data.put("Desc","Descripcion del plato");
		return data;
	}
}
