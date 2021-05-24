package Restaurante.Factories;

import org.json.JSONObject;

import Restaurante.model.Cliente;

public class ClienteBuilder extends Builder<Cliente>{
	public ClienteBuilder() {
		super("Cliente", "Datos del cliente");
	}
	protected Cliente createTheInstance(JSONObject data) {
		int c = data.getInt("Bill");
		String n = data.getString("Name");
		boolean m;
		if(data.getString("Pay").equals("Tarjeta"))
			m = true;
		else
			m = false;
		return new Cliente(n,c,m);
	}
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		data.put("Name", "Nombre del cliente");
		data.put("Bill", "Cuenta del cliente");
		data.put("Pay", "Metodo de pago del cliente");
		return data;
	}
}
