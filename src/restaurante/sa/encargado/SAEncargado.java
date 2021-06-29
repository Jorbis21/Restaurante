package restaurante.sa.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Empleado;
import restaurante.model.Encargado;

public interface SAEncargado {
	boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException;
	boolean modificarEnc(Empleado a,int n, int x) throws FileNotFoundException;
	Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException;
	ArrayList<Encargado> lista() throws FileNotFoundException;
	boolean eliminarEnc(Empleado a,int n, int x) throws FileNotFoundException;
}
