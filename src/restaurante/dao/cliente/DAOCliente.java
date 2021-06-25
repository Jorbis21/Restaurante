package restaurante.dao.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cliente;

public interface DAOCliente {
	boolean aniadirCli(Cliente a) throws FileNotFoundException;
	boolean modificarCli(Cliente a) throws FileNotFoundException;
	Cliente buscarCli(Cliente a) throws FileNotFoundException;
	ArrayList<Cliente> lista() throws FileNotFoundException;
	boolean eliminarCli(Cliente a) throws FileNotFoundException;
}
