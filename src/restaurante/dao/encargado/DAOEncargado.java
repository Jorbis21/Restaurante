package restaurante.dao.encargado;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import restaurante.model.Empleado;

public interface DAOEncargado {
	boolean aniadirEnc(Empleado a, int x) throws FileNotFoundException;
	boolean modificarEnc(Empleado a,int n ,int x) throws FileNotFoundException;
	Empleado buscarEnc(Empleado a, int x) throws FileNotFoundException;
	ArrayList<Empleado> lista(int status, int enc) throws FileNotFoundException;
	boolean eliminarEnc(int a, int x) throws FileNotFoundException;
	boolean buscEmp(String dni, int id) throws FileNotFoundException;
	int buscEnc(String dni, int ide) throws FileNotFoundException;
	boolean buscDni(String dni) throws FileNotFoundException;
	boolean buscId(int id) throws FileNotFoundException;
}
