package restaurante.view.empleado;

import java.io.FileNotFoundException;

import restaurante.control.RestauranteCtrl;
import restaurante.model.Empleado;

public class GuiEmplCtrl {
	private RestauranteCtrl res;
	public GuiEmplCtrl(RestauranteCtrl res) {
		this.res = res;
	}
	public boolean aniadir(Empleado a, int n) throws FileNotFoundException {
		return res.eventoEnc(a, 0, n, -1);
	}
	public boolean eliminar(Empleado a, int n,int x) throws FileNotFoundException {
		return res.eventoEnc(a, 1,n, x);
	}
	public boolean modificar(Empleado a,int n, int x) throws FileNotFoundException {
		return res.eventoEnc(a, 2,n, x);
	}
}
