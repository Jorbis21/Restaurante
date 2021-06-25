package restaurante.sa.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Almacen;

public interface SAAlmacen {
	boolean aniadirAlm(Almacen a) throws FileNotFoundException;
	boolean modificarAlm(Almacen a, int x) throws FileNotFoundException;
	Almacen buscarAlm(Almacen a) throws FileNotFoundException;
	ArrayList<Almacen> lista() throws FileNotFoundException;
	boolean eliminarAlm(Almacen a,int x) throws FileNotFoundException;
}
