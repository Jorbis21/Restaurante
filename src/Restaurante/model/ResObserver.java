/**
 * Interfaz de los observadores
 */
package restaurante.model;
import java.util.List;
public interface ResObserver {
	public void ObsAlm(List<Almacen> alm);
	public void ObsCli(List<Cliente> cli);
	public void ObsEmp(List<Empleado> emp);
	public void ObsEnc(List<Encargado> enc);
	public void ObsCyb(List<ComidaYBebida> cyb);
	public void ObsCoci(List<Cocinero> coci);
}

