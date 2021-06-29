package restaurante.sa.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Empleado;

public interface SAEncargado {
	boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException;
	boolean modificarEnc(Empleado a,int n, int x) throws FileNotFoundException;
	Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException;
	ArrayList<Empleado> lista(int status, int enc) throws FileNotFoundException;
	boolean eliminarEnc(Empleado a,int n, int x) throws FileNotFoundException;
	int buscEnc(String dni, int ide) throws FileNotFoundException;
	boolean buscEmp(String dni, int id) throws FileNotFoundException;
	public boolean buscDni(String dni) throws FileNotFoundException;
	public boolean buscId(int id) throws FileNotFoundException;
}
