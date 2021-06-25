package restaurante.dao.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cocinero;

public interface DAOCoci {
	boolean aniadirCoci(Cocinero a) throws FileNotFoundException;
	boolean modificarCoci(Cocinero a) throws FileNotFoundException;
	Cocinero buscarCoci(Cocinero a) throws FileNotFoundException;
	ArrayList<Cocinero> lista() throws FileNotFoundException;
	boolean eliminarCoci(Cocinero a) throws FileNotFoundException;
}
