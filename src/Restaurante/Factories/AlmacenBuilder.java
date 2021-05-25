/**
 * Constructor de almacen desde archivos JSON
 */

package Restaurante.Factories;

import org.json.JSONObject;

import Restaurante.model.Almacen;

public class AlmacenBuilder extends Builder<Almacen>{
	//----------------------
	//Metodos
	//----------------------
	/**
	 * Constructor
	 */
	public AlmacenBuilder() {
		super("Almacen", "Datos del almacen");
	}
	
	/**
	 * Davalor al objeto
	 */
	@Override
	protected Almacen createTheInstance(JSONObject data) {
		String t = data.getString("Type");
		String n = data.getString("Name");
		int c = data.getInt("Amount");
		return new Almacen(n,t,c);
	}
	/**
	 * Crea los datos
	 */
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("Name", "Nombre del alimento");
		data.put("Type", "Tipo de alimento");
		data.put("Amount", "Cantidad del alimento");
		return data;
	}

}
