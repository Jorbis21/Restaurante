package restaurante.sa.almacen;

import java.io.FileNotFoundException;

import restaurante.model.Almacen;

public interface SAAlmacen {
	boolean aniadirAlm(Almacen a) throws FileNotFoundException;
	boolean modificarAlm(Almacen a) throws FileNotFoundException;
	Almacen buscarAlm(Almacen a) throws FileNotFoundException;
	boolean eliminarAlm(Almacen a) throws FileNotFoundException;
}
