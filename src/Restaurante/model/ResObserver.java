package Restaurante.model;
import java.util.List;
public interface ResObserver {
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc, List<ComidaYBebida> CYB,List<Cocinero> coci);
	public void onRemove(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc, List<ComidaYBebida> CYB,List<Cocinero> coci);
	public void onModified(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc, List<ComidaYBebida> CYB,List<Cocinero> coci);
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc, List<ComidaYBebida> CYB,List<Cocinero> coci);	
}
