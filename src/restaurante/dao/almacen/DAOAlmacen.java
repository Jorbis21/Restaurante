package restaurante.dao.almacen;

import java.io.FileNotFoundException;

import restaurante.model.Almacen;

public interface DAOAlmacen {
	boolean eliminarAlm(Almacen a) throws FileNotFoundException;
	boolean aniadirAlm(Almacen a) throws FileNotFoundException;
	boolean modificarAlm(Almacen a) throws FileNotFoundException;
	Almacen buscarAlm(Almacen a) throws FileNotFoundException;
}
