package restaurante.view.almacen;

import java.io.FileNotFoundException;

import restaurante.control.RestauranteCtrl;
import restaurante.model.Almacen;

public class GuiAlmCtrl {
	private RestauranteCtrl res;
	public GuiAlmCtrl(RestauranteCtrl res) {
		this.res = res;
	}
	public boolean aniadir(Almacen a) throws FileNotFoundException {
		return res.eventoAlm(a, 0, -1);
	}
	public boolean eliminar(Almacen a,int x) throws FileNotFoundException {
		return res.eventoAlm(a, 1, x);
	}
	public boolean modificar(Almacen a, int x) throws FileNotFoundException {
		return res.eventoAlm(a, 2, x);
	}
}
