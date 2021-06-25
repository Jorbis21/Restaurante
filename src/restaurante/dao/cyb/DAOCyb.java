package restaurante.dao.cyb;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import restaurante.model.ComidaYBebida;

public interface DAOCyb {
	boolean aniadirCyb(ComidaYBebida a) throws FileNotFoundException;
	boolean modificarCyb(ComidaYBebida a) throws FileNotFoundException;
	ComidaYBebida buscarCyb(ComidaYBebida a) throws FileNotFoundException;
	ArrayList<ComidaYBebida> lista() throws FileNotFoundException;
	boolean eliminarCyb(ComidaYBebida a) throws FileNotFoundException;
}
