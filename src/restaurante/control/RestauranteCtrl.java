package restaurante.control;

import java.io.FileNotFoundException;

import restaurante.model.Almacen;
import restaurante.model.Cliente;
import restaurante.model.Cocinero;
import restaurante.model.ComidaYBebida;
import restaurante.model.Empleado;
import restaurante.sa.AbstractFactorySA;

public class RestauranteCtrl {
	public boolean eventoAlm(Almacen a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSAAlm().aniadirAlm(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSAAlm().eliminarAlm(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSAAlm().modificarAlm(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoCoci(Cocinero a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACoci().aniadirCoci(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACoci().eliminarCoci(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACoci().modificarCoci(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoCli(Cliente a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACli().aniadirCli(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACli().eliminarCli(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACli().modificarCli(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoCyb(ComidaYBebida a, int e) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSACyb().aniadirCyb(a);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSACyb().eliminarCyb(a);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSACyb().modificarCyb(a);
		}
		break;
		}
		return x;
	}
	public boolean eventoEnc(Empleado a, int e, int n) throws FileNotFoundException {
		boolean x = false;
		switch(e) {
		case 0:{
			 x = AbstractFactorySA.getInstance().createSAEnc().aniadirEnc(a, n);
		}
		break;
		case 1:{
			x = AbstractFactorySA.getInstance().createSAEnc().eliminarEnc(a, n);
		}
		break;
		case 2:{
			x = AbstractFactorySA.getInstance().createSAEnc().modificarEnc(a, n);
		}
		break;
		}
		return x;
	}
}
