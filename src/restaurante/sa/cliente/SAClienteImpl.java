package restaurante.sa.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.dao.AbstractFactoryDAO;
import restaurante.dao.cliente.DAOCliente;
import restaurante.model.Cliente;

public class SAClienteImpl implements SACliente{

	@Override
	public boolean aniadirCli(Cliente a) throws FileNotFoundException {
		DAOCliente x = AbstractFactoryDAO.getInstance().createDAOCli();
		if(buscarCli(a) == null) {
			x.aniadirCli(a);
			return true;
		}
		return false;
	}

	@Override
	public boolean modificarCli(Cliente a, int x) throws FileNotFoundException {
		DAOCliente z = AbstractFactoryDAO.getInstance().createDAOCli();
		z.modificarCli(a, x);
		return true;

	}

	@Override
	public Cliente buscarCli(Cliente a) throws FileNotFoundException {
		DAOCliente x = AbstractFactoryDAO.getInstance().createDAOCli();
		Cliente y;
		y = x.buscarCli(a);
		return y;
	}

	@Override
	public boolean eliminarCli(Cliente a, int x) throws FileNotFoundException {
		DAOCliente z = AbstractFactoryDAO.getInstance().createDAOCli();
		if(buscarCli(a) == null) {
			z.eliminarCli(x);
			return true;
		}
		return false;
	}

	@Override
	public ArrayList<Cliente> lista() throws FileNotFoundException {
		DAOCliente x = AbstractFactoryDAO.getInstance().createDAOCli();
		return x.lista();
	}

}
