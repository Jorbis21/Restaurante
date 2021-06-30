/**
 * Implementacion del DAOCliente
 */
package restaurante.dao.cliente;

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
import restaurante.factories.ClienteBuilder;
import restaurante.factories.Factory;
import restaurante.model.Cliente;

public class DAOClienteImpl implements DAOCliente{
	@Override
	public boolean aniadirCli(Cliente a) throws FileNotFoundException {
		ArrayList<Cliente> ListCliente = iniList();
		ListCliente.add(a);
		guardar(ListCliente);
		return false;
	}
	@Override
	public boolean modificarCli(Cliente a, int x) throws FileNotFoundException {
		ArrayList<Cliente> ListCliente = iniList();
		ListCliente.get(x).setNombre(a.getNombre());
		ListCliente.get(x).setCuenta(a.getCuenta());
		ListCliente.get(x).setMetodoPago(a.getMetodoPago());
		guardar(ListCliente);
		return true;
	}
	@Override
	public Cliente buscarCli(Cliente a) throws FileNotFoundException {
		ArrayList<Cliente> ListCliente = iniList();
		for(Cliente c: ListCliente) 
			if(c.equals(a)) 
				return c;
		return null;
	}
	@Override
	public boolean eliminarCli(int a) throws FileNotFoundException {
		ArrayList<Cliente> ListCliente = iniList();
		ListCliente.remove(a);
		guardar(ListCliente);
		return false;
	}
	@Override
	public ArrayList<Cliente> lista() throws FileNotFoundException {
		return iniList();
	}
	
	//--------------------
	//Metodos auxiliares
	//--------------------
	
	/**
	 * Devuelve una lista con los clientes cargados desde la BBDD
	 * @return
	 * @throws FileNotFoundException
	 */
	private ArrayList<Cliente> iniList() throws FileNotFoundException {
		Factory<Cliente> factoryCli;
		ArrayList<Builder<Cliente>> ClienteBuilder = new ArrayList<>();
		ClienteBuilder.add(new ClienteBuilder());
		factoryCli = new BuilderBasedFactory<Cliente>(ClienteBuilder);
		return loadClientes(new FileInputStream(new File("resources/Clientes.json")),factoryCli);
	}
	/**
	 * Guarda la lista de clientes modificada en la BBDD
	 * @param ListCliente
	 * @throws FileNotFoundException
	 */
	private void guardar(ArrayList<Cliente> ListCliente) throws FileNotFoundException {
		OutputStream os = new FileOutputStream(new File("resources/Clientes.json"));
		@SuppressWarnings("resource")
		PrintStream p = new PrintStream(os);
		p.println(chargeClientes(ListCliente));
	}
	/**
	 * Carga los datos del cliente desde el archivo
	 * @param in
	 * @param factoryCli
	 * @return
	 */
	private ArrayList<Cliente> loadClientes(InputStream in,Factory<Cliente> factoryCli) {
		ArrayList<Cliente> ListCliente = new ArrayList<Cliente>();
		if(in != null) {
			JSONObject jsonInput = new JSONObject(new JSONTokener(in));
			JSONArray cli = jsonInput.getJSONArray("Cliente");
			for (int i = 0; i < cli.length(); i++) {
				Cliente c = factoryCli.createInstance(cli.getJSONObject(i));
				ListCliente.add(c);
			}
		}
		return ListCliente;
	}
	/**
	 * Transforma lista de clientes en un JSONArray para guardarlo en la BBDD
	 * @param ListCliente
	 * @return
	 */
	public JSONObject chargeClientes(ArrayList<Cliente> ListCliente) {
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

	
}
