package restaurante.sa.cocinero;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Cocinero;

public interface SACocinero {
	boolean aniadirCoci(Cocinero a) throws FileNotFoundException;
	boolean modificarCoci(Cocinero a, int x) throws FileNotFoundException;
	Cocinero buscarCoci(Cocinero a) throws FileNotFoundException;
	ArrayList<Cocinero> lista() throws FileNotFoundException;
	boolean eliminarCoci(Cocinero a, int x) throws FileNotFoundException;
	public boolean buscDni(String dni) throws FileNotFoundException;
	public boolean buscId(int id) throws FileNotFoundException;
		
}
