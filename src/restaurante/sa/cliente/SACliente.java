package restaurante.sa.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cliente;

public interface SACliente {
	boolean aniadirCli(Cliente a) throws FileNotFoundException;
	boolean modificarCli(Cliente a, int x) throws FileNotFoundException;
	Cliente buscarCli(Cliente a) throws FileNotFoundException;
	ArrayList<Cliente> lista() throws FileNotFoundException;
	boolean eliminarCli(Cliente a, int x) throws FileNotFoundException;
}
