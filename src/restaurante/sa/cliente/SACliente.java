package restaurante.sa.cliente;

import java.io.FileNotFoundException;

import restaurante.model.Cliente;

public interface SACliente {
	boolean aniadirCli(Cliente a) throws FileNotFoundException;
	boolean modificarCli(Cliente a) throws FileNotFoundException;
	Cliente buscarCli(Cliente a) throws FileNotFoundException;
	boolean eliminarCli(Cliente a) throws FileNotFoundException;
}
