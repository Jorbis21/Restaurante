package restaurante.dao.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Almacen;

public interface DAOAlmacen {
	boolean eliminarAlm(int a) throws FileNotFoundException;
	boolean aniadirAlm(Almacen a) throws FileNotFoundException;
	boolean modificarAlm(Almacen a,int x) throws FileNotFoundException;
	ArrayList<Almacen> lista() throws FileNotFoundException;
	Almacen buscarAlm(Almacen a) throws FileNotFoundException;
}
