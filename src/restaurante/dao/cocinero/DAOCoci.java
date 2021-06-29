package restaurante.dao.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cocinero;

public interface DAOCoci {
	boolean aniadirCoci(Cocinero a) throws FileNotFoundException;
	boolean modificarCoci(Cocinero a, int x) throws FileNotFoundException;
	Cocinero buscarCoci(Cocinero a) throws FileNotFoundException;
	ArrayList<Cocinero> lista() throws FileNotFoundException;
	boolean eliminarCoci(int a) throws FileNotFoundException;
	boolean buscDni(String dni) throws FileNotFoundException;
	boolean buscId(int id) throws FileNotFoundException;
}
