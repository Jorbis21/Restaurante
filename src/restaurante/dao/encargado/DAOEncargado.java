package restaurante.dao.encargado;

import java.io.FileNotFoundException;

import restaurante.model.Empleado;

public interface DAOEncargado {
	boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException;
	boolean modificarEnc(Empleado a, int x) throws FileNotFoundException;
	Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException;
	boolean eliminarEnc(Empleado a, int x) throws FileNotFoundException;
}
